package com.ProyectoSpringBootBackendTODOList.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ProyectoSpringBootBackendTODOList.models.SubTareaModel;
import com.ProyectoSpringBootBackendTODOList.models.TareaModel;
import com.ProyectoSpringBootBackendTODOList.servicesInterface.ISubTareaService;
import com.ProyectoSpringBootBackendTODOList.servicesInterface.ITareaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/subtask")
public class SubTareaController {

    @Autowired
    private ISubTareaService subTareaService;

    @Autowired
    private ITareaService tareaService;

    /*
    * Aquí Se OBTIENEN Las SubTareas O Tareas
    * Secundarias De La Aplicación (Funcional)
     */
    @GetMapping("/subtareas")
    public List<SubTareaModel> index() {  
        return subTareaService.findAll();
    }

    /*
    * Aquí Se OBTIENEN Las SubTareas O Tareas
    * Secundarias De La Aplicación Por El Id
    * De La Tarea Principal (Funcional)
     */
    @GetMapping("/subtareas/{id}")
    public List<SubTareaModel> showSubTareas(@PathVariable Integer id) {
        return subTareaService.findByTarea(id);
    }

    /*
    * Aquí Se CREAN Las SubTareas O Tareas 
    * Secundarias De La Aplicación (Funcional)
     */
    @PostMapping("/crear/{id}")
    public ResponseEntity<?> create(@RequestBody SubTareaModel subTarea, @PathVariable Integer id) {
        SubTareaModel tareaCompleta = null;
        Map<String, Object> response = new HashMap<>();
        
        TareaModel tareaPrincial = tareaService.findById(id);

        if (tareaPrincial == null) {
            response.put("mensaje", "Error, No Se Puede Encontrar La Tarea Principal ID: ".concat(id.toString().concat(" No Existe!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            subTarea.setTarea(tareaPrincial);
            tareaCompleta = subTareaService.save(subTarea);
        
        } catch (DataAccessException e) {
            response.put("mensaje", "Error En La Consulta O Query");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La SubTarea Completa Fue Creada Exitosamente!");
        response.put("tareaCompleta", tareaCompleta);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/subtarea/{id}")
    public SubTareaModel update(@RequestBody SubTareaModel subTarea, @PathVariable Integer id) {
        return subTareaService.save(subTarea);
    }

    /*
    * Aquí Se ELIMINAN Las SubTareas O Tareas 
    * Secundarias De La Aplicación (Funcional)
     */
    @DeleteMapping("/subtarea/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            subTareaService.delete(id);
            
        } catch (DataAccessException e) {
            response.put("mensaje", "Error En La Consulta O Query");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La SubTarea Fue Eliminada Exitosamente!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
