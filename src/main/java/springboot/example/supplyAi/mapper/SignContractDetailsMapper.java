package springboot.example.supplyAi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springboot.example.supplyAi.domain.SignContractorDetails;

import java.util.List;

@Mapper
public interface SignContractDetailsMapper {
    void addStarterSignContract(@Param("starterCreditCode") String starterCreditCode,
                                @Param("starterCompanyName") String starterCompanyName,
                                @Param("starterCompanyAddress") String starterCompanyAddress,
                                @Param("receiverCompanyName") String receiverCompanyName,
                                @Param("receiverCompanyAddress") String receiverCompanyAddress,
                                @Param("receiverCompanyCreditCode") String receiverCompanyCreditCode,
                                @Param("starterType") String starterType,
                                @Param("receiverType") String receiverType,
                                @Param("accountNumber") String accountNumber,
                                @Param("accountNode") String accountNode,
                                @Param("accountName") String accountName,
                                @Param("payment") String payment,
                                @Param("confirmation") String confirmation,
                                @Param("contractId") String contractId,
                                @Param("signState") String signState);


    SignContractorDetails queryContractInfo(@Param("starterCreditCode") String starterCreditCode, @Param("receiverCompanyCreditCode") String receiverCompanyCreditCode);

    List<SignContractorDetails> selectToBeProcessedInfo(@Param("receiverCompanyCreditCode") String receiverCompanyCreditCode, @Param("offset") int offset, @Param("pageCount") int pageCount);

    int queryTotalToBeProcessedInfo(@Param("receiverCompanyCreditCode") String receiverCompanyCreditCode);


    void updateConfirmSignContract(@Param("starterCreditCode") String starterCreditCode,
                                   @Param("starterType") String starterType,
                                   @Param("receiverCompanyCreditCode") String receiverCompanyCreditCode,
                                   @Param("receiverCompanyAddress") String receiverCompanyAddress,
                                   @Param("accountNumber") String accountNumber,
                                   @Param("accountNode")String accountNode,
                                   @Param("accountName")String accountName,
                                   @Param("confirmation") String confirmation,
                                   @Param("signState") String signState);
}
