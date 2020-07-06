//package com.ysb.auth;
//
//import com.ferret.auth.domain.User;
//import com.ferret.auth.service.UserService;
//import com.ferret.controller.BaseController;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * @pack: com.ferret.controller;
// * @auth: xy;
// * @since: 2017/12/27 0027;
// * @desc:
// */
//@RestController
//@Slf4j
//public class UserController{
//
//	@Autowired
//	private UserService userService;
//
//	/**
//	 * 增加用户信息
//	 *
//	 * @param user
//	 * @return
//	 */
//	@RequestMapping(value = "/users", method = RequestMethod.POST)
//	public Boolean doInsertUser(@RequestBody User user) {
//		User findByUsername = userService.findUserByUsername(user.getUsername());
//		if (findByUsername == null) {
//			User users = userService.saveUser(user);
//			if (users != null) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	/**
//	 * 修改用户信息
//	 *
//	 * @param user
//	 * @return
//	 */
//	@RequestMapping(value = "/users", method = RequestMethod.PUT)
//	public ResponseEntity doUpdateUser(@RequestBody User user) {
//		userService.updateUser(user);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//
//	/**
//	 * 根据ID删除某用户信息
//	 *
//	 * @param userId
//	 * @return
//	 */
//	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
//	public ResponseEntity doDeleteUser(@PathVariable("userId") String userId) {
//		userService.deleteUser(userId);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//
//	/**
//	 * 查询用户信息(分页)
//	 * @return
//	 */
//	@RequestMapping(value = "/users", method = RequestMethod.GET)
//	public List<User> doFindAllUser(@RequestParam(value = "pageNo", required = false) Integer pageNum,
//                                    @RequestParam(value = "pageSize", required = false) Integer pageSize) {
//		return userService.findUsersByAreaId(pageNum, pageSize);
//	}
//
//	/**
//	 * 总条数
//	 * @return
//	 */
//	@RequestMapping(value = "/usersCount", method = RequestMethod.GET)
//	public int userCount() {
//        int userCount = userService.findUserNumberByAreaId();
//		return userCount;
//	}
//
//	/**
//	 * 根据用户名查询
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping(value = "/doFindByUsername", method = RequestMethod.POST)
//	public User doFindByUsername(@RequestBody Map<String, String> map) {
//		User user = userService.findUserByUsername(map.get("username"));
//	    return user;
//	}
//
//	/**
//	 * 根据身份证号查找对应的用户信息,如果即有身份证号又有用户id说明是校验用户身份证号是否重复
//	 * @param idCard
//	 * @return
//	 */
//	@RequestMapping(value = "/findUserByIdCard")
//	public User findUserByIdCard(String idCard,String userId){
//		if(StringUtils.isNotBlank(userId)){
//			return userService.checkUserByIdcardAndUserId(idCard,userId);
//		}
//		return userService.findUserByIdCard(idCard);
//	}
//
//	/**
//	 * 增加和修改用户时校验用户名是否重复
//	 * @param userName
//	 * @param userId
//	 * @return
//	 */
//	@RequestMapping(value = "/findUserByUserName")
//	public int findUserByUserName(String userName, String userId){
//		if(StringUtils.isNotBlank(userId)){
//			return userService.findUserCountByUserNameAndUserId(userName,userId);
//		}
//		return userService.findUserCountByUserName(userName);
//	}
//
//	@PostMapping("/findNickName")
//	public int findNickName(@RequestBody User user){
//		return  userService.updatNickName(user.getNickname(),user.getUserId());
//	}
//
//	/**
//	 * 查询用户登录记录
//	 * @return
//	 */
//	@PostMapping("/findUserLoginLog")
//	public ResponseEntity findUserLoginLog(@RequestBody Map map){
//		String startTime = (String) map.get("startTime");
//		String endTime = (String) map.get("endTime");
//		String userName = (String) map.get("userName");
//		Integer pageNum = (Integer) map.get("pageNum");
//		Integer pageSize = (Integer) map.get("pageSize");
//		if (pageNum == null) pageNum = 1;
//		if (pageSize == null) pageSize = 10;
//		return  ResponseEntity.ok(userService.findUserLoginLog(startTime, endTime, userName, pageNum, pageSize));
//	}
//
//	/**
//	 * 查询检索历史记录
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping(value = "/getSearchHistory", method = RequestMethod.POST)
//	public ResponseEntity getSearchHistory(@RequestBody Map map){
//		String startTime = (String) map.get("startDateTime");
//		String endTime = (String) map.get("endDateTime");
//		List<String> userId = (List<String>) map.get("userId");
//		List<String> modular = (List<String>) map.get("modular");
//		Integer pageNum = (Integer) map.get("pageNum");
//		Integer pageSize = (Integer) map.get("pageSize");
//		if (pageNum == null) pageNum = 1;
//		if (pageSize == null) pageSize = 16;
//		return ResponseEntity.ok(userService.findAllSearchHistory(startTime, endTime, userId, modular, pageNum, pageSize));
//	}
//}
