package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ns_list_values")
public class ListValues {

    @Id
    @GeneratedValue
    @Column(name = "List_value_id", nullable = false)
    private Long List_value_id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "Attr_type_def_id")
    private Long Attr_type_def_id;

    public ListValues() {
    }

    public ListValues(Long list_value_id, String name, Long attr_type_def_id) {
        List_value_id = list_value_id;
        this.name = name;
        Attr_type_def_id = attr_type_def_id;
    }

    public Long getList_value_id() {
        return List_value_id;
    }

    public void setList_value_id(Long list_value_id) {
        List_value_id = list_value_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAttr_type_def_id() {
        return Attr_type_def_id;
    }

    public void setAttr_type_def_id(Long attr_type_def_id) {
        Attr_type_def_id = attr_type_def_id;
    }
}