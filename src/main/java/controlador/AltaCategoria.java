/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.CategoriaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Categoria;

/**
 *
 * @author avilo
 */
@Named(value = "AltaControlador")
@ViewScoped
public class AltaCategoria implements Serializable{
    
    private List<Categoria> listaDeCategorias;
    private Categoria cat;
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
        
    @PostConstruct
    public void inicio(){
        cat = new Categoria();
        try{
            listaDeCategorias = categoriaEJB.findAll();
        } catch (Exception e){
            System.out.println("wad");
        }
    }
    
    public void eliminarCategoria(){
        System.out.println("Ha entrado en eliminar(): " + cat.getIdCategoria()); 
        try{
            for (Categoria c : listaDeCategorias){
                if(c.getIdCategoria() == cat.getIdCategoria()){
                    cat=c;
                    System.out.println("Ha entrado en eliminar(): " + cat.getNombre());
                    break;
                }
            }
            categoriaEJB.remove(cat);
        } catch (Exception e){
            System.out.println("wad" + e.getMessage());
        }
    }
    
    public void insertarCategoria(){
        try {
            categoriaEJB.create(cat);
            System.out.println("Ha entrado en insertar(): " + cat.getNombre());
        } catch (Exception e){
            System.err.println("Error al insertar la categoria: " + e.getMessage());
        }
    }
    
    public void modificarCategoria(){
        try {
            categoriaEJB.edit(cat);
            System.out.println("Ha entrado en modificar(): " + cat.getNombre());
        } catch (Exception e){
            System.err.println("Error al modificar la categoria: " + e.getMessage());
        }
    }
    
    public Categoria getCat(){
        return cat;
    }
    
    public void setCat(Categoria c){
        cat = c;
    }
    
    public List<Categoria> getListaDeCategorias(){
        return listaDeCategorias;
    }
    
    public void setCat(List<Categoria> lc){
        listaDeCategorias = lc;
    }
}
