package com.pzg.dubbo.implicit.transfer.util;

import com.pzg.dubbo.implicit.transfer.constant.CommonArgs;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 获取 RpcContext 中保存的参数
 * @author pzg
 */
public class RpcContextUtil {

    public static String getAuthorization() {
        return RpcContext.getContext().getAttachment(CommonArgs.AUTHORIZATION);
    }


}
