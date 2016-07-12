/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity_user;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Kristian Nielsen
 */
@Entity
public class EAdmin extends GeneralUser implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    
    
    public EAdmin(){
        this.setIsAdmin(true);
    }
    
}
