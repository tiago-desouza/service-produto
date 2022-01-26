package io.github.tiagodesouza.serviceproduto.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.tiagodesouza.serviceproduto.event.ProdutoPersistEvent;
import io.github.tiagodesouza.serviceproduto.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProdutoPersistQueueListener implements ApplicationListener<ProdutoPersistEvent> {

    private static final Logger LOGGER = Logger.getLogger(ProdutoPersistQueueListener.class.getName());

    private final ObjectMapper objectMapper;
    private final JmsTemplate jmsTemplate;

    public ProdutoPersistQueueListener(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onApplicationEvent(ProdutoPersistEvent event) {
        try {
            Produto produto = event.getProduto();
            String json = objectMapper.writeValueAsString(produto);
            jmsTemplate.convertAndSend("produto.persist.queue", json);
        } catch (JsonProcessingException e) {
            LOGGER.finer(e.getMessage());
        }
    }
}
