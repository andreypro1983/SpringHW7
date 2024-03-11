package ru.geekbrains.SpringHW7.services;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
import ru.geekbrains.SpringHW7.model.User;

//интерфейс для настройки начальной точки интеграции
@MessagingGateway(defaultRequestChannel = "inputChannel")
public interface UserFileSaver {
    void writeToFile(@Header(FileHeaders.FILENAME) String filename, User user);
}
