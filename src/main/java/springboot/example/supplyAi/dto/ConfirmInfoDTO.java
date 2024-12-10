package springboot.example.supplyAi.dto;

import lombok.Data;

@Data
public class ConfirmInfoDTO {
    private String starterCreditCode;
    private String starterCompanyName;
    private String starterCompanyAddress;
    private String starterType;
    private String signState;
    private String payment;
    private String confirmation;
    private String contractId;
}
