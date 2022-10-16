package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ns_params")
public class Params {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Attr_id")
    private Long Attr_id;

    @Column(name = "Object_id", nullable = false)
    private Long Object_id;

    @Column(name = "Value", length = 30)
    private String Value;

    @Column(name = "Date_value")
    private Date Date_value;

    @Column(name = "List_value_id")
    private Long List_value_id;

    public Params() {
    }

    public Params(Long attr_id, Long object_id, String value, Date date_value, Long list_value_id) {
        Attr_id = attr_id;
        Object_id = object_id;
        Value = value;
        Date_value = date_value;
        List_value_id = list_value_id;
    }

    public Long getAttr_id() {
        return Attr_id;
    }

    public void setAttr_id(Long attr_id) {
        Attr_id = attr_id;
    }

    public Long getObject_id() {
        return Object_id;
    }

    public void setObject_id(Long object_id) {
        Object_id = object_id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public Date getDate_value() {
        return Date_value;
    }

    public void setDate_value(Date date_value) {
        Date_value = date_value;
    }

    public Long getList_value_id() {
        return List_value_id;
    }

    public void setList_value_id(Long list_value_id) {
        List_value_id = list_value_id;
    }
}