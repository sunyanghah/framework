package sy.demo.framework.auth.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by dell on 2018/10/31.
 * @author dell
 */
@TableName("sys_user")
@Data
public class SysUser implements Serializable{

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

}
