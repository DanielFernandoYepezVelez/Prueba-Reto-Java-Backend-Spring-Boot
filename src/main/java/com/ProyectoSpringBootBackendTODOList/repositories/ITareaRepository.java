package com.ProyectoSpringBootBackendTODOList.repositories;

import com.ProyectoSpringBootBackendTODOList.models.TareaModel;

import org.springframework.data.repository.CrudRepository;

public interface ITareaRepository extends CrudRepository<TareaModel, Integer> {
    
}
