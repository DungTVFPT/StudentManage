package com.dungtv.application.domain;

import java.util.ArrayList;
import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "tbl_role")
public class Role extends BaseObject
{
    @Column(name = "code")
    private String code;
    @Column(name = "role")
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<Account> users;
    
    public Role() {
        this.users = new ArrayList<Account>();
    }
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public List<Account> getUsers() {
        return this.users;
    }
    
    public void setUsers(final List<Account> users) {
        this.users = users;
    }
}