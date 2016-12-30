/**
 * @author ming
 * @date 2016年12月29日 下午12:31:30
 */
package com.mg.api.vo;

import java.util.Date;

import com.mg.api.common.util.DateUtil;
import com.mg.api.model.Resource;

public class ResourceVo {
	private String id;

    private Integer useCnt;

    private Date createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUseCnt() {
		return useCnt;
	}

	public void setUseCnt(Integer useCnt) {
		this.useCnt = useCnt;
	}
	
	public String getCreateDate() {
		return DateUtil.getStringByPattern(this.createDate, "yyyy-MM-dd");
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public void fromEntity(Resource resource) {
		this.id = resource.getId();
		this.useCnt = resource.getUseCnt();
		this.createDate = resource.getCreateDate();
	}
}
