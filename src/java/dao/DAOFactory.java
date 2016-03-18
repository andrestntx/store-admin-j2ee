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
 */
public class DAOFactory {
    
    public static UserDAO getUserDAO(EntityManager em){
        return new UserDAO(em);
    }
}
