package com.mg.api.dao;

import com.mg.api.model.Photo;

public interface PhotoDao extends GenericDao<Photo, Long> {
	public Photo getByName(String name);
}