package com.informatorio.proyectofinal.entity;

import org.hibernate.annotations.CreationTimestamp;
import com.informatorio.proyectofinal.entity.Evento;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Emprendimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nombre;
    private String descripcion;
    private String contenido;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    private BigInteger objetivo;
    private Boolean publicado;
    private String url;
    private String tags;
    @ManyToOne(fetch = FetchType.LAZY)
    private Evento evento;
    private Boolean activo;
    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos;

    public Emprendimiento() {
    }

    public Emprendimiento(String nombre, String descripcion, String contenido, BigInteger objetivo,
                          Boolean publicado, String url, String tags, Evento evento, Boolean activo, List<Voto> votos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.objetivo = objetivo;
        this.publicado = publicado;
        this.url = url;
        this.tags = tags;
        this.evento = evento;
        this.activo = activo;
        this.votos = votos;
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

    public String getUrl() {
        return url;
    }

    public String getTags() {
        return tags;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprendimiento that = (Emprendimiento) o;
        return id.equals(that.id) && nombre.equals(that.nombre) && Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(contenido, that.contenido) && fechaCreacion.equals(that.fechaCreacion) &&
                Objects.equals(objetivo, that.objetivo) && publicado.equals(that.publicado) &&
                Objects.equals(url, that.url) && Objects.equals(tags, that.tags) && Objects.equals(votos, that.votos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, contenido, fechaCreacion, objetivo, publicado, url, tags);
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
                ", url='" + url + '\'' +
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
}
