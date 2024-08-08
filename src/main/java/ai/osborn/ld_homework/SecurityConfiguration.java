package ai.osborn.ld_homework;

import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userList = new ArrayList<WebsiteUser>() {{
            add(new WebsiteUser("joseph@belltracy.com",
                    "Joseph",
                    "Hunter",
                    "jojo",
                    bCryptPasswordEncoder().encode("gold"),
                    List.of("USER"), List.of("PHASE_A", "CUSTOMER_AMAZON.COM")));
            add(new WebsiteUser("samone@launchdarkly.com",
                    "Samone",
                    "Biles",
                    "samone",
                    bCryptPasswordEncoder().encode("gold"),
                    List.of("USER", "QA_TESTER"), List.of("EMPLOYEE_LD")));
            add(new WebsiteUser("cmarks@tahiti.com",
                    "Caroline",
                    "Marks",
                    "surfer",
                    bCryptPasswordEncoder().encode("gold"),
                    List.of("USER", "QA_TESTER"), List.of("CONTRACTOR")));
            add(new WebsiteUser("vincent@skeet.com",
                    "Vincent",
                    "Hancock",
                    "skeet",
                    bCryptPasswordEncoder().encode("gold"),
                    List.of("USER"), List.of("EMPLOYEE_SKEET")));
        }};
        return new InMemoryWebsiteUserDetailsManager(userList.toArray(new WebsiteUser[0]));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .csrf(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    public static class WebsiteUser implements UserDetails {

        @Getter
        private final String emailAddress;
        @Getter
        private final String firstName;
        @Getter
        private final String lastName;
        private final String userName;
        private final String password;
        private final Set<GrantedAuthority> authorities;
        private final Set<GrantedAuthority> roles;

        WebsiteUser(String emailAddress,
                    String firstName, String lastName,
                    String userName,
                    String password,
                    Collection<String> authorities,
                    Collection<String> roles) {
            this.emailAddress = emailAddress;
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName = userName;
            this.password = password;
            this.authorities = authorities.stream()
                    .filter(StringUtils::hasLength)
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toUnmodifiableSet());
            this.roles = roles.stream()
                    .filter(StringUtils::hasLength)
                    .map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toUnmodifiableSet());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.authorities;
        }

        @Override
        public String getPassword() {
            return this.password;
        }

        @Override
        public String getUsername() {
            return this.userName;
        }

        public Collection<? extends GrantedAuthority> getRoles() {
            return this.roles;
        }
    }

    public static class InMemoryWebsiteUserDetailsManager implements UserDetailsManager {

        protected final Log logger = LogFactory.getLog(getClass());

        private final Map<String, WebsiteUser> users = new HashMap<>();

        public InMemoryWebsiteUserDetailsManager(UserDetails... users) {
            for (UserDetails user : users) {
                createUser(user);
            }
        }

        @Override
        public void createUser(UserDetails user) {
            Assert.isTrue(!userExists(user.getUsername()), "user should not exist");
            Assert.isInstanceOf(WebsiteUser.class, user);
            this.users.put(user.getUsername().toLowerCase(), (WebsiteUser) user);
        }

        @Override
        public void deleteUser(String username) {
            this.users.remove(username.toLowerCase());
        }

        @Override
        public void updateUser(UserDetails user) {
            Assert.isTrue(userExists(user.getUsername()), "user should exist");
            Assert.isInstanceOf(WebsiteUser.class, user);
            this.users.put(user.getUsername().toLowerCase(), (WebsiteUser) user);
        }

        @Override
        public boolean userExists(String username) {
            return this.users.containsKey(username.toLowerCase());
        }

        @Override
        public void changePassword(String oldPassword, String newPassword) {
            logger.info("change password is not implemented yet");
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            WebsiteUser user = this.users.get(username.toLowerCase());
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
            Assert.isInstanceOf(WebsiteUser.class, user);
            return user;
        }
    }
}