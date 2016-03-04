/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import vo.UserVO;

/**
 *
 * @author andrestntx
 */
@Entity
public class User implements Serializable {
    @Id
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    
    public UserVO toVO(){
        UserVO vo = new UserVO();
        vo.setId(this.getId());
        vo.setName(this.getName());
        vo.setEmail(this.getEmail());
        vo.setUsername(this.getUsername());
        vo.setPassword(this.getPassword());
        return vo;
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
