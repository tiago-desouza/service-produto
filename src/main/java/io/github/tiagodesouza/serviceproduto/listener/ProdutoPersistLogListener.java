package io.github.tiagodesouza.serviceproduto.listener;

import io.github.tiagodesouza.serviceproduto.event.ProdutoPersistEvent;
import io.github.tiagodesouza.serviceproduto.model.Produto;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ProdutoPersistLogListener implements ApplicationListener<ProdutoPersistEvent> {

    @Override
    public void onApplicationEvent(ProdutoPersistEvent event) {
        Produto produto = event.getProduto();
    }
}
