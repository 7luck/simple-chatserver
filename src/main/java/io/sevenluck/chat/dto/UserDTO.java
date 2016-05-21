/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.sevenluck.chat.dto;

import io.sevenluck.chat.domain.User;
import java.io.Serializable;

/**
 *
 * @author loki
 */
public class UserDTO implements Serializable {
    
    private String firstname;
    private String lastname;
    private Long id;

    public UserDTO(final User user) {
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.id = user.getId();
    }
    
    public UserDTO() {
        super();
    }
    
    public UserDTO(String firstname, String lastname, Long id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public User toUser() {
        return new User(this.id, this.firstname, this.lastname);
    }

    @Override
    public String toString() {
        return "UserDTO{" + "firstname=" + firstname + ", lastname=" + lastname + ", id=" + id + '}';
    }
    
    
}
