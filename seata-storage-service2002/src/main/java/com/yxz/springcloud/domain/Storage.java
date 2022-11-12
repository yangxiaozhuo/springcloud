package com.yxz.springcloud.domain;

import lombok.Data;

/**
 * @author yangxiaozhuo
 * @date 2022/11/12
 */
@Data
public class Storage {

    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}


