package com.couldtec.webserver.apps.restful.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/"})
public class BaseController {
	@RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET}, produces={"application/json"})
	  public String vedios(HttpServletRequest req)
	    throws Exception
	  {
	     return "index.html";
	  }
}
