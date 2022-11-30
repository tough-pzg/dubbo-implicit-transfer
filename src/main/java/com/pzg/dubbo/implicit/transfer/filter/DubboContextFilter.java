package com.pzg.dubbo.implicit.transfer.filter;

import com.pzg.dubbo.implicit.transfer.util.OpenTelemetryUtil;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * 服务提供方Filter
 * @author pzg
 */
@Activate(group = PROVIDER)
public class DubboContextFilter implements Filter, Filter.Listener {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //服务提供方提供服务前，设置一下自定义Trace-span属性
        OpenTelemetryUtil.addSpanAttrs();
        return invoker.invoke(invocation);
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {

    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {

    }
}
