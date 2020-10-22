package com.bilgeadam.commonslib.service;

import com.bilgeadam.commonslib.dao.BaseDAO;
import com.bilgeadam.commonslib.entity.BaseEntity;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked") // return types are same because of generics
public abstract class BaseService<T extends BaseEntity, D extends BaseDAO> {

    D baseDAO;

//    public BaseService(D baseDAO) {
//        this.baseDAO = baseDAO;
//    }


    List<T> findAll() {
        return baseDAO.findAll();
    }

    List<T> findAll(Sort sort) {
        return baseDAO.findAll(sort);
    }

    Optional<T> findById(Long id) {
        return baseDAO.findById(id);
    }

    T save(T entity) {
        if (entity.getId() != null)
            return (T) baseDAO.saveAndFlush(entity);
        return (T) baseDAO.save(entity);
    }

    T deleteById(Long id) {
        Optional<T> entity = findById(id);
        if (entity.isPresent()) {
            baseDAO.delete(entity.get());
            return entity.get();
        } else {
            return null;
        }
    }

    Long count() {
        return baseDAO.count();
    }

    List<T> saveAll(Iterable<T> entityList) {
        return baseDAO.saveAll(entityList);
    }



}
