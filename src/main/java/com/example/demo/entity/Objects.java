package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Embeddable
@Table(name = "ns_objects", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "USER_UK", columnNames = "Name") })

public class Objects {
        @Id
        @GeneratedValue
        @Column(name = "Object_id", nullable = false)
        private Long Object_id;

        @Column(name = "Name", length = 30)
        private String Name;

        @Column(name = "Object_type_id")
        private Long Object_type_id;

        @Column(name = "Parent_id")
        private Long Parent_id;

        public Objects() {
        }

        public Objects(String name, Long object_type_id, Long parent_id) {
                Name = name;
                Object_type_id = object_type_id;
                Parent_id = parent_id;
        }

        public Long getObject_id() {
                return Object_id;
        }

        public void setObject_id(Long object_id) {
                Object_id = object_id;
        }

        public String getName() {
                return Name;
        }

        public void setName(String name) {
                Name = name;
        }

        public Long getObject_type_id() {
                return Object_type_id;
        }

        public void setObject_type_id(Long object_type_id) {
                Object_type_id = object_type_id;
        }

        public Long getParent_id() {
                return Parent_id;
        }

        public void setParent_id(Long parent_id) {
                Parent_id = parent_id;
        }
}
