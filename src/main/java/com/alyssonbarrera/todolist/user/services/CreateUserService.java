package com.alyssonbarrera.todolist.user.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.alyssonbarrera.todolist.errors.AppError;
import com.alyssonbarrera.todolist.user.entities.User;
import com.alyssonbarrera.todolist.user.repositories.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UsersRepository usersRepository;

    public CreateUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User execute(User user) {
        User userOnDatabase = this.usersRepository.findByUsername(user.getUsername());

        if (userOnDatabase != null) {
            new AppError("Já existe um usuário com o username informado.", HttpStatus.CONFLICT.value());
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(hashedPassword);

        return this.usersRepository.save(user);
    }
}
