package agentski.modul.app.controller;

import org.springframework.beans.factory.annotation.Autowired;

import agentski.modul.app.repository.ZauzeceRepository;

/**
* Generated by Spring Data Generator on 25/05/2019
*/

public class ZauzeceController {

	private ZauzeceRepository zauzeceRepository;

	@Autowired
	public ZauzeceController(ZauzeceRepository zauzeceRepository) {
		this.zauzeceRepository = zauzeceRepository;
	}

}
