package cn.ymsys.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.ymsys.enums.Enums;
import cn.ymsys.vo.ResultVo;

public class ReturnUtils {

	private static final String ENCODING = "utf-8";
	private static ResultVo<Object> result;

	public static void outputCn(Integer stateCode, HttpServletResponse response) {
		outputCn(null, stateCode, response);
	}

	public static void outputCn(Object data, Integer stateCode, HttpServletResponse response) {
		result = new ResultVo<Object>();
		result.setStateCode(stateCode);
		result.setStateinfo(Enums.ResponseCode.getStateInfoByCode(stateCode));
		result.setData(data);
		new ReturnUtils().outputJson(result, response);
	}

	private void outputJson(Object object, HttpServletResponse response) {
		responsePageEncode(response);
		outputDate(JSON.toJSONString(object), response);
	}

	private void responsePageEncode(HttpServletResponse response) {
		response.setCharacterEncoding(ENCODING);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
	}

	private void outputDate(String output, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(output);
			out.flush();
		} catch (Exception e) {
			System.out.println("输出出错:" + e);
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}
}
