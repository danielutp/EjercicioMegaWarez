package com.sofka.products.repository;

import com.sofka.products.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Categoria
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}