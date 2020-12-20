package br.com.fiap.drone.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuracao detalhada para producers , Caso vc tenha
 * mais de uma fila em sua aplicacao essa configuracao serve para este contexto.
 * <p>
 * <p>
 * Quando a aplicacao startar as filas ja seram adicionadas no RabbitMQ queue
 *
 * @author lucasrodriguesdonascimento
 */
@Configuration
public class RabbitProducerConfiguration {

    @Value("${spring.rabbitmq.routing-key}")
    private String queue;

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.deadletter}")
    private String deadletter;

    /**
     * Exchange -> Recebe a mensagem enviada pelo Produto e encaminha a
     * mensagem para a fila destinada pelo produtor.
     *
     * @return
     */
    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    /**
     * DeadLetter Queue para receber as mensagens que deram erro
     * e quando o aplicacao que deu erro voltar, passar a consumir
     * da deadLetter
     */

    @Bean
    Queue deadLetter() {
        return new Queue(deadletter);
    }

    /**
     * Associar a Queue a deadLetter , pois a deadLetter esta associado a uma
     * queue caso de erro
     * <p>
     * Construtor da Queue :
     * <p>
     * Nome da queie
     * Duravel
     * Tirando auto exclusao
     * Tirando auto complite
     * Associando a queue a deadLetter
     *
     * @return
     */
    @Bean
    Queue queue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", exchange);
        args.put("x-dead-letter-routing-key", deadletter);
        return new Queue(queue, true, false, false, args);
    }

    @Bean
    public Binding bindingQueue() {
        return BindingBuilder.bind(queue()).to(exchange()).with(queue);
    }

    /**
     * Atrela a deadLetter a fila
     *
     * @return
     */
    @Bean
    public Binding bindingQueueDeadLetter() {
        return BindingBuilder.bind(deadLetter()).to(exchange()).with(deadletter);
    }


}
