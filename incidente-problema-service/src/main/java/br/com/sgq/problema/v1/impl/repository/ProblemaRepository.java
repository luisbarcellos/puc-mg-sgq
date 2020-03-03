package br.com.sgq.problema.v1.impl.repository;

import br.com.sgq.problema.v1.impl.model.ProblemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProblemaRepository extends JpaRepository<ProblemaModel, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE PROBLEMA" +
                   "   SET TIPO = :#{#problemaModel.tipo}," +
                   "       GRAVIDADE = :#{#problemaModel.gravidade}," +
                   "       DESCRICAO = :#{#problemaModel.descricao}" +
                   " WHERE ID_PROBLEMA = :idProblema", nativeQuery = true)
    void atualizar(@Param("idProblema") Long idProblema,
                   @Param("problemaModel") ProblemaModel problemaModel);
}