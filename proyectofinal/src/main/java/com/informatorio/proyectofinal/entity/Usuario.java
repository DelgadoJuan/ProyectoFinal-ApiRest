package com.informatorio.proyectofinal.entity;

import com.informatorio.proyectofinal.dto.TipoUsuario;
import org.hibernate.annotations.CreationTimestamp;
import org.jasypt.util.text.BasicTextEncryptor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nombre;
    private String apellido;
    @Column(unique = true)
    @Email(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private String email;
    private String password;
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    private String ciudad;
    private String provincia;
    private String pais;
    private TipoUsuario tipoUsuario;
    private Boolean activo;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String email, String password, String ciudad, String provincia, String pais, TipoUsuario tipoUsuario, Boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
        this.tipoUsuario = tipoUsuario;
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        String CLAVE = "ASDQWEPJ";
        BasicTextEncryptor encriptador = new BasicTextEncryptor();
        encriptador.setPassword(CLAVE);
        return encriptador.encrypt(password);
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPais() {
        return pais;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && nombre.equals(usuario.nombre) && Objects.equals(apellido, usuario.apellido) && email.equals(usuario.email) && password.equals(usuario.password) && fechaCreacion.equals(usuario.fechaCreacion) && Objects.equals(ciudad, usuario.ciudad) && Objects.equals(provincia, usuario.provincia) && Objects.equals(pais, usuario.pais) && tipoUsuario == usuario.tipoUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, email, password, fechaCreacion, ciudad, provincia, pais, tipoUsuario);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", password='" + getPassword() + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", ciudad='" + ciudad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", pais='" + pais + '\'' +
                ", activo='" + activo + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                '}';
    }
}
