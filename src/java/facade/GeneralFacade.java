/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity_user.EAdmin;
import java.util.List;
import javax.persistence.EntityManager;
import jdbc.DBConnector;

/**
 *
 * @author Kristian Nielsen
 */
public class GeneralFacade {
    
    
    
    
    protected static Object addObject(Object object, String primaryKey, Class<?> cls) {
        
        EntityManager em = DBConnector.getEntityManager();
        Object objectResult = null;
        try{
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            objectResult = em.find(cls, primaryKey);
        }catch(Exception exception){
            System.out.println("Something went wrong adding EAdmin (");
            System.out.println(object.toString());
            System.out.println(") to database");
            exception.printStackTrace();
        }finally{
            em.close();
        }
        
        return objectResult;
    }
    
    protected static List<?> getObjects(Class<?> cls){
        EntityManager em = DBConnector.getEntityManager();
        List<?> objects = null;
        try{
            
            objects = em.createQuery("SELECT e from "
                    + cls.getSimpleName() +
                    " e").getResultList();
            
        }finally{
            em.close();
        }
        return objects;
    }
    
    protected static Object updateObject(Object object){
        EntityManager em = DBConnector.getEntityManager();
        Object objectResult = null;
        try{
            em.getTransaction().begin();
            objectResult = em.merge(object);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return objectResult;
    }
    
    protected static int deleteObject(String primaryKey){
        EntityManager em = DBConnector.getEntityManager();
        int rowsAltered = 0;
        try{
            em.getTransaction().begin();
            rowsAltered = 
                    em.createQuery("delete from EAdmin eAdmin where eAdmin.username = :username")
                    .setParameter("username", primaryKey)
                    .executeUpdate();
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return rowsAltered;
        
    }
    
//    protected static boolean deleteObject(Class<?> cls, String primaryKey){
//        EntityManager em = DBConnector.getEntityManager();
//        boolean deleted = false;
//        try{
//            em.getTransaction().begin();
//            Object deletee = em.find(cls, primaryKey);
//            em.remove(deletee);
//            em.getTransaction().commit();
//            if(em.find(cls, primaryKey) == null) deleted = true;
//        }finally{
//            em.close();
//        }
//        return deleted;
//    }
    
    private String createDeleteQueryString(String entityName, String parameterName, String entityReference){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("delete from ")
                .append(entityName)
                .append(" ")
                .append(entityReference)
                .append(" ")
                .append("where ")
                .append(entityReference)
                .append(".")
                .append(parameterName)
                .append(" = :")
                .append(parameterName);
                
        return stringBuilder.toString();
        
//        String base = "delete from EAdmin eAdmin where eAdmin.username = :username";
    }
    
}
