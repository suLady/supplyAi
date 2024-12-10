package springboot.example.supplyAi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private String code;
    private String message;
    private T data;

    public static Result success(){
        return new Result("0000","操作成功",null);
    }
    
    public static Result error(String message){
        return new Result("1111",message,null);
    }

    public static<T> Result<T> success(T data){
        return new Result<>("0000","操作成功",data);
    }
}