package com.goodstuff.mall.wx.util;

import com.goodstuff.mall.db.domain.LitemallCart;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class FreeFreightUtils {
    //免费商品免运费
    public static boolean isFreeFreight(List<LitemallCart> checkedGoodsList){
        for(LitemallCart goods:checkedGoodsList){
            String name=goods.getGoodsName();
            if(StringUtils.isBlank(name)){
                return false;
            }
            if(name.trim().indexOf("捐助有好货开源软件")==-1){
                return false;
            }
        }

        return true;
    }
}
