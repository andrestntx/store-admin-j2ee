/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import vo.UserVO;

/**
 *
 * @author Felipe Iz
 */
public class UserService {
    

        public UserVO getByLogin(String login, String password) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("storeAdminsPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction trans = em.getTransaction();
            UserDAO userDAO = DAOFactory.getUserDAO(em);
            User user = null;
            try {
                trans.begin();
                user = userDAO.getUserLogin(login, password);
                trans.commit();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                em.clear();
                em.close();                
            }
            if(user == null){
                return null;
            }
            return user.toVO();
        }
        
        public UserVO newUser(UserVO userVO){ 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("storeAdminsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        UserDAO studentDAO = DAOFactory.getUserDAO(em);        
        User user = null;
        user = userVO.toEntity();
        try {
            trans.begin();
            studentDAO.save(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();            
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }  
        
        return user.toVO();
    }
    
    public boolean newUsers(Collection<UserVO> users){ 
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("storeAdminsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        boolean flag = true;
        
        try {
            trans.begin();
            for (Iterator iterator = users.iterator(); iterator.hasNext();) {
                UserVO user = (UserVO)iterator.next();
                userDAO.save(user.toEntity());
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            flag = false;
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }  
        
        return flag;
    }
    
    public UserVO findUser(Long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("storeAdminsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        User user = null;
        
        try {
            trans.begin();
            user = userDAO.find(id);
            trans.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }
        
        return user.toVO();
    }
    
    public boolean updateUser(UserVO user){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("storeAdminsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        boolean flag = true;
        
        try {
            trans.begin();
            userDAO.update(user.toEntity());
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            flag = false;
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }   
        
        return flag;
    }
    
    public boolean deleteUser(Long id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("storeAdminsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        boolean flag = true;
        
        try {
            trans.begin();
            flag = userDAO.delete(id);
            trans.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }  
        
        return flag;
    }
    
    public List<UserVO> allUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("storeAdminsPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = em.getTransaction();
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        List<User> users = null;
        List<UserVO> usersVO = new ArrayList<UserVO>();
        
        try {
            trans.begin();
            users = userDAO.getAll();
            trans.commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            em.clear();
            em.close();
        }
        
        for (User user : users) {
            usersVO.add(user.toVO());
        }
        
        return usersVO;
    }
    
}
