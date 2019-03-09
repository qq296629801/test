package cn.ymsys.api.baidu;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 策略模式
 * 
 * @author mjy
 *
 */
@Service
public class BaiduOrcServiceContext {
	private final Map<String, BaiduOrcService> baiduMap = new ConcurrentHashMap<>();

	@Autowired
	public BaiduOrcServiceContext(Map<String, BaiduOrcService> baiduMap) {
		this.baiduMap.clear();
		baiduMap.forEach((k, v) -> this.baiduMap.put(k, v));
	}

	public boolean send(String api) {
		return baiduMap.get(api).send();
	}
}
