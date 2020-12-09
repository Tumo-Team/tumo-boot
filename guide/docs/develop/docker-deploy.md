# Docker 项目部署

## 环境准备

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


