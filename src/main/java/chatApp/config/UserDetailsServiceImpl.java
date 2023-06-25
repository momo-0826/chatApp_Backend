package chatApp.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import chatApp.model.UserInfo;
import chatApp.repository.UserInfoRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			UserInfo entity = userInfoRepository.findByUserName(username);
			return new User(entity.getUserName(), PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(entity.getPassword()), new ArrayList<>());
		} catch (Exception e) {
			throw new UsernameNotFoundException("ユーザーが見つかりません");
		}
	}
	
}
