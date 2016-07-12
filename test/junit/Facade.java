/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit;

import entity_user.EAdmin;
import entity_user.EUser;
import facade.EAdminFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kristian Nielsen
 */
public class Facade {
    
    private final String eAdminEmail = "test@admin.com";
    private final String eAdminPassword = "plaintext";
    private final String eAdminUsername = "adminusername";
    
    
    public Facade() {
    }
    
    @Test
    public void eAdminFacadeTest(){
        String updatedEmail = "updated@email.com";
        
        System.out.println(EUser.class.getSimpleName());
        
        EAdmin eAdmin = new EAdmin();
        eAdmin.setEmail(eAdminEmail);
        eAdmin.setPassword(eAdminPassword);
        eAdmin.setUsername(eAdminUsername);
        assertNotNull(EAdminFacade.addEAdmin(eAdmin));
        assertNotNull(EAdminFacade.getEAdmins());
        
        EAdmin eAdminBeforeUpdate = eAdmin;
        eAdmin.setEmail(updatedEmail);
        eAdmin = EAdminFacade.updateAdmin(eAdmin);
        assertEquals(eAdmin.getEmail(), updatedEmail);
    }
    
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
        assertEquals(1, EAdminFacade.deleteEAdmin(eAdminUsername));
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
