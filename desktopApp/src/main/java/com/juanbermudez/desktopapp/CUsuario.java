/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanbermudez.desktopapp;

import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author juanb
 */
public class CUsuario {
    private String username, password, email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void insetarUsuario(JTextField paramUser, JTextField paramPassword, JTextField paramEmail){
        
        setUsername(paramUser.getText());
        setPassword(paramPassword.getText());
        setEmail(paramEmail.getText());
        
        CConexion objectoConexion = new CConexion();
        
        String consulta = "insert into Users(username, password, email) values (?, ?, ?);";
        
        try{
            CallableStatement cs = objectoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getUsername());
            cs.setString(2, getPassword());
            cs.setString(3, getEmail());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el alumno, error: " + e.toString());
        }
        
    }
    public boolean Validaciones(JTextField usernameField, JTextField passwordField, JTextField emailField){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        boolean entrar = true;
        String regexEmail = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";

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
