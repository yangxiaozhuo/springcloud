package com.yxz.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yxz.springcloud.entities.CommonResult;

/**
 * @author yangxiaozhuo
 * @date 2022/11/11
 */
public class CustomerBlockHandler
{
    public static CommonResult handleException(BlockException exception){
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler1");
    }
    public static CommonResult handleException2(BlockException exception){
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler2");
    }
}
