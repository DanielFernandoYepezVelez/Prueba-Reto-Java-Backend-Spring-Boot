package com.ProyectoSpringBootBackendTODOList.services;

import java.util.List;

import com.ProyectoSpringBootBackendTODOList.models.TareaModel;
import com.ProyectoSpringBootBackendTODOList.repositories.ITareaRepository;
import com.ProyectoSpringBootBackendTODOList.servicesInterface.ITareaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TareaService implements ITareaService {

    @Autowired
    private ITareaRepository tareaRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<TareaModel> findAll() {
        return (List<TareaModel>) tareaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TareaModel findById(Integer id) {
        return tareaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public TareaModel save(TareaModel tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        tareaRepository.deleteById(id);     
    }
    
}