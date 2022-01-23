package io.github.tiagodesouza.serviceproduto.http.data.response;

public class ProdutoResponseDto {

    private Long id;
    private String descricao;

    @Deprecated
    public ProdutoResponseDto() {
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

}
