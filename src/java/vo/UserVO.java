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
public class UserVO {
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    
    public User toEntity(){
        User entity = new User();
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
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
