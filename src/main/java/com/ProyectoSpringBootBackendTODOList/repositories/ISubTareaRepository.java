package com.ProyectoSpringBootBackendTODOList.repositories;

import java.util.List;

import com.ProyectoSpringBootBackendTODOList.models.SubTareaModel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ISubTareaRepository extends CrudRepository<SubTareaModel, Integer> {

    @Query("SELECT s FROM tareas_secundarias s WHERE s.tarea.id = ?1")
    List<SubTareaModel> findByTarea(Integer idTarea);
}