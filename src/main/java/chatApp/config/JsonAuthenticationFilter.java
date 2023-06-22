package chatApp.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	
	public JsonAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		
		// ログインパスを設定
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
		
		// ログイン用パラメータの設定
		setUsernameParameter("username");
		setPasswordParameter("password");
		
		// ログイン成功時はtokenを発行してレスポンスにセットする
		this.setAuthenticationSuccessHandler((req, res, ex) -> {
			// トークンの生成
			String token = JWT.create()
					.withIsuser("com.chatApp") // h発行者
					.withClaim("username", ex.getName()) // keyに対してvalueの設定。汎用的な様々な値を保持できる
					.sign(Algorithm.HMA256("secret")); // 利用アルゴリズムを指定してJWTを新規作成
			res.setHeader("X-AUTH-TOKEN", token); // tokeをX-AUTH-TOKENというKeyにセットする
			res.setStatus(200);
		});
		
		// ログイン失敗時
		this.setAuthenticationFailureHandler((req,res,ex) -> {
			req.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		})
	}
}
