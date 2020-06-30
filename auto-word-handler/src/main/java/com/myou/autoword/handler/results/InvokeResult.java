package com.myou.autoword.handler.results;

import com.myou.autoword.handler.results.FailResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author myou
 * @Date 2020/6/30  10:54 上午
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvokeResult {
    private boolean isSuccess;
    private byte[] resultStream;
    private FailResult failResult;
}
