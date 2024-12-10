package springboot.example.supplyAi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.example.supplyAi.domain.Enterprise;
import springboot.example.supplyAi.domain.Result;
import springboot.example.supplyAi.mapper.EnterpriseMapper;
import springboot.example.supplyAi.service.EnterpriseService;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public Result<Enterprise> findCoreCompany(String companyCreditCode) {
        Enterprise enterprise = enterpriseMapper.queryCoreCompany(companyCreditCode);
        return Result.success(enterprise);
    }

}
