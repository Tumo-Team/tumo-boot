# Docker 部署 [Ubuntu18.04]

## 本地安装Docker [MacOS]

在服务器上安装Docker之前，我们最好先在本地也安装Docker，这样方便后续的镜像自动化推送。

如何安装？

官网 [https://www.docker.com/get-started](https://www.docker.com/get-started) 找到适合自己系统的Desktop版本下载安装即可，其将会自动安装`docker-compose`，我们可以查看安装是否成功：

```shell script
docker --version
docker-compose --version
```

![](http://cdn.tycoding.cn/MIK-H1i4DT.png)


## 服务端安装Docker [Ubuntu18.04]

> Docker 安装

```shell script
sudo apt-get update
sudo apt-get -y install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL http://mirrors.aliyun.com/docker-ce/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] http://mirrors.aliyun.com/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
sudo apt-get -y update
sudo apt-get -y install docker-ce
```

> 启动 Docker 

```shell script
sudo systemctl enable docker
sudo systemctl start docker
```

> 建立 Docker 用户组

```shell script
sudo groupadd docker
sudo usermod -aG docker $USER
```

> 安装 Docker-Compose

```shell script
sudo curl -L https://github.com/docker/compose/releases/download/1.27.4/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

## 开启Ubuntu18 Docker [2375] 端口

修改`/lib/systemd/system/docker.service`文件：

```shell script
vi /lib/systemd/system/docker.service
```

追加如下内容：

```shell script
-H tcp://0.0.0.0:2375
```

![](http://cdn.tycoding.cn/MIK-vC0n0q.png)

重启Docker服务让配置生效：

```shell script
sudo systemctl daemon-reload
sudo systemctl restart docker
```

检查[2375]端口是否开启：

```shell script
lsof -i tcp:2375
```

![](http://cdn.tycoding.cn/MIK-jGIWPA.png)

本地访问Ubuntu服务器Docker 端口是否可以远程访问：`ip:2375/info`

![](http://cdn.tycoding.cn/MIK-rdDGLZ.png)

## 安装Docker私有仓库

[Docker私有仓库安装](https://www.funtl.com/zh/docs-docker/Docker-%E7%A7%81%E6%9C%89%E4%BB%93%E5%BA%93.html#%E5%AE%89%E8%A3%85%E8%BF%90%E8%A1%8C-docker-registry)

Docker私有仓库签发SSL证书时如果报错：`Can't load /root/.rnd into RNG`

```shell
cd /root
openssl rand -writerand .rnd
```



报错：`docker: Error response from daemon: OCI runtime create failed: container_linux.go:349`

是Ubuntu版本和Docker版本不兼容造成的，先查询本地Docker版本，再查询适合当前系统的Docker版本：

```shell
docker --version  #查询本地Docker版本
sudo apt-cache madison docker-ce  #查询适合本系统的Docker版本

sudo apt-get autoremove docker-ce  #卸载之前安装的Docker版本

```



## 安装 Harbor

```shell script
wget https://github.com/goharbor/harbor/releases/download/v2.1.1/harbor-online-installer-v2.1.1.tgz
tar xvzf harbor-online-installer-v2.1.1.tgz
```

以上命令，或者可以直接在GitHub下载最新release代码，copy到服务器

> 修改 harbor 配置

```shell script
mv harbor.yml.tmpl harbor.yml
vi harbor.yml
```

![](http://cdn.tycoding.cn/MIK-IdxldZ.png)

首先将`hostname`修改为访问自己服务器的IP地址。
然后将HTTPS配置注释掉（不然可能出现报错`ERROR:root:Error: The protocol is https but attribute ssl_cert is not set`）

> 安装

执行命令:

```shell script
./install.sh
```

![](http://cdn.tycoding.cn/MIK-llwsCP.png)

> 登录Harbor

浏览器访问：`http://ip`。用户名`admin`，密码`Harbor12345`

![](http://cdn.tycoding.cn/MIK-99W8XD.png)

启动Harbor，进入到`harbor`文件夹下：

![image-20201118180216394](http://cdn.tycoding.cn/20201118180221.png)

启动：

```shell
docker-compose up -d
```

重启：

```shell
docker-compose stop
docker-compose start
```





# 部署项目到私有仓库

上面我们安装配置了Harbor Docker私有仓库，下面将讲解如何将本地的Maven项目打包并自动推送到Harbor私有仓库

## Harbor 创建项目

> 创建项目

![](http://cdn.tycoding.cn/MIK-y4VXeG.png)

如上我创建了一个名为`tumo-boot`的项目

> 创建用户

![](http://cdn.tycoding.cn/MIK-oXH8vk.png)


**注意：** 如上我创建了 用户名`tycoding` 密码 `Tycoding123` 的用户。（记住此用户名和密码，后续将用到）

> 分配项目角色

找到刚才创建的项目，点击`tumo-boot`项目名称进入，点击成员，点击新增用户，输入之前创建的用户名称，并分配`开发者`的角色：

![](http://cdn.tycoding.cn/MIK-OMy1bz.png)

> 查看项目

![](http://cdn.tycoding.cn/MIK-xlQ0jo.png)

如上，点击`镜像仓库`，查看推送命令，可以看到推送命令格式：

```shell script
docker push 172.16.60.134/tumo-boot/REPOSITORY[:TAG]
```

也就是说刚才创建的项目名称其实是作为`Docker` push 的一层`URL`路径，而紧跟其后的`REPOSITORY`才是我们本地项目的名称。`172.16.60.134/tumo-boot/REPOSITORY`共同组成了Docker镜像推送地址。

## 配置本地Docker 私有仓库地址

打开本地的Docker Desktop，找到相关配置项：

![](http://cdn.tycoding.cn/MIK-RnL7L4.png)

添加如上私有仓库的地址：

```json
{
  "insecure-registries": [
    "172.16.60.134"
  ]
}
```

**为什么？**

实际上你会发现，将本地的Maven项目自动推送到远程Docker私有仓库，不仅仅是在`pom.xml`中配置好私有仓库`host`就行了。当执行`push`命令时，会将本地的Maven项目打包制作成Docker镜像，先推送到本地的Docker容器中，然后再由`Docker Desktop`自动推送到远程Docker仓库。

## 配置 Pom

修改本地项目的`pom.xml`：

```xml
<groupId>cn.tycoding.boot</groupId>
<artifactId>tumo-boot-api</artifactId>
<version>1.0.0.RELEASE</version>
<packaging>jar</packaging>
<name>tumo-boot</name>

<properties>
    <!-- Harbor -->
    <docker.registry.url>172.16.60.134</docker.registry.url>
    <docker.registry.host>http://${docker.registry.url}:2375</docker.registry.host>
    <docker.username>tycoding</docker.username>
    <docker.password>Tycoding123</docker.password>
    <docker.namespace>tumo-boot</docker.namespace>
    <docker.plugin.version>1.4.13</docker.plugin.version>
</properties>

<build>
    <finalName>${project.name}</finalName>
    <plugins>
        <!-- Harbor插件 -->
        <plugin>
            <groupId>com.spotify</groupId>
            <artifactId>dockerfile-maven-plugin</artifactId>
            <version>${docker.plugin.version}</version>
            <configuration>
                <username>${docker.username}</username>
                <password>${docker.password}</password>
                <repository>${docker.registry.url}/${docker.namespace}/${project.artifactId}</repository>
                <tag>${project.version}</tag>
                <useMavenSettingsForAuth>true</useMavenSettingsForAuth>
                <buildArgs>
                    <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
                </buildArgs>
            </configuration>
        </plugin>
    </plugins>
</build>
```

如上利用``


## Docker 命令

```shell script
docker ps -aq  #列出所有容器ID
docker stop $(docker ps -aq)  #停止所有容器
docker rm $(docker ps -aq)  #删除所有容器
docker rmi $(docker images -q)  #删除所有镜像
```

