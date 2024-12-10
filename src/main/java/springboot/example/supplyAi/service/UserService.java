package springboot.example.supplyAi.service;

import springboot.example.supplyAi.domain.Result;
import springboot.example.supplyAi.domain.User;
import springboot.example.supplyAi.dto.UserDTO;

import java.security.NoSuchAlgorithmException;

public interface UserService {

    void register(UserDTO userDTO) throws NoSuchAlgorithmException;

    User findUserBycustomerId(String customerId);

    Result findAccountInfo(String customerId, String accountType);

}
