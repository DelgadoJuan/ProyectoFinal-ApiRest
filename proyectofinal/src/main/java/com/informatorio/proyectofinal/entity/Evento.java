package com.informatorio.proyectofinal.entity;

import com.informatorio.proyectofinal.dto.Estado;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detalle;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCierre;
    private Estado estado;
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprendimiento> emprendimientos = new ArrayList<>();
    private BigInteger premio;

    public Evento() {
    }

    public Evento(String detalle, LocalDateTime fechaCierre, Estado estado, List<Emprendimiento> emprendimientos, BigInteger premio) {
        this.detalle = detalle;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
        this.emprendimientos = emprendimientos;
        this.premio = premio;
    }

    public Long getId() {
        return id;
    }

    public String getDetalle() {
        return detalle;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public Estado getEstado() {
        return estado;
    }


    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Emprendimiento> getEmprendimiento() {
        return emprendimientos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return id.equals(evento.id) && Objects.equals(detalle, evento.detalle) && fechaCreacion.equals(evento.fechaCreacion) && Objects.equals(fechaCierre, evento.fechaCierre) && estado == evento.estado && Objects.equals(emprendimientos, evento.emprendimientos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, detalle, fechaCreacion, fechaCierre, estado, emprendimientos);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", detalle='" + detalle + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaCierre=" + fechaCierre +
                ", estado=" + estado +
                ", emprendimientos=" + emprendimientos +
                '}';
    }

    public void agregarEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientos.add(emprendimiento);
        emprendimiento.setEvento(this);
    }

    public void eliminarEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientos.remove(emprendimiento);
        emprendimiento.setEvento(null);
    }

    public BigInteger getPremio() {
        return premio;
    }

    public void setPremio(BigInteger premio) {
        this.premio = premio;
    }
}
