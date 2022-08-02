package com.panda.auth.user.controller;


import com.panda.auth.user.dtos.UserDTO;
import com.panda.auth.user.dtos.requests.LoginRequestDTO;
import com.panda.auth.user.entities.User;
import com.panda.auth.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
@Slf4j
public class LoginRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        User user = userService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        return ResponseEntity.ok(new UserDTO(user));
    }

    /**
     * 登录失败返回 401 以及提示信息.
     *
     * @return the rest
     */
    @PostMapping("/failure")
    public ResponseEntity loginFailure() {

        return new ResponseEntity("登录失败了，老哥",HttpStatus.UNAUTHORIZED);

    }

    /**
     * 登录成功后拿到个人信息.
     *
     * @return the rest
     */
    @PostMapping("/success")
    public ResponseEntity loginSuccess() {
        // 登录成功后用户的认证信息 UserDetails会存在 安全上下文寄存器 SecurityContextHolder 中
        //User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //String username = principal.getUsername();
        return new ResponseEntity("登录成功",HttpStatus.OK);
    }

}
