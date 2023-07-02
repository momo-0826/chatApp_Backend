package chatApp.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
		System.out.println("doFilterInternal is called");
		// headerからTokenを取得する
		String header = request.getHeader("X-AUTH-TOKEN");
		System.out.println("header");
		System.out.println(header);
		
		// チェック
		if (header == null || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			System.out.println("header is null");
			return;
		}
		
		String token = header.substring(7);
		
		// tokenの検証と認証を行う
		DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret")).build().verify(token);
		System.out.println("decodedJWT");
		System.out.println(decodedJWT);
		// usernameの取得
		String username = decodedJWT.getClaim("username").asString();
		System.out.println("username");
		System.out.println(username);
		// ログイン状態を設定
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()));
		filterChain.doFilter(request, response);
	}
}
