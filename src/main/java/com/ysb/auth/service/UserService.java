//package com.ysb.auth.service;
//
//import com.ferret.auth.dao.UserMapper;
//import com.ferret.auth.domain.User;
//import com.ferret.auth.domain.UserLogin;
//import com.ferret.bean.SearchHistory;
//import com.ferret.dao.SequenceMapper;
//import com.ferret.dto.PageDTO;
//import com.ferret.exception.BusinessException;
//import com.ferret.exception.ServiceException;
//import com.ferret.utils.ImageUtil;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.crypto.hash.SimpleHash;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//
///**
// * @pack: com.ferret.auth.service.user.impl;
// * @auth: Administrator;
// * @since: 2017/12/27 0027;
// * @desc:
// */
//@Service
//public class UserService {
//	@Autowired
//	private UserMapper userMapper;
//	@Autowired
//	private SequenceMapper sequenceMapper;
//
//	private static final String ALGORITH_NAME = "md5";
//
//	@Value("${image.prefix.searchHisDir}")
//	private String hisPath;
//
//	/**
//	 * 根据用户名查询
//	 *
//	 * @param username
//	 * @return
//	 */
//	public User findUserByUsername(String username) {
//
//		return userMapper.findUserByUsername(username);
//	}
//
//	/**
//	 * 增加用户数据信息
//	 *
//	 * @param user
//	 */
//	public User saveUser(User user) {
//		Assert.notNull(user, "新增数据时,不能为空");
//		String userID = sequenceMapper.nextVal("user_id");
//		user.setUserId(userID);
//        String salt = UUID.randomUUID().toString().replace("-", "").substring(0,10);
//        user.setSalt(salt);
//        user.setPassword(new SimpleHash(ALGORITH_NAME,user.getPassword(),user.getCredentialsSalt()).toString());
//		int userType = userMapper.insertUser(user);
//		if (userType < 1) {
//			throw new ServiceException("增加用户数据失败");
//		}
//		return user;
//	}
//
//	/**
//	 * 修改用户信息数据
//	 *
//	 * @param user
//	 */
//	public void updateUser(User user) {
//		Assert.notNull(user, "修改用户信息不能为空");
//        String passwordByUserId = userMapper.findPasswordByUserId(user.getUserId());
//        if(!user.getPassword().equals(passwordByUserId)){
//            String salt = UUID.randomUUID().toString().replace("-", "").substring(0,10);
//            user.setSalt(salt);
//            user.setPassword(new SimpleHash(ALGORITH_NAME,user.getPassword(),user.getCredentialsSalt()).toString());
//        }
//		int userTypes = userMapper.updateUser(user);
//		if (userTypes < 1) {
//			throw new BusinessException("修改用户数据失败");
//		}
//
//	}
//
//	/**
//	 * 删除用户信息数据
//	 *
//	 * @param userId
//	 */
//	public void deleteUser(String userId) {
//		//删除user表中的数据
//		userMapper.deleteUser(userId);
//	}
//
//	/**
//	 * 查询总条数
//	 * @return
//	 */
//	public int findUserNumberByAreaId() {
//		return userMapper.findUserNumber() ;
//	}
//
//	/**
//	 * 分页查询用户信息
//	 * @return
//	 */
//	public List<User> findUsersByAreaId(Integer pageNo, Integer pageSize) throws BusinessException {
//		PageHelper.startPage(pageNo, pageSize);
//		List<User> userList = userMapper.findAllUser();
//		for (User s:userList) {
//			// 如果用户过期时间不为空，则去掉过期时间末尾的.0
//			if (s.getOverTime() != null) {
//				s.setOverTime(s.getOverTime().substring(0, 10));
//			}
//		}
//		return userList;
//	}
//
//	public User findUserByIdCard(String idCard) {
//        User userByIdCard = userMapper.findUserByIdCard(idCard);
//        if(userByIdCard != null){
//			if(null != userByIdCard.getCreateTime()) {
//				userByIdCard.setCreateTime(userByIdCard.getCreateTime().substring(0, 19));
//			}
//			if(null != userByIdCard.getOverTime()) {
//				userByIdCard.setOverTime(userByIdCard.getOverTime().substring(0, 19));
//			}
//            userByIdCard.setPassword(null);
//        }
//        return userByIdCard;
//	}
//
//    /**
//     * 根据用户名和身份证号校验该身份证号是否重复
//     * @param idCard
//     * @param userId
//     * @return
//     */
//    public User checkUserByIdcardAndUserId(String idCard,String userId) {
//        return userMapper.checkUserByIdcardAndUserId(idCard,userId);
//    }
//
//    public int findUserCountByUserName(String username) {
//        return userMapper.findUserCountByUserName(username);
//    }
//
//    public int findUserCountByUserNameAndUserId(String userName, String userId) {
//        return userMapper.checkUserByUserIdAndUsername(userName,userId);
//    }
//
//	public int updatNickName(String nickname,String userid) {
//		return userMapper.updataUserNicknameByUserId(nickname, userid);
//	}
//
//    public PageDTO findUserLoginLog(String startTime, String endTime, String userName, Integer pageNum, Integer pageSize) {
//        Page<UserLogin> objects = PageHelper.startPage(pageNum, pageSize).doSelectPage(()->{
//            userMapper.findUserLoginLog(startTime, endTime, userName);
//        });
//        return new PageDTO(objects.getResult(),objects.getTotal());
//    }
//
//    public PageDTO findAllSearchHistory(String startTime, String endTime, List<String> userId, List<String> modular, Integer pageNum, Integer pageSize){
//		Page<SearchHistory> objects = PageHelper.startPage(pageNum, pageSize).doSelectPage(()->{
//			userMapper.findAllSearchHistory(startTime, endTime, userId, modular);
//		});
//		return new PageDTO(objects.getResult(),objects.getTotal());
//	}
//
//	public int insertSearchHis(String modular,String base64){
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
//		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH-mm-ss");
//		// 将base64转存到本地
//		String imageName = ImageUtil.transferSearchHis(base64,hisPath);
//		// 生成图片路径和图片名称并插入数据库
//		String path = hisPath+"/"+imageName;
//		return userMapper.insertSearchHis(user.getUserId(),modular,format.format(new Date()),path,imageName);
//	}
//}
