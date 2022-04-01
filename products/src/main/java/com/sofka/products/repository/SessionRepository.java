package com.sofka.products.repository;

import com.sofka.products.domain.Session;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio para la entidad Session
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
public interface SessionRepository extends CrudRepository<Session, Integer> {
}