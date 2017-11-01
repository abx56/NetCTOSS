package com.tarena.dao;

import java.util.List;

import com.tarena.entity.Cost;

public interface CostDao {
	
	List<Cost> findAll();
	
	Cost findById(int id);
}
