package com.roman.pharma_pro_backend.common;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class SpecificationBuilder<T> {

    public Specification<T> build(Map<String, Object> filters, String conditionInput) {
        return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (filters == null || filters.isEmpty()) {
                return cb.conjunction(); // return all
            }

            List<Predicate> predicates = new ArrayList<>();
            int filterSize = filters.size();

            // Determine condition: AND, OR, or fallback
            String condition = "AND";
            if (filterSize >= 2 && conditionInput != null) {
                condition = conditionInput.trim().toUpperCase();
            }

            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                if (value == null || value.toString().isBlank()) continue;

                Path<Object> path = (Path<Object>) root;
                for (String part : key.split("\\.")) {
                    path = path.get(part);
                }

                Class<?> fieldType = path.getJavaType();
                Predicate predicate;

                if (value instanceof String strVal && strVal.contains("%")) {
                    // Handle ILIKE with wildcards
                    predicate = cb.like(cb.lower(path.as(String.class)), strVal.toLowerCase());
                } else if (fieldType.equals(UUID.class) && value instanceof String strVal) {
                    // Handle UUID conversion
                    try {
                        predicate = cb.equal(path, UUID.fromString(strVal));
                    } catch (IllegalArgumentException e) {
                        continue; // skip invalid UUIDs
                    }
                } else if (fieldType.equals(LocalDate.class) && value instanceof String strVal) {
                    // Handle LocalDate conversion
                    try {
                        predicate = cb.equal(path, LocalDate.parse(strVal));
                    } catch (DateTimeParseException e) {
                        continue; // skip invalid date
                    }
                } else if (Number.class.isAssignableFrom(fieldType) && value instanceof String strVal) {
                    // Handle numeric conversion
                    try {
                        if (fieldType.equals(Integer.class)) {
                            predicate = cb.equal(path, Integer.valueOf(strVal));
                        } else if (fieldType.equals(Long.class)) {
                            predicate = cb.equal(path, Long.valueOf(strVal));
                        } else if (fieldType.equals(Double.class)) {
                            predicate = cb.equal(path, Double.valueOf(strVal));
                        } else {
                            predicate = cb.equal(path, value); // fallback
                        }
                    } catch (NumberFormatException e) {
                        continue; // skip invalid number
                    }
                } else {
                    predicate = cb.equal(path, value); // default
                }

                predicates.add(predicate);
            }

            if (predicates.isEmpty()) return cb.conjunction();

            if (predicates.size() >= 2) {
                if ("OR".equalsIgnoreCase(condition)) {
                    return cb.or(predicates.toArray(new Predicate[0]));
                } else {
                    return cb.and(predicates.toArray(new Predicate[0]));
                }
            } else {
                return predicates.get(0);
            }
        };
    }
}

