/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;

/**
 *
 * @author Felipe Iz
 * @param <T>
 */
public class BaseDAO <T> {
    
    protected EntityManager em;
    protected Class<T> type;

    public BaseDAO(EntityManager em) {
        
        this.em = em;
    }
    
    public void save(T entity) {
        this.em.persist(entity);
    }
    
    public T find(Long id){
        return this.em.find(this.type, id);
    }
    
    public void update(T entity){
        this.em.merge(entity);
    }
    
    public boolean delete(Long id){
        T entity = this.find(id);
        if(entity != null){
            this.delete(entity);
            return true;
        }
        
        return false;
    }
    
    public void delete(T entity){
        this.em.remove(this.em.merge(entity));
    } 

}
