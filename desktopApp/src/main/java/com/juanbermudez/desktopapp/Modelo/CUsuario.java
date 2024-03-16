/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juanbermudez.desktopapp.Modelo;

import com.juanbermudez.desktopapp.Controlador.CConexion;
import com.juanbermudez.desktopapp.Controlador.Enlaces;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        
        String consulta = "insert into Users(username, password, email, tipo_usuario) values (?, ?, ?, 'normal');";
        
        try{
            CallableStatement cs = objectoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getUsername());
            cs.setString(2, getPassword());
            cs.setString(3, getEmail());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario");
            cs.close();
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el alumno, error: " + e.toString());
        }
        
    }
    public boolean buscarUsuario(JTextField paramUser, JTextField paramPassword){
        setUsername(paramUser.getText());
        setPassword(paramPassword.getText());
        CConexion objectoConexion = new CConexion();
        String consulta = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try{
            PreparedStatement pstmt = objectoConexion.estableceConexion().prepareStatement(consulta);
            pstmt.setString(1, getUsername());
            pstmt.setString(2, getPassword());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                String tipoUsuario = rs.getString("tipo_usuario");
                if(tipoUsuario.equals("admin")){
                    Enlaces enl = new Enlaces();
                    enl.fromIn2Admin();
                } else {
                    Enlaces enl = new Enlaces();
                    enl.fromIn2Normal();
                }
            }
            rs.close();
            pstmt.close();
            return true;
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el alumno, error: " + e.toString());
            return false;
        }
    }
    public boolean validaciones(JTextField usernameField, JTextField passwordField, JTextField emailField){
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
