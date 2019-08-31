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
        name = "GXSP",
        desc = "更新商品",
        group = "商品信息"
)
@AdminCommand
public class GoodsUpdateCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        System.out.println("更新商品" );
        System.out.println("请输入更新商品的编号：");
        int goodsId = Integer.parseInt(scanner.nextLine());
        Goods goods = this.goodsService.getGoods(goodsId);
        if (goods == null) {
            System.out.println("不存在此编号的货物");
            return;
        } else {
            System.out.println("商品原信息如下：");
            System.out.println(goods);
        }
        System.out.println("请输入需要更新的商品简介：");
        String introduce = scanner.next();
        System.out.println("请输入需要更新的商品库存：");
        int stock = scanner.nextInt();
        System.out.println("请输入需要更新的商品价格：");
        int price = new Double(100 * scanner.nextDouble()).intValue();
        System.out.println("请输入需要更新的商品折扣：75表示75折");
        int discount = scanner.nextInt();
        System.out.println("请选择是否更新");
        System.out.println("请确认是否更新：Y/N");
        String confirm = scanner.next();
        if ("Y".equalsIgnoreCase(confirm)) {
            goods.setIntroduce(introduce);
            goods.setStock(stock);
            goods.setPrice(price);
            goods.setDiscount(discount);
            boolean effect = this.goodsService.modifyGoods(goods);
            if (effect) {
                System.out.println("更新商品成功！");
            } else {
                System.out.println("更新商品失败！");
            }
        } else {
            System.out.println("您选择了不更新此商品！");
        }
    }
}
