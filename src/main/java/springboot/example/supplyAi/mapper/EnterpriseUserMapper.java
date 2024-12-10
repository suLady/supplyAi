package springboot.example.supplyAi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springboot.example.supplyAi.domain.EnterpriseUser;

import java.util.List;

@Mapper
public interface EnterpriseUserMapper {
    List<EnterpriseUser> queryComCreditCode(@Param("creditCode") String creditCode, @Param("offset") int offset, @Param("pageCount") int pageCount);

    int queryTotalCompany(String creditCode);

}
