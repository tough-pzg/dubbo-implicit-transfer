package com.pzg.dubbo.implicit.transfer.util;

import com.pzg.dubbo.implicit.transfer.constant.CommonArgs;
import io.opentelemetry.api.trace.Span;

/**
 * 设置  OpenTelemetry span属性
 * @author pzg
 */
public class OpenTelemetryUtil {

    private static final String PREFIX = "com.pzg.";
    private static final String OPENTELEMETRY_AUTHORIZATION = PREFIX + CommonArgs.AUTHORIZATION;

    public static void addSpanAttrs() {
        try {
            Span span = Span.current();
            span.setAttribute(OPENTELEMETRY_AUTHORIZATION, RpcContextUtil.getAuthorization());
        } catch (Exception e){
            //无操作
        }

    }
}
