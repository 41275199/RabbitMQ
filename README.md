## 使用方式
 ### 1.补全application.yml中RabbitMQ的ip
 ### 2.填上用户名和密码（初次默认为guest）
 ### 3.创建数据库，执行sql/db.sql脚本文件
 ### 4.运行本程序 swagger地址http://127.0.0.1:8080/swagger-ui/index.html#/
 
 ## 业务背景
### 用户下单后，一段时间未支付，关闭订单
### RabbitMQConfig中设置了延迟时间为1分钟

## 技术背景
###SpringBoot/MyBatis/MySQL/RabbitMQ/Swagger3.0