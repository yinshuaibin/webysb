//package com.ysb.auth.dao;
//
//import com.ferret.auth.domain.User;
//import com.ferret.auth.domain.UserLogin;
//import com.ferret.bean.SearchHistory;
//import com.ferret.dao.provider.UserMapperProvider;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface UserMapper {
//	/** 查询用户信息 */
//	List<User> findAllUser();
//
//	/**
//	 * 查询总条数
//	 *
//	 * @return
//	 */
//	@Select("select count(*) from tb_user")
//	int findUserNumber();
//
//
//	/** 根据username查询用户信息 */
//	@Select("SELECT * FROM tb_user WHERE  username=#{username}")
//	User findUserByUsername(@Param("username") String username);
//
//	/** 增加用户信息 */
//	@Insert("INSERT INTO tb_user(" + "user_id,salt,role_id,username,nickname,password,"
//			+ "login_ip,dt_create,last_login,area_name,"
//			+ "scores,email,telephone,office_phone,enabled,account_non_expired,"
//			+ "account_non_locked,credentials_non_expired,idcard,jnumber,createtime,overtime,face, need_compare)" + "VALUES"
//			+ "(#{userId},#{salt},#{roleId},#{username},#{nickname},#{password},"
//			+ "#{loginIp},#{dtCreate},#{lastLogin},#{areaName},"
//			+ "#{scores},#{email},#{telephone},#{officePhone},#{enabled},#{accountNonExpired},"
//			+ "#{accountNonLocked},#{credentialsNonExpired},#{idCard},#{jnumber},#{createTime},#{overTime},#{face},#{needCompare})")
//	int insertUser(User user);
//
//	/** 修改用户信息 */
//	@Update("UPDATE tb_user SET role_id=#{roleId},salt=#{salt},username=#{username},"
//			+ "nickname=#{nickname},password=#{password}," + "login_ip=#{loginIp},dt_create=#{dtCreate},"
//			+ "last_login=#{lastLogin},area_name=#{areaName},"
//			+ "scores=#{scores},email=#{email},telephone=#{telephone},"
//			+ "office_phone=#{officePhone},enabled=#{enabled},"
//			+ "account_non_expired=#{accountNonExpired},account_non_locked=#{accountNonLocked},"
//			+ "credentials_non_expired=#{credentialsNonExpired},idcard=#{idCard},jnumber=#{jnumber},overtime=#{overTime},face=#{face},need_compare=#{needCompare} " + "WHERE user_id=#{userId}")
//	int updateUser(User user);
//
//	/** 删除用户信息 */
//	@Delete("DELETE FROM tb_user WHERE user_id=#{userId}")
//	int deleteUser(@Param("userId") String userId);
//
//	/**
//	 * 查询: 根据用户名查询用户信息(包括对应角色及角色对应权限)
//	 *
//	 * @param username
//	 * @return
//	 */
//	@Select("SELECT user_id, role_id,salt, username, nickname, login_ip, password, dt_create, "
//			+ "last_login, area_name, scores, email, telephone, office_phone, enabled, "
//			+ "account_non_expired, account_non_locked, credentials_non_expired,createtime,overtime,face, need_compare " + "FROM tb_user "
//			+ "WHERE username = #{username} AND enabled = 1 and overtime >= now()")
//	@Results({ @Result(id = true, property = "userId", column = "user_id"),
//			@Result(property = "roleId", column = "role_id"), @Result(property = "username", column = "username"),
//			@Result(property = "nickname", column = "nickname"), @Result(property = "loginIp", column = "login_ip"),
//			@Result(property = "password", column = "password"), @Result(property = "dtCreate", column = "dt_create"),
//			@Result(property = "areaName", column = "area_name"), @Result(property = "scores", column = "scores"),
//			@Result(property = "email", column = "email"), @Result(property = "telephone", column = "telephone"),
//			@Result(property = "officePhone", column = "office_phone"),
//			@Result(property = "enabled", column = "enabled"),
//			@Result(property = "jnumber", column = "jnumber"),
//			@Result(property = "accountNonExpired", column = "account_non_expired"),
//			@Result(property = "accountNonLocked", column = "account_non_locked"),
//			@Result(property = "credentialsNonExpired", column = "credentials_non_expired"),
//			@Result(property = "role", column = "role_id", one = @One(select = "com.ferret.auth.dao.RoleMapper.selectByRoleId")),
//			@Result(property = "createTime", column = "createtime"),
//			@Result(property = "overTime", column = "overtime")})
//	User findUserAndRoleByUsername(String username);
//
//
//	/**
//	 * 根绝角色id查询用户信息
//	 * @param roleId
//	 * @return
//	 */
//	@Select("select * from tb_user where role_id= #{roleId}")
//	@Results({ @Result(id = true, property = "userId", column = "user_id"),
//			@Result(property = "roleId", column = "role_id"),
//			@Result(property = "username", column = "username"),
//			@Result(property = "nickname", column = "nickname")})
//	List<User> findUserListByRoleIdAndUserId(@Param("roleId") String roleId, @Param("userId") String userId);
//
//	/**
//	 * 删除角色时使用,如果有用户拥有对应的角色, 则不让其删除此角色
//	 * @param roleId
//	 * @return
//	 */
//	@Select("select * from tb_user where role_id=#{roleId}")
//	@Results({ @Result(id = true, property = "userId", column = "user_id"),
//			@Result(property = "roleId", column = "role_id"),
//			@Result(property = "username", column = "username"),
//			@Result(property = "nickname", column = "nickname")})
//	List<User> findUserByRoleId(String roleId);
//
//	/**
//	 * 用户登录成功后,将用户的登录信息,存入到数据库中
//	 */
//	@Insert("insert into tb_user_login (user_id,user_name,login_ip,login_time) values(#{userId},#{username},#{loginIp},now())")
//	int userLoginInformationLog(User user);
//
//
//	/**
//	 * 根据身份证号查询对应的用户
//	 * @param idCard
//	 * @return
//	 */
//	@Select("select * from tb_user where idcard = #{idCard}")
//	@Results({ @Result(id = true, property = "userId", column = "user_id"),
//			@Result(property = "roleId", column = "role_id"), @Result(property = "username", column = "username"),
//			@Result(property = "nickname", column = "nickname"), @Result(property = "loginIp", column = "login_ip"),
//			@Result(property = "password", column = "password"), @Result(property = "dtCreate", column = "dt_create"),
//			@Result(property = "areaName", column = "area_name"), @Result(property = "scores", column = "scores"),
//			@Result(property = "email", column = "email"), @Result(property = "telephone", column = "telephone"),
//			@Result(property = "officePhone", column = "office_phone"),
//			@Result(property = "enabled", column = "enabled"),
//			@Result(property = "jnumber", column = "jnumber"),
//			@Result(property = "accountNonExpired", column = "account_non_expired"),
//			@Result(property = "accountNonLocked", column = "account_non_locked"),
//			@Result(property = "credentialsNonExpired", column = "credentials_non_expired"),
//			@Result(property = "role", column = "role_id", one = @One(select = "com.ferret.auth.dao.RoleMapper.selectByRoleId")),
//			@Result(property = "createTime", column = "createtime"),
//			@Result(property = "overTime", column = "overtime")})
//	User findUserByIdCard(String idCard);
//
//	/**
//	 *
//	 * @param idCard
//	 * @param userId
//	 * @return
//	 */
//	@Select("select * from tb_user where idcard = #{idCard} and user_id != #{userId}")
//	User checkUserByIdcardAndUserId(@Param("idCard") String idCard, @Param("userId") String userId);
//
//
//	/**
//	 * 根据用户id查询用户密码
//	 * @param userId
//	 * @return
//	 */
//	@Select("select password from tb_user where user_id = #{userId}")
//	String findPasswordByUserId(String userId);
//
//	@Select("select count(user_id) from tb_user where user_id != #{userId} and username = #{userName}")
//	int checkUserByUserIdAndUsername(@Param("userName") String userName, @Param("userId") String userId);
//
//	@Select("select count(user_id) from tb_user where username = #{userName}")
//	int findUserCountByUserName(@Param("userName") String userName);
//
//	/**
//	 * 修改用户昵称
//	 * zyy
//	 * **/
//	@Update("UPDATE tb_user SET nickname = #{nickname} WHERE user_id =#{userid}")
//	int updataUserNicknameByUserId(@Param("nickname") String nickname, @Param("userid") String userid);
//
//	/**
//	 * 根据时间段和用户名 查询用户登录记录
//	 * @param startTime
//	 * @param endTime
//	 * @param userName
//	 * @return
//	 */
//	@SelectProvider(type = UserMapperProvider.class, method = "findUserLoginLog")
//	List<UserLogin> findUserLoginLog(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("userName") String userName);
//
//	/**
//	 * 根据时间段和用户名及模块 查询检索历史
//	 * @param startTime
//	 * @param endTime
//	 * @param userId
//	 * @param modular
//	 * @return
//	 */
//	@SelectProvider(type = UserMapperProvider.class, method = "findAllSearchHistory")
//	List<SearchHistory> findAllSearchHistory(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("userId") List<String> userId, @Param("modular") List<String> modular);
//
//	@Insert("insert INTO tb_search_history (userid,modular,searchtime,path,imageName) VALUES(#{userid},#{modular},#{searchtime},#{path},#{imageName})")
//	int insertSearchHis(@Param("userid") String userid, @Param("modular") String modular, @Param("searchtime") String searchtime, @Param("path") String path, @Param("imageName") String imageName);
//
//    /**
//     * 根据用户id,更改用户的陌生人/重点关注人员推送规则
//     * @param strangerParam
//     * @param userId
//     * @return
//     */
//	@Update("update tb_user set stranger_param = #{strangerParam} where user_id = #{userId}")
//    int updateUserStrangerParams(@Param("strangerParam") String strangerParam, @Param("userId") String userId);
//
//    /**
//     * 根据用户id获取陌生人/重点关注人员推送规则
//     * @param userId
//     * @return
//     */
//    @Select("select stranger_param from tb_user where user_id = #{userId}")
//	String getStrangerParamsByUserId(String userId);
//}