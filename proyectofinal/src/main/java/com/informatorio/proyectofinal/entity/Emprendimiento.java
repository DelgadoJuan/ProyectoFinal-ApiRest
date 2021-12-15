package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Emprendimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String nombre;
    private String descripcion;
    private String contenido;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    private BigInteger objetivo;
    private Boolean publicado;
    @OneToOne
    @JoinColumn(name = "creador_id")
    @JsonIgnore
    private Usuario creador;
    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Url> urls;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "emprendimiento_id",
            joinColumns = @JoinColumn(name = "emprendimiento_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id")
    )
    @JsonIgnore
    private List<Tags> tags = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Evento evento;
    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos;
    private int cantidadVotos = 0;

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public Emprendimiento() {
    }

    public Emprendimiento(String nombre, String descripcion, String contenido, BigInteger objetivo,
                          Boolean publicado, Usuario creador, Evento evento, Boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.objetivo = objetivo;
        this.publicado = publicado;
        this.creador = creador;
        this.evento = evento;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public BigInteger getObjetivo() {
        return objetivo;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setObjetivo(BigInteger objetivo) {
        this.objetivo = objetivo;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprendimiento that = (Emprendimiento) o;
        return id.equals(that.id) && nombre.equals(that.nombre) && Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(contenido, that.contenido) && fechaCreacion.equals(that.fechaCreacion) &&
                Objects.equals(objetivo, that.objetivo) && publicado.equals(that.publicado) &&
                Objects.equals(urls, that.urls) && Objects.equals(tags, that.tags) && Objects.equals(votos, that.votos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, contenido, fechaCreacion, objetivo, publicado, urls, tags);
    }

    @Override
    public String toString() {
        return "Emprendimiento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", objetivo=" + objetivo +
                ", publicado=" + publicado +
                ", url='" + urls + '\'' +
                ", tags='" + tags + '\'' +
                ", votos='" + votos + '\'' +
                '}';
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void agregarVoto(Voto voto) {
        votos.add(voto);
        voto.setEmprendimiento(this);
    }

    public void eliminarVoto(Voto voto) {
        votos.remove(voto);
        voto.setEmprendimiento(null);
    }

    public List<Url> getUrls() {
        return urls;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void agregarTag(Tags tag) {
        tags.add(tag);
        tag.getEmprendimiento().add(this);
    }

    /*public void agregarUrl(Tags tag) {
        tags.add(tag);
        tag.setEmprendimiento(this);
    }

    public void eliminarUrl(Url url) {
        tags.add(tag);
        url.(null);
    } */

    public int getCantidadVotos() {
        return cantidadVotos;
    }

    public void setCantidadVotos(int cantidadVotos) {
        this.cantidadVotos = cantidadVotos;
    }
}
