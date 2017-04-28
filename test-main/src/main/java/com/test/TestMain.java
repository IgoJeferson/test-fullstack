/*
 * Product: OMotor
 * Copyright (C) 2017 OMotor. All Rights Reserved.
 */
package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.test.controller.UsuarioResource;

/**
 * Start da aplicação
 * @author esdrastavares
 *
 */
@SpringBootApplication
public class TestMain {

	public static void main(String[] args) {
		SpringApplication.run(TestMain.class, args);
	}
}
