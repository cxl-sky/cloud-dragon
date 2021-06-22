package com.dragon.user.client;

import com.dragon.user.client.config.UserClientConfig;
import com.dragon.user.client.dto.PermissionDecideDto;
import com.dragon.user.client.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenxiaolong
 */
@FeignClient(name = UserClientConfig.SERVICE_NAME, path = UserClientConfig.SERVICE_NAME + UserClientConfig.CLIENT_PATH)
public interface UserClient {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @GetMapping("/username")
    UserVo selectUserByUsername(@RequestParam("username") String username);

    /**
     * 判断是否有权限
     *
     * @param permissionDecideDto
     * @return
     */
    @PostMapping("/permission")
    boolean hasPermission(@RequestBody PermissionDecideDto permissionDecideDto);
}
