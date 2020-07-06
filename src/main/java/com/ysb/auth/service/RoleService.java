//package com.ysb.auth.service;
//
//import com.ferret.auth.LoginController;
//import com.ferret.auth.dao.RoleAuthcMapper;
//import com.ferret.auth.dao.RoleCameragroupMapper;
//import com.ferret.auth.dao.RoleMapper;
//import com.ferret.auth.dao.UserMapper;
//import com.ferret.auth.domain.Role;
//import com.ferret.auth.domain.User;
//import com.ferret.bean.Authc;
//import com.ferret.dao.AuthcMapper;
//import com.ferret.dao.SequenceMapper;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.*;
//
///**
// * 修改人:y 修改时间:0724 修改原因:因页面变动,修改后台逻辑 从树-->列表形式,后台手动添加父节点
// *
// * @author bin
// *
// */
//@Service("roleService")
//public class RoleService {
//
//	@Resource
//	private RoleMapper roleMapper;
//	@Resource
//	private RoleAuthcMapper roleAuthcMapper;
//	@Resource
//	private SequenceMapper seqMapper;
//
//	@Autowired
//	private UserMapper userMapper;
//
//	@Autowired
//	private AuthcMapper authcMapper;
//
//	@Autowired
//	private RoleCameragroupMapper cameragroupMapper;
//
//	public boolean addRole(Role role) {
//		role.setRoleId(seqMapper.nextVal("role_id"));
//		// 角色信息入库  // roleId}, #{roleName}, #{roleDesc}
//		int roleCount = roleMapper.insertRole(role.getRoleId(), role.getRoleName(), role.getRoleDesc());
//		// 如入库条数小于1, 说明插入失败
//		if (roleCount < 1) {
//			return false;
//		}
//		// 角色id,权限id 入库
//		int authcCount = roleAuthcMapper.insertAuthorityIds(role.getAuthorityIds(),role.getRoleId());
//		// 如入库条数小于1, 说明插入失败
//		if (authcCount < 1) {
//			return false;
//		}
//		return true;
//	}
//
//	/**
//	 * 修改人:y
//	 * 修改原因:因插入角色权限规则改变,修改权限的方法也改变了
//	 * 修改日期:0724
//	 */
//	public boolean updateRole(Role role) {
//		// 修改角色信息
//		int count = roleMapper.updateById(role);
//		// 修改对应的权限
//		roleAuthcMapper.deleteByRoleId(role.getRoleId());
//		roleAuthcMapper.insertAuthorityIds(role.getAuthorityIds(),role.getRoleId());
//		// 更改roleTreeMap中对应权限的数据
//		return count >= 1;
//	}
//
//	/**
//	 * 修改人:y 修改时间:0724 修改原因:删除roleTreeMap
//	 */
//	public boolean deleteRole(String roleId) {
//		// 删除时首先查询是否有角色拥有此角色,如果有,则不让其删除
//		List<User> users = userMapper.findUserByRoleId(roleId);
//		if (users != null && users.size() > 0) {
//			return false;
//		}
//		// 删除role
//		int count = roleMapper.deleteById(roleId);
//		// 删除role对应的authc
//		int count2 = roleAuthcMapper.deleteByRoleId(roleId);
//		// 删除role 对应的 相机分组
//		cameragroupMapper.deleteRolaCameragroupByRoleId(roleId);
//		if (count < 1 || count2 < 1) {
//			return false;
//		}
//		LoginController.userCameraIds.remove(roleId);
//		return true;
//	}
//
//	public List<Role> getRoleByPage(Integer pageNo, Integer pageSize) {
//		Integer startNum = (pageNo - 1) * pageSize;
//		return roleMapper.getRoleByPage(startNum, pageSize);
//	}
//
//	/**
//	 * 修改人:hyl 修改时间:0816
//	 * 修改原因:不显示超级管理员角色，显示操作在前台处理，这里只是取消超级管理员后的总数
//	 */
//	public int selectCount() {
//		return roleMapper.selectAllCount()-1;
//	}
//
//	public Role selectByRoleName(String roleName) {
//		// 通过roleName查询role
//		return roleMapper.selectRoleByRoleName(roleName);
//	}
//
//	/** 查询所有角色信息（不包含管理员角色信息） */
//	public List<Role> selectAllRole() {
//		return roleMapper.selectAll();
//	}
//
//	/** 查询所有角色信息（包含管理员信息） */
//	public List<Role> findAllRoles () {
//		return roleMapper.selectRoles();
//	}
//
//	/**
//	 * 根据roleId查询对应的authc里页面跳转路径的名称
//	 */
//	public List<String> getAuthcMenuName(String roleId) {
//		return authcMapper.selectMenuNameByRoleId(roleId);
//	}
//
//	/**
//	 * 根绝roleId查询对应的权限
//	 * 修改人:y
//	 * 修改原因:因页面变动,更改此逻辑
//	 * @param roleId
//	 * @return
//	 */
//	public List<Authc> getAuthcsByRoleId(String roleId) {
//		//查询出所有对应的权限数据
//		Role role = roleMapper.selectByRoleId(roleId);
//		List<Authc> authcs = role.getAuthcs();
//		Set<String> parentSet = new HashSet<>();
//		//删除所有的父节点数据,只保留子节点数据
//		for (Authc a : authcs) {
//			if (StringUtils.isNotBlank(a.getParentId())) {
//				parentSet.add(a.getParentId());
//			}
//		}
//		for (String authorityId : parentSet) {
//			for (int x = 0; x < authcs.size(); x++) {
//				if (authcs.get(x).getAuthorityId().equals(authorityId)) {
//					authcs.remove(x);
//					x--;
//				}
//			}
//		}
//		return authcs;
//	}
//
//	public List<Map<String, Object>> findRoleAndUserTree(String userId) {
//		List<Map<String, Object>> roles = roleMapper.findAllRoles();
//		List<Map<String, Object>> result = new ArrayList<>();
//		/** 角色信息添加到tree */
//		for (Map<String, Object> map : roles) {
//			String roleId = (String) map.get("roleId");
//			if(null != roleId) {
//				map.put("children", userMapper.findUserListByRoleIdAndUserId(roleId, userId));
//				result.add(map);
//			}
//		}
//		return result;
//	}
//
//	public int findRoleCountByRoleNameAndRoleId(String roleName, String roleId) {
//		return roleMapper.findRoleCountByRoleNameAndRoleId(roleName, roleId);
//	}
//
//	public int findRoleCountByRoleName(String roleName) {
//		return roleMapper.findRoleCountByRoleName(roleName);
//	}
//
//	/** 角色添加关联相机分组权限 */
//	public int addRoleCameraGroup (List<String> camGroupIds, String roleId) {
//		int del = cameragroupMapper.deleteRolaCameragroupByRoleId(roleId);
//		if(camGroupIds.size() == 0){
//			return del;
//		}
//		return cameragroupMapper.addRoleCameragroupLists(roleId, camGroupIds);
//	}
//
//	/** 查询角色下已关联的相机分组 */
//	public List<String> findRoleCameraGroupByRoleId (String roleId) {
//		return cameragroupMapper.findCameraGroupIdsByRoleId(roleId);
//	}
//
//	/** 根据角色Id 查询用户可以查看的相机Id集合 */
//	public List<Integer> findCameraIdsByRoleId (String roleId) {
//		return cameragroupMapper.findCameraIdsByRoleId(roleId);
//	}
//}
