package com.shopmanagement.user.service.impl;

import com.shopmanagement.masters.entity.City;
import com.shopmanagement.masters.entity.Country;
import com.shopmanagement.masters.entity.State;
import com.shopmanagement.masters.repository.CityRepo;
import com.shopmanagement.masters.repository.CountryRepo;
import com.shopmanagement.masters.repository.StateRepo;
import com.shopmanagement.response.Response;
import com.shopmanagement.user.entity.Users;
import com.shopmanagement.user.repository.UserRepo;
import com.shopmanagement.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CityRepo cityRepo;

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Users createUser(Users users) {
        try {
            System.out.println("users=" + users);
            Optional<Country> countryOpt = Optional.ofNullable(countryRepo.findByName(users.getCountry().getName()));
            System.out.println("countryOpt=="+countryOpt);
            Country country = countryOpt.orElseGet(() -> countryRepo.save(users.getCountry()));
            System.out.println("country=="+country);
            users.setCountry(country);

            Optional<State> stateOptional = Optional.ofNullable(stateRepo.findByName(users.getState().getName()));
            State state1 = stateOptional.orElseGet(() -> {
                users.getState().setCountry(country);
                return stateRepo.save(users.getState());
            });

            users.setState(state1);

            Optional<City> cityOptional = Optional.ofNullable(cityRepo.findByName(users.getCity().getName()));
            City city1 = cityOptional.orElseGet(() -> {
                users.getCity().setState(state1);
                return cityRepo.save(users.getCity());
            });
            users.setCity(city1);

            users.setAge(Period.between(users.getDateOfBirth(), LocalDate.now()).getYears());
            users.setUserCreateDateTime(LocalDateTime.now());

            users.setPassword(passwordEncoder.encode(users.getPassword()));

            System.out.println("users.getPassword()="+users.getPassword());
            Users savedUser = userRepo.save(users);
            return savedUser;
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to create user", e);
        }
    }

    @Override
    public List<Users> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<Users> getUser(Integer userId) {
        return userRepo.findById(userId);
    }

    @Override
    public ResponseEntity<?> loginUser(String userName, String password) {

        System.out.println("in loginUser");
        var response=new Response<>();
        Optional<Users> getUser= Optional.ofNullable(userRepo.findByLoginName(userName));
        if (getUser.isPresent()){
            boolean isPasswordMatch = passwordEncoder.matches(password, getUser.get().getPassword());
            System.out.println("isPasswordMatch="+isPasswordMatch);
            if (isPasswordMatch){
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("User Login Successfully.");
                return ResponseEntity.ok(response);
            }
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Invalid Password !!");
            return ResponseEntity.badRequest().body(response);
        }
        response.setMessage("Invalid Login name !!");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(response);

    }

}
