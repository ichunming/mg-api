/**
 * @author ming
 * @date 2017年1月3日 下午12:19:32
 */
package com.mg.api.service.impl;

import org.springframework.stereotype.Service;

import com.mg.api.core.helper.LocationManage;
import com.mg.api.model.Location;
import com.mg.api.service.ILocationService;

@Service
public class LocationServiceImpl implements ILocationService {
	
	/* (non-Javadoc)
	 * @see com.mg.api.service.ILocationService#getName(int)
	 */
	@Override
	public String getName(Integer id) {
		Location location = LocationManage.get(id);
		return null == location ? null : location.getName();
	}
}