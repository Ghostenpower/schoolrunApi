# School Run 校园跑步系统

## 项目简介

School Run 是一个基于 Spring Boot 的校园跑步管理系统，旨在为学校提供一个完整的跑步活动管理解决方案。该系统支持用户跑步记录管理、数据统计分析等功能。

## 技术栈

- **后端框架：** Spring Boot 3.1.4
- **数据库：** MySQL
- **ORM 框架：** MyBatis-Plus 3.5.3.1
- **缓存：** Redis
- **认证：** JWT
- **API 文档：** Knife4j 4.4.0
- **对象存储：** 腾讯云 COS
- **工具库：**
  - Lombok
  - Hutool
  - Guava
  - Apache Commons
  - FastJSON

## 系统要求

- JDK 17 或以上
- MySQL 5.7 或以上
- Maven 3.6 或以上

## 快速开始

1. **克隆项目**
   ```bash
   git clone [项目地址]
   ```

2. **配置数据库**
   - 创建 MySQL 数据库
   - 修改 `application.properties` 或 `application.yml` 中的数据库配置

3. **配置 Redis**
   - 确保 Redis 服务已启动
   - 修改 Redis 配置信息

4. **配置腾讯云 COS**
   - 在腾讯云获取 COS 的相关配置信息
   - 更新配置文件中的 COS 配置

5. **构建和运行**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

6. **访问接口文档**
   - 启动应用后访问：`http://localhost:8080/doc.html`

## 主要功能

- 用户管理
- 跑步记录管理
- 数据统计分析
- 文件上传
- 系统监控

## 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/pzj/schoolrun/
│   │       ├── config/        // 配置类
│   │       ├── controller/    // 控制器
│   │       ├── service/       // 服务层
│   │       ├── mapper/        // MyBatis mapper
│   │       ├── model/         // 数据模型
│   │       └── util/          // 工具类
│   └── resources/
│       ├── mapper/           // MyBatis XML映射文件
│       └── application.yml   // 应用配置文件
```

## 开发团队

- [开发者名称]

## 许可证

[许可证类型] 