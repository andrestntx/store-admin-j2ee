/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import service.ServiceFactory;
import service.UserService;
import vo.UserVO;

/**
 *
 * @author andrestntx
 */
public class UserFacade extends BaseFacade {
    
    public List<UserVO> allUsers(){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        UserService service = ServiceFactory.getUserService();
        List<UserVO> users = new ArrayList<>();
        
        try {
            trans.begin();
            users = service.allUsers(em);
            System.out.println(users.size());
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return users;
    }
    
    public UserVO newUser(UserVO userVO){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        UserService service = ServiceFactory.getUserService();
        
        try {
            trans.begin();
            userVO = service.newUser(userVO, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return userVO;
    }
    
    public UserVO updateUser(UserVO userVO){
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        UserService service = ServiceFactory.getUserService();
        
        try {
            trans.begin();
            userVO = service.updateUser(userVO, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return userVO;
    }

    public UserVO getUser(Long id) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        UserService service = ServiceFactory.getUserService();
        UserVO userVO = null;
        
        try {
            trans.begin();
            userVO = service.getUser(id, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return userVO;
    }

    public boolean deleteUser(Long id) {
        EntityManager em = this.getNewEntityManager();
        EntityTransaction trans = this.getEntityTransaction(em);
        UserService service = ServiceFactory.getUserService();
        boolean flag = true;
        
        try {
            trans.begin();
            service.deleteUser(id, em);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
            flag = false;
        } finally {
            this.closeAndClearEntityManager(em);
        }  

        return flag;
    }
}
