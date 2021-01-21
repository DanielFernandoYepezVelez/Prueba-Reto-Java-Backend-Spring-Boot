package com.ProyectoSpringBootBackendTODOList.servicesInterface;

import java.util.List;

import com.ProyectoSpringBootBackendTODOList.models.SubTareaModel;

public interface ISubTareaService {

    public List<SubTareaModel> findAll();
    public List<SubTareaModel> findByTarea(Integer id);
    public SubTareaModel findById(Integer id);
    public SubTareaModel save(SubTareaModel tarea);
    public void delete(Integer id);
    
}
