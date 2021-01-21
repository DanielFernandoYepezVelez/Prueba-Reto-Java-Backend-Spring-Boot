package com.ProyectoSpringBootBackendTODOList.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name="tareas_secundarias")
@Table(name="tareas_secundarias")
public class SubTareaModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name="created_at", nullable = false)
    private Date createdAt;

    @ManyToOne()
    @JoinColumn(name = "tarea_id")
    private TareaModel tarea;

    /* Antes De Que Se Guarde En La Base De Datos */
    @PrePersist
    public void prePersist() {
        createdAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TareaModel getTarea() {
        return tarea;
    }

    public void setTarea(TareaModel tarea) {
        this.tarea = tarea;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Atributo Estatico Requerido Al
     * Implementar La Interface Serializable
     */
    private static final long serialVersionUID = 1L;

}
