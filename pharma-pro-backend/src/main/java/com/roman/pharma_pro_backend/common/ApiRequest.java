package com.roman.pharma_pro_backend.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ApiRequest {
    private String service;
    private boolean pagination;
    private int pageNumber;
    private int perPage;
    private Map<String, Object> filter;
    private String condition = "AND";
}
