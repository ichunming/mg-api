/**
 * @author ming
 * @date 2016年12月29日 下午6:36:23
 */
package com.mg.api.vo;

import java.math.BigDecimal;

public class ResourceStatsVo {
	private int type;
	
	private int num;
	
	private Long size;

	public ResourceStatsVo() {}
	
	public ResourceStatsVo(int type, int num, Long size) {
		this.type = type;
		this.num = num;
		this.size = size;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSize() {
		BigDecimal unit = new BigDecimal(1024L);
		BigDecimal size = new BigDecimal(this.size); // Byte
		size = size.divide(unit, 3, BigDecimal.ROUND_UP); // KB
		String value = Math.ceil(size.doubleValue()) + " KB";
		
		if(size.compareTo(unit) >= 0) {
			size = size.divide(unit, 3, BigDecimal.ROUND_UP); // M
			value = Math.ceil(size.doubleValue()) + " MB";
			
			if(size.compareTo(unit) >= 0) {
				size = size.divide(unit, 1, BigDecimal.ROUND_UP); // G
				value = size.toString() + " GB";
			}
		}
		
		return value;
	}

	public void setSize(Long size) {
		this.size = size;
	}
}
