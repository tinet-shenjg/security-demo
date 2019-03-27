package com.security.demo.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息controller
 *
 * @author shenjg
 * @date 2019/03/27
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private TokenStore tokenStore;

    @PreAuthorize("hasAuthority('READ') or hasAuthority('WRITE')")
    @GetMapping("name")
    public String name(OAuth2Authentication authentication) {
        return authentication.getName();
    }

    @PreAuthorize("hasAuthority('READ') or hasAuthority('WRITE')")
    @GetMapping
    public OAuth2Authentication read(OAuth2Authentication authentication) {
        return authentication;
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping
    public Object write(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        return accessToken.getAdditionalInformation().getOrDefault("userDetails", null);
    }
}
