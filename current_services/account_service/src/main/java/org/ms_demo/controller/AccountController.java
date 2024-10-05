package org.ms_demo.controller;

import org.apache.ibatis.annotations.Update;
import org.ms_demo.service.AccountService;
import org.redisson.Redisson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;



    @GetMapping("/get_balance")
    public float getBalance(@RequestParam int uid) {
        return accountService.getBalance(uid);
    }

    @PutMapping("/decr_balance")
    public void decrBalance(@RequestParam int uid, @RequestParam float amount) {

    }
}
