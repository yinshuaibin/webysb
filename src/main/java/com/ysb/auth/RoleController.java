//package com.ysb.auth;
//
//import com.ferret.auth.domain.Role;
//import com.ferret.auth.service.RoleService;
//import com.ferret.bean.Authc;
//import com.ferret.controller.BaseController;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@Slf4j
//public class RoleController extends BaseController {
//
//	@Resource
//	private RoleService roleService;
//
//	/** 查询全部数据 */
//	@RequestMapping(value = "/roler", method = RequestMethod.GET)
//	public List<Role> read(HttpServletRequest req) {
//		List<Role> roleList = roleService.selectAllRole();
//		return roleList;
//	}
//
//	/** 分页查询角色 */
//	@RequestMapping(value = "/getRoleByPage")
//	public List<Role> getRoleByPage(@RequestParam(value = "pageNo") Integer pageNo,
//                                    @RequestParam(value = "pageSize") Integer pageSize, HttpServletRequest req) {
//		List<Role> roleList = roleService.getRoleByPage(pageNo, pageSize);
//		return roleList;
//	}
//
//	/**
//	 * 查询符合登录的用户的角色权限内的条数
//	 */
//	@RequestMapping(value = "/roleCount", method = RequestMethod.GET)
//	public int roleCount() {
//		int count = roleService.selectCount();
//		return count;
//	}
//
//	/**
//	 * 根据roleId查询对应的权限
//	 * 修改人:y
//	 * 修改原因:因页面变动,更改此逻辑
//	 *
//	 * 修改:不再从后台session中获取用户roleId,改为从前台获取  y 0814
//	 * @param roleId
//	 * @return
//	 */
//	@RequestMapping(value = "/role/authcTree", method = RequestMethod.GET)
//	public List<Authc> authcTree(String roleId) {
//		return roleService.getAuthcsByRoleId(roleId);
//	}
//
//	/**
//	 * 添加 role
//	 *
//	 * @return
//	 */
//	@RequestMapping(value = "roleAdd", method = RequestMethod.POST)
//	public boolean roleAdd(@RequestBody Role role) {
//		// 查询输入roleName是否存在 （不存在则添加role）
//		Role roleName = roleService.selectByRoleName(role.getRoleName());
//		if (null == roleName) {
//			return roleService.addRole(role);
//		}
//		return false;
//	}
//
//	/**
//	 * 删除角色信息
//	 *
//	 * @param roleId
//	 * @return
//	 */
//	@RequestMapping(value = "/delRole/{roleId}", method = RequestMethod.DELETE)
//	public boolean roleDelete(@PathVariable("roleId") String roleId) {
//		return roleService.deleteRole(roleId);
//	}
//
//	/**
//	 * 修改角色信息
//	 *
//	 * @param role
//	 * @return
//	 */
//	@RequestMapping(value = "/roleUpdate", method = RequestMethod.POST)
//	public boolean roleUpdate(@RequestBody Role role) {
//		return roleService.updateRole(role);
//	}
//
//	@RequestMapping(value = "/getAuthcMenuName")
//	public List<String> getAuthcMenuName(String roleId) {
//		return roleService.getAuthcMenuName(roleId);
//	}
//
//	@RequestMapping(value = "/findRoleCountByRoleNameAndRoleId")
//	public int findRoleCountByRoleNameAndRoleId(String roleName, String roleId){
//		if(StringUtils.isNotBlank(roleId)){
//			return roleService.findRoleCountByRoleNameAndRoleId(roleName,roleId);
//		}
//		return roleService.findRoleCountByRoleName(roleName);
//	}
//
//	@RequestMapping(value = "/editRoleCameraGroup", method = RequestMethod.POST)
//	public ResponseEntity<?> roleCameraGroupAdd (@RequestBody Map reqMap) {
//		int i = roleService.addRoleCameraGroup((List<String>) reqMap.get("camGroupIds"), (String) reqMap.get("roleId"));
//		if (i > 0) {
//			List<Integer> list = roleService.findCameraIdsByRoleId((String) reqMap.get("roleId"));
//			if (null != list && list.size() > 0) {
//				LoginController.userCameraIds.put((String) reqMap.get("roleId"), list);
//			} else {
//				LoginController.userCameraIds.remove(reqMap.get("roleId"));
//			}
//			return ResponseEntity.ok("success");
//		}
//		return ResponseEntity.ok("error");
//	}
//
//	@RequestMapping(value = "/findCameraGroupIdsByRoleId/{roleId}", method = RequestMethod.GET)
//	public ResponseEntity<?> findCameraGroupIdsByRoleId (@PathVariable String roleId) {
//		List<String> result = roleService.findRoleCameraGroupByRoleId(roleId);
//		return ResponseEntity.ok(result);
//	}
//}
