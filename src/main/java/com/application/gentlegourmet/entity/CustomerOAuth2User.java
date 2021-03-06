package com.application.gentlegourmet.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomerOAuth2User implements OAuth2User {

    //receives the default OAuth2User instance form CustomerOAuth2UserService : it allows to obtain the user attributes
    private final OAuth2User oAuth2User;

    //////////////////////////////////////////////////////////////////////

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    //added to use in thymeleaf view
    @Override
    public String getName() {
        return oAuth2User.getAttribute("name");
    }


}
