package com.alyssonbarrera.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.alyssonbarrera.todolist.user.entities.User;
import com.alyssonbarrera.todolist.user.repositories.UsersRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if (!servletPath.startsWith("/tasks")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorization =  request.getHeader("Authorization");

        String encodedAuthorization = authorization.substring("Basic".length()).trim();

        byte[] decodedAuthorization = Base64.getDecoder().decode(encodedAuthorization);

        String authorizationString = new String(decodedAuthorization);

        String[] credentials = authorizationString.split(":");

        String username = credentials[0];
        String password = credentials[1];

        User user = this.usersRepository.findByUsername(username);

        if (user == null) {
            response.sendError(401);
            return;
        }

        boolean passwordIsValid = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified;

        if (!passwordIsValid) {
            response.sendError(401);
        }

        request.setAttribute("user", user);
        filterChain.doFilter(request, response);
    }
}
