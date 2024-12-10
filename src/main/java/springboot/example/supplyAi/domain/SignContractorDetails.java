package springboot.example.supplyAi.domain;

import lombok.Data;


@Data
public class SignContractorDetails {
    private String starterCreditCode;
    private String starterCompanyName;
    private String starterCompanyAddress;
    private String receiverCompanyName;
    private String receiverCompanyAddress;
    private String receiverCompanyCreditCode;
    private String starterType;
    private String receiverType;
    private String signState;
    private String accountNumber;
    private String accountNode;
    private String accountName;
    private String payment;
    private String confirmation;
    private String contractId;
}
