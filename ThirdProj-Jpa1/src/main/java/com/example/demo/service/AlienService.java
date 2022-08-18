package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;

@Service
public class AlienService {
	
	//@Autowired
	AlienRepo repo;
	
	public List<Alien> getAliens()
	{
		return repo.findAll();
	}

}
