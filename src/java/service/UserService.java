/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserDAO;
import entity.User;
import javax.persistence.EntityManager;
import vo.UserVO;

/**
 *
 * @author andrestntx
 */
public class UserService {

    public void persist(UserVO vo, EntityManager entityManager) {
        User entity = new User();
        entity.setId(vo.getId());
        entity.setUsername(vo.getUsername());
        entity.setName(vo.getName());
        entity.setEmail(vo.getEmail());
        entity.setPassword(vo.getPassword());
        
        UserDAO.persist(entity, entityManager);
    }
    
}
