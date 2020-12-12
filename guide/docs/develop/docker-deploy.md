# Docker 项目部署

## 环境准备

### 本地安装Docker

首先我们需要在本地安装Docker客户端，项目镜像构建`docker build`后将存放在本地的Docker镜像仓库中，然后再通过`docker push`命令将本地仓库中的镜像推送到远程仓库。

这和我们将本地项目推送到GitHub思路相同：

```shell script
git add.                #将当前目录下所有文件添加到暂存区
git commit -m 'init'    #将暂存区文件添加到本地仓库
git add remote [url]    #添加远程仓库推送地址
git push                #推送本地Git仓库代码到远程仓库
```

> 下载Docker客户端（作者这里使用的Mac OS Docker客户端）

官方下载地址：[https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)

### 添加阿里云镜像加速器

注册阿里云账户，在 **产品与服务** 中找到 **容器镜像服务**：

![](http://cdn.tycoding.cn/MIK-hKXum9.png)

最下方找到 **镜像加速器**：

![](http://cdn.tycoding.cn/MIK-6SvHp8.png)

按照上述提示，就可以正常配置加速器了。

![](http://cdn.tycoding.cn/MIK-QmvC3I.png)

### 搭建服务器

这里我们用 VMware 搭建Ubuntu18项目。具体的Ubuntu18安装教程不再赘述。这里记录几个常见的问题：

> 允许Ubuntu远程登录`root`账户

```shell script
vi /etc/ssh/sshd_config
```

![](http://cdn.tycoding.cn/MIK-osSQoB.png)

> 重置root账户密码

```shell script
sudo passwd root
```

> 关闭、开启防火墙

```shell script
sudo ufw enable   #开启防火墙
sudo ufw disable  #禁用防火墙
sudo ufw reset    #禁用防火墙并删除所有规则
sudo ufw allow 80 #开放80端口
```

### 服务器安装Docker

1. 更新apt源并添加HTTPS支持

```shell script
sudo apt-get update && sudo apt-get install apt-transport-https ca-certificates curl software-properties-common -y
```

2. 添加utc源和GPG Key

```shell script
curl -fsSL https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/gpg | sudo apt-key add 
```

3. 添加Docker-ce稳定版源地址

```shell script
sudo add-apt-repository "deb [arch=amd64] https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu $(lsb_release -cs) stable"
```

4. 安装Docker-ce

先搜索适合本系统的Docker-ce版本：

```shell script
apt search docker-ce
```

![](http://cdn.tycoding.cn/MIK-b3VEP2.png)

选择最新版安装：

```shell script
sudo apt-get update
sudo apt install -y docker-ce=5:20.10.0~3-0~ubuntu-bionic
```

5. 安装Docker-Compose

直接去官方文档中找到安装步骤：[https://docs.docker.com/compose/install/](https://docs.docker.com/compose/install/)

```shell script
sudo curl -L "https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

sudo chmod +x /usr/local/bin/docker-compose
```

![](http://cdn.tycoding.cn/MIK-6LcYCz.png)

### Docker镜像加速器

同上，在阿里云容器镜像加速 服务中能找到配置教程：

```shell script
vi /etc/docker/daemon.json
```

如果没有就新建`daemon.json`文件，写入

```json
{
  "registry-mirrors": [
      "https://xxxx.mirror.aliyuncs.com"
  ] 
}
```

![](http://cdn.tycoding.cn/MIK-OP7Rct.png)

启用配置并重启docker服务：

```shell script
sudo systemctl daemon-reload
sudo systemctl restart docker
```

### 服务器安装Harbor

因为Harbor开源在GitHub上，所以需要去GitHub下载release版本：[https://github.com/goharbor/harbor/releases/](https://github.com/goharbor/harbor/releases/)

![](http://cdn.tycoding.cn/MIK-TPxlEF.png)

选择`harbor-online-installer`在线安装版下载（不选择离线版，相对于GitHub下载速度，在线安装版要更快）。

然后将下载后的zip解压后借助第三方工具上传到服务器。当然也可以直接在服务器上下载：

```shell script
wget https://github.com/goharbor/harbor/releases/download/v1.10.6/harbor-online-installer-v1.10.6.tgz
```

1. 修改配置

进入`harbor`目录，修改`harbor.yml`配置文件：

- `hostname`：外部访问Harbor控制台UI的地址（例如这里我是用当前服务器的IP）
- `http.port`：HTTP访问端口，如果80端口占用了就改为其他端口，访问Harbor UI使用`hostname:port`的格式
- `https`：因为我们并没有配置HTTPS，而Harbor注册镜像默认也是使用HTTPS，如果不配置请禁用HTTPS配置
- `harbor_admin_password`：`admin`管理员密码，管理员用户名就是`admin`不能修改

![](http://cdn.tycoding.cn/MIK-ZFfhnv.png)

2. 安装

执行`./install.sh`命令，将安装Harbor。

安装成功后访问：`172.16.60.137:80`

![](http://cdn.tycoding.cn/MIK-EK7Zul.png)

输入：`admin Harbor12345`即可登录


## 前端项目部署

### Dockerfile

> Dockerfile 是一个用于构建镜像的文本文件，其中包含了构建镜像所需的指令，每一行指令代表一个步骤。

在项目根目录下创建 `Dockerfile` 文件，那么在当前目录下执行 `docker build` 命令将自动使用此`Dockerfile`文件来构建镜像。

扩展阅读： [https://www.runoob.com/docker/docker-dockerfile.html](https://www.runoob.com/docker/docker-dockerfile.html)

### Build

执行命令：

```shell script
docker build -t tumo-boot-admin:v1 .
```

- `-t`: `-tag`镜像的名称及标签，格式`name:tag`
- `tumo-boot-admin`: Name镜像名称 
- `:v1`: Tag镜像标签

![](http://cdn.tycoding.cn/MIK-tFpwab.png)


### Push

如同Git操作一样，当本地Docker指定了register远程镜像注册地址，本地的Docker镜像将可以使用`docker push`命令推送镜像到远程Docker仓库。

执行命令：

```shell script
docker tag tumo-boot-admin:v1 172.16.60.137:80/tumo-boot/tumo-boot-admin:v1
```

- `tag`: `docker tag`给指定镜像打标签Tag
- `tumo-boot-admin:v1`: 本地Docker镜像名称个Tag
- `172.16.60.137:80/tumo-boot/tumo-boot-admin:v1`: 格式：`ip:port/projects[repository]/name:tag`，其中`ip:port`代表远程私有仓库的注册的地址和端口（这里指定的`80`端口，因为新版本中Harbor默认使用HTTPS协议，如果我们手动配置禁用了HTTPS协议，应该在此手动指定注册地址的端口）


## 打包并推送后端镜像

![](http://cdn.tycoding.cn/MIK-X3nOTv.png)

