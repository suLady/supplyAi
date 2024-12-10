package springboot.example.supplyAi.service;

import springboot.example.supplyAi.domain.EnterpriseUser;
import springboot.example.supplyAi.domain.PageResult;

import java.util.List;

public interface EnterpriseUserService {
    PageResult<List<EnterpriseUser>> findCompanyCreditCode(String creditCode, int page, int pageSize);
}
