package com.ProyectoSpringBootBackendTODOList.servicesInterface;

import java.util.List;

import com.ProyectoSpringBootBackendTODOList.models.TareaModel;

public interface ITareaService {

    public List<TareaModel> findAll();
    public TareaModel findById(Integer id);
    public TareaModel save(TareaModel tarea);
    public void delete(Integer id);

}
