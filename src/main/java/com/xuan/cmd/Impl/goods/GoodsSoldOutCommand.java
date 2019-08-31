package com.xuan.cmd.Impl.goods;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.AdminCommand;
import com.xuan.cmd.annotation.CommandMeta;
import com.xuan.entity.Goods;
import com.xuan.service.GoodsService;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "XJSP",
        desc = "下架商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsSoldOutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("下架商品");
        System.out.println("请输入想要下架的商品ID：");
        int goodsId = scanner.nextInt();
        Goods goods = this.goodsService.getGoods(goodsId);
        if (goods == null) {
            System.out.println("没有此id的商品！");
            return;
        }
        System.out.println("下架商品信息如下：");
        System.out.println(goods);
        System.out.println("请确认是否下架：Y/N");
        String confirm = scanner.next();
        if ("Y".equalsIgnoreCase(confirm)) {
            boolean effect = this.goodsService.soldOutGoods(goods);
            if (effect) {
                System.out.println("商品下架成功！");
            } else {
                System.out.println("商品下架失败！");
            }
        } else {
            System.out.println("您选择了不下架此商品！");
        }
    }
}
