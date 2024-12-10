package springboot.example.supplyAi.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.example.supplyAi.domain.Enterprise;


@Mapper
public interface EnterpriseMapper{
    Enterprise queryCoreCompany(String companyCreditCode);

}
