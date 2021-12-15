package com.informatorio.proyectofinal.repository;

import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Evento;
import com.informatorio.proyectofinal.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Long> {
    @Query("FROM Emprendimiento e join fetch e.tags t WHERE t.nombre LIKE %:tag%")
    List<Emprendimiento> findByTag(@Param("tag") String tag);
    List<Emprendimiento> findByEventoOrderByCantidadVotosDesc(Evento evento);
    //List<Emprendimiento> findByTagsNombreContainingIn(Collection<Tags> tags, String tag);
    List<Emprendimiento> findByPublicadoFalse();
}