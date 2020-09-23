package com.example.lesscode.service.impl;

import com.example.lesscode.dao.SecurityUserDao;
import com.example.lesscode.domain.AuthClientDetails;
import com.example.lesscode.domain.CustomClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @Date: 2019/12/11 14:51
 * @Author: Qtl
 * @Description:
 */
@Service
public class CustomClientDetailsService implements ClientDetailsService {

    @Autowired
    private SecurityUserDao dao;

    /**
     * Load a client by the client id. This method must not return null.
     *
     * @param clientId The client id.
     * @return The client details (never null).
     * @throws ClientRegistrationException If the client account is locked, expired, disabled, or invalid for any other reason.
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        AuthClientDetails clientDetails = dao.selectClientDetailsByClientId(clientId);
        if (clientDetails == null) {
            throw new ClientRegistrationException("该客户端不存在");
        }
        CustomClientDetails details = new CustomClientDetails(clientDetails);
        return details;
    }
}
