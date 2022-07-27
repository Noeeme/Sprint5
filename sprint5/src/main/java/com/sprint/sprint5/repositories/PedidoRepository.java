package com.sprint.sprint5.repositories;

import com.sprint.sprint5.models.Pedido;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT pedido FROM Pedido pedido WHERE (:cpf IS NULL OR :cpf = cpf)")
    List<Pedido> findWithFilters(@Param("cpf") String cpf, Sort sort);
}
