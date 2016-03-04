/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import service.UserService;
import vo.UserVO;

/**
 *
 * @author andrestntx
 */
public class UserFacade {
    private UserService service;

    public UserFacade(UserService service) {
        this.service = service;
    }
    
    public void createUser(UserVO vo){
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("TestPU");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            service.persist(vo, entityManager);
            entityTransaction.commit();
        } catch (Exception e) {
            if(entityManager != null && entityTransaction != null){
                entityTransaction.rollback();
            }
        } finally {
            if(entityManager != null){
                entityManager.clear();
                entityManager.close();
            }
        }
    }
}
