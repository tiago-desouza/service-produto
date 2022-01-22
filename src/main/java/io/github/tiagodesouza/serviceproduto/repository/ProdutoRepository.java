package io.github.tiagodesouza.serviceproduto.repository;

import io.github.tiagodesouza.serviceproduto.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
