/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DAOFactory;
import dao.UserDAO;
import entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import vo.UserVO;

/**
 *
 * @author Felipe Iz
 */
public class UserService {
    
    private static UserService service;
    
    private UserService() {}
    
    public static UserService getService() {
        if(service == null) {
            service = new UserService(); 
        }
        return service;
    }
    
    protected List<UserVO> toVO(List<User> entities) {
        List<UserVO> vos = new ArrayList<>();
        for (User entity : entities) {
            vos.add(entity.toVO());
        }
        return vos;
    }
    
    public UserVO getByLogin(String login, String password, EntityManager em) {
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        return userDAO.getUserLogin(login, password).toVO();
    }

    public UserVO newUser(UserVO userVO, EntityManager em){ 
        UserDAO userDAO = DAOFactory.getUserDAO(em);        
        User user = userVO.toEntity();
        userDAO.save(user);
        return user.toVO();
    }
    
    public boolean newUsers(Collection<UserVO> usersVO, EntityManager em){ 
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        for (Iterator iterator = usersVO.iterator(); iterator.hasNext();) {
            UserVO user = (UserVO)iterator.next();
            userDAO.save(user.toEntity());
        }
        return true;
    }
    
    public UserVO getUser(Long id, EntityManager em){
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        User user = userDAO.find(id);
        
        if(user != null){
            return user.toVO();
        }
       
        return null;
    }
    
    public UserVO updateUser(UserVO userVO, EntityManager em){
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        return userDAO.update(userVO.toEntity()).toVO();
    }
    
    public boolean deleteUser(Long id, EntityManager em){
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        return userDAO.delete(id);
    }
    
    public List<UserVO> allUsers(EntityManager em) {
        UserDAO userDAO = DAOFactory.getUserDAO(em);
        return this.toVO(userDAO.getAll());
    }
}
