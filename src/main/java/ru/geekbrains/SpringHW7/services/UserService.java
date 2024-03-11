package ru.geekbrains.SpringHW7.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.SpringHW7.model.User;
import ru.geekbrains.SpringHW7.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserFileSaver userFileSaver;

    private final UserRepository userRepository;

    // Получение списка всех пользователей
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Добавление пользователя
    public void addUser(User user) {
        userRepository.save(user);
    }

    // Удаление пользователя
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    //Поиск пользователя по id
    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    //Обновление пользователя
    public User updateUser(User user){
        userFileSaver.writeToFile(user.getName()+"_"+user.getCity(),user);
        userRepository.save(user);
        return user;
    }
}
