package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;
import com.example.demo.service.AlienService;

@RestController
public class AlienController {
	@Autowired
	AlienRepo repo;
	
	//@Autowired
	//AlienService service;
	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
	}
	@RequestMapping("/alienAction")
    public String addAlien(Alien alien)
    {
		repo.save(alien);
    	return "home.jsp";
    }
	@RequestMapping("/getAlien")
    public ModelAndView addAlien(@RequestParam int aid)
    {
		ModelAndView mv=new ModelAndView("showAlien.jsp");
		Alien alien=repo.findById(aid).orElse(new Alien());
		System.out.println(repo.findByTech("Java"));
		System.out.println(repo.findByAidGreaterThan(52));
		System.out.println(repo.findByTechSorted("Java"));
		mv.addObject(alien);
		return mv;
    }
//	@RequestMapping("/updateAlien")
//	public String updateAlien(Alien alien)
//	{
//		repo.save(alien);
//		return "home.jsp";
//	}
//	@RequestMapping("/deleteAlien")
//	public String deleteAlien(@RequestParam int aid)
//	{
//		repo.deleteById(aid);
//		return "home.jsp";
//	}
	// Method to restrict for special format.,produces="application/xml"
	@GetMapping(path="/aliens")
	public List<Alien> getAliens()
	{
		return repo.findAll();
	}
	@RequestMapping("/aliens/{aid}")
	public Optional<Alien> getAlien(@PathVariable("aid") int aid)
	{
		return repo.findById(aid);
	}
	
	// Method to accept data from client. in json form only
	@PostMapping(path="/alien",consumes="application/json")// to limit which type of data we can get.
	public Alien getAliens(@RequestBody Alien alien)// for raw data we use RequestBody
	{
		repo.save(alien);
		return alien;
	}
	
	@DeleteMapping(path="/alien/{aid}")
	public String delete(@PathVariable int aid)
	{
		repo.deleteById(aid);
		return "deleted";
	}
	
	@PutMapping(path="/alien",consumes="application/json")
	public Alien saveOrUpdate(@RequestBody Alien alien)
	{
		repo.save(alien);
		return alien;
	}
}
