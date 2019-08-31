package com.xuan.service;

import com.xuan.dao.AccountDao;
import com.xuan.entity.Account;

import java.util.List;

/**
 * Created by hx on 2019/8/4
 */
public class AccountService {

    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDao();
    }

    public Account login(String username, String password) {
        return this.accountDao.login(username, password);
    }

    public boolean register(Account account) {
        return this.accountDao.register(account);
    }

    public List<Account> queryAllAcounts() {
        return this.accountDao.queryAccounts();
    }

    public Account queryAccountsById(int accountId) {
        return this.accountDao.queryAccountById(accountId);
    }

    public boolean reSetPassword(Account account) {
        return this.accountDao.resetPassword(account);
    }

    public boolean stopAccount(Account account) {
        return this.accountDao.stopAccount(account);
    }
}
