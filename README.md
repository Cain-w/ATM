# ATM
基于Springcloud-Springboot-Openfeign-Nacos-Gateway-Mybatis实现ATM机  
实现功能如下：  
1. 用户认证
2. 账户管理（用户信息管理）
3. 交易操作（存款、取款、转账）
4. 异常处理及抛出（程序错误后回滚数据库记录）


没前端 没使用Session  
Nacous作为注册中心 localhost:8848  
ATM作为ATM机服务 localhost:8001  
Consumer作为用户服务 localhost:8003  
Gateway作为网关 localhost:8099  
