/**
 * @author ming
 * @date 2017年1月3日 下午3:01:22
 */
package com.mg.api.core.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mg.api.model.Location;

public class LocationManage {
	private static Map<Integer, Location> provinces;
	
	private static Map<Integer, Location> cities;
	
	private static Map<Integer, List<Location>> relation;

	public static void setLocations(List<Location> locations) {
		LocationManage.provinces = new HashMap<Integer, Location>();
		LocationManage.cities = new HashMap<Integer, Location>();
		LocationManage.relation = new HashMap<Integer, List<Location>>();
		for(Location location : locations) {
			if(location.getPid() == 0) {
				LocationManage.provinces.put(location.getId(), location);
				LocationManage.relation.put(location.getId(), new ArrayList<Location>());
			} else {
				LocationManage.cities.put(location.getId(), location);
				LocationManage.relation.get(location.getPid()).add(location);
			}
		}
	}
	
	public static Location get(Integer id) {
		return LocationManage.provinces.get(id) != null ? LocationManage.provinces.get(id) : LocationManage.cities.get(id);
	}
	
	public static List<Location> getProvinces() {
		return new ArrayList<Location>(provinces.values());
	}
	
	public static List<Location> getCities(int id) {
		return LocationManage.relation.get(id);
	}
}
