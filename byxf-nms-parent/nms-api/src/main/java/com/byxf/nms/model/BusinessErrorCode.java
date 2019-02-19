package com.byxf.nms.model;

public enum BusinessErrorCode{
	/***
	 * WS--系统WARN--公用错误码--APPIFWSB01-APPIFWSB99
	 */
	WARN_SYSTEM("APPSTWS500"),

	/***
	 * ES--系统ERROR--公用错误码--APPIFESB01-APPIFESB99
	 */
	ERROR_SYSTEM("APPSTES500"),

	/***
	 * ES--token无效--公用错误码--APPSTEF400-APPIFESB99
	 */
	TOKEN_FAIL("APPSTES400");

	private String code;

	private BusinessErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}



