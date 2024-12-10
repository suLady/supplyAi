package springboot.example.supplyAi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.example.supplyAi.domain.EnterpriseUser;
import springboot.example.supplyAi.domain.PageResult;
import springboot.example.supplyAi.mapper.EnterpriseUserMapper;
import springboot.example.supplyAi.service.EnterpriseUserService;

import java.util.List;

@Service
public class EnterpriseUserServiceImpl implements EnterpriseUserService {
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;
    @Override
    public PageResult<List<EnterpriseUser>> findCompanyCreditCode(String creditCode, int page, int pageSize) {
        int offset = (page - 1) * page;
        int pageCount = pageSize;
        List<EnterpriseUser> enterpriseUsers = enterpriseUserMapper.queryComCreditCode(creditCode,offset,pageCount);
        int totalCompany = enterpriseUserMapper.queryTotalCompany(creditCode);
        int totalPages = (int) Math.ceil((double)totalCompany/pageSize);
        return PageResult.success(page,pageSize,totalPages,totalCompany, enterpriseUsers);
    }
}
