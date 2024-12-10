package springboot.example.supplyAi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.example.supplyAi.domain.*;
import springboot.example.supplyAi.dto.ConfirmInfoDTO;
import springboot.example.supplyAi.dto.SignatureDTO;
import springboot.example.supplyAi.dto.WangyinSignContractorDetailsDTO;
import springboot.example.supplyAi.manageservice.wangyin.WangyinService;
import springboot.example.supplyAi.utils.ThreadLocalUtil;

import java.util.List;

@RestController
@RequestMapping("/company")
public class WangyinEnterpriseController {

    @Autowired
    private WangyinService wangyinService;

    @GetMapping("/companyInfo")
    public PageResult<List<Enterprise>> findCoreCompany(int page, int pageSize){
        User user = ThreadLocalUtil.get();
        PageResult<List<Enterprise>> enterprisesList = wangyinService.queryCoreCompany(user.getCreditCode(),page,pageSize);
        return enterprisesList;
    }

    @GetMapping("/toBeConfirmInfo")
    public PageResult<List<ConfirmInfoDTO>> findToBeConfirmInfo(int page,int pageSize){
        User user = ThreadLocalUtil.get();
        PageResult<List<ConfirmInfoDTO>> confirmInfo = wangyinService.queryToBeConfirmInfo(user.getCreditCode(), page, pageSize);
        return confirmInfo;
    }
    @PostMapping("/submitStartInfo")
    public Result<Void> submitContract(WangyinSignContractorDetailsDTO wangyinSignContractorDetailsDTO){
        User user = ThreadLocalUtil.get();
        wangyinService.submitContract(user.getCustomerId(),user.getCreditCode(),user.getUsername(),user.getAddress(), wangyinSignContractorDetailsDTO);
        return Result.success();
    }

    @PostMapping("/signature")
    public Result<Void> generateSignature(SignatureDTO signatureDTO){
        User user = ThreadLocalUtil.get();
        Signature signature = wangyinService.generateSignature(user.getUsername(),signatureDTO.getStarterCompanyName(),signatureDTO.getPayment());
        return Result.success();
    }

    @PostMapping("submitConfirmInfo")
    public Result<Void> submitConfirmInfoContract(WangyinSignContractorDetailsDTO wangyinSignContractorDetailsDTO){
        User user = ThreadLocalUtil.get();
        Result<Void> voidResult = wangyinService.submitConfirmInfoContract(user.getCreditCode(), user.getUsername(), wangyinSignContractorDetailsDTO);
        return voidResult;
    }
}
