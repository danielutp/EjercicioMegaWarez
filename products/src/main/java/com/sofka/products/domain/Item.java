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
@Table(name = "item")
public class Item implements Serializable {
    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itm_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace con la entidad Subcategoria (una Subcategoria puede tener muchos items)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Subcategoria.class, optional = false)
    @JoinColumn(name = "itm_subcategoria_id", nullable = false)
    @JsonBackReference(value = "subcategory-item")
    private Subcategoria subcategoria;

    /**
     * Nombre del item
     */
    @Column(name = "itm_nombre", nullable = false, length = 80)
    private String Nombre;

    /**
     * Fecha de creacion del item
     */
    @Column(name = "itm_created_at", nullable = false)
    private Instant CreatedAt;

    /**
     * Punto de enlace entre la entidad de la item y descarga (un item puede tener muchas descargas)
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Descarga.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "item"
    )
    @JsonManagedReference(value = "item-download")
    private List<Descarga> descargas = new ArrayList<>();
}