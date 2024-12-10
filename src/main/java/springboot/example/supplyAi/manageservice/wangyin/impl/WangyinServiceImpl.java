package springboot.example.supplyAi.manageservice.wangyin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.example.supplyAi.domain.*;
import springboot.example.supplyAi.dto.ConfirmInfoDTO;
import springboot.example.supplyAi.dto.ResponseDTO;
import springboot.example.supplyAi.dto.WangyinSignContractorDetailsDTO;
import springboot.example.supplyAi.manageservice.wangyin.WangyinService;
import springboot.example.supplyAi.manageservice.xindai.XindaiService;
import springboot.example.supplyAi.mapper.wangyin.WangyinSignContractorDetailsMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WangyinServiceImpl implements WangyinService {
    @Autowired
    private XindaiService xindaiService;

    @Autowired
    private WangyinSignContractorDetailsMapper wangyinSignContractorDetailsMapper;


    @Override
    public PageResult<List<Enterprise>> queryCoreCompany(String creditCode, int page, int pageSize) {
        PageResult<List<Enterprise>> enterpriseList =  xindaiService.queryCoreCompanyByCreditCode(creditCode,page,pageSize);
        return enterpriseList ;
    }

    @Override
    public PageResult<List<ConfirmInfoDTO>> queryToBeConfirmInfo(String creditCode, int page, int pageSize) {
        PageResult<List<SignContractorDetails>> listPageResult = xindaiService.queryToBeConfirmInfo(creditCode, page, pageSize);
        List<SignContractorDetails> signContractorDetailsList = listPageResult.getData();
        List<ConfirmInfoDTO> confirmInfoDTOList = new ArrayList<>();

        if(!signContractorDetailsList.isEmpty()){
            //简易 lambda 表达式写法
           /* List<ConfirmInfoDTO> confirmInfoDTOList = signContractorDetailsList.stream().
                    map(signContractorDetails -> {
                        ConfirmInfoDTO confirmInfoDTO = new ConfirmInfoDTO();
                        confirmInfoDTO.setConfirmation(signContractorDetails.getConfirmation());
                        confirmInfoDTO.setPayment(signContractorDetails.getPayment());
                        confirmInfoDTO.setStarterType(signContractorDetails.getStarterType());
                        confirmInfoDTO.setStarterCompanyName(signContractorDetails.getStarterCompanyName());
                        confirmInfoDTO.setStarterCreditCode(signContractorDetails.getStarterCreditCode());
                        confirmInfoDTO.setContractId(signContractorDetails.getContractId());
                        confirmInfoDTO.setSignState(signContractorDetails.getSignState());
                        confirmInfoDTO.setStarterCompanyAddress(signContractorDetails.getStarterCompanyAddress());
                        return confirmInfoDTO;
                    }).collect(Collectors.toList());*/


            // 普通遍历循环
            for(int i=0;i<signContractorDetailsList.size();i++){
                // 取数据
                SignContractorDetails signContractorDetails = signContractorDetailsList.get(i);

                // 转数据
                ConfirmInfoDTO confirmInfoDTO = new ConfirmInfoDTO();
                confirmInfoDTO.setConfirmation(signContractorDetails.getConfirmation());
                confirmInfoDTO.setPayment(signContractorDetails.getPayment());
                confirmInfoDTO.setStarterType(signContractorDetails.getStarterType());
                confirmInfoDTO.setStarterCompanyName(signContractorDetails.getStarterCompanyName());
                confirmInfoDTO.setStarterCreditCode(signContractorDetails.getStarterCreditCode());
                confirmInfoDTO.setContractId(signContractorDetails.getContractId());
                confirmInfoDTO.setSignState(signContractorDetails.getSignState());
                confirmInfoDTO.setStarterCompanyAddress(signContractorDetails.getStarterCompanyAddress());

                // 把转后的数据放入返回list
                confirmInfoDTOList.add(confirmInfoDTO);

            }

       }
        return PageResult.success(listPageResult.getPage(), listPageResult.getPageSize(), listPageResult.getTotalPages(),
                listPageResult.getTotalCompany(), confirmInfoDTOList);
    }

    @Override
    public WangyinSignDetails selectContractInfo(String creditCode, String receiverCompanyCreditCode) {
        return wangyinSignContractorDetailsMapper.queryContractInfo(creditCode,receiverCompanyCreditCode);
    }

    @Override
    public Signature generateSignature(String username, String starterCompanyName, String payment) {
        String baseurl = "http://example.com/";
        String randomId = UUID.randomUUID().toString();
        String url_address = baseurl + randomId;
        return wangyinSignContractorDetailsMapper.addSignature(username,starterCompanyName,payment,url_address);
    }

    @Override
    public Result<Void> submitConfirmInfoContract(String creditCode,String username, WangyinSignContractorDetailsDTO wangyinSignContractorDetailsDTO) {
        Signature signature = generateSignature(username, wangyinSignContractorDetailsDTO.getStarterCompanyName(), wangyinSignContractorDetailsDTO.getPayment());
        if(signature.getUrlAddress() != null){
            Result<String> stringResult = xindaiService.updateContract(creditCode, wangyinSignContractorDetailsDTO.getReceiverCompanyCreditCode(), wangyinSignContractorDetailsDTO.getReceiverCompanyAddress(), wangyinSignContractorDetailsDTO.getAccountNumber(), wangyinSignContractorDetailsDTO.getAccountNode(), wangyinSignContractorDetailsDTO.getAccountName(), wangyinSignContractorDetailsDTO.getConfirmation());
            if (!stringResult.getCode().equals("1111")){
                //throw new RuntimeException("..diaoyongxindaishibai);
            }
            String signState = stringResult.getData();
            wangyinSignContractorDetailsMapper.updateContractInfo(creditCode,wangyinSignContractorDetailsDTO.getReceiverCompanyCreditCode(),wangyinSignContractorDetailsDTO.getReceiverCompanyAddress(),wangyinSignContractorDetailsDTO.getReceiverPhone(),wangyinSignContractorDetailsDTO.getConfirmation(),wangyinSignContractorDetailsDTO.getAccountName(),wangyinSignContractorDetailsDTO.getAccountNode(),wangyinSignContractorDetailsDTO.getAccountNumber(),signState);
        }else{
           return Result.error("签章失败");
        }

        //无论哪种状态都要更新签约状态
        //出票人
        if(wangyinSignContractorDetailsDTO.getStarterType().equals("0")){
            //买方付息
            if(wangyinSignContractorDetailsDTO.getPayment().equals("0")){
                //卖方付息
            } else if (wangyinSignContractorDetailsDTO.getPayment().equals("1")) {

            }
            //收款人
        }else if(wangyinSignContractorDetailsDTO.getReceiverType().equals("1")){
            //买方付息
            if(wangyinSignContractorDetailsDTO.getPayment().equals("0")){
            //卖方付息
            } else if (wangyinSignContractorDetailsDTO.getPayment().equals("1")) {

            }
        }
        return null;
    }

    @Override
    public void submitContract(String customerId, String creditCode, String userName, String address, WangyinSignContractorDetailsDTO wangyinSignContractorDetailsDTO) {
        String starterType = "";

        if(wangyinSignContractorDetailsDTO.getReceiverType().equals("0")){
            starterType = "1";
        } else if (wangyinSignContractorDetailsDTO.getReceiverType().equals("1")) {
            starterType = "0";
        }

        Result<ResponseDTO> responseDTOResult = xindaiService.addStartSign(
                creditCode,
                userName,
                address,
                wangyinSignContractorDetailsDTO.getReceiverCompanyName(),
                wangyinSignContractorDetailsDTO.getReceiverCompanyAddress(),
                wangyinSignContractorDetailsDTO.getReceiverCompanyCreditCode(),
                starterType,
                wangyinSignContractorDetailsDTO.getReceiverType(),
                wangyinSignContractorDetailsDTO.getAccountNumber(),
                wangyinSignContractorDetailsDTO.getAccountNode(),
                wangyinSignContractorDetailsDTO.getAccountName(),
                wangyinSignContractorDetailsDTO.getPayment(),
                wangyinSignContractorDetailsDTO.getConfirmation()
        );
       String contractId = responseDTOResult.getData().getContractId();
       String signState = responseDTOResult.getData().getSignState();
       WangyinSignDetails wangyinSignDetails = selectContractInfo(creditCode, wangyinSignContractorDetailsDTO.getReceiverCompanyCreditCode());
       if(wangyinSignDetails != null){
           Result.error("不可重复签约");
       }else {
           wangyinSignContractorDetailsMapper.submitSignInfo(
                   customerId,
                   creditCode,
                   userName,
                   address,
                   wangyinSignContractorDetailsDTO.getReceiverType(),
                   starterType,
                   wangyinSignContractorDetailsDTO.getStarterPhone(),
                   wangyinSignContractorDetailsDTO.getReceiverCompanyCreditCode(),
                   wangyinSignContractorDetailsDTO.getReceiverCompanyAddress(),
                   wangyinSignContractorDetailsDTO.getReceiverCompanyName(),
                   wangyinSignContractorDetailsDTO.getAccountNumber(),
                   wangyinSignContractorDetailsDTO.getAccountName(),
                   wangyinSignContractorDetailsDTO.getAccountNode(),
                   wangyinSignContractorDetailsDTO.getPayment(),
                   wangyinSignContractorDetailsDTO.getConfirmation(),
                   wangyinSignContractorDetailsDTO.getAgreement(),
                   contractId,
                   signState
           );
       }
    }
}
