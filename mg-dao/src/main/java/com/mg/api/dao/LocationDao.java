package com.mg.api.dao;

import java.util.List;

import com.mg.api.model.Location;

public interface LocationDao extends GenericDao<Location, Long> {
    public List<Location> getAll();
}