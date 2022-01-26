package io.github.tiagodesouza.serviceproduto.service;

import io.github.tiagodesouza.serviceproduto.event.ProdutoPersistEvent;
import io.github.tiagodesouza.serviceproduto.model.Produto;
import io.github.tiagodesouza.serviceproduto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    final ProdutoRepository produtoRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.produtoRepository = produtoRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Produto save(Produto produto) {
        Produto produtoPersist =  produtoRepository.save(produto);
        applicationEventPublisher.publishEvent(new ProdutoPersistEvent(this, produto));
        return produtoPersist;
    }

    @Override
    public Produto one(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NoResultException(String.format("Produto de código %d não encontrado", id)));
    }

    @Override
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NoResultException(String.format("Produto de código %d não encontrado", id));
        }

        produtoRepository.deleteById(id);
    }

    @Override
    public Produto update(Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new NoResultException(String.format("Produto de código %d não encontrado", produto.getId()));
        }

        return save(produto);
    }
}
