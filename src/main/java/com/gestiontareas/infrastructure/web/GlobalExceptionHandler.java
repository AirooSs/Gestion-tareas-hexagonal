package com.gestiontareas.infrastructure.web;

import com.gestiontareas.domain.exception.RecursoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manejador global de excepciones.
 * Intercepta las excepciones y devuelve respuestas JSON limpias.
 * Las claves del response se definen como constantes para evitar strings duplicados.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String ERRORES = "errores";

    /** Maneja los recursos no encontrados — devuelve 404 */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now().toString());
        body.put(STATUS, 404);
        body.put(ERROR, "No encontrado");
        body.put(MENSAJE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    /** Maneja argumentos inválidos — devuelve 400 */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now().toString());
        body.put(STATUS, 400);
        body.put(ERROR, "Petición incorrecta");
        body.put(MENSAJE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    /** Maneja los errores de validación de @Valid — devuelve 400 con detalle por campo */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now().toString());
        body.put(STATUS, 400);
        body.put(ERROR, "Error de validación");

        Map<String, String> errores = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errores.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        body.put(ERRORES, errores);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}