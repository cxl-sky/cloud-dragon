#!/usr/bin/env bash
echo '==================1.开发环境准备================================'
echo '1.1请确保安装并java8, git, maven并设置好环境变量'
echo '1.2请确保安装并docker,docker-compose并设置好环境变量'

#确认环境信息准备就绪
read -r -p "开发环境准备好了吗? [Y/n] " envConfirm
case $envConfirm in
    [yY][eE][sS]|[yY])
		echo "Yes 继续执行"
		;;
    [nN][oO]|[nN])
		echo "No 终止执行"
		exit 1
       	;;
    *)
		echo "Invalid input... 终止执行"
		exit 1
		;;
esac

#echo '==================1.3清理当前脚本启动的容器和产生的镜像(可选的)=============='
#清理当前脚本启动的容器和产生的镜像(可选的)
#docker stop sc-rabbitmq sc-redis sc-mysql
#docker rm sc-rabbitmq sc-redis sc-mysql
#docker image rm rabbitmq:alpine redis:alpine mysql:9.6-alpine

#docker stop sc-monitor-admin sc-authorization-server sc-authentication-server sc-organization sc-gateway-admin sc-gateway-web
#docker rm sc-monitor-admin sc-authorization-server sc-authentication-server sc-organization sc-gateway-admin sc-gateway-web
#docker image rm cike/admin cike/authorization-server:latest cike/authentication-server:latest cike/organization:latest cike/gateway-admin:latest cike/gateway-web:latest

echo '==================2.安装认证公共包到本地maven仓库=================='
#安装认证公共包到本地maven仓库
cd common && mvn install
echo '当前目录:' && pwd

#回到根目录
cd -

echo '==================4.docker-compose启动公共服务==================='
#去devops/docker-compose目录
cd devops/docker-compose
echo '==================创建 docker 网络: =========='
docker network create -d bridge dragon-net

echo '==================4.1 显示环境变量: =========='
echo '==================redis.env: =========='
cat ../env/redis.env
echo '==================rabbitmq.env: =========='
cat ../env/rabbitmq.env
echo '==================mysql.env: =========='
cat ../env/mysql.env
echo '==================elasticsearch.env: =========='
cat ../env/elasticsearch.env
echo '==================nacos.env: =========='
cat ../env/nacos.env
echo ''

#开启公共服务
echo '==================4.2 启动 mysql or redis or rabbitmq ========'
docker-compose -f docker-compose-1-base.yml up
#按需启动
#docker-compose -f docker-compose-1-base.yml up -d mysql
#docker-compose -f docker-compose-1-base.yml up -d redis
#docker-compose -f docker-compose-1-base.yml up -d rabbitmq


#确认是否需要初始化数据库脚本
echo '你可以编辑 devops/docker-compose-2-init 中需要执行的 sql 初始化脚本,然后回来继续...'
read -r -p "确认初始化的数据库脚本了吗? [Y/n] " sqlInitConfirm
case $sqlInitConfirm in
    [yY][eE][sS]|[yY])
		echo "Yes 继续执行"
		docker-compose -f docker-compose-2-init.yml up
		;;
    [nN][oO]|[nN])
		echo "No 终止执行"
		exit 1
       	;;
    *)
		echo "Invalid input... 终止执行"
		exit 1
		;;
esac

echo '==================4.3 启动 elk ========'
docker-compose -f docker-compose-3-elk.yml up
#按需启动
#docker-compose -f docker-compose-3-elk.yml up -d elasticsearch
#docker-compose -f docker-compose-3-elk.yml up -d kibana

echo '==================4.4 启动 nacos ========'
docker-compose -f docker-compose-4-nacos.yml up
#按需启动
#docker-compose -f docker-compose-4-nacos.yml up -d nacos

echo '==================4.5 启动 prometheus、grafana ========'
docker-compose -f docker-compose-5-monitor.yml up
#按需启动
#docker-compose -f docker-compose-5-monitor.yml up -d prometheus
#docker-compose -f docker-compose-5-monitor.yml up -d grafana

echo '当前目录:' && pwd

#回到根目录
cd -
