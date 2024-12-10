package springboot.example.supplyAi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import springboot.example.supplyAi.domain.User;
import springboot.example.supplyAi.dto.AccountDTO;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Select("select * from user where customer_id = #{customerId}")
    User queryUserByCustomerId(@Param("customerId") String customerId);

    @Insert("insert into user(customer_id,password,credit_code,username,address) values (#{customerId},#{password},#{creditCode},#{username},#{address})")
    void addUser(@Param("customerId") String customerId, @Param("password")String password, @Param("creditCode")String creditCode,@Param("username") String username, @Param("address") String address);

    @Select("select * from account where customer_id= #{customerId} and account_type = #{accountType}")
    ArrayList<AccountDTO> getAccountInfo(@Param("customerId") String customerId, @Param("accountType") String accountType);
}
