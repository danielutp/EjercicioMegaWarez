package com.sofka.products.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad del Categoria
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id", nullable = false)
    private Integer id;

    /**
     * Nombre de la categoria
     */
    @Column(name = "cat_nombre", nullable = false, length = 80)
    private String nombre;

    /**
     * Fecha de la creacion de la categoria
     */
    @Column(name = "cat_created_at", nullable = false)
    private Instant createdAt;

    /**
     * Punto de enlace entre la entidad de la categoria y subcategoria (una categoria puede tener muchas subcategorias)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Subcategoria.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "categoria"
    )
    @JsonManagedReference(value = "category-subcategory")
    private List<Subcategoria> subcategorias = new ArrayList<>();
}