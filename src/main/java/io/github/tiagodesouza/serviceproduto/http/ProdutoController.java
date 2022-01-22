package io.github.tiagodesouza.serviceproduto.http;

import io.github.tiagodesouza.serviceproduto.http.data.request.ProdutoPersistDto;
import io.github.tiagodesouza.serviceproduto.http.data.response.ProdutoResponseDto;
import io.github.tiagodesouza.serviceproduto.model.Produto;
import io.github.tiagodesouza.serviceproduto.service.ProdutoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ProdutoResponseDto inserir(@RequestBody ProdutoPersistDto dto) {
        Produto produto = new Produto(dto.getDescricao(), dto.getValor());
        Produto produtoPersistido = produtoService.inserir(produto);
        return new ProdutoResponseDto(produtoPersistido.getId(), produto.getDescricao());
    }

}
