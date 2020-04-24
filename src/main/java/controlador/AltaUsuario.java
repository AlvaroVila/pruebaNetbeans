/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CategoriaFacadeLocal;
import EJB.RolFacadeLocal;
import EJB.UsuarioFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Persona;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author avilo
 */
@Named(value = "AltaUsuario")
@ViewScoped
public class AltaUsuario implements Serializable{
    
    private Usuario usu;
    private Persona per;
    private Rol rol;
    private List<Rol> listaDeRoles;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    @EJB
    private RolFacadeLocal rolEJB;
        
    @PostConstruct
    public void inicio(){
        usu = new Usuario();
        per = new Persona();
        rol = new Rol();
        try{
            listaDeRoles = rolEJB.findAll();
            Rol r = new Rol();
            r.setTipoUsuario("Seleccione Rol");
            listaDeRoles.add(0, r);
        } catch (Exception e){
            System.out.println("Error al listar Roles: " + e.getMessage());
        }
    }
    
    private void valida(){
        List<Usuario> valido = usuarioEJB.verificarUsuario(usu);
        for (Usuario u : valido){
            System.out.println(u.toString());
        }
    }

    public void insertarUsuario(){
        
        //Set de Rol
        try{
            System.out.println("Rol :" + rol.getIdRol());
            for (Rol r : listaDeRoles){
                if(r.getIdRol() == rol.getIdRol()){
                    rol=r;
                    break;
                }
            }
            usu.setRol(rol);
        } catch (Exception e){
            System.out.println("El rol no existe en la lista: " + e.getMessage());
        }
        
        //Set de Persona
        try{
            usu.setPersona(per);
        } catch (Exception e){
            System.out.println("Error al asignar Persona: " + e.getMessage());
        }
        
        //insertar en BD
        try {
            System.out.println("Ha entrado en insertarUsuario(): " + usu.getUser());
            if (usuarioEJB.comprobarDuplicado(usu)) {
                System.out.println("El usuario ya existe!");
            } else {
                System.out.println("El usuario no se ha encontrado en base de datos");
                usuarioEJB.create(usu);
            }
                       
        } catch (Exception e){
            System.err.println("Error al insertar nuevo usuario: " + e.getMessage());
        }
        valida();
    }
    
    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }
    
     public Persona getPer() {
        return per;
    }

    public void setPer(Persona per) {
        this.per = per;
    }

    public List<Rol> getListaDeRoles() {
        return listaDeRoles;
    }

    public void setListaDeRoles(List<Rol> listaDeRoles) {
        this.listaDeRoles = listaDeRoles;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
}
