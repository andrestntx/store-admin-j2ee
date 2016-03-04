/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Product;
import javax.persistence.EntityManager;

/**
 *
 * @author andrestntx
 */
public class ProductDAO {
    public static void persist(Product entity, EntityManager entityManager){
        entityManager.persist(entity);
    }
}
