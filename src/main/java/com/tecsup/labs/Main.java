package com.tecsup.labs;

public class Main {

    public static void main(String[] args) {
        UserRegistrationService service = new UserRegistrationService();

        service.registerUser("juan", "123", "juan@correo.com");
        System.out.println(service.getLastErrorMessage());

        service.registerUser(null, "12345678", "correo-sin-arroba");
        System.out.println(service.getLastErrorMessage());

        service.registerUser("error", "12345678", "error@correo.com");
        System.out.println(service.getLastErrorMessage());
    }
}
