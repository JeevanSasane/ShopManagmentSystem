package com.shopmanagement.user.entity;

import com.shopmanagement.masters.entity.City;
import com.shopmanagement.masters.entity.Country;
import com.shopmanagement.masters.entity.State;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private LocalDate dateOfBirth;

    private Integer age;

    private Boolean isActive=true;

    @NonNull
    private String mobileNo;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private String landmark;

    private LocalDateTime userCreateDateTime;

    @NonNull
    private String loginName;

    @NonNull
    private String password;

}
