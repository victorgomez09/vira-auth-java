package com.virasoftware.common.dto;

public interface Dto<T> {
    Dto<T> fromEntity(T entity);
    T toEntity();
}
