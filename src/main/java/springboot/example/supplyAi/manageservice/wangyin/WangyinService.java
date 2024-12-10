package springboot.example.supplyAi.manageservice.wangyin;

import springboot.example.supplyAi.domain.*;
import springboot.example.supplyAi.dto.ConfirmInfoDTO;
import springboot.example.supplyAi.dto.WangyinSignContractorDetailsDTO;

import java.util.List;

public interface WangyinService {

    PageResult<List<Enterprise>> queryCoreCompany(String creditCode, int page, int pageSize);

    PageResult<List<ConfirmInfoDTO>> queryToBeConfirmInfo(String creditCode, int page, int pageSize);

    void submitContract(String customerId, String creditCode, String userName, String address, WangyinSignContractorDetailsDTO wangyinSignContractorDetailsDTO);


    WangyinSignDetails selectContractInfo(String creditCode, String receiverCompanyCreditCode);




    void submitConfirmInfoContract(String creditCode,String username,  WangyinSignContractorDetailsDTO wangyinSignContractorDetailsDTO);


    Signature generateSignature(String username, String starterCompanyName, String payment);
}
