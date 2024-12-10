package springboot.example.supplyAi.domain;

import lombok.Data;

@Data
public class WangyinSignDetails {
    private String customerId;
    private String starterCreditCode;
    private String starterCompanyName;
    private String starterCompanyAddress;
    private String starterPhone;
    private String agreement;
    private String receiverCompanyName;
    private String receiverCompanyAddress;
    private String receiverCompanyCreditCode;
    private String receiverPhone;
    private String starterType;
    private String receiverType;
    private String accountNumber;
    private String accountNode;
    private String accountName;
    private String payment;
    private String confirmation;
    private String contractId;
    private String signState;
}
