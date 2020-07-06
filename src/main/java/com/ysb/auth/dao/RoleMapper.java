//package com.ysb.auth.dao;
//
//import com.ferret.auth.domain.Role;
//import com.ferret.dao.provider.RoleMapperProvider;
//import org.apache.ibatis.annotations.*;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Map;
//
//@Mapper
//@Repository
//public interface RoleMapper {
//
//	/**
//	 * 插入角色: 新增一条记录
//	 * @param roleId
//	 * @param roleName
//	 * @param roleDesc
//	 * @return
//	 */
//	@Insert("INSERT INTO tb_role (role_id, role_name, role_desc , enabled) VALUES (#{roleId}, #{roleName}, #{roleDesc},1)")
//	int insertRole(@Param("roleId") String roleId, @Param("roleName") String roleName, @Param("roleDesc") String roleDesc);
//
//	/**
//	 * 删除: 修改启用状态以达到"删除"目的
//	 *
//	 * @param roleId
//	 * @return
//	 */
//	@Update("UPDATE tb_role SET enabled = 0 WHERE role_id = #{roleId}")
//	int deleteById(String roleId);
//
//	/**
//	 * 修改: 根据roleId修改信息
//	 *
//	 * @param role
//	 * @return
//	 */
//	@UpdateProvider(type = RoleMapperProvider.class, method = "updateSql")
//	int updateById(@Param("role") Role role);
//
//	/**
//	 * 查询: 查询出所有启用的角色及对应的权限
//	 * 若存在root用户，则添加和修改用户的时候不提供超级管理员角色
//	 * @return
//	 */
//	@Select("SELECT role_id, role_name, role_desc FROM tb_role WHERE enabled = 1 and role_id <>(SELECT role_id FROM tb_user WHERE username = 'root')")
//	@Results({ @Result(id = true, property = "roleId", column = "role_id"),
//			@Result(property = "roleName", column = "role_name"), @Result(property = "roleDesc", column = "role_desc"),
//			@Result(property = "authcs", column = "role_id", many = @Many(select = "com.ferret.dao.AuthcMapper.selectByRoleId")) })
//	List<Role> selectAll();
//
//	/**
//	 * 查询: 查询出所有启用的角色及对应的权限
//	 * 若存在root用户，则添加和修改用户的时候不提供超级管理员角色
//	 * @return
//	 */
//	@Select("SELECT role_id, role_name, role_desc FROM tb_role WHERE enabled = 1")
//	@Results({ @Result(id = true, property = "roleId", column = "role_id"),
//			@Result(property = "roleName", column = "role_name"), @Result(property = "roleDesc", column = "role_desc"),
//			@Result(property = "authcs", column = "role_id", many = @Many(select = "com.ferret.dao.AuthcMapper.selectByRoleId")) })
//	List<Role> selectRoles();
//
//	/**
//	 * 查询出所有角色条数
//	 */
//	@Select("select count(*) from tb_role where enabled=1")
//	int selectAllCount();
//
//	/**
//	 * 查询: 根据角色id查询角色信息及对应权限信息
//	 *
//	 * @param roleId
//	 * @return
//	 */
//	@Select("SELECT role_id, role_name, role_desc FROM tb_role WHERE role_id = #{roleId}")
//	@Results({ @Result(id = true, property = "roleId", column = "role_id"),
//			@Result(property = "roleName", column = "role_name"), @Result(property = "roleDesc", column = "role_desc"),
//			@Result(property = "authcs", column = "role_id", many = @Many(select = "com.ferret.dao.AuthcMapper.selectByRoleId")) })
//	Role selectByRoleId(String roleId);
//
//	/**
//	 * 查询：通过角色 roleName查询角色信息
//	 *
//	 * @param roleName
//	 * @return
//	 */
//	@Select("SELECT role_id,role_name,role_desc FROM tb_role WHERE role_name = #{roleName} AND enabled = 1")
//	@Results({ @Result(id = true, property = "roleId", column = "role_id"),
//			@Result(property = "roleName", column = "role_name"),
//			@Result(property = "roleDesc", column = "role_desc"), })
//	Role selectRoleByRoleName(@Param("roleName") String roleName);
//
//	/**
//	 * 分页查询所有
//	 *
//	 * @param pageNum
//	 * @param pageSize
//	 * @return
//	 */
//	@Select("SELECT role_id, role_name, role_desc, enabled FROM tb_role WHERE enabled = 1 AND role_id <>'000001' LIMIT #{startNum},#{psize}")
//	@Results({ @Result(id = true, property = "roleId", column = "role_id"),
//			@Result(property = "roleName", column = "role_name"),
//			@Result(property = "roleDesc", column = "role_desc"),
//			@Result(property = "enabled", column =  "enabled")
//			})
//	List<Role> getRoleByPage(@Param("startNum") Integer pageNum, @Param("psize") Integer pageSize);
//
//	/**
//	 * @Descriptions 查询所有启用角色
//	 * @author
//	 * @date 2019/03/04 16:17
//	 */
//	@Select("SELECT role_id roleId, role_name roleName FROM tb_role WHERE enabled = 1")
//	List<Map<String,Object>> findAllRoles();
//
//	@Select("select count(role_id) from tb_role where role_name = #{roleName} and role_id != #{roleId}")
//	int findRoleCountByRoleNameAndRoleId(@Param("roleName") String roleName, @Param("roleId") String roleId);
//
//	@Select("select count(role_id) from tb_role where role_name = #{roleName}")
//	int findRoleCountByRoleName(@Param("roleName") String roleName);
//}