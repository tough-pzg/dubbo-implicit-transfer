package com.pzg.dubbo.implicit.transfer.interceptor;

import com.pzg.dubbo.implicit.transfer.constant.CommonArgs;
import com.pzg.dubbo.implicit.transfer.util.OpenTelemetryUtil;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pzg
 */
@Order(99)
@Component
public class ControllerHandlerInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //RpcContext 设置参数
        RpcContext context = RpcContext.getContext();
        //从 请求头中获取 AUTHORIZATION
        context.setAttachment(CommonArgs.AUTHORIZATION, request.getHeader(CommonArgs.AUTHORIZATION));

        //收到页面请求后，设置一下自定义Trace-span属性
        OpenTelemetryUtil.addSpanAttrs();
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //重要！！！ 请求处理完成之后，清除 rpcContextThreadLocal
        DubboConsumerContextClusterInterceptor.rpcContextThreadLocal.remove();
    }

}
