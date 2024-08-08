package ai.osborn.ld_homework;


import com.launchdarkly.sdk.ContextKind;
import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.LDValue;
import com.launchdarkly.sdk.server.LDClient;
import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.security.Principal;

@Configuration
public class LaunchDarklyConfiguration {

    private static final Logger log = LoggerFactory.getLogger(LaunchDarklyConfiguration.class);

    @Bean
    LDClient getLaunchDarklyClient(@Value("${launchdarkly.sdk-key}") String key) {
        var client = new LDClient(key);
        // track flag changes here for logging
        client.getFlagTracker().addFlagChangeListener(event -> {
            log.info("Flag {} has changed", event.getKey());
        });
        return client;
    }

    public static LDContext toLDContext(Principal principal) {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, principal);
        var token = (UsernamePasswordAuthenticationToken) principal;
        Assert.isInstanceOf(SecurityConfiguration.WebsiteUser.class, token.getPrincipal());
        var user = (SecurityConfiguration.WebsiteUser) token.getPrincipal();
        return LDContext.builder(user.getEmailAddress())
                .name(user.getUsername())
                .set("email", user.getEmailAddress())
                .set("firstName", user.getFirstName())
                .set("lastName", user.getLastName())
                .set("groups", LDValue.arrayOf(user.getAuthorities().stream().map(g -> LDValue.of(g.getAuthority())).toArray(LDValue[]::new)))
                .kind(ContextKind.DEFAULT)
                .build();
    }

    public static LaunchDarklyContext toLaunchDarklyContext(Principal principal) {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, principal);
        var token = (UsernamePasswordAuthenticationToken) principal;
        Assert.isInstanceOf(SecurityConfiguration.WebsiteUser.class, token.getPrincipal());
        var user = (SecurityConfiguration.WebsiteUser) token.getPrincipal();

        return LaunchDarklyContext.builder()
                .kind("user")
                .key(user.getEmailAddress())
                .email(user.getEmailAddress())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .groups(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .build();
    }

    @Data
    @Builder
    public static class LaunchDarklyContext {
        private String kind;
        private String key;
        private String firstName;
        private String lastName;
        private String email;
        private String[] groups;
    }
}
