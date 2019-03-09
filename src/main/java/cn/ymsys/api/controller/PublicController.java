package cn.ymsys.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.ymsys.enums.Enums.ResponseCode;
import cn.ymsys.util.ReturnUtils;

@RestController
@RequestMapping("/api/public")
public class PublicController {
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpServletResponse res;

	@RequestMapping("/upload")
	public void handleFileUpload(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return;
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		System.out.println("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		System.out.println("上传的后缀名为：" + suffixName);
		// 文件上传后的路径
		String filePath = "/usr/local/nginx/html/img/";
		System.out.println(filePath);
		// 解决中文问题，liunx下中文路径，图片显示问题
		fileName = UUID.randomUUID() + suffixName;
		System.out.println("上传的路径为：" + filePath + fileName);
		File dest = new File(filePath + fileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			ReturnUtils.outputCn(fileName, ResponseCode.SUCCESS.getStateCode(), res);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
