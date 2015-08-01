package com.ethercamp.starter.rest;


import com.ethercamp.starter.ethereum.EthereumBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MyRestController {

    @Autowired
    EthereumBean ethereumBean;

    @RequestMapping(value = "/bestBlock", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getBestBlock() throws IOException {
        return ethereumBean.getBestBlock();
    }


}
