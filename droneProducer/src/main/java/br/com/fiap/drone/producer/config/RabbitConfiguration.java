package br.com.fiap.drone.producer.config;

import br.com.fiap.DroneProducerApplication;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * Padrao de conexao para qualquer connector com o Rabbit
 *
 * @author lucasrodriguesdonascimento
 */
@Configuration
public class RabbitConfiguration {

    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * Ouvinte da fabrica de conexao convertida pelo JacksonConverter.
     *
     * @return
     */
    public SimpleRabbitListenerContainerFactory simplesRabbitListenerContainerFactory() {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());
        return factory;

    }

    @Bean
    public RabbitTemplate RabbitTemplate() {
        /**
         * Construtor do RabbitTemplate pede um ConnectionFacotry
         * Passando uma forma de conversao de mensagem com o metodo jacksonConverter
         */
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter());
        return rabbitTemplate;
    }


    /**
     * Metodo responsavel por receber a mensagem e converter
     * para as propriedades do JacksonConverter
     *
     * @return
     */
    @Bean
    Jackson2JsonMessageConverter jacksonConverter() {
        final ObjectMapper mapper = Jackson2ObjectMapperBuilder
                .json()
                .modules(new JavaTimeModule())
                .dateFormat(new StdDateFormat())
                .failOnUnknownProperties(false)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .serializationInclusion(JsonInclude.Include.NON_DEFAULT)
                .moduleClassLoader(DroneProducerApplication.class.getClassLoader())
                .build();
        return new Jackson2JsonMessageConverter(mapper);

    }
}