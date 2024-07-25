package com.shopmanagement.masters.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "mt_state")
@AllArgsConstructor
@NoArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Boolean isActive=true;

    private LocalDateTime createDateTime;

    @ManyToOne
    @JoinColumn(name = "country_id",nullable = false)
    private Country country;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "created_by")
//    private Users createBy;

}
