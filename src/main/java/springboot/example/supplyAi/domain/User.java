package springboot.example.supplyAi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
public class User {
    private String customerId;
    @JsonIgnore
    private String password;
    private String username;
    private String address;
    private String creditCode;
}
