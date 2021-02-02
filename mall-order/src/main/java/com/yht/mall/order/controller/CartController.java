package com.yht.mall.order.controller;

import com.yht.common.DO.CartDO;
import com.yht.common.utils.JwtUtils;
import com.yht.common.utils.Result;
import com.yht.mall.order.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 购物车
 */
@RestController
@RequestMapping("mall/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     *加入购物车
     */
    @PostMapping("/save")
    public Result saveCart(HttpServletRequest request,@RequestBody CartDO cart){
        Long userId = JwtUtils.getUserId(request.getHeader("token"));
        cart.setUserId(userId);
        cartService.saveCart(cart);
        return Result.ok();
    }
    /**
     * 查看购物车
     */
    @GetMapping("/list")
    public Result list(HttpServletRequest request){
        Long userId = JwtUtils.getUserId(request.getHeader("token"));
        List<CartDO> list = cartService.listCart(userId);
        return Result.ok().put("list",list);
    }
    /**
     * 购物车删除商品
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody Long cartId){
        cartService.removeById(cartId);
        return Result.ok();
    }
}
