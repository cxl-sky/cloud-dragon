<p align="left">
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

3. 使用 IDE 导入项目，由于项目中使用了 Lombok，所以需要安装 Lombok 插件，并配置 JDK，下载 Maven 依赖

### 编译 & 启动
> 进入项目 devops/docker-compose 目录，在这里提供了环境部署的 docker-compose.yml 文件，以便一键启动项目，文件有编号，按照文件编号顺序执行，也可以按照需要执行，具体步骤如下：

* 1. 启动基础服务
以下为程序的基础服务，其中数据库，KV缓存，消息中间件，注册中心，配置中心是必须要启动的基础服务。
     
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

    - docker-compose-1-base.yml 这个文件中放了 redis、mysql、rabbitmq 这三个服务 ，可以使用 `docker-compose -f docker-compose-1-base.yml up` 一键启动，也可以使用 `docker-compose -f docker-compose-1-base.yml 服务名 up` 启动其中某一个服务。

    - docker-compose-2-init-db.yml 这个文件主要用于初始化数据库，需要制定初始化脚本，具体可以参考其中的配置，如果已经初始化过，就不需要再次执行了。

    - docker-compose-3-nacos.yml 这个文件主要为了启动 nacos 注册中心和配置中心，后续的服务都依赖于 nacos，因为是必须要启动的基础服务之一。

    - 以上步骤执行完后，基础环境就创建好了，其他服务可以选择性启动，看个人需要。



