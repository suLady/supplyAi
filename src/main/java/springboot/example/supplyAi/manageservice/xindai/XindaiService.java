package springboot.example.supplyAi.manageservice.xindai;

import springboot.example.supplyAi.domain.Enterprise;
import springboot.example.supplyAi.domain.PageResult;
import springboot.example.supplyAi.domain.Result;
import springboot.example.supplyAi.domain.SignContractorDetails;
import springboot.example.supplyAi.dto.ResponseDTO;

import java.util.List;

public interface XindaiService {
    PageResult<List<Enterprise>> queryCoreCompanyByCreditCode(String creditCode, int page, int pageSize);

    PageResult<List<SignContractorDetails>>  queryToBeConfirmInfo(String receiverCompanyCreditCode, int page, int pageSize);

    Result<ResponseDTO> addStartSign(String starterCreditCode,
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
                                     String confirmation);

    Result<String> updateContract(String starterCreditCode,
                        String receiverCompanyCreditCode,
                        String receiverCompanyAddress,
                        String accountNumber,
                        String accountNode,
                        String accountName,
                        String confirmation

    );
}