package chatApp.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTGenerationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO 自動生成されたメソッド・スタブ
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			
		}
		
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return request.getServletPath() != "/me";
	}
	
	private String generateJWT(Authentication authentication) {
		var test = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.toByteArray(StandardCharsets.UTF_8));
	}

}
