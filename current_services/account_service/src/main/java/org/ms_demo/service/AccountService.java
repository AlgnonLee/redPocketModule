package org.ms_demo.service;

import org.ms_demo.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AutumnLeaf
 * @date 2024/10/1
 * @Description
 */
public interface AccountService {

    String createAccount(Account account);

    List<Account> getAllAcount();

    float getBalance(Integer uid);

}
