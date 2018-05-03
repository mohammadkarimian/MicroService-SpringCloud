package com.microservice;

import com.microservice.model.GreetingRequest;
import com.microservice.model.GreetingResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface GreetingController {
    @RequestMapping(value = "/greeting",method = RequestMethod.POST)
    @ResponseBody
    GreetingResponse greeting(@RequestBody GreetingRequest request);
}