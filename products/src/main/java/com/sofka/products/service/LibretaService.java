package com.sofka.products.service;

import com.sofka.products.domain.Usuario;
import com.sofka.products.repository.UsuarioRepository;
import com.sofka.products.service.interfaces.ILibreta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Clase tipo Servicio para el manejo de la libreta
 *
 * @version 1.0.0 2022-04-01
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
@Service
public class LibretaService implements ILibreta {

    /**
     * Repositorio de Usuario
     */
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Devuelve una lista de Usuarios con todos usuarios del sistema
     *
     * @return
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getList() {
        return usuarioRepository.findAll();
    }

    /**
     * Crea un usuario en el sistema
     *
     * @param usuario Objeto del usuario a crear
     * @return Objeto del usuario creado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        usuario.setCreatedAt(Instant.now());
        return usuarioRepository.save(usuario);
    }
}