package com.sc.tradmaster.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanjian
 */
public enum ExcelType {
    house;

    public Map<String, String> enName() {
        Map map = new LinkedHashMap();
        switch (this) {
            case house:
                map.put("houseNo", "房号");
                map.put("presalePermissionInfo", "预售证号");
                map.put("district","区位号");
                map.put("buildingNo", "楼栋号");
                map.put("unit", "单元号");
                map.put("floor", "楼层");
                map.put("direct", "朝向");
                map.put("buildArea", "建筑面积");
                map.put("usefulArea", "使用面积");
                map.put("listPrice", "列表价");
                map.put("minimumPrice", "底价");
                map.put("shopPrice", "中介供货价");
                map.put("decorationStandardName", "装修标准");
                map.put("houseTypeName", "户型名称");
                /*map.put("houseStatusName", "房源状态");
                map.put("shelvTime", "发布时间");
                map.put("isOpenName", "中介是否可见");
                map.put("houseNum", "房源编号");*/
                map.put("houseKindName", "房源类型");
                return map;
            default:
                throw new RuntimeException();
        }
    }
}
