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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	private AuthenticationManager authenticationManager;
	
	// ログイン後/homeに遷移させる
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors().disable();
//		http.csrf().disable();
//		http.headers(header -> {
//			header.frameOptions().disable();
//		});
//		// antMatchers()、mvcMatchers()はSpring security5.8にて削除
//		// requestMatchers()を使用する
//		http.authorizeHttpRequests(authorize -> {
//			authorize.requestMatchers("/api/chatApp").permitAll().anyRequest().authenticated();
//		});
//		http.formLogin(form -> {
//			form.defaultSuccessUrl("/home");
//		});
//		.addFilterAfter(new RedirectLoginUserFilter(),UsernamePasswordAuthenticationFilter.class);
		
		// ラムダDSLで記述
//		http.formLogin(login -> login
//				.loginProcessingUrl("/login/process")      // ユーザ名・パスワードの送信先
//				.loginPage("/login")               // ログイン画面のURL 
//				.usernameParameter("username")
//				.passwordParameter("password")
//				.defaultSuccessUrl("/chat")            // ログイン成功後のリダイレクト先URL
//				.failureForwardUrl("/login?error") // ログイン失敗後のリダイレクト先URL
//				.permitAll()                       // ログイン画面については未ログインでもアクセスできるようにする
//		).logout(logout -> logout
//				.logoutSuccessUrl("/")             // ログアウト後のリダイレクト先のURL
//		).authorizeHttpRequests(authz -> authz
//				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() //"/css/**"等はログイン無しでアクセス可能とする
//				.requestMatchers("/").permitAll()  // "/"はログイン無しでもアクセス可能とする
//				.requestMatchers("/general").hasRole("GENERAL")
//				.requestMatchers("/admin").hasRole("ADMIN")
//				.requestMatchers("/api/chatApp").permitAll().anyRequest().authenticated()
//		)
//		.cors(cors -> cors.disable())
//		.csrf(csrf -> csrf.disable());
		
		// 最小構成で試す
		http
        .formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
		)
        .logout((logout) -> logout.permitAll())
        .authorizeHttpRequests(authz -> authz
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        		.requestMatchers("/", "/home", "/api/chatapp/login")
                .permitAll()
                .anyRequest().authenticated()
        )
        .csrf(csrf -> csrf.disable())
//        .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
//        .addFilterAfter(new LoginUserFilter(),UsernamePasswordAuthenticationFilter.class);
//        .addFilter(new JsonAuthenticationFilter(authenticationManager))
//		.addFilterAfter(new LoginFilter(),JsonAuthenticationFilter.class)
		;
		
//		http
//		    .authorizeHttpRequests(requests -> requests
//		    		.requestMatchers("/", "/home").permitAll()
//		    		.anyRequest().authenticated()
//		    ).formLogin(form -> form
//		    		.loginPage("/login")
//		    		.permitAll()
//		    );
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
