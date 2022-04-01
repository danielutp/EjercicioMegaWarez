package com.sofka.products.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

/**
 * Entidad de la session
 *
 * @version 1.0.0 2022-03-31
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "session")
public class Session implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ses_id", nullable = false)
    private Integer id;

    /**
     * Punto de enlace con la entidad Usuario (un usuario puede tener muchas sessiones iniciadas)
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Usuario.class, optional = false)
    @JoinColumn(name = "ses_usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    /**
     * Token para usar la session de usuario
     */
    @Column(name = "ses_token", nullable = false, length = 32)
    private String tokenUser;

    /**
     * Fecha de creacion de la session
     */
    @Column(name = "ses_created_at")
    private Instant CreatedAt;
}