package springboot.example.supplyAi.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EnterpriseUserDTO {
    @NotNull
    private String creditCode;
    @NotNull
    private String companyCreditCode;

}
