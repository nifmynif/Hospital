package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ns_attributes")
public class Attributes {

    @Id
    @GeneratedValue
    @Column(name = "Attr_id", nullable = false)
    private Long Attr_id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "Attr_type_id")
    private Long Attr_type_id;

    @Column(name = "Attr_group_id ")
    private Long Attr_group_id ;

    @Column(name = "Attr_type_def_id")
    private Long Attr_type_def_id;

    @Column(name = "Params", length = 30)
    private String Params;

    public Attributes() {
    }

    public Attributes(String name, Long attr_type_id, Long attr_group_id, Long attr_type_def_id, String params) {
        this.name = name;
        Attr_type_id = attr_type_id;
        Attr_group_id = attr_group_id;
        Attr_type_def_id = attr_type_def_id;
        Params = params;
    }

    public Long getAttr_id() {
        return Attr_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAttr_type_id() {
        return Attr_type_id;
    }

    public void setAttr_type_id(Long attr_type_id) {
        Attr_type_id = attr_type_id;
    }

    public Long getAttr_group_id() {
        return Attr_group_id;
    }

    public void setAttr_group_id(Long attr_group_id) {
        Attr_group_id = attr_group_id;
    }

    public Long getAttr_type_def_id() {
        return Attr_type_def_id;
    }

    public void setAttr_type_def_id(Long attr_type_def_id) {
        Attr_type_def_id = attr_type_def_id;
    }

    public String getParams() {
        return Params;
    }

    public void setParams(String params) {
        Params = params;
    }
}