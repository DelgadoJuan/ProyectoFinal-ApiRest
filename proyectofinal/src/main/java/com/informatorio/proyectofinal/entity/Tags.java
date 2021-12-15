package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Emprendimiento> emprendimientos = new ArrayList<>();

    public Tags() {
    }

    public Tags(String nombre, List<Emprendimiento> emprendimientos) {
        this.nombre = nombre;
        this.emprendimientos = emprendimientos;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Emprendimiento> getEmprendimiento() {
        return emprendimientos;
    }

    public void setEmprendimiento(List<Emprendimiento> emprendimientos) {
        this.emprendimientos = emprendimientos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tags tags = (Tags) o;
        return id.equals(tags.id) && Objects.equals(nombre, tags.nombre) && Objects.equals(emprendimientos,
                tags.emprendimientos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, emprendimientos);
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", emprendimiento=" + emprendimientos +
                '}';
    }
}
