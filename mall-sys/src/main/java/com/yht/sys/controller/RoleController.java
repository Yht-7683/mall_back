package com.yht.sys.controller;

import com.yht.sys.DO.RoleDO;
import com.yht.sys.annotation.MyLog;
import com.yht.sys.service.RoleMenuService;
import com.yht.sys.service.RoleService;
import com.yht.sys.utils.JwtUtils;
import com.yht.sys.utils.PageUtils;
import com.yht.sys.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private HttpServletRequest request;
    /**
     * 用户管理添加用户时调用的角色列表
     * @return
     */
    @GetMapping("/select")
    public Result select(){
        List<RoleDO> list =  roleService.listRole();
        return Result.ok().put("list", list);
    }
    /**
     * 角色管理中的角色列表
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> map){
        PageUtils page = roleService.queryPage(map);
        return Result.ok().put("page",page);
    }
    /**
     * 修改角色时调用的角色信息
     */
    @GetMapping("/info/{roleId}")
    public Result info(@PathVariable("roleId") Long roleId){
        RoleDO roleDO = roleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> menuIdList = roleMenuService.queryMenuIdList(roleId);
        roleDO.setMenuIdList(menuIdList);
        return Result.ok().put("role",roleDO);
    }
    /**
     * 修改角色
     */
    @MyLog("修改角色")
    @PostMapping("/update")
    public Result update(@RequestBody RoleDO role){
        role.setCreateUserId(JwtUtils.getUserId(request.getHeader("token")));
       roleService.update(role);
       return Result.ok();
    }
    /**
     * 保存角色
     */
    @MyLog("添加角色")
    @PostMapping("/save")
    public Result save(@RequestBody RoleDO role){
        role.setCreateUserId(JwtUtils.getUserId(request.getHeader("token")));
        roleService.saveRole(role);
        return Result.ok();
    }
    /**
     * 删除角色
     */
    @MyLog("删除角色")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] roleIds){
        roleService.deleteBatch(roleIds);
        return Result.ok();
    }
}
