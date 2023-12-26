package com.project_paypal.project.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Adress")
public class adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    private String adress_1;

    private String adress_2;

    @NotNull
    @Size(min = 1)
    private String country;

    @NotNull
    @Size(min = 1)
    private String state;

    @NotNull
    @Size(min = 1)
    private String province;

    @NotNull
    @Digits(integer = 5, fraction = 0)
    private long postal_code;

    @ManyToOne
    @JsonIgnoreProperties("adress")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress_1() {
        return adress_1;
    }

    public void setAdress_1(String adress_1) {
        this.adress_1 = adress_1;
    }

    public String getAdress_2() {
        return adress_2;
    }

    public void setAdress_2(String adress_2) {
        this.adress_2 = adress_2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Long getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(Long postal_code) {
        this.postal_code = postal_code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
