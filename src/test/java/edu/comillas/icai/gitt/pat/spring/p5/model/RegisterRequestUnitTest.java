package edu.comillas.icai.gitt.pat.spring.p5.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * TODO#7
 * Añade 2 tests unitarios adicionales que validen diferentes casos
 * (no variaciones del mismo caso) de registro con datos inválidos
 */

class RegisterRequestUnitTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
 /* los test unitarios van a probar que funcionan pequeñas partes del codigo correctamente no entero*/
    @Test
    public void testValidRequest() {
        // Given ...
        RegisterRequest registro = new RegisterRequest(
                "Nombre", "nombre@email.com",
                Role.USER, "aaaaaaA1");
        // When ...
        Set<ConstraintViolation<RegisterRequest>> violations =
                validator.validate(registro);
        // Then ...
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testEmailEmpty() {
        // Given
        String name = "Lorena";
        String email = "";  /* lo dejo vacío para ver como actua, ya que antes ya especificamos las condiciones de email*/
        String password = "Password17";
        Role role = Role.USER;

        // When
        RegisterRequest request = new RegisterRequest(name, email, role, password); /* hacemos el hipotetico caso de que nos estamos dando de alta*/

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty()); /* Es decir tiene que tener violaciones!! porque estamos haciendo una bad request*/
    }


    @Test
    public void testShortPassword() {
        // Given
        String name = "Mario";
        String email = "mario@comillas.com";
        String password = "1"; /* muy corta, en la clase dada se pedía un mínimo de 8 y más requisitos*/
        Role role = Role.USER;

        RegisterRequest request = new RegisterRequest(name, email, role, password);

        // When: validamos el objeto
        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        // Then: esperamos que haya errores de validación
        assertFalse(violations.isEmpty()); /* Es decir tiene que tener violaciones!!*/
    }





}