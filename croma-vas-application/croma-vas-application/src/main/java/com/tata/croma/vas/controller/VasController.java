package com.tata.croma.vas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tata.croma.vas.service.IVasService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/vas")
public class VasController {

	@Autowired
	IVasService service;
	
	@GetMapping("/save")
	public void savevas() throws Exception {
		log.info("vas controller called...");
		service.savedata();
	}
	
	@GetMapping("/get")
	public void getvasfromexcel() throws Exception {
		log.info("get vas from excel called...");
		service.getdata();
	}
}
