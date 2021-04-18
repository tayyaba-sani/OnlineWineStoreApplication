package com.springboot.wine.store.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Address extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String city;
    private String street1;
    private String street2;
    private String state;

    @Column(name = "Zip_Code")
    private int zipCode;


}
