/*
 * Copyright 2017-Present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring.cloud.samples.fortuneteller.fortuneservice.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.cloud.samples.fortuneteller.fortuneservice.domain.Fortune;
import io.spring.cloud.samples.fortuneteller.fortuneservice.respositories.FortuneRepository;

@RestController
public class FortuneController {

	Logger logger = LoggerFactory.getLogger(FortuneController.class);

	@Autowired
	FortuneRepository repository;

	@RequestMapping("/fortunes")
	public Iterable<Fortune> fortunes() {
		return repository.findAll();
	}

	@RequestMapping("/random")
	public Fortune randomFortune() {
		logger.debug("Handling request to /random!");
		logger.info("SR: received call to /random.");
		List<Fortune> randomFortunes = repository.randomFortunes(new PageRequest(0, 1));
		logger.info("SS: responding to call to /random.");
		return randomFortunes.get(0);
	}
}
