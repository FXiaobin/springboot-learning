package com.example.springboot_learning.config;

import com.example.springboot_learning.model.user.SecurityUserDetails;
import com.example.springboot_learning.serviceImpl.SecurityUserDetailService;
//import com.example.springboot_learning.utils.securityUtils.JwtRequestFilter;
import com.example.springboot_learning.utils.securityUtils.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity  //开启webSecurity服务
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) //启用方法级别的认证
public class SecurityConfig {

    /**
     * 配置用户信息查询
     */
    @Autowired
    private SecurityUserDetailService securityUserDetailService;


    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    /**
     * 配置JWT过滤器
     */
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    /**
     * 配置登录成功处理器
     */
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    /**
     * 配置登录失败处理器
     */
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 配置退出成功处理器
     */
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;



    @Autowired
    AuthenticationEntryPointImpl authenticationEntryPointImpl;
//
//    @Autowired
//    AccessDeniedHandlerImpl accessDeniedHandlerImpl;


    /**
     * 配置密码编码器。
     *
     * 本方法通过实例化一个BCryptPasswordEncoder对象来提供密码编码服务。BCryptPasswordEncoder是一种基于bcrypt算法的密码编码器，
     * 它用于将用户密码加密为安全的hash值，以保护密码不被未授权访问。在Spring Security中，PasswordEncoder接口的实现用于验证用户输入的密码
     * 是否与存储的hash密码匹配。
     *
     * @return 返回一个新的BCryptPasswordEncoder实例，用于对用户密码进行编码。
     */
    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    /**
     * 配置认证提供者，用于处理认证请求。
     *
     * @return AuthenticationProvider 实例，用于验证用户身份。
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        // 创建DaoAuthenticationProvider实例，用于支持基于数据库的用户认证。
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // 配置用户详情服务，用于从数据库中加载用户信息。
        authenticationProvider.setUserDetailsService(securityUserDetailService);
        // 配置密码编码器，用于加密和验证用户密码。
        authenticationProvider.setPasswordEncoder(bcryptPasswordEncoder());
        // 确保用户未找到异常被适当处理，通过设置hideUserNotFoundExceptions标志。
        // 这是一个设计选择；如果你希望异常向上抛出以便在更高层级处理，可以省略此行。
//        authenticationProvider.setHideUserNotFoundExceptions(false);
        // 返回认证管理器，供Spring Security框架使用。
        AuthenticationManager authenticationManager = new ProviderManager(authenticationProvider);
        return authenticationManager;
    }



//  # 说明
//- permitAll() 代表放行该资源,该资源为公共资源 无需认证和授权可以直接访问
//- anyRequest().authenticated() 代表所有请求,必须认证之后才能访问
//- formLogin() 代表开启表单认证
//  ## 注意: 放行资源必须放在所有认证请求之前!

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(
                        Customizer.withDefaults()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/user/addUser").permitAll()
                                .requestMatchers("/user/loginWithUserNamePassword").permitAll()
                                .requestMatchers("/user/selectAllUserList").permitAll()
                                .requestMatchers(HttpMethod.GET, "/").permitAll()
                                // 其它接口都要认证
                                 .anyRequest().authenticated()

//                                .anyRequest().permitAll()
                )
//                .formLogin(formLogin -> formLogin
//
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .successHandler(myAuthenticationSuccessHandler)
//                        .failureHandler(myAuthenticationFailureHandler)
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessHandler(myLogoutSuccessHandler)
//                        .permitAll()
//                )
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .authenticationEntryPoint(authenticationEntryPointImpl)
//                )

                // 关闭session
//        // 关闭原因：
//        // 1. 前后端进行通信，每个请求都是一个独立的事务，开启session管理可能会使得信息无法共享
//        // 2. 采用session管理的话，多个用户进行访问服务器端的内存会占用过高，这是因为session的废除机制是超时机制
//        // 3. 采用session管理功能，这也是一个安全漏洞
//        // 这里使用jwt（Java web token）令牌的方式进行认证，不需要session了
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                /**
                 * 将token验证添加在密码验证前面
                 * 在安全配置中添加过滤器，用于在执行任何Spring Security的过滤之前处理JWT令牌。
                 * 这一步是必要的，因为它允许我们验证JWT令牌的有效性，并在验证成功后设置安全上下文，
                 * 从而让Spring Security能够基于令牌的内容做出授权决策。
                 *
                 * @param jwtRequestFilter JWT请求过滤器，负责解析JWT令牌并进行相应的安全上下文设置。
                 * @param usernamePasswordAuthenticationFilter Spring Security的默认用户名密码认证过滤器类，
                 * 用于指定过滤器的执行顺序，确保JWT请求过滤器在它之前执行。
                 */
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)

//                .authenticationManager(authenticationManager())
//                .authenticationProvider(authenticationProvider())
//                .exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandlerImpl))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPointImpl))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));


        return http.build();
    }


}

