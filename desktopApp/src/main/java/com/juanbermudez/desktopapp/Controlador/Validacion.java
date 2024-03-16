package com.juanbermudez.desktopapp.Controlador;

import javax.swing.JTextField;

/**
 * @author juanb
 */
public class Validacion {
    
    public boolean validacionesUsuario(JTextField usernameField, JTextField passwordField, JTextField emailField){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        boolean entrar = true;
        String regexEmail = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,5})+$";

        // Verificar longitud del nombre de usuario
        if (username.length() < 6) {
            usernameField.setText("");
            entrar = false;
        }

        // Verificar longitud de la contraseña
        if (password.length() < 8) {
            entrar = false;
            passwordField.setText("");
        }

        // Verificar formato del correo electrónico
        if (!email.matches(regexEmail)) {
            emailField.setText("");
            entrar = false;
        }
        return entrar;
    }
    
}
