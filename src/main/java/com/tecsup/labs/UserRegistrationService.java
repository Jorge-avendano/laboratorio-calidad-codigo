package com.tecsup.labs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servicio de registro de usuarios refactorizado.
 * Se corrigieron vulnerabilidades, duplicidad y malas prácticas.
 */
public final class UserRegistrationService {

    /** Logger para registrar los eventos del servicio. */
    private static final Logger LOGGER =
            Logger.getLogger(UserRegistrationService.class.getName());

    /** Longitud mínima requerida para la contraseña. */
    private static final int MIN_PASS_LENGTH = 8;

    /** Almacena el mensaje del último error generado. */
    private String lastErrorMessage = "";

    /** Lista interna que simula una base de datos de usuarios. */
    private final List<String> users = new ArrayList<>();

    /**
     * Constructor por defecto del servicio de registro.
     */
    public UserRegistrationService() {
        LOGGER.info("Servicio de registro inicializado.");
    }

    /**
     * Intenta registrar un nuevo usuario en el sistema.
     *
     * @param username el nombre de usuario a registrar
     * @param password la contraseña del usuario
     * @param email el correo electrónico del usuario
     * @return true si se registra exitosamente, false si falla
     */
    public boolean registerUser(final String username, final String password,
                                final String email) {
        if (username == null || username.trim().isEmpty()) {
            this.lastErrorMessage = "El usuario es inválido.";
            return false;
        }

        if (password == null || password.length() < MIN_PASS_LENGTH) {
            this.lastErrorMessage = "La contraseña es muy corta.";
            return false;
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            this.lastErrorMessage = "El correo es inválido.";
            return false;
        }

        try {
            this.saveUser(username, password, email);
        } catch (IllegalArgumentException e) {
            this.lastErrorMessage = "Error: " + e.getMessage();
            LOGGER.log(Level.WARNING, "Fallo registro: {0}", e.getMessage());
            return false;
        }

        LOGGER.log(Level.INFO, "Usuario registrado: {0}", username);
        return true;
    }

    /**
     * Guarda el usuario internamente simulando la persistencia.
     *
     * @param username el nombre de usuario
     * @param password la contraseña
     * @param email el correo electrónico
     * @throws IllegalArgumentException si el nombre de usuario está prohibido
     */
    private void saveUser(final String username, final String password,
                          final String email) {
        if ("error".equalsIgnoreCase(username)) {
            throw new IllegalArgumentException("Usuario no permitido.");
        }
        this.users.add(username);
    }

    /**
     * Devuelve el último mensaje de error registrado.
     *
     * @return una cadena con el mensaje de error
     */
    public String getLastErrorMessage() {
        return this.lastErrorMessage;
    }

    /**
     * Calcula la longitud de una cadena de texto de forma segura.
     *
     * @param text el texto a evaluar
     * @return la cantidad de caracteres o -1 si el texto es nulo
     */
    public int calculateStringLength(final String text) {
        if (text == null) {
            return -1;
        }
        return text.length();
    }
}
