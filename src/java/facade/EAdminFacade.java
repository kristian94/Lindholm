/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity_user.EAdmin;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import jdbc.DBConnector;

/**
 *
 * @author Kristian Nielsen
 */
public class EAdminFacade extends GeneralFacade {
    
     
    public static EAdmin addEAdmin(EAdmin eAdmin) {
        
        return (EAdmin) addObject(eAdmin, eAdmin.getUsername(), EAdmin.class);
    }
    
    public static List<EAdmin> getEAdmins(){

        return (List<EAdmin>) getObjects(EAdmin.class);
    }
    
    public static EAdmin updateAdmin(EAdmin eAdmin){

        return (EAdmin) updateObject(eAdmin);
    }
    
    public static int deleteEAdmin(String eAdminUsername){
        EntityManager em = DBConnector.getEntityManager();
        int rowsAltered = 0;
        try{
            em.getTransaction().begin();
            rowsAltered = 
                    em.createQuery("delete from EAdmin eAdmin where eAdmin.username = :username")
                    .setParameter("username", eAdminUsername)
                    .executeUpdate();
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return rowsAltered;
        
    }
}