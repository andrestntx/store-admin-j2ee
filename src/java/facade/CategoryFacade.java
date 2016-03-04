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
import service.CategoryService;
import vo.CategoryVO;

/**
 *
 * @author andrestntx
 */
public class CategoryFacade {
    private CategoryService service;

    public CategoryFacade(CategoryService service) {
        this.service = service;
    }
    
    public void createCategory(CategoryVO vo){
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
