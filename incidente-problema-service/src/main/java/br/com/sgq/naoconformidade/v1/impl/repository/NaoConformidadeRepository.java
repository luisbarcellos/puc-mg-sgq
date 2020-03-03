package br.com.sgq.naoconformidade.v1.impl.repository;

import br.com.sgq.naoconformidade.v1.impl.model.NaoConformidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface NaoConformidadeRepository extends JpaRepository<NaoConformidadeModel, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE NAO_CONFORMIDADE" +
                   "   SET TIPO = :#{#naoConformidadeModel.tipo}," +
                   "       DESCRICAO = :#{#naoConformidadeModel.descricao}" +
                   " WHERE ID_NAO_CONFORMIDADE = :idNaoConformidade", nativeQuery = true)
    void atualizar(@Param("idNaoConformidade") Long idNaoConformidade,
                   @Param("naoConformidadeModel") NaoConformidadeModel naoConformidadeModel);

    @Transactional
    @Modifying
    @Query(value = "UPDATE NAO_CONFORMIDADE" +
                   "   SET ID_PROBLEMA = :idProblema" +
                   " WHERE ID_NAO_CONFORMIDADE = :idNaoConformidade", nativeQuery = true)
    void atualizarProblema(@Param("idNaoConformidade") Long idNaoConformidade,
                           @Param("idProblema") Long idProblema);

    @Transactional
    @Modifying
    @Query(value = "UPDATE NAO_CONFORMIDADE" +
                   "   SET ID_RISCO_ACIDENTE = :idRiscoAcidente" +
                   " WHERE ID_NAO_CONFORMIDADE = :idNaoConformidade", nativeQuery = true)
    void atualizarRiscoAcidente(@Param("idNaoConformidade") Long idNaoConformidade,
                          @Param("idRiscoAcidente") Long idRiscoAcidente);

    @Query(value = "SELECT * " +
                   "  FROM NAO_CONFORMIDADE" +
                   " WHERE ID_RISCO_ACIDENTE = :idRiscoAcidente", nativeQuery = true)
    Optional<NaoConformidadeModel> buscarNaoConformidadePorRiscoAcidente(@Param("idRiscoAcidente")Long idRiscoAcidente);
}