package com.xuan.service;

import com.xuan.dao.GoodsDao;
import com.xuan.entity.Goods;

import java.util.List;

/**
 * Created by hx on 2019/8/6
 */
public class GoodsService {

     private GoodsDao goodsDao;

    public GoodsService() {
        this.goodsDao = new GoodsDao();
    }

    public List<Goods> quarryAllGoods() {
        return this.goodsDao.quarryAllGoods();
    }

    public Goods getGoods(int goodsId) {
        return this.goodsDao.getGoods(goodsId);
    }

    public boolean soldOutGoods(Goods goods) {
        return this.goodsDao.soldOutGoods(goods);
    }

    public boolean putAwayGoods(Goods goods) {
        return this.goodsDao.putAwayGoods(goods);
    }

    public boolean modifyGoods(Goods goods) {
        return this.goodsDao.modifyGoods(goods);
    }

    public boolean updateAfterPay(Goods goods, int goodsNum) {
        return this.goodsDao.updateAfterPay(goods, goodsNum);
    }
}
