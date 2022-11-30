package com.pzg.dubbo.implicit.transfer.interceptor;

import com.pzg.dubbo.implicit.transfer.constant.CommonArgs;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.cluster.interceptor.ConsumerContextClusterInterceptor;
import org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker;
import org.springframework.stereotype.Component;

/**
 * 自定义 ClusterInterceptor,与 Dubbo Filter不同,Dubbo Interceptor执行在最外层
 * @author pzg
 */
@Component
@Activate
public class DubboConsumerContextClusterInterceptor extends ConsumerContextClusterInterceptor {

    /**
     * dubbo每次RPC调用链结束时，会清除当前线程的 RpcContext: {@link ConsumerContextClusterInterceptor#after(AbstractClusterInvoker, Invocation)}
     * 使用 rpcContextThreadLocal 解决多次调用的问题
     */
    public static ThreadLocal<RpcContext> rpcContextThreadLocal = new ThreadLocal<>();

    @Override
    public void before(AbstractClusterInvoker<?> clusterInvoker, Invocation invocation) {
        RpcContext context = RpcContext.getContext();
        RpcContext cachedRpcContext = rpcContextThreadLocal.get();

        if (cachedRpcContext != null && cachedRpcContext != context) {
            copyAttachment(cachedRpcContext, context);
        }
    }

    @Override
    public void after(AbstractClusterInvoker<?> clusterInvoker, Invocation invocation) {
        rpcContextThreadLocal.set(RpcContext.getContext());
    }

    @Override
    public void onMessage(Result appResponse, AbstractClusterInvoker<?> clusterInvoker, Invocation invocation) {
//        super.onMessage(appResponse,clusterInvoker,invocation);
    }

    @Override
    public void onError(Throwable t, AbstractClusterInvoker<?> clusterInvoker, Invocation invocation) {
//        super.onError(t,clusterInvoker,invocation);
    }

    private void copyAttachment(RpcContext oldContext, RpcContext newContext) {
        //复制所有隐式传参参数
        newContext.setAttachment(CommonArgs.AUTHORIZATION, oldContext.getAttachment(CommonArgs.AUTHORIZATION));
    }

}
