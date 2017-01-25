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

public class LocationManager {
	private static Map<Integer, Location> provinces;
	
	private static Map<Integer, Location> cities;
	
	private static Map<Integer, List<Location>> relation;

	public static void setLocations(List<Location> locations) {
		LocationManager.provinces = new HashMap<Integer, Location>();
		LocationManager.cities = new HashMap<Integer, Location>();
		LocationManager.relation = new HashMap<Integer, List<Location>>();
		for(Location location : locations) {
			if(location.getPid() == 0) {
				LocationManager.provinces.put(location.getId(), location);
				LocationManager.relation.put(location.getId(), new ArrayList<Location>());
			} else {
				LocationManager.cities.put(location.getId(), location);
				LocationManager.relation.get(location.getPid()).add(location);
			}
		}
	}
	
	public static Location get(Integer id) {
		return LocationManager.provinces.get(id) != null ? LocationManager.provinces.get(id) : LocationManager.cities.get(id);
	}
	
	public static List<Location> getProvinces() {
		return new ArrayList<Location>(provinces.values());
	}
	
	public static List<Location> getCities(int id) {
		return LocationManager.relation.get(id);
	}
}
