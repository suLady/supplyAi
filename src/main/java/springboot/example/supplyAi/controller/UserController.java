package springboot.example.supplyAi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import springboot.example.supplyAi.domain.Result;
import springboot.example.supplyAi.domain.User;
import springboot.example.supplyAi.dto.UserDTO;
import springboot.example.supplyAi.service.UserService;
import springboot.example.supplyAi.utils.JWTUtil;
import springboot.example.supplyAi.utils.SHA256Util;
import springboot.example.supplyAi.utils.ThreadLocalUtil;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @PostMapping("/register")
    public Result register(@Valid UserDTO userDTO) throws NoSuchAlgorithmException {
        User user = userService.findUserBycustomerId(userDTO.getCustomerId());
        if(user == null){
            userService.register(userDTO);
            return Result.success();
        }else{
            return Result.error("用户账号已注册");
        }
    }

    @PostMapping("/login")
    public Result login(UserDTO userDTO) throws NoSuchAlgorithmException {
        User loginUser = userService.findUserBycustomerId(userDTO.getCustomerId());
        if(loginUser != null){
            if(SHA256Util.encrypt(userDTO.getPassword()).equals(loginUser.getPassword())){
                String token = JWTUtil.generateToken(loginUser.getCustomerId(),1000*60*60*12);
                return Result.success(token);
            }else{
                return Result.error("密码不正确");
            }
        }else{
            return Result.error("用户不存在");
        }
    }

    @GetMapping("/userInfo")
    public Result<User> queryUserInfo(){
        User user  = (User) ThreadLocalUtil.get();
        return Result.success(user);
    }

    @GetMapping("/accountInfo")
    public Result queryUserAccount(String accountType){
        User user = (User) ThreadLocalUtil.get();
        return userService.findAccountInfo(user.getCustomerId(),accountType);
    }
}
