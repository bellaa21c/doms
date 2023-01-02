package com.skbroadband.doms.global.config;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.lang.reflect.InvocationTargetException;

@Mapper
public interface PaginationMapper {
    default <D> Page<D> toDtoList(Page<?> e, Class<D> clazz) {
        return e.map(entity -> {
            try {
                return clazz.getConstructor(entity.getClass()).newInstance(entity);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
