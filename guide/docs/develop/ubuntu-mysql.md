# Ubuntu安装Mysql

安装

```shell script
sudo apt-get install mysql-server
```

管理员身份进入MYSQL

```shell script
sudo mysql
```

查看版本：

```shell script
mysql -V
```

![](http://cdn.tycoding.cn/MIK-0TNrVW.png)

进入`/etc/mysql`目录，找到`debian.cnf`：

![](http://cdn.tycoding.cn/MIK-HkqubB.png)


可以看到MYSQL默认提供的用户名密码：

```
user     = debian-sys-maint
password = dsXWkA58eEMbz3tQ
```

使用如下命令登录：

```shell script
mysql -u debian-sys-maint -p dsXWkA58eEMbz3tQ
```

登录后，去修改`root`用户的密码

```sql
use mysql;
update user set authentication_string=PASSWORD("root") where user='root';
update user set plugin="mysql_native_password";
flush privileges;
quit;
```

重启mysql

```shell script
/etc/init.d/mysql restart
```

设置允许MYSQL远程登录：

```shell script
vi /etc/mysql/mysql.conf.d/mysqld.cnf 
```

![](http://cdn.tycoding.cn/MIK-oXBYe8.png)

使用`root`账户登录MYSQL，并开启权限：

```shell script
grant all on *.* to root@'%' identified by 'root' with grant option;
flush privileges;
```

重启MYSQL：

```shell script
sudo /etc/init.d/mysql restart
```

![](http://cdn.tycoding.cn/MIK-IYhIaq.png)







