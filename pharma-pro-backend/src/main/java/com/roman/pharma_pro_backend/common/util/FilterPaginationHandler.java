package com.roman.pharma_pro_backend.common.util;


import com.roman.pharma_pro_backend.common.ApiRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterPaginationHandler<T> {

    public Page<T> handle(ApiRequest request, JpaSpecificationExecutor<T> repository, Class<T> clazz) {
        SpecificationBuilder<T> builder = new SpecificationBuilder<>();

        // Pass filter and condition to builder
        Specification<T> spec = builder.build(request.getFilter(), request.getCondition());

        if (request.isPagination()) {
            Pageable pageable = PageRequest.of(request.getPageNumber() - 1, request.getPerPage());
            return repository.findAll(spec, pageable);
        } else {
            List<T> list = repository.findAll(spec);
            return new PageImpl<>(list);
        }
    }
}

