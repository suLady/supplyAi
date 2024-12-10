package springboot.example.supplyAi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.example.supplyAi.domain.Result;
import springboot.example.supplyAi.domain.User;
import springboot.example.supplyAi.dto.AccountDTO;
import springboot.example.supplyAi.dto.UserDTO;
import springboot.example.supplyAi.mapper.UserMapper;
import springboot.example.supplyAi.service.UserService;
import springboot.example.supplyAi.utils.SHA256Util;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserBycustomerId(String  customerId) {
        User user = userMapper.queryUserByCustomerId(customerId);
        return user;
    }

    @Override
    public Result findAccountInfo(String customerId, String accountType) {
        ArrayList<AccountDTO> accounts = userMapper.getAccountInfo(customerId,accountType);
        return Result.success(accounts);
    }


    @Override
    public void register(UserDTO userDTO) throws NoSuchAlgorithmException {
        userMapper.addUser(userDTO.getCustomerId(),SHA256Util.encrypt(userDTO.getPassword()),userDTO.getCreditCode(),userDTO.getUsername(),userDTO.getAddress());
    }

}
