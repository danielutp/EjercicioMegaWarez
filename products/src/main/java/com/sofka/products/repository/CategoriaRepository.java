package com.sofka.products.repository;

import com.sofka.products.domain.Categoria;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio para la entidad Categoria
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
}