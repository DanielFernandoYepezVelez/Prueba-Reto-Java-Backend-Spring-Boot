package com.ProyectoSpringBootBackendTODOList.services;

import java.util.List;

import com.ProyectoSpringBootBackendTODOList.models.SubTareaModel;
import com.ProyectoSpringBootBackendTODOList.repositories.ISubTareaRepository;
import com.ProyectoSpringBootBackendTODOList.servicesInterface.ISubTareaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubTareaService implements ISubTareaService {

    @Autowired
    public ISubTareaRepository subTareaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SubTareaModel> findAll() {
        return (List<SubTareaModel>) subTareaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubTareaModel> findByTarea(Integer id) {
        return subTareaRepository.findByTarea(id);
    }

    @Override
    @Transactional(readOnly = true)
    public SubTareaModel findById(Integer id) {
        return subTareaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public SubTareaModel save(SubTareaModel subTarea) {
        return subTareaRepository.save(subTarea);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        subTareaRepository.deleteById(id);
    }

}
