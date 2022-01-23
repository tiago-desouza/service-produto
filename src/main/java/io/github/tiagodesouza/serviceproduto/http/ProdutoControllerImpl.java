package io.github.tiagodesouza.serviceproduto.http;

import io.github.tiagodesouza.serviceproduto.http.data.request.ProdutoPersistDto;
import io.github.tiagodesouza.serviceproduto.http.data.response.ProdutoResponseDto;
import io.github.tiagodesouza.serviceproduto.model.Produto;
import io.github.tiagodesouza.serviceproduto.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("produto")
public class ProdutoControllerImpl implements ProdutoController {

    final ProdutoService produtoService;

    public ProdutoControllerImpl(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto inserir(@Valid @RequestBody ProdutoPersistDto dto) {
        Produto produto = new Produto(dto.getDescricao(), dto.getValor());
        return produtoService.inserir(produto);
    }

    @Override
    @GetMapping("{id}")
    public Produto one(@PathVariable("id") Long id) {
        return produtoService.one(id);
    }

}
