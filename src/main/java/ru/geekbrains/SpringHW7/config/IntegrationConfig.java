package ru.geekbrains.SpringHW7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;
import ru.geekbrains.SpringHW7.model.User;
import java.io.File;

// конфигурация бинов интеграции
@Configuration
public class IntegrationConfig {

    // входящий канал данных
    @Bean
    public MessageChannel inputChannel(){
        return new DirectChannel();
    }

    // исходящий канал данных
    @Bean
    public MessageChannel outputChannel(){
        return new DirectChannel();
    }

    // производится преобразование объекта User в строковое выражение со скрытием пароля
    @Bean
    @Transformer(inputChannel = "inputChannel",outputChannel = "outputChannel")
    public GenericTransformer<User,String> userInfoTransformer(){
        return user -> {
          user.setPassword("*****");
          return user.toString();
        };
    }

    // производится сохранение текстового сообщения в файл.
    @Bean
    @ServiceActivator(inputChannel = "outputChannel")
    public FileWritingMessageHandler messageHandler(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("D:/Test"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}
