package com.xuan.dao;

import com.xuan.cmd.common.AccountStatus;
import com.xuan.cmd.common.AccountType;
import com.xuan.entity.Account;
import org.apache.commons.codec.digest.DigestUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hx on 2019/8/4
 */
public class AccountDao extends BaseDao {
    public boolean register(Account account) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean flag = false;
        try {
            connection = this.getConnection(true);
            String sql = "insert into account(username,password," +
                    "name,account_type,account_status) values(?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, DigestUtils.md5Hex(account.getPassword()));
            preparedStatement.setString(3, account.getName());
            preparedStatement.setInt(4, account.getAccountType().getFlag());
            preparedStatement.setInt(5, account.getAccountStatus().getFlag());

            flag = (preparedStatement.executeUpdate() == 1);

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                account.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }
        return flag;
    }

    //操作数据库
    public Account login(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = null;
        try {
            connection = this.getConnection(true);
            String sql = "select id,username,password,name,account_type,account_status " +
                    "from account where username=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, DigestUtils.md5Hex(password));
            resultSet = preparedStatement.executeQuery();
            //返回结果收集到resultSet
            if (resultSet.next()) {
                //解析resultSet,拿到对应的account
                account = this.extractAccount(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }
        return account;
    }

    public List<Account> queryAccounts() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Account> accountList = new ArrayList<>();
        try{
            connection = this.getConnection(true);
            String sql = "select * from account ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Account account = this.extractAccount(resultSet);
                accountList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(resultSet,preparedStatement,connection);
        }
        return accountList;
    }

    public boolean resetPassword(Account account) {
        boolean effect = false;
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = this.getConnection(false);
            String sql = "update account set password=? where id=?;";
            pre = conn.prepareStatement(sql);
            pre.setString(1, account.getPassword());
            pre.setInt(2, account.getId());
            effect = pre.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(null, pre, conn);
        }
        return effect;
    }

    public boolean stopAccount(Account account) {
        boolean eff = false;
        Connection conn = null;
        PreparedStatement pre = null;
        try {
            conn = this.getConnection(false);
            String sql = "update account set account_status=? where id=?;";
            pre = conn.prepareStatement(sql);
            pre.setInt(1, account.getAccountStatus().getFlag());
            pre.setInt(2, account.getId());
            eff = pre.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(null, pre, conn);
        }
        return eff;
    }

    public Account queryAccountById(int accountId) {
        Connection conn = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            conn = this.getConnection(true);
            String sql = "select * from account where id=?;";
            pre = conn.prepareStatement(sql);
            pre.setInt(1,accountId);
            res = pre.executeQuery();
            if (res.next()) {
                return this.extractAccount(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(res, pre, conn);
        }
        return null;
    }

    private Account extractAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setUsername(resultSet.getString("username"));
        account.setPassword(resultSet.getString("password"));
        account.setName(resultSet.getString("name"));
        account.setAccountType(AccountType.valueOf(resultSet.getInt("account_type")));
        account.setAccountStatus(AccountStatus.valueOf(resultSet.getInt("account_status")));

        return account;
    }



}
