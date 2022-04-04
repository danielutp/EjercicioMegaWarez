package com.sofka.products.repository;

import com.sofka.products.domain.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad SubCategoria
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Integer> {
}