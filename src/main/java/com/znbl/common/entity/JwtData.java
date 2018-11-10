package com.znbl.common.entity;

/**
 * 用于jwt验证
 * @author Gray
 * @version 2017年7月29日
 */
public class JwtData {
	
	private String jwtFlag;

	public JwtData(String jwtFlag) {
		super();
		this.jwtFlag = jwtFlag;
	}

	public JwtData() {
		super();
	}

	public String getJwtFlag() {
		return jwtFlag;
	}

	public void setJwtFlag(String jwtFlag) {
		this.jwtFlag = jwtFlag;
	}

	@Override
	public String toString() {
		return "JwtData [jwtFlag=" + jwtFlag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jwtFlag == null) ? 0 : jwtFlag.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JwtData other = (JwtData) obj;
		if (jwtFlag == null) {
			if (other.jwtFlag != null)
				return false;
		} else if (!jwtFlag.equals(other.jwtFlag))
			return false;
		return true;
	}

}
