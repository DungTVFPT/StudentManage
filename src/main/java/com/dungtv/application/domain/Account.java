package com.dungtv.application.domain;

import java.util.ArrayList;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "tbl_account")
public class Account extends BaseObject
{
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_of_birth")
    private Date dob;
    @Column(name = "gender", columnDefinition = "tinyint(1) default 0")
    private boolean gender;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private Integer status;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<Role> roles;
    
    public Account() {
        this.roles = new ArrayList<Role>();
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
    
    public Date getDob() {
        return this.dob;
    }
    
    public void setDob(final Date dob) {
        this.dob = dob;
    }
    
    public boolean isGender() {
        return this.gender;
    }
    
    public void setGender(final boolean gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(final String address) {
        this.address = address;
    }
    
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(final Integer status) {
        this.status = status;
    }
    
    public List<Role> getRoles() {
        return this.roles;
    }
    
    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }
}