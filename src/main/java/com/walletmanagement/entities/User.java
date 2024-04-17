package com.walletmanagement.entities;

import java.util.Collection;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;


    private String password;


    //RELATION WITH ROLES
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(
            name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    public User() {
        
    }
    
    public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }


    
    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public void setId(Long id) {
        this.id = id;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
}
