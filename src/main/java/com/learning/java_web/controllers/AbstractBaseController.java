package com.learning.java_web.controllers;

import com.learning.java_web.commons.responses.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseController {
    @Autowired
    ResponseUtil responseUtil;

}
