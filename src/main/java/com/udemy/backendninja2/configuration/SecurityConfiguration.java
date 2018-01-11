package com.udemy.backendninja2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // Como es una clase de Configuración, agregamos esta notación
@EnableWebSecurity  // Para habilitar la seguridad Web
@EnableGlobalMethodSecurity(prePostEnabled = true)  // Nos permite escribir anotaciones para controlar el acceso a los métodos según la seguridad
// WebSecurityConfigurerAdapter nos va a ayudar a guiarnos por la configuración
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    @Qualifier("userService")
    private UserDetailsService userService;

    // Nos servirá para añadir el UserDetailService que nos regresa el UserService
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        //passwordEncoder nos ayudará a encriptar la contraseña en la BD
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // Lo vamos a sobreescribir para la configuración
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*.antMatchers("/contacts/contactform").hasRole("ADMIN")*/
        http.authorizeRequests()
                .antMatchers("/css/*", "/imgs/*").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/loginsuccess").permitAll()
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
                .permitAll();

        //Usamos el objeto http
        /* .antMatchers().permitAll(): índicamos las Request autorizadas sin la necesidad de un login
            o con .hasRole("ROLE") para permitirselo solo a ese ROL */
        // .anyRequest(): el resto de Request, las ponemos con Identificación
        // .and(): añadimos más configuración
        /* .formLogin(): índicamos la url del login page '/login' con la url que lo procesa '/logincheck'
             con username y password. */
        // defaultSuccessUrl() nos índica hacia qué url mandarnos al autentificarse. Y al login se lo permitimos a todos
        // .and(): añadimos más configuración
        // Lo mismo con el logout, se irá a login con el parámetro logout y un permitAll()
    }
}
