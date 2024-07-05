import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable().and() // 开启 CSRF 保护，默认使用
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())  // 使用 Cookie 存储 CSRF 令牌
                .and()
            .headers()
                .contentSecurityPolicy("script-src 'self'; report-uri /csp-report-endpoint/")  // 添加 CSP 策略
                .and()
            .frameOptions().sameOrigin()  // 防止点击劫持
            .and()
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()  // 允许所有人访问首页和/home
                .anyRequest().authenticated()  // 其他所有请求都需要认证
                .and()
            .formLogin()
                .loginPage("/login")  // 指定登录页面
                .permitAll()  // 允许所有人访问登录页面
                .and()
            .logout()
                .permitAll();  // 允许所有人注销
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
            .and()
            .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}