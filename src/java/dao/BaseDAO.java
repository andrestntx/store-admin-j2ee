/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Felipe Iz
 * @param <T>
 */
public class BaseDAO <T> {
    
    protected EntityManager em;
    private final Class<T> type;
    
    public BaseDAO(EntityManager em, Class<T> type) {
        this.em = em;
        this.type = type;
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class<T> getType() {
        return type;
    }

    public void save(T entity) {
        this.em.persist(entity);
    }
    
    public T find(Long id){
        return this.em.find(this.type, id);
    }
    
    public T update(T entity){
        return this.em.merge(entity);
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
    
    public List<T> getAll() {
        return this.getAll(50);
    }
    
    public List<T> getAll(int maxResults) {
        Query jpql = this.em.createQuery("select s from " + this.type.getSimpleName() + " s").setMaxResults(maxResults);  
        List<T> resultados = (List<T>)jpql.getResultList();
//Query jpql = this.em.createQuery("select s from " + this.type.getSimpleName() + " s").setMaxResults(maxResults);  
        return (List<T>)jpql.getResultList();
    }
    
    public List<T> getByAttribute(String attribute, String value){
        System.out.println("atribute " + attribute +" value " + value);
        Query jpql = this.em.createQuery("select s from " + this.type.getSimpleName() + " s where s.:attribute = :value");
        jpql.setParameter("attribute", attribute);
        jpql.setParameter("value", value);
        
        return (List<T>)jpql.getResultList();
    }
    
    public List<T> getLikeByAttribute(String attribute, String value){
        Query jpql = this.em.createQuery("select s from " + this.type.getSimpleName() + " s where s.:attribute.id LIKE % :value %");
        jpql.setParameter("attribute", attribute);
        jpql.setParameter("value", value);
        
        return (List<T>)jpql.getResultList();
    }
}