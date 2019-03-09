package cn.ymsys.api.baidu;

import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class Snippet {
	public void sample(AipOcr client) {
		// 传入可选参数调用接口
		HashMap<String, String> options = new HashMap<String, String>();

		// 参数为本地路径
		String image = "test.jpg";
		JSONObject res = client.businessLicense(image, options);
		System.out.println(res.toString(2));

		// 参数为二进制数组
		byte[] file = readFile("test.jpg");
		res = client.businessLicense(file, options);
		System.out.println(res.toString(2));
	}

	public byte[] readFile(String path) {

		return null;
	}
}
