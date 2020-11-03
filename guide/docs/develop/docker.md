# Docker 部署

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

