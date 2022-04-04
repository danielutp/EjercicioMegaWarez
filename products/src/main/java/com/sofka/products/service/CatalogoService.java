package com.sofka.products.service;

import com.sofka.products.domain.Categoria;
import com.sofka.products.domain.Subcategoria;
import com.sofka.products.repository.CategoriaRepository;
import com.sofka.products.repository.SubcategoriaRepository;
import com.sofka.products.service.interfaces.ICatalogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Clase tipo Servicio para el manejo de un catalogo
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
@Service
public class CatalogoService implements ICatalogo {

    /**
     * Repositorio de categoria
     */
    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Repositorio de subcategoria
     */
    @Autowired
    private SubcategoriaRepository subcategoriaRepository;

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
    public List<Categoria> getList() {
        return categoriaRepository.findAll();
    }

    /**
     * Crea una subcategoria en el sistema
     *
     * @param subcategoria Objeto de la subcategoria a crear
     * @return Objeto de la subcategoria creado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Subcategoria createSubCategoria(Subcategoria subcategoria) {
        subcategoria.setCreatedAt(Instant.now());
        return subcategoriaRepository.save(subcategoria);
    }

    /**
     * Crea una categoria en el sistema
     *
     * @param categoria Objeto del usuario a crear
     * @return Objeto del usuario creado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Categoria createCategoria(Categoria categoria) {
        categoria.setCreatedAt(Instant.now());
        return categoriaRepository.save(categoria);
    }

    /**
     * Devuelve una lista de categorias con todas las categorias del sistema ordenados por el campo indicado ya sea ascendente por fecha o por nombre
     * o descendete
     *
     * @param field campo por el cual ordenar
     * @param order método de ordenado ASC o DESC
     * @return Lista de categorias
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getList(String field, Sort.Direction order) {
        return categoriaRepository.findAll(Sort.by(order, field));
    }

    /**
     * Borra una categoria del sistema
     *
     * @param id Identificación de la categoria a borrar
     * @return Objeto de la categoria borrado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Categoria deleteCategoria(Integer id) {
        var categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            categoriaRepository.delete(categoria.get());
            return categoria.get();
        } else {
            return null;
        }
    }
    /**
     * Borra una subcategoria del sistema
     *
     * @param id Identificación de la subcategoria a borrar
     * @return Objeto de la subcategoria borrado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Subcategoria deleteSubcategoria(Integer id) {
        var subcategoria = subcategoriaRepository.findById(id);
        if (subcategoria.isPresent()) {
            subcategoriaRepository.delete(subcategoria.get());
            return subcategoria.get();
        } else {
            return null;
        }
    }

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
    @Override
    @Transactional
    public Categoria updateCategoria(Integer id, Categoria categoria) {
        categoria.setCreatedAt(Instant.now());
        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }

    /**
     * Actualiza la tupla completa de una subcategoria en el sistema
     *
     * @param id Identificador de la subcategoria a actualizar
     * @param subcategoria Objeto de la subcategoria a actualizar
     * @return Objeto de la subcategoria actualizado
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @Override
    @Transactional
    public Subcategoria updateSubCategoria(Integer id, Subcategoria subcategoria) {
        subcategoria.setId(id);
        subcategoria.setCreatedAt(Instant.now());
        subcategoriaRepository.save(subcategoria);
        return subcategoria;
    }
}