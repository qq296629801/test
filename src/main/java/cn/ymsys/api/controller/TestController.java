package cn.ymsys.api.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ymsys.util.Const;
import cn.ymsys.util.Shell;

@RestController
@RequestMapping("/")
public class TestController {
	@Autowired
	private HttpServletRequest request;

	private Shell shell = new Shell();

	@RequestMapping
	public String index() throws IOException, InterruptedException {
		return "ok";
	}

	@RequestMapping("/ts")
	public String ts() {
		return shell.ts(Const.ROOT + request.getParameter("name"));
	}

	@RequestMapping("/m3u8")
	public String m3u8() {
		return shell.m3u8(Const.ROOT + request.getParameter("name"));
	}

	@RequestMapping("/ps")
	public String ps() {
		return shell.ps(request.getParameter("name"));
	}
}
