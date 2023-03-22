package com.ntu.sctp.group1.Security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.ntu.sctp.group1.entity.UserCredentials;
import com.ntu.sctp.group1.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Component
public class FirebaseFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepo;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // a. Check if there is an authorization header;
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // let's continue down the filer chain
            return;
        }



        // Get the actual Token string
        FirebaseToken decodedToken = null;
        String token = authHeader.replace("Bearer ", "");

        // Send the string token to firebase to verify its authenticity
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch(Exception ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Error!");
        }

        // Check if the token is valid
        if(decodedToken == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is invalid!!");
        }

        // Check if the User has an existing record in DB
        Optional<UserCredentials> user = userRepo.findByUid(decodedToken.getUid());
        if(user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User credentials does not match!");
        }

        // Checked if User has been logged out
        if(!user.get().isTokenIsActive()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You have been logged out!");
        }

        // If all okay, continue with filter chain
        filterChain.doFilter(request, response);

    }
}
