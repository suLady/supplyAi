package springboot.example.supplyAi.service;

import springboot.example.supplyAi.domain.Enterprise;
import springboot.example.supplyAi.domain.Result;

public interface EnterpriseService {

    Result<Enterprise> findCoreCompany(String companyCreditCode);
}
