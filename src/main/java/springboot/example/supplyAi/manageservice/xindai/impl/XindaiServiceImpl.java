package springboot.example.supplyAi.manageservice.xindai.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.example.supplyAi.domain.*;
import springboot.example.supplyAi.dto.ResponseDTO;
import springboot.example.supplyAi.manageservice.xindai.XindaiService;
import springboot.example.supplyAi.service.EnterpriseService;
import springboot.example.supplyAi.service.EnterpriseUserService;
import springboot.example.supplyAi.service.SignContractDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class XindaiServiceImpl implements XindaiService {
    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private EnterpriseUserService enterpriseUserService;

    @Autowired
    private SignContractDetailsService signContractDetailsService;

    @Override
    public PageResult<List<Enterprise>> queryCoreCompanyByCreditCode(String creditCode, int page, int pageSize) {

        PageResult<List<EnterpriseUser>> companyCreditCode = enterpriseUserService.findCompanyCreditCode(creditCode, page, pageSize);
        List<EnterpriseUser> enterpriseUsers = companyCreditCode.getData();
        List<Enterprise> enterpriseList = new ArrayList<>();
        if (!enterpriseUsers.isEmpty()) {
            for (int i = 0; i < enterpriseUsers.size(); i++) {
                EnterpriseUser enterpriseUser = enterpriseUsers.get(i);
                Result<Enterprise> coreCompany = enterpriseService.findCoreCompany(enterpriseUser.getCompanyCreditCode());
                enterpriseList.add(coreCompany.getData());
            }
        }

        return PageResult.success(companyCreditCode.getPage(), companyCreditCode.getPageSize(), companyCreditCode.getTotalPages(), companyCreditCode.getTotalCompany(), enterpriseList);
    }

    @Override
    public PageResult<List<SignContractorDetails>> queryToBeConfirmInfo(String receiverCompanyCreditCode, int page, int pageSize) {
        PageResult<List<SignContractorDetails>> listPageResult = signContractDetailsService.selectConfirmInfo(receiverCompanyCreditCode, page, pageSize);
        List<SignContractorDetails> signContractorDetails = listPageResult.getData();
        return PageResult.success(listPageResult.getPage(),listPageResult.getPageSize(),listPageResult.getTotalPages(),listPageResult.getTotalCompany(),signContractorDetails);
    }

    @Override
    public Result<ResponseDTO> addStartSign(String starterCreditCode, String starterCompanyName, String starterCompanyAddress, String receiverCompanyName, String receiverCompanyAddress, String receiverCompanyCreditCode, String starterType, String receiverType,  String accountNumber, String accountNode, String accountName, String payment, String confirmation) {
        String contractId = UUID.randomUUID().toString();
        String signState = "0";
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSignState(signState);
        responseDTO.setContractId(contractId);
        SignContractorDetails signContractorDetails = signContractDetailsService.selectContractInfo(starterCreditCode, receiverCompanyCreditCode);
        if(signContractorDetails != null){
            Result.error("不能重复签约");
        }else{
            signContractDetailsService.addSignContract(starterCreditCode, starterCompanyName, starterCompanyAddress, receiverCompanyName, receiverCompanyAddress,receiverCompanyCreditCode,starterType,receiverType, accountNumber, accountNode,accountName, payment, confirmation,contractId,signState);
        }
        return Result.success(responseDTO);
    }

    @Override
    public Result<String>  updateContract(String starterCreditCode, String receiverCompanyCreditCode, String receiverCompanyAddress, String accountNumber, String accountNode, String accountName,String confirmation) {
        SignContractorDetails signContractorDetails = signContractDetailsService.selectContractInfo(starterCreditCode, receiverCompanyCreditCode);
        if(signContractorDetails == null){
            throw new RuntimeException("不存在需要更新的记录");
        }else {
            String signState = "1";
            signContractDetailsService.updateContract(starterCreditCode,receiverCompanyCreditCode,receiverCompanyAddress,accountNumber,accountNode,accountName,confirmation,signState);
            return Result.success(signState);
        }
    }
}
