/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Usuario;

/**
 *
 * @author avilo
 */
@Named(value = "PlantillaController")
@ViewScoped
public class PlantillaController implements Serializable{
            
    @PostConstruct
    public void inicio(){
    }
    
    /**
     * Funcion
     */
    public void verificarYMostrar(){
        System.out.println("Entro en funcion verificarYMostrar()");
        Usuario usuarioActual = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioActual");
        
        //Saca la ruta absoluta actual, como yo llamo desde index.xhtml, la ruta es desde: /prueba (desde el directorio raiz)(mi proyecto se llama "prueba")
        String url = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        
        try{
            if (usuarioActual == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/permisosinsuficientes.xhtml?faces-redirect=true");
                //Parece que hay que redirigir desde ruta absoluta:           /prueba/permisosinsuficientes.xhtml?faces-redirect=true
                // Yo tengo el permisosinsuficientes.xhtml en el directorio raiz, donde esta index.xhtml, estan los dos juntos
                // Tambien vale poner redirect("../permisosinsuficientes.xhtml?faces-redirect=true"); porque quien va hacer la redireccion
                // es un elemento que esta en un XHTML dentro de la carpeta "private", por lo que para acceder a la carpeta superior que
                // es donde esta "permisosinsuficinetes" se puede usar ..
            }
        } catch (Exception e){
             System.out.println("Error al redireccionar: " + e.getMessage());
        }
        
        //Simulo cierre de sesion quitando el usuario de las variables globales
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioActual");
    }

}
