package chatApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	// ログイン後/homeに遷移させる
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers(header -> {
			header.frameOptions().disable();
		});
		// antMatchers()、mvcMatchers()はSpring security5.8にて削除
		// requestMatchers()を使用する
		// "/h2-console/**"以下は、認可を必要とせず、対象パスへのアクセスを許可する。
		http.authorizeHttpRequests(authorize -> {
			authorize.requestMatchers("/h2-console/**").permitAll().anyRequest().authenticated();
		});
		http.formLogin(form -> {
			form.defaultSuccessUrl("/home");
		});
		return http.build();
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
