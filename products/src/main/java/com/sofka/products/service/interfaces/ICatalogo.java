package com.sofka.products.service.interfaces;

import com.sofka.products.domain.Categoria;
import com.sofka.products.domain.Subcategoria;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Interface para el servicio de un catalogo
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
public interface ICatalogo {

    /**
     * Devuelve una lista de Contactos con todos categorias del sistema
     *
     * @return
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    public Iterable<Categoria> getList();

    /**
     * Crea una subcategoria en el sistema
     *
     * @param subcategoria Objeto de la subcategoria a crear
     * @return Objeto del subcategoria creado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */

    public Subcategoria createSubCategoria(Subcategoria subcategoria);

    /**
     * Crea una categoria en el sistema
     *
     * @param categoria Objeto de la categoria a crear
     * @return Objeto de la categoria creado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    public Categoria createCategoria(Categoria categoria);

    /**
     * Devuelve una lista de Categorias con todas las categorias del sistema ordenados por el campo indicado
     * (nombre o fecha) ya sea ascendente o descendente
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de categorias
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    public List<Categoria> getList(String field, Sort.Direction order);

    /**
     * Borra una categoria del sistema basado en su identificador
     *
     * @param id Identificación de la categoria a borrar
     * @return Objeto de la categoria borrado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    Categoria deleteCategoria(Integer id);

    /**
     * Borra una subcategoria del sistema basado en su identificador
     *
     * @param id Identificación de la subcategoria a borrar
     * @return Objeto de la subcategoria borrado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    Subcategoria deleteSubcategoria(Integer id);

    /**
     * Actualiza una tupla completa de una categoria
     *
     * @param id Identificador de la categoria a actualizar
     * @param categoria Objeto de la categoria a actualizar
     * @return Objeto de la categoria actualizado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    Categoria updateCategoria(Integer id, Categoria categoria);

    /**
     * Actualiza la tupla completa de una subcategoria en el sistema basado en su identificador
     *
     * @param id Identificador de la subcategoria a actualizar
     * @param subcategoria Objeto de la subcategoria a actualizar
     * @return Objeto de la subcategoria actualizado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    public Subcategoria updateSubCategoria(Integer id, Subcategoria subcategoria);
}