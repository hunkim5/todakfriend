package com.todak.todakf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(value="test")
	public String test() {
		return "test11";
	}
}
