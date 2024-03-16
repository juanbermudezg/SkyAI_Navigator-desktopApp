package com.juanbermudez.desktopapp.Controlador;

import com.juanbermudez.desktopapp.Modelo.Usuario;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @author juanb
 */
public class CRUDOperation {
    public boolean insetarUsuario(JTextField paramUser, JTextField paramPassword, JTextField paramEmail){
        Validacion validacionObj = new Validacion();
        boolean bandera = validacionObj.validacionesUsuario(paramUser, paramPassword, paramEmail);
        if (bandera){
            Usuario userObj = new Usuario();
            userObj.setUsername(paramUser.getText());
            userObj.setPassword(paramPassword.getText());
            userObj.setEmail(paramEmail.getText());
            Conexion objectoConexion = new Conexion();
            String consulta = "insert into Users(username, password, email, tipo_usuario) values (?, ?, ?, 'normal');";
            try{
                CallableStatement cs = objectoConexion.estableceConexion().prepareCall(consulta);
                cs.setString(1, userObj.getUsername());
                cs.setString(2, userObj.getPassword());
                cs.setString(3, userObj.getEmail());
                cs.execute();
                cs.close();
                JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario");
                return true;
            }
            catch (SQLIntegrityConstraintViolationException e){
                JOptionPane.showMessageDialog(null, "Nombre de usuario ya existe.");
                return false;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario, error: " + e.toString());
                return false;
            }
            finally {
                objectoConexion.cerrarConexion();
                
            }
         }
        else {
            JOptionPane.showMessageDialog(null, "Error, por favor verifique los datos guardados.");
            return false;
        }
    }
    public boolean buscarUsuario(JTextField paramUser, JTextField paramPassword){
        Usuario userObj = new Usuario();
        userObj.setUsername(paramUser.getText());
        userObj.setPassword(paramPassword.getText());
        Conexion objectoConexion = new Conexion();
        String consulta = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try{
            PreparedStatement pstmt = objectoConexion.estableceConexion().prepareStatement(consulta);
            pstmt.setString(1, userObj.getUsername());
            pstmt.setString(2, userObj.getPassword());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                String tipoUsuario = rs.getString("tipo_usuario");
                if(tipoUsuario.equals("admin")){
                    Enlace enl = new Enlace();
                    enl.fromIn2Admin(userObj);
                }
                else {
                    Enlace enl = new Enlace();
                    enl.fromIn2Normal();
                }
            } 
            rs.close();
            pstmt.close();
            objectoConexion.cerrarConexion();
            return true;
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
            objectoConexion.cerrarConexion();
            return false;
        }
    }
    public int consultarFilasTabla(){
        Conexion objectoConexion = new Conexion();
        String consulta = " select count(*) from Users";
        try{
            PreparedStatement pstmt = objectoConexion.estableceConexion().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            JOptionPane.showMessageDialog(null, rs);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.toString());
            objectoConexion.cerrarConexion();
        }
        return 1;
    }
    public DefaultTableModel mostrarUsuarios(){
        DefaultTableModel tUsuario = new DefaultTableModel();
        tUsuario.addColumn("ID");
        tUsuario.addColumn("Nombre");
        tUsuario.addColumn("Apellidos");
        tUsuario.addColumn("Usuario");
        tUsuario.addColumn("Contraseña");
        tUsuario.addColumn("Email");
        tUsuario.addColumn("Tipo de usuario");
        String [] datos = new String[7];
        Conexion objectoConexion = new Conexion();
        String consulta = "SELECT * FROM Users";
        try{
            PreparedStatement pstmt = objectoConexion.estableceConexion().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(9);
                tUsuario.addRow(datos);
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.toString());
            objectoConexion.cerrarConexion();
        }
        return tUsuario;
    }
}
