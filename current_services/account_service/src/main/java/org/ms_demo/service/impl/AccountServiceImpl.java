package org.ms_demo.service.impl;

import com.netflix.discovery.converters.Auto;
import org.ms_demo.entity.Account;
import org.ms_demo.mapper.AccountMapper;
import org.ms_demo.service.AccountService;
import org.ms_demo.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public String createAccount(Account account) {
        return "";
    }

    @Override
    public List<Account> getAllAcount() {
        List<Account> accounts = accountMapper.selectList(null);
        return accounts;
    }

    @Override
    public float getBalance(Integer uid) {
        String Balance = JedisUtil.hget("accounts", String.valueOf(uid));
        return Float.parseFloat(Balance);
    }

}
