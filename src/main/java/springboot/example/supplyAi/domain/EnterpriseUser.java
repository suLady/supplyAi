package springboot.example.supplyAi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnterpriseUser {
    private String creditCode;
    private String companyCreditCode;
}
