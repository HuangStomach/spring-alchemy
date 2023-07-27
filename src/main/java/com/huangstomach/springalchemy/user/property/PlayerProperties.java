package com.huangstomach.springalchemy.user.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("custom.player")
public class PlayerProperties {
    private String name;
    private String role;
    private List<String> heros;

    public PlayerProperties(String name, String role, List<String> heros) {
        this.name = name;
        this.role = role;
        this.heros = heros;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public List<String> getHeros() {
        return this.heros;
    }
}
