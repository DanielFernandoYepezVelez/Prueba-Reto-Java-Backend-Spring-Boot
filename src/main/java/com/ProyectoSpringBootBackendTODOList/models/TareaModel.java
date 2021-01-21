package com.ProyectoSpringBootBackendTODOList.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name="tareas_principales")
@Table(name="tareas_principales")
public class TareaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable = false)
    private Date createdAt;

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
