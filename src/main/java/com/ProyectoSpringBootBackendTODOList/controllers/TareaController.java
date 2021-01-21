package com.ProyectoSpringBootBackendTODOList.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ProyectoSpringBootBackendTODOList.models.TareaModel;
import com.ProyectoSpringBootBackendTODOList.servicesInterface.ITareaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/task")
public class TareaController {

    @Autowired
    private ITareaService tareaService;

    /*
    * Aquí Se OBTIENEN Las Tareas Principales
    * O Primarias De La Aplicación (Funcional)
     */
    @GetMapping("/tareas")
    public List<TareaModel> index() {
        return tareaService.findAll();
    }

    /*
    * Aquí Se OBTIENE La Tarea Principal
    * O Primaria De La Aplicación Por El Id.
    * Este Servicio Es Funcional (Aunque No Es
    * Solicitado Por El Frontend Actualmente)
     */
    @GetMapping("/tarea/{id}")
    public ResponseEntity<?> show(@PathVariable Integer id) {
        TareaModel tarea = null;
        Map<String, Object> response = new HashMap<>();

        try {
            tarea = tareaService.findById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error En La Consulta O Query");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (tarea == null) {
            response.put("mensaje", "La Tarea ID: ".concat(id.toString().concat(" No Existe!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<TareaModel>(tarea, HttpStatus.OK);
    }

    /*
    * Aquí Se CREAN Las Tareas Principales
    * O Primarias De La Aplicación (Funcional)
     */
    @PostMapping("/crear")
    /* @ResponseStatus(HttpStatus.CREATED) */
    public ResponseEntity<?> create(@RequestBody TareaModel tarea) {
        TareaModel nuevaTarea = null;
        Map<String, Object> response = new HashMap<>();

        try {
            nuevaTarea = tareaService.save(tarea);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error En La Consulta O Query");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La Tarea Fue Creada Exitosamente!");
        response.put("tarea", nuevaTarea);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    /*
    * Aquí Se ACTUALIZA La Tarea Principal
    * O Primaria De La Aplicación Por El Id.
    * Este Servicio Es Funcional (Aunque No Es
    * Solicitado Por El Frontend Actualmente)
     */
    @PutMapping("/tarea/{id}")
    /* @ResponseStatus(HttpStatus.CREATED) */
    public ResponseEntity<?> update(@RequestBody TareaModel tarea, @PathVariable Integer id) {
        TareaModel tareaActualizada = null;
        Map<String, Object> response = new HashMap<>();
        
        TareaModel tareaActual = tareaService.findById(id);
        
        if (tareaActual == null) {
            response.put("mensaje", "Error, No Se Puede Editar La Tarea ID: ".concat(id.toString().concat(" No Existe!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            tareaActual.setName(tarea.getName());
            tareaActual.setCreatedAt(tarea.getCreatedAt());
            
            tareaActualizada = tareaService.save(tareaActual);
        
        } catch (DataAccessException e) {
            response.put("mensaje", "Error En La Consulta O Query");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("mensaje", "La Tarea Fue Actualizada Exitosamente!");
        response.put("tarea", tareaActualizada);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /*
    * Aquí Se ELIMINAN Las Tareas Principales
    * O Primarias De La Aplicación (Funcional)
     */
    @DeleteMapping("/tarea/{id}")
    /* @ResponseStatus(HttpStatus.NO_CONTENT) */
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            tareaService.delete(id);
            
        } catch (DataAccessException e) {
            response.put("mensaje", "Error En La Consulta O Query");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "La Tarea Fue Eliminada Exitosamente!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
