package io.github.tiagodesouza.serviceproduto.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.github.tiagodesouza.serviceproduto.http.data.request.ProdutoPersistDto;
import io.github.tiagodesouza.serviceproduto.http.data.response.ProdutoResponseDto;
import io.github.tiagodesouza.serviceproduto.model.Produto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface ProdutoController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Produto inserir(@Valid @RequestBody ProdutoPersistDto dto);

    @Operation(summary = "Retonar o produto correspondente ao identificador recuperado por paramêtro.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(
                            mediaType = "application/json"
                    )
            )
    })
    @GetMapping("{id}")
    Produto one(@PathVariable("id") Long id);

    @PatchMapping("{id}")
    Produto update(@PathVariable("id") Long id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException;

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") Long id);

    @PutMapping("{id}")
    Produto updateAll(@PathVariable("id") Long id, @RequestBody ProdutoPersistDto dto);
}
