package sy.demo.framework.jenkinsK8s.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by dell on 2019/3/20.
 * @author dell
 */
@Data
public class TestDto {

    @JsonFormat(pattern = "yyyy-MM")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM")
    private Date endDate;
}
