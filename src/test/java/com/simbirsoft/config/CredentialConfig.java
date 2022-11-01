package com.simbirsoft.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:credential.properties"
})
public interface CredentialConfig extends Config {

    String browserstack_user();
    String browserstack_key();
    String browserstack_url();
    String selenoid_user();
    String selenoid_password();
    String selenoid_url();
}
