# hola-rpc-framework 简单的RPC框架

## 前置条件
注意：本实例仅为学习RPC原理构建
如需运行本项目实例需要部署注册中心，目前该项目提供了zookeeper与alibaba nacos两种实现。默认启用Nacos实现，修改示例配置文件nacos注册中心依赖地址。并确保您使用的JDK版本为 1.8 +（包含1.8）。

- [必选]部署Nacos注册中心：https://nacos.io/zh-cn/docs/quick-start.html
- [可选]部署Zookeeper：https://zookeeper.apache.org/
- [必选]JDK 1.8+：https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html



## 备注

如需启用zookeeper注册中心，需要将注册接口实现实例化为zookeeper实现，并部署对应zookeeper版本。



## 实验内容

本实验基于 Hola-RPC-framework 快速构建一个微服务，主要包括以下几个部分：

* 使用 hola-rpc 发布服务
* 使用 hola-rpc 调用服务
* 通过 Nacos 查看服务信息

## 任务

#### 1、任务准备

从  github 上将 demo 工程克隆到本地
```bash
git clone https://github.com/yuhanwen2013/hola-rpc-framework.git
```

然后将工程导入到 IDEA 或者 eclipse。导入之后界面如下：

[![a8VSCn.png](https://s1.ax1x.com/2020/08/01/a8VSCn.png)](https://imgchr.com/i/a8VSCn)

* hola-client： 服务消费者客户端
* hola-common：公共组件类
* hola-core：hola-RPC 核心服务框架
* hola-server:  服务提供者服务端

#### 2、配置注册中心

在本地或者云端ECS部署 Nacos 作为注册中心，版本为 Nacos 1.3.1 ，建议使用docker部署。

* 获取nacos镜像

```bash
docker pull nacos/nacos-server
```

* 以单机模式运行Nacos镜像，并运行在8848端口

```bash
docker run --env MODE=standalone --name nacos -d -p 8848:8848 nacos/nacos-server
```

#### 2、配置客户端与服务端

修改 hola-client 注册中心地址

修改 `resources/rpc.properties` hola配置文件，将`rpc.nacos.address=127.0.0.1` 修改为对应Nacos注册中心地址

修改 hola-server 注册中心地址

同cline端，修改 `resources/rpc.properties` hola配置文件，将`rpc.nacos.address=127.0.0.1` 修改为对应Nacos注册中心地址

#### 3、启动服务

运行 hola-server ，入口类为ServerApplication，启动对应main方法。

[![a8KyIU.md.png](https://s1.ax1x.com/2020/08/01/a8KyIU.md.png)](https://imgchr.com/i/a8KyIU)



运行hola-client ，入口类为ClientApplication，启动对应main方法。

[![a8KsaT.md.png](https://s1.ax1x.com/2020/08/01/a8KsaT.md.png)](https://imgchr.com/i/a8KsaT)



服务运行正常后，即可查看服务调用信息。