package com.sofka.products.repository;

import com.sofka.products.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio para la entidad Usuario
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}