package com.tecsup.labs;

/**
 * Clase principal para la ejecución de pruebas manuales.
 */
public final class Main {

    /**
     * Constructor privado para evitar la instanciación de clase utilitaria.
     */
    private Main() {
        // Constructor vacío intencional
    }

    /**
     * Método de entrada principal.
     *
     * @param args argumentos enviados por línea de comandos
     */
    public static void main(final String[] args) {
        final UserRegistrationService service = new UserRegistrationService();

        service.registerUser("juan", "123", "juan@correo.com");
        System.out.println(service.getLastErrorMessage());

        service.registerUser(null, "12345678", "correo");
        System.out.println(service.getLastErrorMessage());

        service.registerUser("error", "12345678", "e@c.com");
        System.out.println(service.getLastErrorMessage());
    }
}
