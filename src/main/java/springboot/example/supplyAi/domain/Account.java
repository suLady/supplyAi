package springboot.example.supplyAi.domain;

import lombok.Data;

@Data
public class Account {
    private String customerId;
    private String accountCode;
    private String accountNumber;
    private int accountType;
    private String accountNode;
    private String accountName;
}
