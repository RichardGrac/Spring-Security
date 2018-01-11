package com.udemy.backendninja2.component;

import com.udemy.backendninja2.repository.LogRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/* Clase que intercepta todas las peticiones dentro de nuestra aplicación Web*/
@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    @Qualifier("logRepository")
    private LogRepository logRepository;

    private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);

    // /example/exampleString
    // Se ejecuta antes de entrar al método del Controllador
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis()); // Guarda el tiempo actual en millisegundos
        return true;
    }

    // Se ejecuta justo antes de lanzar la vista al navegador
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (long) request.getAttribute("startTime");

        /* Nos permitirá registrar el Log dentro de la BD, por medio del LogRepository y su entidad correspondiente (Log) */
        String url = request.getRequestURL().toString();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = "";
        if (auth != null && auth.isAuthenticated()){
            username = auth.getName();
        }

        // Lanzará una excepción porque no necesariamente estará logeado un usuario
        String details = "";
        try {
            details = auth.getDetails().toString();
        }catch (Exception e){}finally {
            // Agregamos el Log a la BD
            logRepository.save(new com.udemy.backendninja2.entity.Log(new Date(), details, username, url));
        }
        /* ----------------------------------  ./log añadido ------------------------------------------------ */

        LOG.info("--Url to: '" + url + "' --in: '" + (System.currentTimeMillis() - startTime) + "' ms.");
    }
}
