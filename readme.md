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
</p>

------------------------------

## 📚 项目介绍
Cloud Dragon 是一套适合于企业级使用的开源微服务脚手架，可以帮助快速构建微服务架构，采用前后端分离架构，
后端主要以 Spring Cloud Alibaba & Nacos 为主，前端主要以 Vue & Element-UI 为主。


Cloud Dragon 使用 Oauth2 进行认证授权管理，采用 JWT + Redis 的方式控制令牌发放与销毁。
统一使用 Docker 部署，提供一键脚本安装，快速部署使用。支持一键快速生成前后端代码，提高开发开发效率，只需专注于业务，
是一个非常适用的微服务快速开发脚手架。

### Cloud Dragon 主要技术栈及功能规划：

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


### Cloud Dragon 平台管理页面主要功能规划：

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

|  服务           |   服务名         |  端口      |   进度 | 备注                                            |
|----------------|-----------------|-----------| ------|-------------------------------------------------|
|  数据库         |   mysql         |  3306     | ✅     |目前各应用共用1个实例，各应用可建不同的database     |
|  KV缓存         |   redis         |  6379     | ✅     | 目前共用，原则上各应用单独实例       |
|  消息中间件      |   rabbitmq      |  5672     | ✅     | 共用                            |
|  注册与配置中心  |   nacos          |  8848     | ✅     |共用                            |
|  搜索引擎中间件  |   elasticsearch  |  9200     | ✅     |共用                            |
|  日志分析工具    |   kibana         |  5601     | ✅     | 共用                            |
|  数据可视化工具  |   grafana        |  3000     | ✅     |共用                             |
|  前端页面服务    |   dragon-web     |  10080    | 🏗     | 前端管理后台页面    |
|  服务API动态网关 |   gateway        |  10081    | ✅     | 对外只开放gateway端口供前端调用     |
|  认证中心       |   auth-center    | （10082）  | ✅     | 通过gateway内网转发，不对外开发端口，开发环境开放用于调试    |
|  授权中心       |   resource-center| （10083）  | ✅     | 通过gateway内网转发，不对外开发端口，开发环境开放用于调试    |
|  用户中心       |   user-center    | （10084）  | 🏗     | 通过gateway内网转发，不对外开发端口，开发环境开放用于调试    |
|  文件上传中心    |   upload-center  | （10085）  | 🏗     | 通过gateway内网转发，不对外开发端口，开发环境开放用于调试    |
|  代码生成中心    |   code-generate-center  | （10086）  | 🏗     | 通过gateway内网转发，不对外开发端口，开发环境开放用于调试    |
|  （待完善）    |   （待完善）  |  （待完善） | （待完善）    |  （待完善）   |
