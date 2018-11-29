package sy.demo.framework.common.dto.gateway;

import lombok.Data;

import java.util.List;

/**
 * Created by dell on 2018/11/8.
 * @author dell
 */
@Data
public class OutResourcesDto {

    private String resourceMethod;

    private String resourceUrl;

    private String serviceId;
}
