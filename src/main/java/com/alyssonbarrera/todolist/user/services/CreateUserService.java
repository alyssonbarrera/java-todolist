package com.alyssonbarrera.todolist.user.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.alyssonbarrera.todolist.errors.AppError;
import com.alyssonbarrera.todolist.user.entities.User;
import com.alyssonbarrera.todolist.user.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    @Autowired
    private UsersRepository usersRepository;

    public User execute(User user) {
        var userOnDatabase = this.usersRepository.findByUsername(user.getUsername());

        if (userOnDatabase != null) {
            throw new AppError("Já existe um usuário com o username informado.", 409);
        }

        String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(hashedPassword);

        return this.usersRepository.save(user);
    }
}
