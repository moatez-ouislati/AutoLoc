package tn.esprit.autoloc.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import tn.esprit.autoloc.exception.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {
        return construire(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(IllegalArgumentException ex) {
        return construire(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String, Object>> handleConflict(IllegalStateException ex) {
        return construire(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleUnreadable(HttpMessageNotReadableException ex) {
        log.warn("Corps de requête illisible : {}", ex.getMostSpecificCause().getMessage());
        return construire(HttpStatus.BAD_REQUEST, "Corps de requête invalide");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleIntegrity(DataIntegrityViolationException ex) {
        log.warn("Violation d'intégrité : {}", ex.getMostSpecificCause().getMessage());
        return construire(HttpStatus.CONFLICT, "Violation de contrainte d'intégrité");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> erreurs = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> erreurs.put(e.getField(), e.getDefaultMessage()));
        return construireAvecErreurs(erreurs);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, Object>> handleParamValidation(HandlerMethodValidationException ex) {
        Map<String, String> erreurs = new LinkedHashMap<>();
        ex.getParameterValidationResults().forEach(r -> {
            String nom = r.getMethodParameter().getParameterName();
            String champ = nom != null ? nom : "parametre";
            r.getResolvableErrors().forEach(e -> erreurs.put(champ, e.getDefaultMessage()));
        });
        return construireAvecErreurs(erreurs);
    }

    private ResponseEntity<Map<String, Object>> construire(HttpStatus status, String message) {
        Map<String, Object> corps = new LinkedHashMap<>();
        corps.put("statut", status.value());
        corps.put("message", message);
        corps.put("horodatage", LocalDateTime.now(ZoneId.systemDefault()));
        return ResponseEntity.status(status).body(corps);
    }

    private ResponseEntity<Map<String, Object>> construireAvecErreurs(Map<String, String> erreurs) {
        Map<String, Object> corps = new LinkedHashMap<>();
        corps.put("statut", HttpStatus.BAD_REQUEST.value());
        corps.put("message", "Données invalides");
        corps.put("erreurs", erreurs);
        corps.put("horodatage", LocalDateTime.now(ZoneId.systemDefault()));
        return ResponseEntity.badRequest().body(corps);
    }
}