/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import vo.UserVO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import service.ServiceFactory;

/**
 *
 * @author LOPEZ
 */
public class LoginFacade extends BaseFacade {

    public UserVO login(String name, String password) {
        
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        service.UserService service = ServiceFactory.getUserService();
        UserVO userVO = null;
        try {
            trans.begin();
            userVO = service.getByLogin(name, password, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return userVO; 
    }

}
