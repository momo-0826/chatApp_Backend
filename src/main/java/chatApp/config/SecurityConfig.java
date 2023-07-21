package chatApp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
			.requestMatchers("/", "/hello", "/login").permitAll()
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form
//			.loginPage("/login")
			.defaultSuccessUrl("/hello")
			.failureUrl("/error")
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
	
//	// インメモリ認証
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withUsername("user")
////				.password(
////						PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("password")
////				)
//				.password("$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//				.roles("USER")
//				.build();
//		System.out.println(user.getUsername());
//		System.out.println(user.getPassword());
//		return new InMemoryUserDetailsManager(user);
//	}

//    // jdbc認証
//	@Bean
//	public UserDetailsManager userDetailsService() {
//		// DB上のusersテーブルに指定のデータを作成する
//		JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);
//		
////		UserDetails user = User.withUsername("user")
//////				.password(
//////						PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("test")
//////				)
////				.password("$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
////				.roles("USER")
////				.build();
//		UserDetails user = User.builder()
//				.username("user")
//				.password("$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//				.roles("USER")
//				.build();
//		UserDetails admin = User.builder()
//				.username("admin")
//				.password("$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//				.roles("USER", "ADMIN")
//				.build();
//		users.createUser(user);
//		users.createUser(admin);
//		return users;
//	}
	
	/**
     * パスワードをBCryptで暗号化するクラス
     * @return パスワードをBCryptで暗号化するクラスオブジェクト
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
