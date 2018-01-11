package com.udemy.backendninja2.converter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The type Test crypt.
 */
public class TestCrypt {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        System.out.println(pe.encode("admin"));
    }
}
