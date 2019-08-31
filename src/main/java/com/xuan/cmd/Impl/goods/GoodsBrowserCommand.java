package com.xuan.cmd.Impl.goods;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.AdminCommand;
import com.xuan.cmd.annotation.CommandMeta;
import com.xuan.cmd.annotation.CustomerCommand;
import com.xuan.entity.Goods;

import java.util.List;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "LLSP",
        desc = "浏览商品",
        group = "商品信息"
)
@AdminCommand
@CustomerCommand
public class GoodsBrowserCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("浏览商品");
        //1.查询商品
        List<Goods> goodsList = this.goodsService.quarryAllGoods();
        for (Goods goods : goodsList) {
            System.out.println(goods.toString());
        }
    }
}
