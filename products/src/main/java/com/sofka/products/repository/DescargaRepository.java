package com.sofka.products.repository;

import com.sofka.products.domain.Descarga;
import org.springframework.data.repository.CrudRepository;

/**
 * Repositorio para la entidad Descarga
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
public interface DescargaRepository extends CrudRepository<Descarga, Integer> {
}