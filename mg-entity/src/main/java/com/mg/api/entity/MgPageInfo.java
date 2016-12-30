/**
 * @author ming
 * @date 2016年12月29日 下午2:14:30
 */
package com.mg.api.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;

public class MgPageInfo<T> extends PageInfo<T> {
	private static final long serialVersionUID = -1517485771766688702L;

    public MgPageInfo(List<T> list) {
        super(list);
    }
    
	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#getOrderBy()
	 */
	@Override
	@JsonIgnore
	public String getOrderBy() {
		return super.getOrderBy();
	}
	
	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#getStartRow()
	 */
	@Override
	@JsonIgnore
	public int getStartRow() {
		return super.getStartRow();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#getEndRow()
	 */
	@Override
	@JsonIgnore
	public int getEndRow() {
		return super.getEndRow();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#getPrePage()
	 */
	@Override
	@JsonIgnore
	public int getPrePage() {
		return super.getPrePage();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#getNextPage()
	 */
	@Override
	@JsonIgnore
	public int getNextPage() {
		return super.getNextPage();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#isIsFirstPage()
	 */
	@Override
	@JsonIgnore
	public boolean isIsFirstPage() {
		return super.isIsFirstPage();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#isIsLastPage()
	 */
	@Override
	@JsonIgnore
	public boolean isIsLastPage() {
		return super.isIsLastPage();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#isHasPreviousPage()
	 */
	@Override
	@JsonIgnore
	public boolean isHasPreviousPage() {
		return super.isHasPreviousPage();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#isHasNextPage()
	 */
	@Override
	@JsonIgnore
	public boolean isHasNextPage() {
		return super.isHasNextPage();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#getNavigatePages()
	 */
	@Override
	@JsonIgnore
	public int getNavigatePages() {
		return super.getNavigatePages();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#getNavigatepageNums()
	 */
	@Override
	@JsonIgnore
	public int[] getNavigatepageNums() {
		return super.getNavigatepageNums();
	}
	
	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#getFirstPage()
	 */
	@Override
	@JsonIgnore
	public int getFirstPage() {
		return super.getFirstPage();
	}

	/* (non-Javadoc)
	 * @see com.github.pagehelper.PageInfo#getLastPage()
	 */
	@Override
	@JsonIgnore
	public int getLastPage() {
		return super.getLastPage();
	}
}