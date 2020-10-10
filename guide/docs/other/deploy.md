<p align="center">
    <img src="http://cdn.tycoding.cn/MIK-WxRzP9.png" />
</p>
<p align="center">
    <a href="https://github.com/Tumo-Team" target="_blank">
        <strong>Tumo Team —— Tumo-Boot</strong>
    </a>
</p>

# 文档部署

## 部署到GitHub Pages

## 部署到阿里云服务器（Nginx）

### 在本目录下创建脚本文件`deploy.sh`

```shell script
#!/usr/bin/env sh

# 确保脚本抛出遇到的错误
set -e

# 先删除.git仓库
rm -rf .git

git init
git add -A
git commit -m 'deploy'

git push -f "root@tycoding.cn:/var/www/tumo-boot-docs.git" master
```

上述脚本其实就是我们平时往GitHub推送代码的脚本。关键点在于`git push`，因为这里我们是将文件推送到阿里云服务器，所以需要遵循以下结构：

```shell script
git push -f "服务器用户名@服务器IP地址:指定目录下的.git文件" 默认分支
```

### 在阿里云服务器指定目录下创建`.git`文件

其实就是在阿里云服务器的某个文件夹目录下`git init`初始化git仓库。

按照上述写法，我们在`/var/www`目录下初始化git仓库，并重命名为`tumo-boot-docs.git`：

![](http://cdn.tycoding.cn/MIK-jyziKg.png)

### 进入`tumo-boot-docs.git/hooks`

![](http://cdn.tycoding.cn/MIK-0KKpud.png)

思考一下，如果我们在本地执行了`deploy.sh`脚本，将文件推送到了阿里云服务器，那么阿里云服务器如何监听文件的提交呢？

这里我们需要了解Git钩子，Git中文网有这样一段解释：[https://git-scm.com/book/zh/v2/%E8%87%AA%E5%AE%9A%E4%B9%89-Git-Git-%E9%92%A9%E5%AD%90](https://git-scm.com/book/zh/v2/%E8%87%AA%E5%AE%9A%E4%B9%89-Git-Git-%E9%92%A9%E5%AD%90)

> post-receive: 挂钩在整个过程完结以后运行，可以用来更新其他系统服务或者通知用户。 它接受与 pre-receive 相同的标准输入数据。 它的用途包括给某个邮件列表发信，通知持续集成（continous integration）的服务器， 或者更新问题追踪系统（ticket-tracking system） —— 甚至可以通过分析提交信息来决定某个问题（ticket）是否应该被开启，修改或者关闭。 该脚本无法终止推送进程，不过客户端在它结束运行之前将保持连接状态， 所以如果你想做其他操作需谨慎使用它，因为它将耗费你很长的一段时间。

于是我们只需要在`hooks`目录下创建`post-receive`文件，Git会自动监听到文件上传并触发此脚本。

### 写入`post-receive`

```shell script
cd /var/www/tumo-boot-docs.git/hooks

vi post-receive
```

进入VIM界面后，先按`i`键进入INSERT编辑模式，输入：

```shell script
git --work-tree=/var/www/tumo-boot-docs --git-dir=/var/www/tumo-boot-docs.git checkout -f
```

**解释：** `--work-tree`指文档所在目录，`--git-dir`指监听本地文件提交到服务器的git目录。此时我们可以简单的把服务器认为是GitHub，此时的操作就是往GitHub是哪个推送代码。

编辑完成后先按`ESC`键进入 read only只读模式，再输入`:`进入命令模式，输入`:wq!`回车（`!`表示强制的意思）

![](http://cdn.tycoding.cn/MIK-AAvWmA.png)

![](http://cdn.tycoding.cn/MIK-QJO4fE.png)

修改文件权限：`chmod +x post-receive`：

![](http://cdn.tycoding.cn/MIK-TLvHBy.png)

### 创建`tumo-boot-docs`文件夹

在`/var/www`目录下创建`tumo-boot-docs`文件夹

![](http://cdn.tycoding.cn/MIK-enTZ6J.png)

### 执行`deploy.sh`文件

```shell script
sh deploy.sh
```

首次提交会出现如下报错：

![](http://cdn.tycoding.cn/MIK-ScF21N.png)

如何解决？

在服务器`/var/www/tumo-boot-docs.git/`目录下执行：

```shell script
git config receive.denyCurrentBranch ignore
```

如此，便可以正常推送文件了：

![](http://cdn.tycoding.cn/MIK-FoNtIG.png)

### 修改Nginx配置

在Nginx配置文件中添加一条路由规则，指向`/var/www/tumo-boot-docs/index.html`：

```
server {
  listen 8082;
  #server_name docs.boot.tycoding.cn;
  location / {
    root /var/www/tumo-boot-docs;
    index index.html;
    try_files $uri $uri/ /index.html;
    add_header Cache-Control "no-cache, no-store";
  }
}
```

之后重启Nginx，访问`ip:8082`即可访问了，你也可以按照`server_name`配置域名地址，通过域名访问：

```
server {
  listen 80;
  server_name docs.boot.tycoding.cn;
  location / {
    root /var/www/tumo-boot-docs;
    index index.html;
    try_files $uri $uri/ /index.html;
    add_header Cache-Control "no-cache, no-store";
  }
}
```

![](http://cdn.tycoding.cn/MIK-L9ZUNG.png)

最后不要忘记在阿里云服务器控制台中防火墙释放`8082`端口，不然可能无法访问：

