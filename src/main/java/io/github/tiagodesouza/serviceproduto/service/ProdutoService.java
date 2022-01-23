package io.github.tiagodesouza.serviceproduto.service;

import io.github.tiagodesouza.serviceproduto.model.Produto;

public interface ProdutoService {

    Produto save(Produto produto);

    Produto one(Long id);

    void delete(Long id);

    Produto update(Produto produto);
}
