package com.codegym.model;

import javax.persistence.*;

@Entity
@Table(name = "province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameProvince;

    public Province() {
    }

    public Province(String nameProvince) {
        this.nameProvince = nameProvince;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }
}
