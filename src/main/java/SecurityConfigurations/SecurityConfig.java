package SecurityConfigurations;

import enums.RoleName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth->auth
                .requestMatchers("/admin/**").hasRole(RoleName.ROLE_ADMIN.name())  // Restrict access to /admin/** for users with ADMIN role
                .requestMatchers("/**").permitAll()  // Permit access to all other URLs
                              .anyRequest().authenticated() // Requires authentication for all endpoints
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF if needed
                .exceptionHandling(ex -> ex.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))) // Redirect to login page
                .logout(logout -> logout.logoutSuccessUrl("/login?logout=true"));
        return http.build();


    }




}