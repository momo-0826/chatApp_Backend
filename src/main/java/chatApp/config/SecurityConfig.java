package chatApp.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// ログイン後/homeに遷移させる
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors().disable();
//		http.csrf().disable();
		http.headers(header -> {
			header.frameOptions().disable();
		});
		// antMatchers()、mvcMatchers()はSpring security5.8にて削除
		// requestMatchers()を使用する
//		http.authorizeHttpRequests(authorize -> {
//			authorize.requestMatchers("/api/chatApp").permitAll().anyRequest().authenticated();
//		});
//		http.formLogin(form -> {
//			form.defaultSuccessUrl("/home");
//		});
		
		// ラムダDSLで記述
		http.formLogin(login -> login
				.loginProcessingUrl("/login")      // ユーザ名・パスワードの送信先
				.loginPage("/login")               // ログイン画面のURL 
				.defaultSuccessUrl("/")            // ログイン成功後のリダイレクト先URL
				.failureForwardUrl("/login?error") // ログイン失敗後のリダイレクト先URL
				.permitAll()                       // ログイン画面については未ログインでもアクセスできるようにする
		).logout(logout -> logout
				.logoutSuccessUrl("/")             // ログアウト後のリダイレクト先のURL
		).authorizeHttpRequests(authz -> authz
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //"/css/**"等はログイン無しでアクセス可能とする
				.requestMatchers("/").permitAll()  // "/"はログイン無しでもアクセス可能とする
				.requestMatchers("/api/chatApp").permitAll().anyRequest().authenticated()
		)
		.cors(cors -> cors.disable())
		.csrf(csrf -> csrf.disable());           
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
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user")
				.password(
						PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("test")
				).roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
