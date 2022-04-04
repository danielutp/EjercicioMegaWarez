package com.sofka.products.controller;

import com.sofka.products.domain.Categoria;
import com.sofka.products.domain.Subcategoria;
import com.sofka.products.service.CatalogoService;
import com.sofka.products.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Controlador para la libreta
 *
 * @version 1.0.0 2022-04-1
 * @author Daniel Gil <danistcruz@gmail.com>
 * @since 1.0.0
 */
@Slf4j
@RestController
public class CatalogoController {

    /**
     * Servicio para el manejo de la libreta
     */
    @Autowired
    private CatalogoService catalogoService;

    /**
     * Variable para el manejo de las respuestas de las API
     */
    private Response response = new Response();

    /**
     * Manejo del código HTTP que se responde en las API
     */
    private HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Atención a la dirección raíz, API del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/apiC/")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz, API del sistema y versión, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/apiC/v1/")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Index del sistema, responde con el listado de usuarios
     *
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/apiC/v1/index")
    public ResponseEntity<Response> index() {
        response.restart();
        try {
            response.data = catalogoService.getList();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea una nueva categoria en el sistema
     *
     * @param categoria Objeto categoria a crear
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/apiC/v1/categoria")
    public ResponseEntity<Response> createCategoria(@RequestBody Categoria categoria) {
        response.restart();
        try {
            log.info("Categoria a crear: {}", categoria);
            response.data = catalogoService.createCategoria(categoria);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Crea una nueva subcategoria en el sistema
     *
     * @param subcategoria Objeto subcategoria a crear
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @PostMapping(path = "/apiC/v1/subcategoria")
    public ResponseEntity<Response> createSubCategoria(@RequestBody Subcategoria subcategoria) {
        response.restart();
        try {
            log.info("SubCategoria a crear: {}", subcategoria);
            response.data = catalogoService.createSubCategoria(subcategoria);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Devuelve todos las categorias  ordenados por nombre o fecha de forma ascendente o descendente
     *
     * @param orderBy Nombre del campo por donde se desea ordenar la información
     * @param order Tipo de orden que debe tener la información ASC o DESC
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @GetMapping(path = "/apiC/v1/index/orderby/{orderBy}/{order}")
    public ResponseEntity<Response> indexOrderBy(
            @PathVariable(value="orderBy") String orderBy,
            @PathVariable(value="order") Sort.Direction order
    ) {
        response.restart();
        try {
            response.data = catalogoService.getList(orderBy, order);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza todos los campos de un categoria
     *
     * @param categoria Objeto categoria a actualizar
     * @param id Identificador de la categoria a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @PutMapping(path = "/apiC/v1/categoria/{id}")
    public ResponseEntity<Response> updateCategoria(
            @RequestBody Categoria categoria,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = catalogoService.updateCategoria(id, categoria);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Actualiza todos los campos de una subcategoria
     *
     * @param subcategoria Objeto subcategoria a actualizar
     * @param id Identificador de la subcategoria a actualizar
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @PutMapping(path = "/api/v1/subcategoria/{id}")
    public ResponseEntity<Response> updateSubCategoria(
            @RequestBody Subcategoria subcategoria,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = catalogoService.updateSubCategoria(id, subcategoria);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Borra una categoria del sistema
     *
     * @param id Identificador del categoria a borrar
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/apiC/v1/categoria/{id}")
    public ResponseEntity<Response> deleteCategoria(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = catalogoService.deleteCategoria(id);
            if (response.data == null) {
                response.message = "La categoria no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "La categoria fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Borra una subcategoria del sistema
     *
     * @param id Identificador del subcategoria a borrar
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    @DeleteMapping(path = "/apiC/v1/subcategoria/{id}")
    public ResponseEntity<Response> deleteSubcategoria(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = catalogoService.deleteSubcategoria(id);
            if (response.data == null) {
                response.message = "La subcategoria no existe";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "La subcategoria fue removido exitosamente";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para la redirección al controllador /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse para el manejo de la redirección
     * @return Objeto Response en formato JSON
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    private ResponseEntity<Response> getResponseHome(HttpServletResponse httpResponse) {
        response.restart();
        try {
            httpResponse.sendRedirect("/apiC/v1/index");
        } catch (IOException exception) {
            response.error = true;
            response.data = exception.getCause();
            response.message = exception.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
     *
     * @param exception Objeto DataAccessException
     *
     * @author Daniel Gil <danistcruz@gmail.com>
     * @since 1.0.0
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if(exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}