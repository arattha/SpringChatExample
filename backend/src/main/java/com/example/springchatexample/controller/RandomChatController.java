package com.example.springchatexample.controller;

import com.example.springchatexample.service.RandomChatService;
import com.example.springchatexample.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@CrossOrigin
@RequestMapping("/randomChat")
public class RandomChatController {

    @Autowired
    RandomChatService randomChatService;

    @GetMapping
    @ResponseBody
    public DeferredResult<String> join(){
        String sessionId = ServletUtil.getSession().getId();
        System.out.println(sessionId);
        final DeferredResult<String> deferredResult = new DeferredResult<>(null);
        randomChatService.join(sessionId,deferredResult);


        return deferredResult;
    }

}
