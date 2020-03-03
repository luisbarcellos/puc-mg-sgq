package br.com.sgq.riscoacidente.v1.impl.repository;

import br.com.sgq.riscoacidente.v1.impl.model.RiscoAcidenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RiscoAcidenteRepository extends JpaRepository<RiscoAcidenteModel, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE RISCO_ACIDENTE" +
                   "   SET TIPO = :#{#riscoAcidenteModel.tipo}," +
                   "       DESCRICAO = :#{#riscoAcidenteModel.descricao}" +
                   " WHERE ID_RISCO_ACIDENTE = :idRiscoAcidente", nativeQuery = true)
    void atualizar(@Param("idRiscoAcidente") Long idRiscoAcidente,
                   @Param("riscoAcidenteModel") RiscoAcidenteModel riscoAcidenteModel);
}