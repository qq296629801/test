package cn.ymsys.enums;

public class Enums {
	public enum ResponseCode {
		SUCCESS(200, "成功"),
		ERROR(500, "失败"),
		NEED_LOGIN(300, "需要登录"),
		ILLEGAL_ARGUMENT(201, "非法参数"),
		DATA_EXIST(202, "数据已经存在"),
		;

		private Integer stateCode;
		private String stateInfo;

		public Integer getStateCode() {
			return stateCode;
		}

		public void setStateCode(Integer stateCode) {
			this.stateCode = stateCode;
		}

		public String getStateInfo() {
			return stateInfo;
		}

		public void setStateInfo(String stateInfo) {
			this.stateInfo = stateInfo;
		}

		private ResponseCode(int stateCode,String stateInfo){
			this.stateCode = stateCode;
			this.stateInfo = stateInfo;
		}
		
		public static String getStateInfoByCode(Integer code){
			for (ResponseCode responseCode : ResponseCode.values()) {
				if(code.equals(responseCode.getStateCode())){
					return responseCode.getStateInfo();
				}
			}
			return null;
		}
	}
}
