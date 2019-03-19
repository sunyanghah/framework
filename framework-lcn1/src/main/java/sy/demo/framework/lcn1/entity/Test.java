package sy.demo.framework.lcn1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by dell on 2019/3/19.
 * @author dell
 */
@Data
@TableName("tf_f_test")
public class Test {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;
}
