package com.udemy.backendninja2.service.impl;

import com.udemy.backendninja2.entity.User;
import com.udemy.backendninja2.entity.UserRole;
import com.udemy.backendninja2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* Servicio de Spring Security que devuelve a un usuario */
/* loadUserByUsername() es el método principal que devolverá la sesión, haciendo uso de buildAuthorities() para convertir
   un array de Roles propios a un array que Spring pueda identificar (GrantedAuthority); y de buildUser() para convertir
    un usuario de nuestro tipo 'User' a uno que Spring pueda reconocer (org.spring...userdetails.user). */

@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    /* Se encarga de llamar a nuestro repositorio mediante un 'findByUsername' obteniendo la entidad Usuario,
    * para lo que ocuparemos convertirla en UserDetails */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.udemy.backendninja2.entity.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
        return buildUser(user, authorities);
    }

    /* GrantedAuthority es el objeto que Spring Security que en realidad es Nuestra entidad Rol */
    /* Returnamos un tipo 'User' que usa Spring (Casualmente se llama igual que el de nosotros) */
    private org.springframework.security.core.userdetails.User buildUser(User user, List<GrantedAuthority> authorities){

        /* Igualamos nuestra entidad User con la de Spring, dejando los otros campos como 'true' ya que nosotros no
        manejamos esos campos (accountNonExpired, credentialsNonExpired, accountNonLocked) */
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    /* Es el que transformará nuestro set de Roles de usuario (<UserRole>) a un Listado <GrantedAuthority>
    (que es el objeto que SSecurity necesita para saber los roles del usuario autenticado */
    private List<GrantedAuthority> buildAuthorities(Set<UserRole> userRoles){
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
        for (UserRole userRole : userRoles) {
            auths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<GrantedAuthority>(auths);
    }
}
