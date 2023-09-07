# Getting Started
### 前序知识
```
Dubbo远程调用的执行流程：
1.发起调用前，先执行 ConsumerContextClusterInterceptor 的 before方法 （自定义的Interceptor.before 会在默认 Interceptor.before  后执行）
2.执行 消费方Filter invoke方法
3.执行 服务方Filter invoke方法
4.远程调用目标方法
5.执行 ConsumerContextClusterInterceptor 的 after方法 （自定义的Interceptor.after 会在默认 Interceptor.after  前执行）
所以说，ConsumerContextClusterInterceptor 是执行在外层的。
注意：一个线程中 发起远程调用前后的 RpcContext 不是一个对象
```

### Dubbo 服务间调用隐式传参
```
请求： 页面 --> 服务A --> 服务B --> 服务C
        ↑        ↓
       响应      | --> 服务D
        |        |
        |        ↓ --> DB
        |        .
        |        .
        |        .
         ←  ←  ←    
                
   如上图：一次页面向服务A 发起请求，A请求了B,B又请求了C
          然后A收到了响应后，又请求 D，最后调用了DB 等等操作。
          
   本项目旨在解决: 页面传入A服务的某些参数，可以隐式的传入 B 、C、D 等服务中，
    而A、 B、 C 和 D服务中的接口。不需要声明这些参数，就可以获取！       
```


