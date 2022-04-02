package com.sofka.products.service.interfaces;

import com.sofka.products.domain.Usuario;

/**
 * Interface para el servicio de Libreta
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
public interface ILibreta {

    /**
     * Devuelve una lista de Contactos con todos contactos del sistema
     *
     * @return
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    public Iterable<Usuario> getList();

    /**
     * Crea un usuario en el sistema
     *
     * @param usuario Objeto del usuario a crear
     * @return Objeto del usuario creado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */

    public Usuario createUsuario(Usuario usuario);

}
