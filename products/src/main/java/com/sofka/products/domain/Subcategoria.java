package com.sofka.products.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad de la session
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "subcategoria")
public class Subcategoria implements Serializable {
    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scat_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace con la entidad Categoria (una categoria puede tener muchas subcategorias)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Categoria.class, optional = false)
    @JoinColumn(name = "scat_categoria_id")
    @JsonBackReference
    private Categoria categoria;

    /**
     * Punto de enlace entre la entidad de subcategoria y item (una subcategoria puede tener muchos items)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Item.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "subcategoria"
    )
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();


    /**
     * Nombre de la subcategoria
     */
    @Column(name = "scat_nombre", nullable = false, length = 80)
    private String Username;

    /**
     * Fecha de creacion de la subcategoria
     */
    @Column(name = "scat_created_at", nullable = false)
    private Instant CreatedAt;

}