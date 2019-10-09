package com.codegym.ApplicationConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomSuccessHandle customSuccessHandle;


}
