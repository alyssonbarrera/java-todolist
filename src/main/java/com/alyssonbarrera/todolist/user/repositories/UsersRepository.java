package com.alyssonbarrera.todolist.user.repositories;

import com.alyssonbarrera.todolist.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
