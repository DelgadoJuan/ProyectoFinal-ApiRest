package com.informatorio.proyectofinal.dto;

public class VotoDto {
    private Long usuarioId;
    private Long emprendimientoId;
    private Plataforma generadoPor;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getEmprendimientoId() {
        return emprendimientoId;
    }

    public void setEmprendimientoId(Long emprendimientoId) {
        this.emprendimientoId = emprendimientoId;
    }

    public Plataforma getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneradoPor(Plataforma generadoPor) {
        this.generadoPor = generadoPor;
    }
}
