package org.ms_demo.component;

import org.ms_demo.entity.Account;
import org.ms_demo.service.AccountService;
import org.ms_demo.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 */
@Component
public class AccountInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AccountService accountService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Initializing Account");
        JedisUtil.del("accounts");
        List<Account> allAcount = accountService.getAllAcount();
        if (allAcount.size() > 0) {
            for (Account account : allAcount) {
                JedisUtil.hset("accounts",String.valueOf(account.getUid()),String.valueOf(account.getBalance()));
            }
        }
    }
}
