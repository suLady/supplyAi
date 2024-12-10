package springboot.example.supplyAi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.example.supplyAi.domain.PageResult;
import springboot.example.supplyAi.domain.SignContractorDetails;
import springboot.example.supplyAi.mapper.SignContractDetailsMapper;
import springboot.example.supplyAi.service.SignContractDetailsService;

import java.util.List;

@Service
public class SignContractDetailsServiceImpl implements SignContractDetailsService {

    @Autowired
    private SignContractDetailsMapper signContractDetailsMapper;

    @Override
    public SignContractorDetails selectContractInfo(String starterCreditCode, String receiverCompanyCreditCode) {
        return signContractDetailsMapper.queryContractInfo(starterCreditCode,receiverCompanyCreditCode);
    }

    @Override
    public void addSignContract(String starterCreditCode,String starterCompanyName, String starterCompanyAddress, String receiverCompanyName, String receiverCompanyAddress, String receiverCompanyCreditCode,String starterType, String receiverType, String accountNumber, String accountNode, String accountName, String payment, String confirmation, String contractId,String signState) {
        signContractDetailsMapper.addStarterSignContract(starterCreditCode,starterCompanyName, starterCompanyAddress, receiverCompanyName, receiverCompanyAddress, receiverCompanyCreditCode,starterType,receiverType, accountNumber,  accountNode,  accountName,  payment,  confirmation,  contractId,signState );
    }

    @Override
    public void updateContract(String starterCreditCode, String starterType, String receiverCompanyCreditCode, String receiverCompanyAddress,  String accountNumber, String accountNode,String accountName,String confirmation, String signState) {
        signContractDetailsMapper.updateConfirmSignContract(starterCreditCode,starterType,receiverCompanyCreditCode,receiverCompanyAddress,accountNumber,accountNode,accountName,confirmation,signState);
    }


    @Override
    public PageResult<List<SignContractorDetails>> selectConfirmInfo(String receiverCompanyCreditCode, int page, int pageSize) {
        int offset = (page - 1) * page;
        int pageCount = pageSize;
        List<SignContractorDetails> signContractorDetailsList = signContractDetailsMapper.selectToBeProcessedInfo(receiverCompanyCreditCode,offset,pageCount);
        int totalCompanyInfo = signContractDetailsMapper.queryTotalToBeProcessedInfo(receiverCompanyCreditCode);
        int totalPages = (int) Math.ceil((double)totalCompanyInfo/pageSize);
        return PageResult.success(page,pageSize,totalPages,totalCompanyInfo,signContractorDetailsList);
    }

}
