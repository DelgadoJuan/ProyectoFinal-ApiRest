package com.informatorio.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Emprendimiento emprendimiento;
    private String url;

    public Url() {
    }

    public Url(Emprendimiento emprendimiento, String url) {
        this.emprendimiento = emprendimiento;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public Emprendimiento getEmprendimiento() {
        return emprendimiento;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
