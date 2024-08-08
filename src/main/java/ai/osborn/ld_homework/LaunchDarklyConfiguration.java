package ai.osborn.ld_homework;


import com.launchdarkly.sdk.ContextKind;
import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.security.Principal;

@Configuration
public class LaunchDarklyConfiguration {

    @Bean
    LDClient getLaunchDarklyClient(@Value("${launchdarkly.sdk-key}") String key) {
        return new LDClient(key);
    }

    public static LDContext toLDContext(Principal principal) {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, principal);
        var token = (UsernamePasswordAuthenticationToken) principal;
        Assert.isInstanceOf(SecurityConfiguration.WebsiteUser.class, token.getPrincipal());
        var user = (SecurityConfiguration.WebsiteUser) token.getPrincipal();
        return LDContext.builder(user.getEmailAddress())
                .name(user.getFirstName())
                .set("email", user.getEmailAddress())
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
