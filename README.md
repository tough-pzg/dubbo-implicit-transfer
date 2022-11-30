# Getting Started

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


