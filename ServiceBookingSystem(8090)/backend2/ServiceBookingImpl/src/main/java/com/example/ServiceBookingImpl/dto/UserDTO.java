package com.example.ServiceBookingImpl.dto;

import com.example.ServiceBookingImpl.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String password;

    private String name;

    private String username;

    private String phone;

    private UserRole role;

    public UserDTO getDto(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setPhone(phone);
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setRole(role);

        return userDTO;

    }


}
