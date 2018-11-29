package sy.demo.framework.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import sy.demo.framework.auth.client.mapper.ClientMapper;

/**
 * Created by dell on 2018/11/12.
 */
public class CustomClientService implements ClientDetailsService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        CustomClientDetails customClientDetails = new CustomClientDetails();
        customClientDetails.setSysOauthClientDetails(clientMapper.selectById(clientId));
        return customClientDetails;
    }
}
