# Ubuntu 18.04

## 开启root账户

```shell script
sudo passwd root

sudo vi /etc/ssh/sshd_config
```

![](http://cdn.tycoding.cn/MIK-5txS3q.png)

```shell script
sudo systemctl restart sshd
```
