/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author andrestntx
 */
public class UserDAO extends BaseDAO<User>{     

   public UserDAO(EntityManager em) {
        super(em);
    }
   
   public User getUserLogin(String login, String password){
            Query jpql = this.em.createQuery("select s from User s where s.login = :login AND s.password = :password");
            jpql.setParameter("login", login);
            jpql.setParameter("password", password);
            List<User> users = (List<User>)jpql.getResultList();
            if(users.size() > 0){
                return users.get(0);
            }
            return null;        
    }
   
   public List<User> getAll() {
        Query jpql = this.em.createQuery("select u from User u").setMaxResults(50);        
        return (List<User>)jpql.getResultList();
    }
}
