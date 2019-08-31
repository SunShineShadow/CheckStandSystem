package com.xuan.cmd.Impl.goods;

import com.xuan.cmd.Impl.AbstractCommand;
import com.xuan.cmd.Subject;
import com.xuan.cmd.annotation.AdminCommand;
import com.xuan.cmd.annotation.CommandMeta;
import com.xuan.entity.Goods;

/**
 * Created by hx on 2019/8/4
 */
@CommandMeta(
        name = "SJSP",
        desc = "上架商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsPutAwayCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("上架商品");
        System.out.println("商品名称：");
        String name = scanner.nextLine();
        System.out.println("商品简介：");
        String introduce = scanner.nextLine();
        System.out.println("商品库存：");
        Integer stock = scanner.nextInt();
        System.out.println("商品单位：(包，个，箱...)");
        String unit = scanner.next();
        System.out.println("商品价格：单位（元）");
        int price = new Double(100 * scanner.nextDouble()).intValue();
        System.out.println("请输入商品折扣：75表示75折");
        int discount = scanner.nextInt();

        Goods goods = new Goods();
        goods.setName(name);
        goods.setIntroduce(introduce);
        goods.setStock(stock);
        goods.setUnit(unit);
        goods.setPrice(price);
        goods.setDiscount(discount);

        boolean effect = this.goodsService.putAwayGoods(goods);
        if (effect) {
            System.out.println("上架成功");
        } else {
            System.out.println("上架失败");
        }
    }
}
