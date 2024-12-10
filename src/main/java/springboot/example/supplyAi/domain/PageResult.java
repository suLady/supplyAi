package springboot.example.supplyAi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private String code;
    private String message;
    private int page;
    private int pageSize;
    private int totalPages;
    private int totalCompany;
    private T data;




    public static<T> PageResult success(int page,int pageSize,int totalPages,int totalCompany,T data){
        PageResult pageResult = new PageResult<>();
        pageResult.setCode("0000");
        pageResult.setMessage("操作成功");
        pageResult.setPage(page);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalPages(totalPages);
        pageResult.setTotalCompany(totalCompany);
        pageResult.setData(data);
        return pageResult;
    }

    public static PageResult error(int page,int pageSize,int totalPages,int totalCompany){
        PageResult pageResult = new PageResult<>();
        pageResult.setCode("1111");
        pageResult.setMessage("操作失败");
        pageResult.setPage(page);
        pageResult.setPageSize(pageSize);
        pageResult.setTotalPages(totalPages);
        pageResult.setTotalCompany(totalCompany);
        pageResult.setData(null);
        return pageResult;
    }

}
