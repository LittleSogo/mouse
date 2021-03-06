package com.mouse.api.feign.mall;

import com.mouse.api.commons.enums.RefererEnum;
import com.mouse.api.commons.req.SaveCommentReq;
import com.mouse.api.commons.req.SaveOrderReq;
import com.mouse.api.hystrix.mall.HystrixOrderFeign;
import com.mouse.core.base.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author ; lidongdong
 * @Description 订单服务 feign
 * @Date 2020-01-28
 */
@FeignClient(name = "http://ROTOR-API",
        path = "rotor-api/order",
        fallbackFactory = HystrixOrderFeign.class)
public interface OrderFeign {

    /**
     * 订单列表
     *
     * @param userId   用户ID
     * @param showType 订单信息
     * @param referer
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @GetMapping("findPage")
    R findPage(@RequestParam("userId") String userId,
               @RequestParam(name = "showType", defaultValue = "0") Integer showType,
               @RequestParam(name = "referer") RefererEnum referer,
               @Min(value = 0, message = "必须从0页开始")
               @RequestParam(name = "pageNum", defaultValue = "0", required = false) Integer pageNum,
               @Min(value = 1, message = "每页必须大于1")
               @Max(value = 300, message = "每页必须小于300")
               @RequestParam(name = "pageSize", defaultValue = "20", required = false) Integer pageSize,
               @RequestParam(name = "sort", defaultValue = "add_time", required = false) String sort,
               @RequestParam(name = "order", defaultValue = "desc", required = false) String order);


    /**
     * 订单详情
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @return 订单详情
     */
    @GetMapping("detail")
    R detail(@RequestParam(name = "userId") String userId,
             @RequestParam(name = "orderId") String orderId);

    /**
     * 提交订单
     *
     * @param param  订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx, message: xxx, grouponRulesId: xxx,  grouponLinkId: xxx}
     * @return 提交订单操作结果
     */
    @PostMapping("submit")
    R submit(@RequestBody SaveOrderReq param);

    /**
     * 取消订单
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 取消订单操作结果
     */
    @PostMapping("cancel")
    R cancel(@RequestParam(name = "userId") String userId,
             @RequestParam(name = "orderId") String orderId);

    /**
     * 确认收货
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("confirm")
    R confirm(@RequestParam(name = "userId") String userId,
              @RequestParam(name = "orderId") String orderId);

    /**
     * 删除订单
     *
     * @param userId  用户ID
     * @param orderId 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("delete")
    R delete(@RequestParam(name = "userId") String userId,
             @RequestParam(name = "orderId") String orderId);

    /**
     * 待评价订单商品信息
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @param goodsId 商品ID
     * @return 待评价订单商品信息
     */
    @GetMapping("goods")
    R goods(@RequestParam(name = "userId") String userId,
            @RequestParam(name = "orderId") String orderId,
            @RequestParam(name = "goodsId") Integer goodsId);

    /**
     * 评价订单商品
     *
     * @param userId 用户ID
     * @param param  订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    @PostMapping("comment")
    R comment(@RequestParam(name = "userId") String userId,
              @RequestBody SaveCommentReq param);
}
