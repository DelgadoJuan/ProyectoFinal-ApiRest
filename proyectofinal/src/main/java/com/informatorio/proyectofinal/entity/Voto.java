package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.informatorio.proyectofinal.dto.Plataforma;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Plataforma generadoPor;
    @OneToOne
    @JsonIgnore
    private Usuario usuario;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JsonIgnore
    private Emprendimiento emprendimiento;

    public Voto() {
    }

    public Voto(Plataforma generadoPor, Usuario usuario, LocalDateTime fechaCreacion, Emprendimiento emprendimiento) {
        this.generadoPor = generadoPor;
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.emprendimiento = emprendimiento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Plataforma getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneradoPor(Plataforma generadoPor) {
        this.generadoPor = generadoPor;
    }

    public Emprendimiento getEmprendimiento() {
        return emprendimiento;
    }

    public void setEmprendimiento(Emprendimiento emprendimiento) {
        this.emprendimiento = emprendimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return id.equals(voto.id) && generadoPor == voto.generadoPor && Objects.equals(usuario, voto.usuario) &&
                fechaCreacion.equals(voto.fechaCreacion) && Objects.equals(emprendimiento, voto.emprendimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, generadoPor, usuario, fechaCreacion, emprendimiento);
    }

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", generadoPor=" + generadoPor +
                ", usuario=" + usuario +
                ", fechaCreacion=" + fechaCreacion +
                ", emprendimiento=" + emprendimiento +
                '}';
    }
}
