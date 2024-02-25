package ru.geekbrains.SpringHW7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.SpringHW7.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
