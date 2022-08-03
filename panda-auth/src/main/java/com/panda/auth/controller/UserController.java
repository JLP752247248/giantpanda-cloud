package com.panda.auth.controller;


import com.panda.auth.dao.UserInfoDao;
import com.panda.auth.entity.UserInfo;
import com.panda.common.mvc.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/user"})
public class UserController
{
  public static final Logger logger = LoggerFactory.getLogger(UserController.class);
  @Resource
  private UserInfoDao dao;
  @RequestMapping(value={"/info"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  @ResponseBody
  public Response info(HttpServletRequest req, @RequestParam String userName)
  throws Exception
  {
	  UserInfo obj=dao.findByUserNameOrEmailOrMobile(userName,userName,userName);

      return Response.createSuc(obj);
  }

  @RequestMapping(value={"/login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Response log(HttpServletRequest req,@RequestBody UserInfo user)

  {
	 UserInfo obj=dao.findByUserNameOrEmailOrMobile(user.getUserName(), user.getUserName(), user.getUserName());
     return Response.createSuc(obj);
  }

  @RequestMapping(value={"/registe"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  @ResponseBody
  public Response regist(HttpServletRequest req,@RequestBody UserInfo user)
  {
	 UserInfo obj=dao.save(user);
	 return Response.createSuc(obj);
  }


}