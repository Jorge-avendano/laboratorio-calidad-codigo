package com.tecsup.labs;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserRegistrationService {

    private static final Logger LOGGER = Logger.getLogger(UserRegistrationService.class.getName());
    private static final int MIN_PASSWORD_LENGTH = 8;

    // Encapsulamiento correcto
    private String lastErrorMessage = "";
    // Uso de genéricos para seguridad de tipos
    private List<String> users = new ArrayList<>();

    public UserRegistrationService() {
        LOGGER.info("Servicio de registro inicializado correctamente.");
    }

    public boolean registerUser(String username, String password, String email) {
        // Prevención de NullPointerException
        if (username == null || username.trim().isEmpty()) {
            lastErrorMessage = "El nombre de usuario es inválido o está vacío.";
            return false;
        }

        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            lastErrorMessage = "La contraseña es nula o muy corta.";
            return false;
        }

        // Validación estricta de formato de correo mediante Regex
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            lastErrorMessage = "El correo electrónico no tiene un formato válido.";
            return false;
        }

        try {
            saveUser(username, password, email);
        } catch (IllegalArgumentException e) {
            lastErrorMessage = "Error de negocio: " + e.getMessage();
            LOGGER.log(Level.WARNING, "Intento de registro fallido: {0}", e.getMessage());
            return false;
        }

        LOGGER.log(Level.INFO, "Usuario registrado exitosamente: {0}", username);
        return true;
    }

    private void saveUser(String username, String password, String email) {
        if ("error".equalsIgnoreCase(username)) {
            // Se lanza una excepción específica en lugar de una genérica
            throw new IllegalArgumentException("Nombre de usuario reservado o no permitido.");
        }
        users.add(username);
    }

    // Getter para acceder al mensaje de error de forma segura
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    /**
     * Calcula la longitud de una cadena de forma eficiente.
     * Reemplaza al método con nombre poco claro 'x'.
     */
    public int calculateStringLength(String text) {
        if (text == null) {
            return -1;
        }
        return text.length();
    }
}