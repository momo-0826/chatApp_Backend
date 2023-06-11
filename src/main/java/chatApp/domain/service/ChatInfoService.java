package chatApp.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import chatApp.dto.ChatInfoDto;
import chatApp.dto.FriendDto;
import chatApp.dto.UserInfoDto;
import chatApp.model.Friend;
import chatApp.model.UserInfo;
import chatApp.repository.FriendRepository;
import chatApp.repository.UserInfoRepository;

@Service
public class ChatInfoService {
	private final UserInfoRepository userInfoRepository;
	private final FriendRepository friendRepository;
	
	public ChatInfoService(UserInfoRepository userInfoRepository, FriendRepository friendRepository) {
		this.userInfoRepository = userInfoRepository;
		this.friendRepository = friendRepository;
	}
	
	// 登録ユーザ情報返却
	public ChatInfoDto getUserInfo(String userName, String password) {
		ChatInfoDto chatInfoDto = new ChatInfoDto();
		
		// ユーザ情報を取得
		UserInfo userInfo = userInfoRepository.findByUserNameAndPassword(userName, password);
		
		// 取得したユーザ情報をdtoにコピー
		UserInfoDto userInfoDto = new UserInfoDto();
		BeanUtils.copyProperties(userInfo, userInfoDto);
		
		// ユーザ情報のIdを使用して友達情報を取得
		List<Friend> friendsId = friendRepository.findByUserId(userInfo.getId());
				
		// 取得した友達情報のfriendUserIdを使用して対象ユーザの友達であるユーザの情報を全員分取得
		List<Long> idList = new ArrayList<>();
		for(Friend entity: friendsId) {
			idList.add(entity.getFriendUser().getId());
		}
		
		List<UserInfo> friendsInfo = userInfoRepository.findByIdList(idList);
		
		if(!friendsInfo.isEmpty()) {
			for(Friend entity: friendsId) {
				FriendDto friendDto = new FriendDto();
				friendDto.setId();
			}
			
		}
		
		
		
		return chatInfoDto;
	}
	
}