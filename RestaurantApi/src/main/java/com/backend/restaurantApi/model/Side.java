package com.backend.restaurantApi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Side {

    @Id
    @Column(name = "sideId", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dishAllergies_id", nullable = true)
    private DishAllergies dAllergies;

    @Column(name = "dishName")
    private String dishName;

    // used to serialize object to json
    @Override
    public String toString() {
        return "Main{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                '}';
    }
}
