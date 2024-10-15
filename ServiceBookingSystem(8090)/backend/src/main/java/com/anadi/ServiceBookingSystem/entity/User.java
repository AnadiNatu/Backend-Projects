package com.anadi.ServiceBookingSystem.entity;


import com.anadi.ServiceBookingSystem.dto.UserDto;
import com.anadi.ServiceBookingSystem.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String password;

    private String fname;

    private String lname;

    private String phone;

    private String email;

    private UserRole role;


    public UserDto getDto(){

        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setFname(fname);
        userDto.setLname(lname);
        userDto.setPhone(phone);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setRole(role);

        return userDto;

}
}
