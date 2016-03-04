/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import javax.persistence.EntityManager;

/**
 *
 * @author andrestntx
 */
public class CategoryDAO {
    public static void persist(Category entity, EntityManager entityManager){
        entityManager.persist(entity);
    }
}
