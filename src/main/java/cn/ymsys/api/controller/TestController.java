package cn.ymsys.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ymsys.api.service.TranService;
import cn.ymsys.util.Trans;

@Controller
@RequestMapping("/")
public class TestController {
	@Autowired
	private TranService tranService;

	@RequestMapping
	public String index() throws IOException, InterruptedException {
		tranService.save("test");
		new Trans().start();
		return "ok";
	}
}
