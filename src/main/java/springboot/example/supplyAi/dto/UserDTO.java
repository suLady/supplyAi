package springboot.example.supplyAi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDTO {
    @NotNull
    private String customerId;
    @NotNull
    private String password;
    @NotNull
    private String username;
    @NotNull
    private String address;
    @NotNull
    private String creditCode;
}
