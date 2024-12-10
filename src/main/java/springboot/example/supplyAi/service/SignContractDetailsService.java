package springboot.example.supplyAi.service;

import springboot.example.supplyAi.domain.PageResult;
import springboot.example.supplyAi.domain.SignContractorDetails;

import java.util.List;

public interface SignContractDetailsService {

    SignContractorDetails selectContractInfo(String starterCreditCode,String receiverCompanyCreditCode);
    void addSignContract(String starterCreditCode,
                           String starterCompanyName,
                           String starterCompanyAddress,
                           String receiverCompanyName,
                           String receiverCompanyAddress,
                           String receiverCompanyCreditCode,
                           String starterType,
                           String receiverType,
                           String accountNumber,
                           String accountNode,
                           String accountName,
                           String payment,
                           String confirmation,
                           String contractId,
                           String signState);

    void updateContract(String starterCreditCode,
                        String receiverCompanyCreditCode,
                        String receiverCompanyAddress,
                        String accountNumber,
                        String accountNode,
                        String accountName,
                        String confirmation,
                        String signState
                        );

    PageResult<List<SignContractorDetails>> selectConfirmInfo(String receiverCompanyCreditCode, int page, int pageSize);
}