/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Usuario;

/**
 *
 * @author avilo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_prueba_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public boolean comprobarDuplicado(Usuario usu){
        List<Usuario> listaUsuarios = this.findAll();
        boolean existe = false;
        
        for(Usuario u : listaUsuarios){
            if(u.getUser().equals(usu.getUser())){
                existe = true;
                break;
            }
        }
        return existe;
    }
    
    
}
