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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mt_country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Boolean isActive=true;

    private LocalDateTime createDateTime;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "created_by")
//    private Users createBy;

}
