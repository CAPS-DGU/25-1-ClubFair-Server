package caps.tf.security.config;

import caps.tf.constants.Constants;
import caps.tf.security.filter.JwtAuthenticationFilter;
import caps.tf.security.filter.JwtExceptionFilter;
import caps.tf.security.handler.exception.CustomAccessDeniedHandler;
import caps.tf.security.handler.exception.CustomAuthenticationEntryPointHandler;
import caps.tf.security.handler.login.DefaultFailureHandler;
import caps.tf.security.handler.login.DefaultSuccessHandler;
import caps.tf.security.handler.logout.CustomLogoutHandler;
import caps.tf.security.handler.logout.CustomLogoutSuccessHandler;
import caps.tf.security.provider.JwtAuthenticationManager;
import caps.tf.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final JwtAuthenticationManager jwtAuthenticationManager;
    private final DefaultSuccessHandler defaultSuccessHandler;
    private final DefaultFailureHandler defaultFailureHandler;
    private final CustomLogoutHandler customLogoutHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPointHandler customAuthenticationEntryPointHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(Constants.NO_NEED_AUTH.toArray(String[]::new)).permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/wiki/public/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/api/auth/sign-in")
                        .usernameParameter("id")
                        .passwordParameter("password")
                        .successHandler(defaultSuccessHandler)
                        .failureHandler(defaultFailureHandler)
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/api/auth/logout")
                                .addLogoutHandler(customLogoutHandler)
                                .logoutSuccessHandler(customLogoutSuccessHandler)
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(customAuthenticationEntryPointHandler)
                )
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtUtil, jwtAuthenticationManager), LogoutFilter.class
                )
                .addFilterBefore(
                        new JwtExceptionFilter(), JwtAuthenticationFilter.class
                )
                .getOrBuild();
    }
}