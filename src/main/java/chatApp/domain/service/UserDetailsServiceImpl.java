package chatApp.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import chatApp.model.Roles;
import chatApp.model.UserInfo;
import chatApp.model.UserRole;
import chatApp.repository.RolesRepository;
import chatApp.repository.UserAuthRepository;
import chatApp.repository.UserInfoRepository;
import chatApp.repository.UserRoleRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserInfoRepository userInfoRepository;
	private final UserAuthRepository userAuthRepository;
	private final RolesRepository rolesRepository;
	private final UserRoleRepository userRoleRepository;
	
	public UserDetailsServiceImpl(UserInfoRepository userInfoRepository, UserAuthRepository userAuthRepository, RolesRepository rolesRepository, UserRoleRepository userRoleRepository) {
		this.userInfoRepository = userInfoRepository;
		this.userAuthRepository = userAuthRepository;
		this.rolesRepository = rolesRepository;
		this.userRoleRepository = userRoleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			// TODO	:1度のDBアクセスで情報を取得するようにする
//			List<UserAuthDto> userAuthDtoList = this.userAuthRepository.selectUserAuth(username);
//			if (userAuthDtoList.size() == 0) {
//			return null;
//			}
//			UserAuthDto userAuthDto = userAuthDtoList.get(0);
//		
//			Set<Roles> roles = new HashSet<>();
//			userAuthDtoList.forEach(dto -> {
//				roles.add(dto.getRole());
//			});
//		
//			ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//			roles.forEach(role -> {
//				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//			});
			
			// 以下、暫定版
			// ユーザ名に紐づくデータを取得する
			UserInfo userInfo = this.userInfoRepository.findByUserName(username);
			// ユーザの持っている権限データを取得する
			List<UserRole> userRoleList = this.userRoleRepository.findAllById(userInfo.getId());
			List<Long> roleIdList = new ArrayList<>();
			userRoleList.forEach(userRole -> {
				roleIdList.add(userRole.getRoleId());
			});
			
			// 権限名を取得する
			List<Roles> roles = this.rolesRepository.findByIdIn(roleIdList);
			
			ArrayList<GrantedAuthority> authorities = new ArrayList<>();
			roles.forEach(role -> {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
			});

		
			return new User(userInfo.getUserName(), userInfo.getPassword(), userInfo.getEnabled(), true, true, true, authorities);
		}catch (UsernameNotFoundException  e) {
			throw new UsernameNotFoundException("User details not found for the user :" + username);
		}
	}

}
