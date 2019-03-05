package com.goodstuff.mall.admin.web;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.goodstuff.mall.admin.annotation.RequiresPermissionsDesc;
import com.goodstuff.mall.core.util.ResponseUtil;
import com.goodstuff.mall.core.validator.Order;
import com.goodstuff.mall.core.validator.Sort;
import com.goodstuff.mall.db.domain.LitemallComment;
import com.goodstuff.mall.db.service.LitemallCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/comment")
@Validated
public class AdminCommentController {
    private final Log logger = LogFactory.getLog(AdminCommentController.class);

    @Autowired
    private LitemallCommentService commentService;

    @RequiresPermissions("admin:comment:list")
    @RequiresPermissionsDesc(menu={"商品管理" , "评论管理"}, button="查询")
    @GetMapping("/list")
    public Object list(String userId, String valueId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallComment> brandList = commentService.querySelective(userId, valueId, page, limit, sort, order);
        long total = PageInfo.of(brandList).getTotal();
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("items", brandList);

        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:comment:delete")
    @RequiresPermissionsDesc(menu={"商品管理" , "评论管理"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallComment comment) {
        Integer id = comment.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        commentService.deleteById(id);
        return ResponseUtil.ok();
    }

}
