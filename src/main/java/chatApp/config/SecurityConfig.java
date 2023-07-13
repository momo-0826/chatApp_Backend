package chatApp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	private AuthenticationManager authenticationManager;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/", "/home", "/login").permitAll()
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form
//			.loginPage("/login")
			.permitAll()
		)
		.logout((logout) -> logout.permitAll()
		)
		.csrf((csrf) -> csrf.disable());
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
		corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
		corsConfiguration.addExposedHeader("X-AUTH-TOKEN");
		corsConfiguration.addAllowedOrigin("http://localhost:8080");
		corsConfiguration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource corsSource =new UrlBasedCorsConfigurationSource();
		corsSource.registerCorsConfiguration("/**", corsConfiguration);
		return corsSource;
	}
	
	// インメモリ認証
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("user")
//				.password(
//						PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password")
//				)
				.password("{bcrypt}\\$2a\\$10\\$1gHHMqYmv7spE.896lYtKuenhXSRGyZ0FK.JTzAOSD6qgRKtPl5wy")
				.roles("USER")
				.build();
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		return new InMemoryUserDetailsManager(user);
	}

	/**
     * パスワードをBCryptで暗号化するクラス
     * @return パスワードをBCryptで暗号化するクラスオブジェクト
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
	
//	@Bean
//	public UserDetailsManager userDetailsService() {
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);
//		// DBに指定のデータを作成する
//		UserDetails user = User.withUsername("user")
//				.password(
//						PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("test")
//				).roles("USER")
//				.build();
//		users.createUser(user);
//		return users;
//	}
}
