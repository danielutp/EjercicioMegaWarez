package com.sofka.products.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad del Contacto
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id", nullable = false)
    private Integer id;

    /**
     * Nombre del usuario
     */
    @Column(name = "usu_username", nullable = false, length = 80)
    private String username;

    /**
     * Contraseña del usuario
     */
    @Column(name = "usu_password", nullable = false, length = 32)
    private String password;

    /**
     * Fecha de creacion del usuario
     */
    @Column(name = "usu_created_at", nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * Fecha de actualizacion del usuario
     */
    @Column(name = "usu_updated_at")
    private Instant updatedAt;

    /**
     * Punto de enlace entre la entidad del Usuario y descargas (un usuario puede tener muchas secciones)
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            targetEntity = Descarga.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "usuario"
    )
    @JsonManagedReference(value = "user-download")
    private List<Descarga> descargas = new ArrayList<>();

    /**
     * Punto de enlace entre la entidad del Usuario y seccion (un usuario puede tener muchas secciones)
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            targetEntity = Session.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "usuario"
    )
    @JsonManagedReference(value = "user-session")
    private List<Session> sessions = new ArrayList<>();
}