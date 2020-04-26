/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * @author avilo
 */
@Named(value = "LoginController")
@ViewScoped
public class LoginController implements Serializable{
    
    //Atributos que necesitamos
    private Usuario usu;
        
    //Facades para atacar a base de datos
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
            
    @PostConstruct
    public void inicio(){
        usu = new Usuario();
    }
    
    /**
     * Funcion
     */
    public String verificarUsuario(){
        System.out.println("Entro en funcion verificarUsuario()");
        System.out.println("Usuario: " + usu.getUser() + " , Pass: " + usu.getPassword());
        
        if(usuarioEJB.verificarUsuario(usu) != null){
            return "private/principal.xhtml?faces-redirect=true";
        }
        return "permisosinsuficientes.xhtml?faces-redirect=true";
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }
    
    
}
