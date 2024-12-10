package springboot.example.supplyAi.dto;

import lombok.Data;

@Data
public class SignatureDTO {
    private String starterCompanyName;
    private String receiverCompanyName;
    private String payment;
    private String urlAddress;
}
