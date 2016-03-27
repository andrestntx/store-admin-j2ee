/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author andrestntx
 */
public class ProductDAO extends BaseDAO<Product>{     

    public ProductDAO(EntityManager em) {
        super(em, Product.class);
    }

    @Override
    public void save(Product entity) {
        this.em.merge(entity);
    }
    
    public List<Product> search(String value){
        Query jpql = this.em.createQuery("select p from Product p where p.name LIKE :value OR p.description LIKE :value");
        jpql.setParameter("value", "%" + value + "%");
        
        return (List<Product>)jpql.getResultList();
    }
    
    
}
