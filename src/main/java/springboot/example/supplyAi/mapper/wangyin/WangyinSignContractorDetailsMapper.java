package springboot.example.supplyAi.mapper.wangyin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springboot.example.supplyAi.domain.Signature;
import springboot.example.supplyAi.domain.WangyinSignDetails;

@Mapper
public interface WangyinSignContractorDetailsMapper {
    WangyinSignDetails queryContractInfo(@Param("starterCreditCode") String creditCode, @Param("receiverCompanyCreditCode") String receiverCompanyCreditCode);

    void submitSignInfo(@Param("customerId") String customerId,
                        @Param("starterCreditCode") String creditCode,
                        @Param("starterCompanyName") String userName,
                        @Param("starterCompanyAddress") String address,
                        @Param("receiverType") String receiverType,
                        @Param("starterType") String starterType,
                        @Param("starterPhone") String starterPhone,
                        @Param("receiverCompanyCreditCode") String receiverCompanyCreditCode,
                        @Param("receiverCompanyAddress") String receiverCompanyAddress,
                        @Param("receiverCompanyName") String receiverCompanyName,
                        @Param("accountNumber") String accountNumber,
                        @Param("accountName") String accountName,
                        @Param("accountNode") String accountNode,
                        @Param("payment") String payment,
                        @Param("confirmation") String confirmation,
                        @Param("agreement") String agreement,
                        @Param("contractId") String contractId,
                        @Param("signState") String signState);


    Signature addSignature(@Param("receiverCompanyName") String username,
                      @Param("starterCompanyName") String starterCompanyName,
                      @Param("payment") String payment,
                      @Param("urlAddress") String urlAddress);

    void updateContractInfo(@Param("starterCreditCode") String creditCode,
                            @Param("receiverCompanyCreditCode") String receiverCompanyCreditCode,
                            @Param("receiverCompanyAddress") String receiverCompanyAddress,
                            @Param("receiverPhone") String receiverPhone,
                            @Param("confirmation") String confirmation,
                            @Param("accountName") String accountName,
                            @Param("accountNode") String accountNode,
                            @Param("accountNumber") String accountNumber,
                            @Param("signState") String signState);
}
