package com.example.crud_task.config;

import com.example.crud_task.Models.Entity.Student;
import com.example.crud_task.Repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new User(
                student.getEmail(),
                student.getPassword(),
                new ArrayList<>()
        );

    }
}
