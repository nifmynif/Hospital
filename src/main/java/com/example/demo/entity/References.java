package com.example.demo.entity;


import javax.persistence.*;

@Entity
@Table(name = "ns_references")
public class References {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Attr_id")
    private Long Attr_id;

    @Column(name = "Object_id")
    private Long Object_id;

    @Column(name = "Reference")
    private Long Reference;

    public References() {
    }

    public References(Long attr_id, Long object_id, Long reference) {
        Attr_id = attr_id;
        Object_id = object_id;
        Reference = reference;
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

    public Long getReference() {
        return Reference;
    }

    public void setReference(Long reference) {
        Reference = reference;
    }
}
