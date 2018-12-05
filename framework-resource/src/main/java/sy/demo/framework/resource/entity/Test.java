package sy.demo.framework.resource.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * Created by dell on 2018/12/4.
 * @author dell
 */
@Data
@TableName("test")
public class Test {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;
}
