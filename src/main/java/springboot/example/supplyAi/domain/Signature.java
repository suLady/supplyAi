package springboot.example.supplyAi.domain;

import lombok.Data;

@Data
public class Signature {
    private String starterCompanyName;
    private String receiverCompanyName;
    private String payment;
    private String urlAddress;
}
