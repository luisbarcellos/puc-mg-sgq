package br.com.sgq.incidente.v1.impl.repository;

import br.com.sgq.incidente.v1.impl.model.IncidenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IncidenteRepository extends JpaRepository<IncidenteModel, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE INCIDENTE" +
                   "   SET TIPO = :#{#incidenteModel.tipo}," +
                   "       GRAVIDADE = :#{#incidenteModel.gravidade}," +
                   "       DESCRICAO = :#{#incidenteModel.descricao}" +
                   " WHERE ID_INCIDENTE = :idIncidente", nativeQuery = true)
    void atualizar(@Param("idIncidente") Long idIncidente,
                   @Param("incidenteModel") IncidenteModel incidenteModel);
}