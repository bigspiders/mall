package com.goodstuff.mall.admin.web;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.goodstuff.mall.admin.annotation.RequiresPermissionsDesc;
import com.goodstuff.mall.core.util.ResponseUtil;
import com.goodstuff.mall.core.validator.Order;
import com.goodstuff.mall.core.validator.Sort;
import com.goodstuff.mall.db.domain.LitemallSearchHistory;
import com.goodstuff.mall.db.service.LitemallSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/history")
public class AdminHistoryController {
    private final Log logger = LogFactory.getLog(AdminHistoryController.class);

    @Autowired
    private LitemallSearchHistoryService searchHistoryService;

    @RequiresPermissions("admin:history:list")
    @RequiresPermissionsDesc(menu={"用户管理" , "搜索历史"}, button="查询")
    @GetMapping("/list")
    public Object list(String userId, String keyword,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallSearchHistory> footprintList = searchHistoryService.querySelective(userId, keyword, page, limit, sort, order);
        long total = PageInfo.of(footprintList).getTotal();
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", footprintList);

        return ResponseUtil.ok(data);
    }
}
