package com.juanbermudez.desktopapp.Controlador;
import com.juanbermudez.desktopapp.Modelo.Usuario;
import com.juanbermudez.desktopapp.Vista.AdminPanel;
import com.juanbermudez.desktopapp.Vista.Sign_in;
import com.juanbermudez.desktopapp.Vista.Sign_up;
/**
 * @author juanb
 */
public class Enlace { //Conexiones entre ventanas
    public void fromUp2In(){
        Sign_in objSign_in = new Sign_in();
        objSign_in.setVisible(true);
    }
    public void fromIn2Up(){
        Sign_up objSign_in = new Sign_up();
        objSign_in.setVisible(true);
    }
    public void fromIn2Admin(Usuario userObj){
        AdminPanel objSign_in = new AdminPanel(userObj);
        objSign_in.setVisible(true);
    }
    public void fromIn2Normal(){
        
    }
}
