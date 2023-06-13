package chatApp.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import chatApp.dto.ChatInfoDto;
import chatApp.dto.FriendDto;
import chatApp.dto.MessageDto;
import chatApp.dto.UserInfoDto;
import chatApp.model.Friend;
import chatApp.model.Message;
import chatApp.model.UserInfo;
import chatApp.repository.FriendRepository;
import chatApp.repository.MessageRepository;
import chatApp.repository.UserInfoRepository;

@Service
public class ChatInfoService {
	private final UserInfoRepository userInfoRepository;
	private final FriendRepository friendRepository;
	private final MessageRepository messageRepository;
	
	public ChatInfoService(UserInfoRepository userInfoRepository, FriendRepository friendRepository, MessageRepository messageRepository) {
		this.userInfoRepository = userInfoRepository;
		this.friendRepository = friendRepository;
		this.messageRepository = messageRepository;
		
	}
	
	// 登録ユーザ情報返却
	public ChatInfoDto getChatInfo(String userName, String password) {
		ChatInfoDto chatInfoDto = new ChatInfoDto();
		
		// ユーザ情報を取得
		UserInfo userInfo = userInfoRepository.findByUserNameAndPassword(userName, password);
		
		// 取得したユーザ情報をdtoにコピー
		UserInfoDto userInfoDto = new UserInfoDto();
		BeanUtils.copyProperties(userInfo, userInfoDto);
		
		chatInfoDto.setUserInfo(userInfoDto);
		
		// ユーザ情報のIdを使用して友達情報を取得
		List<Friend> friendsId = friendRepository.findByUserId(userInfo.getId());
				
		// 取得した友達情報のfriendUserIdを使用して対象ユーザの友達であるユーザの情報を全員分取得
		List<Long> idList = new ArrayList<>();
		for(Friend entity: friendsId) {
			idList.add(entity.getFriendUser().getId());
		}
		
		List<UserInfo> friendsInfo = userInfoRepository.findByIdList(idList);
		
		if(!friendsInfo.isEmpty()) {
			// 取得した友達情報のfriendUserIdを使用して対象ユーザの友達であるユーザの情報を全員分取得
			List<Long> friendIdList = new ArrayList<>();
			for(UserInfo entity: friendsInfo) {
				friendIdList.add(entity.getId());
			}
			List<Friend> friendsUserInfo = friendRepository.findByUser2Id(friendIdList);
			int i = 0;
			
			// TODO 無駄な処理が多いため、要修正
			for(Friend entity: friendsId) {
				// 友達であるユーザのDto作成
				UserInfoDto friendUser = new UserInfoDto();
				friendUser.setId(friendsInfo.get(i).getId());
				friendUser.setUserName(friendsInfo.get(i).getUserName());
				friendUser.setPassword(friendsInfo.get(i).getPassword());
				friendUser.setEmail(friendsInfo.get(i).getEmail());
				
				FriendDto friendDto = new FriendDto();
				friendDto.setId(entity.getId());
				friendDto.setUserId(userInfo.getId());
				friendDto.setFriendUserId(friendsUserInfo.get(i).getId());
				friendDto.setFriendUser(friendUser);
				
				chatInfoDto.getFriends().add(friendDto);
				i++;
			}
			
			// 対象者のユーザIdを使用して、ユーザのメッセージの取得
			List<Message> messages = messageRepository.findBySenderIdANDRecipientIdIN(userInfo.getId(), friendIdList);
			
			List<MessageDto> messageDtoList = new ArrayList<>();
			for(Message entity: messages) {
				MessageDto messageDto = new MessageDto();
				BeanUtils.copyProperties(entity, messageDto);
				messageDtoList.add(messageDto);
			}
			
			chatInfoDto.setMessages(messageDtoList);
			
		}
		
		
		return chatInfoDto;
	}
	
}