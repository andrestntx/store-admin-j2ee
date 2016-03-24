/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import entity.User;

/**
 *
 * @author andrestntx
 */
public class UserVO extends BaseVO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    
    public UserVO(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;        
        this.password = password;
        
    }

    public UserVO() {
       
    }
    
    public User toEntity(){
        User entity = new User();     
        entity.setId(this.getId());
        entity.setName(this.getName());
        entity.setUsername(this.getUsername());
        entity.setEmail(this.getEmail());
        entity.setPassword(this.getPassword());        
        return entity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
