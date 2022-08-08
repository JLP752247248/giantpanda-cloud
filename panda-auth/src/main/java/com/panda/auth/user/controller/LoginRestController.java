package com.panda.auth.user.controller;


import com.panda.auth.user.dtos.UserDTO;
import com.panda.auth.user.dtos.requests.LoginRequestDTO;
import com.panda.auth.user.entities.User;
import com.panda.auth.user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
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
    public ResponseEntity<String> loginSuccess() {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(token.toString(),HttpStatus.OK);
    }

    /**
     * 登录失败返回 401 以及提示信息.
     *
     * @return the rest
     */
    @GetMapping("/oauth2")
    public String oAuthLogin() {
        return "login";
    }

}
