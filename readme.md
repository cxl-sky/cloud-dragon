<p align="center">
  <strong>云龙  Cloud Dragon</strong>
</p>
<p align="center">
	<strong>适合于企业级别的微服务开发脚手架，功能齐全，开箱即用，部署快捷</strong>
</p>
<p align="center">
	<a target="_blank" href="https://gitter.im/dragon_cloud/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge">
		<img src="https://badges.gitter.im/dragon_cloud/community.svg" />
	</a>
    <a target="_blank" href="https://opensource.org/licenses/Apache-2.0">
		<img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" />
	</a>
	<a target="_blank" href="https://travis-ci.org/cxl-sky/cloud-dragon">
		<img src="https://travis-ci.org/cxl-sky/cloud-dragon.svg?branch=master" />
	</a>
    <a target="_blank" href="https://qm.qq.com/cgi-bin/qm/qr?k=gQw2MJysfnRTy9qKf8hWnK-xB24c__Dj&authKey=pxIxNHae0uFqcQenxT72xKQ3Isz3Xt5p9J+d2RCUPr2HaqLv+KElTKavXBPvgKsj&noverify=0">
		<img src="https://img.shields.io/badge/QQ%E7%BE%A4-878510003-blue" />
	</a>

</p>

------------------------------
> 目前项目处于开发阶段，部分功能已经可以使用，后续会逐步完成其他规划内容，并完善项目文档，如果你在使用过程中遇到任何问题，可以通过 QQ 群聊联系我，[请戳这里](https://qm.qq.com/cgi-bin/qm/qr?k=gQw2MJysfnRTy9qKf8hWnK-xB24c__Dj&authKey=pxIxNHae0uFqcQenxT72xKQ3Isz3Xt5p9J+d2RCUPr2HaqLv+KElTKavXBPvgKsj&noverify=0) 加入群聊。


## 📚 项目介绍


Cloud Dragon 是一套适合于企业级使用的微服务脚手架项目，可以帮助快速构建微服务架构，采用前后端分离架构， 后端主要以 Spring Cloud Alibaba & Nacos 为主，前端主要以 Vue & Element-UI 为主。

Cloud Dragon 使用 Oauth2 进行认证授权管理，采用 JWT + Redis 的方式控制令牌发放与销毁。 统一使用 Docker 部署，提供一键脚本安装，快速部署使用。支持一键快速生成前后端代码，提高开发开发效率，只需专注于业务，是一个非常适用的微服务快速开发脚手架。

- [点击快速开始](./docs/quick-start.md)
- [开发指南]()

## 主要功能及规划
### 1. 主要技术栈及规划：

|  服务     | 使用技术                       |   进度        |    备注   |
|----------|------------------------------|---------------|-----------|
|  注册中心 | Nacos                         |   ✅          |           |
|  配置中心 | Nacos                         |   ✅          |           |
|  消息中心 | Spring Cloud Stream + Rabbitmq|   🏗          |           |
|  动态网关 | Spring Cloud Gateway          |   ✅          |  多种维度的流量控制（服务、IP、用户等），后端可配置化          |
|  认证中心 | Spring Cloud Security OAuth2  |   ✅          |  发放 Token 服务（JWT）
|  授权认证 | Spring Cloud Security OAuth2  |   ✅          |  根据 Token 判断权限服务   |
|  服务容错 | Spring Cloud Sentinel         |   🏗          |           |
|  服务调用 | Spring Cloud OpenFeign        |   ✅          |           |
|  对象存储 | FastDFS/Minio                 |   🏗          |           |
|  任务调度 | XXL-Job                       |   🏗          |           |
|  分库分表 | Sharding Sphere               |   🏗          |           |
|  数据权限 | Mybatis Plus                  |   🏗          |  对原查询做增强，业务代码不用控制，即可实现。         |
|  代码生成 |                               |   🏗          |  前后端代码的生成，支持Vue         |
|  文档管理 | Swagger2                      |   🏗          |           |
|  服务监控 | Spring Boot Admin             |   🏗          |           |
|  链路追踪 | SkyWalking                    |   🏗          |           |
|  操作审计 |                               |   🏗          |  系统关键操作日志记录和查询         |
|  日志管理 | ES + Kibana + Logstash        |   🏗          |  ELK         |
|  监控告警 | Grafana                       |   🏗          |           |
|  持续集成与部署 | Jenkins + Docker + Docker Compose   |   🏗          |           |
|  集群部署 | Jenkins + Docker Swarm                  |   🏗          |  使用 Docker 原生集群部署管理工具         |


### 2. 平台管理页面主要功能规划：

|  服务        | 使用技术     |   进度         |    备注   |
|-------------|-------------|---------------|-----------|
|  用户管理     | 自开发       |   🏗          |  用户是系统操作者，该功能主要完成系统用户配置。          |
|  角色管理     | 自开发       |   🏗          |  角色菜单权限分配、设置角色按机构进行数据范围权限划分。   |
|  菜单管理     | 自开发       |   🏗          |  配置系统菜单，操作权限，按钮权限标识等。               |
|  部门管理     | 自开发       |   🏗          |  配置系统组织机构，树结构展现，可随意调整上下级。        |
|  代码生成管理  | 自开发       |   🏗          |  用于生成系统页面代码，包括前端代码、后端代码        |
|  统一文档管理  | 自开发       |   🏗          |  文档管理                                     |
|  操作审计     | 自开发       |   🏗          |  系统关键操作日志记录和查询         |
|  缓存管理     | 自开发       |   🏗          |  通过页面可视化管理缓存         |

## 🥞 系统架构
### 系统架构图
> 待完善
### 项目服务
> 以下服务均采用 Docker & Docker Compose，支持一键部署。（待完善）
#### 1. 基础服务

|  服务           |   服务名         |  端口      |   进度 | 备注                                            |
|----------------|-----------------|-----------| ------|-------------------------------------------------|
|  数据库         |   mysql         |  3306     | ✅     | 共用，各应用可建不同的 database                     |
|  KV缓存         |   redis         |  6379     | ✅     | 共用，原则上各应用单独实例                          |
|  消息中间件      |   rabbitmq      |  5672     | ✅     | 共用                                            |
|  注册与配置中心  |   nacos          |  8848     | ✅     | 共用                                            |
|  搜索引擎中间件  |   elasticsearch  |  9200     | ✅     | 共用                                            |
|  日志分析工具    |   kibana         |  5601     | ✅     | 共用                                            |
|  数据可视化工具  |   grafana        |  3000     | ✅     | 共用                                            |
|  对象存储服务    |   MinIO          |  3001     | 🏗     | 共用                                             |

#### 2.应用服务
> 以下服务均需要依赖基础服务中的 nacos，务必确保 nacos 正常运行，项目的配置文件均保存在 nacos 配置中心统一管理，本地无配置
> 所有应用都需要进过网关，务必保证网关启动（开发环境调试可不做限制）

| 服务分类     | 服务名                 | 进度    |  依赖基础组件             |   简介                      |  应用地址                  | 文档     |
|------------|-----------------------|----|-------------------------|-----------------------------|--------------------------|----------|
|  web       | dragon-web            | 🏗  | vue3、element-ui        |  前端管理页面                  |  http://localhost:10080  | 待完善    |
|  common    | 基础公共依赖包           | ✅  |                         |  基础公共依赖包               |  jar包引入                |          |
|  gateway   | gateway               | ✅  | resource-server         |  统一API网关，负责路由转发      |  http://localhost:10081  | 待完善    |
|  auth      | auth-server           | ✅  | mysql、redis            |  认证服务，生成jwt             |  http://localhost:10082  | 待完善    |
|  auth      | resource-server       | ✅  | mysql、user-server      |  授权服务，判断是否可以访问      |  http://localhost:10083  | 待完善    |
|application | user-center           | 🏗  | mysql                   |  用户中心，权限角色管理等        |  http://localhost:10084  | 待完善    |
|application | upload-center         | 🏗  | mysql、MinIO            |  文件上传中心                  |  http://localhost:10085  | 待完善    |
|application | code-generate-center  | 🏗  | mysql                   |  代码生成中心，快速生成前后端代码 |  http://localhost:10086  | 待完善    |
|未完待续      |      ...             | ... | ...                     |  ...                        | ...                      | ...      |


## 联系交流

### 加入贡献代码

请入群 [请戳这里]() 加群主微信。

### 给作者 Star 支持

如果你觉的有帮助到您，请帮忙点击右上角 star 支持我，您的支持是我的动力。

### 学习交流

EMail：ustbcxl@163.com

加群[请戳这里](https://qm.qq.com/cgi-bin/qm/qr?k=gQw2MJysfnRTy9qKf8hWnK-xB24c__Dj&authKey=pxIxNHae0uFqcQenxT72xKQ3Isz3Xt5p9J+d2RCUPr2HaqLv+KElTKavXBPvgKsj&noverify=0)

