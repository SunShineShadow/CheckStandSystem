package com.xuan.dao;

import com.xuan.entity.Goods;

import java.sql.*;
import java.util.*;

/**
 * Created by hx on 2019/8/6
 */
public class GoodsDao extends BaseDao {
    //更新商品
    public boolean modifyGoods(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "update goods set name=?,introduce=?," +
                    "stock=?,price=?,discount=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, goods.getName());
            preparedStatement.setString(2, goods.getIntroduce());
            preparedStatement.setInt(3, goods.getStock());
            preparedStatement.setInt(4, goods.getPrice());
            preparedStatement.setInt(5,goods.getDiscount());
            preparedStatement.setInt(6, goods.getId());
            effect = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            this.closeResource(null,preparedStatement, connection);
        }
        return effect;
    }
    //下架商品
    public boolean soldOutGoods(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "delete from goods where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goods.getId());
            effect = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(null, preparedStatement, connection);
        }
        return effect;
    }
    //上架商品
    public boolean putAwayGoods(Goods goods) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "insert into goods(name,introduce,stock,unit,price,discount)" +
                    "values(?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, goods.getName());
            preparedStatement.setString(2, goods.getIntroduce());
            preparedStatement.setInt(3, goods.getStock());
            preparedStatement.setString(4, goods.getUnit());
            preparedStatement.setInt(5, goods.getPrice());
            preparedStatement.setInt(6,goods.getDiscount());
            effect = preparedStatement.executeUpdate() == 1;
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                goods.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }
        return effect;
    }
    //浏览所有商品
    public List<Goods> quarryAllGoods() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Goods> list = new ArrayList<>();
        try {
            connection = this.getConnection(true);
            String sql = "select * from goods" ;
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                //解析resultSet,拿到对应的货物信息
                Goods goods = this.extractGoods(resultSet);
                if (goods != null) {
                    list.add(goods);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }
        return list;
    }
    //支付订单后更新商品
    public boolean updateAfterPay(Goods goods, int goodsBuyNum) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "update goods set stock =? where id =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goods.getStock() - goodsBuyNum);
            preparedStatement.setInt(2, goods.getId());
            effect = preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeResource(null, preparedStatement, connection);
        }
        return effect;
    }

    //根据ID获取对应商品
    public Goods getGoods(int goodsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection(true);
            String sql = "select * from goods where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodsId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return this.extractGoods(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }
        return null;
    }

    public Goods extractGoods(ResultSet resultSet) throws SQLException {
        Goods goods = new Goods();
        goods.setId(resultSet.getInt("id"));
        goods.setName(resultSet.getString("name"));
        goods.setIntroduce(resultSet.getString("introduce"));
        goods.setStock(resultSet.getInt("stock"));
        goods.setUnit(resultSet.getString("unit"));
        goods.setPrice(resultSet.getInt("price"));
        goods.setDiscount(resultSet.getInt("discount"));
        return goods;
    }


}
