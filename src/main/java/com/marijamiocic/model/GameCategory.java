package com.marijamiocic.model;


import jakarta.persistence.*;

/**
 * Represents a game category (e.g., History, Science).
 */
@Entity
@Table(name = "GameCategories")
public class GameCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isActive;

    public GameCategory() {}

    public GameCategory(Integer code, String name, boolean isActive) {
        this.code = code;
        this.name = name;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "GameCategory{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
