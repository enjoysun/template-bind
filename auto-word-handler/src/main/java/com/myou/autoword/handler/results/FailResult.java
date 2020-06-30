package com.myou.autoword.handler.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author myou
 * @Date 2020/6/30  10:53 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FailResult {
    private String failMsg;
    private String modelName;
}
