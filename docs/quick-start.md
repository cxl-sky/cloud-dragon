<p align="center">
        <img src="logo.png" width="50px" syt height="50px" />
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
    <a target="_blank" href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">
		<img src="https://img.shields.io/badge/JDK-8+-green.svg" />
	</a>
	<a target="_blank" href="https://spring.io/projects/spring-boot">
		<img src="https://img.shields.io/badge/spring%20boot-2.2.5-yellowgreen" />
	</a>
</p>

## 快速开始

### 先决条件

首先本机先要安装以下环境，建议先学习了解 SpringBoot 和 SpringCloud 基础知识，建议采用 Docker 容器部署，需要学习 Docker 相关知识。

- [git（必须）](https://git-scm.com/)
- [java8（必须）](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
- [maven（必须）](http://maven.apache.org/) 
- [docker（可选）](https://docs.docker.com/engine/install/) 


### 开发环境搭建
> 后续的部署均使用 Docker 部署，如果你不需要 Docker，那么需要手动安装各个服务，当然建议还是使用 Docker 部署。

**一键脚本启动：**
1. Linux 和 Mac 系统可在项目根目录下执行 `./install.sh` 快速搭建开发环境。  
2. Windows 系统可在项目根目录下执行 `./install.bat` 快速搭建开发环境。  
> 如要了解具体的步骤，请看如下文档。  

**开发环境：**

1. 克隆代码库： `git clone https://github.com/cxl-sky/cloud-dragon.git`

2. 安装公共库到本地仓库：

`cd common && mvn install`

`cd resource_server/resource-client && mvn install`

3. 使用 IDEA 导入项目，由于项目中使用了 Lombok，所以需要安装 Lombok 插件，并配置 JDK，下载 Maven 依赖，配置 Docker 环境

### 编译 & 启动
> 进入项目 devops/docker-compose 目录，在这里0提供了环境部署的 docker-compose.yml 文件，以便一键启动项目，文件有编号，按照文件编号顺序执行，也可以按照需要执行，具体步骤如下：

#### 1. 启动基础服务
以下为程序的基础服务，其中数据库，KV缓存，消息中间件是必须要启动的基础服务。
     
|  服务           |   服务名         |  端口     | 备注                                            |
|----------------|-----------------|-----------|-------------------------------------------------|
|  数据库         |   mysql         |  3306     |  共用，应用使用不同的database     |
|  KV缓存         |   redis         |  6379     |  共用    |
|  消息中间件      |   rabbitmq      |  5672     |  共用                          |
|  注册与配置中心  |   nacos         |  8848     |  公用             |
|  日志收集中间件  |   zipkin-server |  9411     |  共用                          |
|  搜索引擎中间件  |   elasticsearch |  9200     |  共用    |
|  日志分析工具    |   kibana        |  5601     |  共用    |
|  数据可视化工具  |   grafana       |  3000     |  共用    |

docker-compose-1-base.yml 这个文件中放了 redis、mysql、rabbitmq 这三个服务 ，可以使用 `docker-compose -f docker-compose-1-base.yml up` 一键启动，也可以使用 `docker-compose -f docker-compose-1-base.yml up -d 服务名` 启动其中某一个服务。

以上步骤执行完后，基础环境就创建好了，接下来需要初始化脚本并启动nacos。

#### 2. 创建数据库表及初始化数据
    
有部分应用需要预先初始化数据库表结构及数据 (例如 nacos、认证中心等)，本项目将初始化脚本放在了 conf 目录下各个子目录下的 `init.sql` 文件，例如: `config/auth/init.sql`，`config/nacos/init.sql`
    
本项目使用 docker 对数据库初始化，运行 `docker-compose -f docker-compose-2-init-db.yml up` 来初始化数据库表及数据，如果无需初始化的表，可以在 `docker-compose-2-init-db.yml`文件中，注释对应的初始化脚本文件


#### 3. 启动 nacos

docker-compose-3-nacos.yml 这个文件主要为了启动 nacos 注册中心和配置中心，后续的服务都依赖于 nacos，是必须要启动的基础服务之一。


#### 4. 启动应用
目前已经开发的应用包括如下几个：

| 服务名                     |  依赖基础组件             |   简介                        |  应用地址                 | 文档     |
|---------------------------|-------------------------|------------------------------|-------------------------|----------|
| gateway                   | nacos、mysql、redis      |  动态API网关                  |  http://localhost:10081  | 待完善    |
| auth-server               | nacos、mysql、redis      |  认证中心                     |  开发调试 http://localhost:10082，正式发布后不暴露端口，通过网关转发  | 待完善    |
| resource-server           | nacos、mysql、redis      |  鉴权中心                     |  开发调试 http://localhost:10083，正式发布后不暴露端口，通过网关转发  | 待完善    |
| user-center               |                         |  用户中心，权限角色管理等        |  开发调试 http://localhost:10084，正式发布后不暴露端口，通过网关转发  | 待完善    |
| upload-center             |                         |  文件上传中心                  |  开发调试 http://localhost:10085，正式发布后不暴露端口，通过网关转发  | 待完善    |
| code-generate-center      |                         |  代码生成中心，快速生成前后端代码  |  开发调试 http://localhost:10086，正式发布后不暴露端口，通过网关转发  | 待完善    |
| web                       |                         |  前端项目                     |  http://localhost:18888  | 待完善    |
到对应的项目目录下执行 `mvn clean package && docker build`，会将 jar 包打成一个 docker 镜像。

运行 `docker-compose -f docker-compose-6-application up` 即可一键启动所有的项目。


### 项目功能预览
**用户管理**
> 待完善

**角色管理**
> 待完善

**权限管理**
> 待完善

**部门管理**
> 待完善

**API文档**
> 待完善

**文件上传**
> 待完善

**代码生成**
> 待完善


## 联系交流


### 给作者 Star 支持

如果你觉的有帮助到您，请帮忙点击右上角 star 支持我，您的支持是我的动力。

### 学习交流，答疑解惑

邮箱：ustbcxl@163.com

加群[请戳这里](https://qm.qq.com/cgi-bin/qm/qr?k=gQw2MJysfnRTy9qKf8hWnK-xB24c__Dj&authKey=pxIxNHae0uFqcQenxT72xKQ3Isz3Xt5p9J+d2RCUPr2HaqLv+KElTKavXBPvgKsj&noverify=0)


