package talk.innertalk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
@EnableWebSecurity
public class WebConfig {

    //시큐리티 필터 메서드
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/innertalk/","/innertalk","/innertalk/home","/innertalk/login","/innertalk/signup").permitAll()
                        .anyRequest().authenticated()
                );

        //로그인 설정
        http
                .formLogin((auth)->auth
                        .loginPage("/innertalk/login")
                        .loginProcessingUrl("/innertalk/login-processing")
                        .failureUrl("/innertalk/login")
                        .defaultSuccessUrl("/innertalk/home")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll()
                );

        //로그아웃 설정
        http
                .logout((auth)->auth.logoutUrl("/innertalk/logout").logoutSuccessUrl("/innertalk/home"));

        http
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
