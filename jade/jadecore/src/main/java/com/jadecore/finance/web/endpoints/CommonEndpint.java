/*******************************************************************************
 *
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER. 
 *  Copyright (c) 2015 Jade Global
 *  All Rights Reserved. All content is proprietary and confidential.
 *
 *******************************************************************************/
package com.jadecore.finance.web.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jadecore.finance.abstraction.service.CommonService;

@RestController
@RequestMapping(value = "/common")
public class CommonEndpint {

	private CommonService commonService;

	@Autowired
	public CommonEndpint(CommonService commonService) {
		this.commonService = commonService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String helloWorld() {
		return commonService.helloWorld();
	}

}
