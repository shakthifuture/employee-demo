package com.example.employeedemo.mapper;

import java.util.List;

public interface BeanMapperUtil<E, D> {

    E toEntity(D d);

    D toDto(E e);

    List<E> toEntityList(List<D> d);

    List<D> toDtoList(List<E> e);

}
