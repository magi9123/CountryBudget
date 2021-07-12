package com.models;

import com.enums.Provider;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String name;

    public UserModel(Provider provider) {
        this.provider = provider;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
