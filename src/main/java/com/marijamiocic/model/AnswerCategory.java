package com.marijamiocic.model;

import jakarta.persistence.*;

/**
 * Represents the type of answer category (e.g., single or multiple correct).
 */
@Entity
@Table(name = "AnswerCategories")
public class AnswerCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isActive;

    public AnswerCategory() {}

    public AnswerCategory(Integer code, String name, boolean isActive) {
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
        return "AnswerCategory{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
