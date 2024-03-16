package com.juanbermudez.desktopapp.Controlador;

import com.juanbermudez.desktopapp.Vista.AdminPanel2;
import com.juanbermudez.desktopapp.Vista.Sign_in;
import com.juanbermudez.desktopapp.Vista.Sign_up;

/**
 * @author juanb
 */
public class Enlaces {
    public void fromUp2In(){
        Sign_in objSign_in = new Sign_in();
        objSign_in.setVisible(true);
    }
    public void fromIn2Up(){
        Sign_up objSign_in = new Sign_up();
        objSign_in.setVisible(true);
    }
    public void fromIn2Admin(){
        AdminPanel2 objSign_in = new AdminPanel2();
        objSign_in.setVisible(true);
    }
    public void fromIn2Normal(){
        
    }
}
