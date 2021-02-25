<p align="center">
    <img src="http://cdn.tycoding.cn/MIK-WxRzP9.png" />
</p>
<p align="center">
    <a href="https://github.com/Tumo-Team" target="_blank">
        <strong>Tumo Team —— Tumo-Boot</strong>
    </a>
</p>


- Tumo-Boot演示地址：[http://boot.tycoding.cn](http://boot.tycoding.cn)
- Tumo-Boot在线文档：[http://docs.boot.tycoding.cn](http://docs.boot.tycoding.cn)
- Tumo-Boot后端地址：[https://github.com/Tumo-Team/Tumo-Boot](https://github.com/Tumo-Team/Tumo-Boot)
- Tumo-Boot前端地址：[https://github.com/Tumo-Team/Tumo-Boot-UI](https://github.com/Tumo-Team/Tumo-Boot-UI)
- Tumo-Boot文档地址：[https://github.com/Tumo-Team/Tumo-Boot-Docs](https://github.com/Tumo-Team/Tumo-Boot-Docs)



# Tumo Boot

```java
public static void main(String[] args){
    System.out.println("Hello Tumo Team!");
}
```

**特点：**

1. 代码简洁、规范。
2. 提供最后端系统最基础的权限体系模块。
3. 项目文档将涉及前后端、运维，带你一步步学习怎么优雅的开发。
4. 基于SpringBoot最新版，单体架构，前后端分离。前端采用Ant-Design-Vue。
5. 提供最最最基础、完善的项目开发文档，所有源码及文档都开源在GitHub上。

## Docs

- **Intro**
    - **[1.项目介绍](http://docs.boot.tycoding.cn/#/docs/intro/1.intro.md)**
    - **[2.文档介绍](http://docs.boot.tycoding.cn/#/docs/intro/2.docs-introduce.md)**
    - **[3.代码生成](http://docs.boot.tycoding.cn/#/docs/intro/3.generate.md)**
    - **[4.运行项目](http://docs.boot.tycoding.cn/#/docs/intro/4.run.md)**

* **Docker**

    * [1.Docker安装](http://docs.boot.tycoding.cn/#/docs/docker/1.docker-install.md)
    * [2.Docker-Build](http://docs.boot.tycoding.cn/#/docs/docker/2.docker-build.md)
    * [3.Docker-Registry](http://docs.boot.tycoding.cn/#/docs/docker/3.docker-registry.md)
    * [4.构建TumoBoot项目镜像](http://docs.boot.tycoding.cn/#/docs/docker/4.build-tumo-boot.md)
    * [5.Docker容器网络通信](http://docs.boot.tycoding.cn/#/docs/docker/5.docker-container-net.md)

* **后端**

    * 1.项目设计
        * [x] [1.1 环境准备](http://docs.boot.tycoding.cn/#/docs/api/1.design/1.1environment.md)
        * [x] [1.2 SpringBoot项目搭建](http://docs.boot.tycoding.cn/#/docs/api/1.design/1.2create-springboot.md)
        * [x] [1.3 Tumo-Boot项目搭建](http://docs.boot.tycoding.cn/#/docs/api/1.design/1.3init-tumo-boot.md)

    * 2.数据库设计
        * [ ] [2.1 RBAC权限表设计](docs/api/2.db/2.1rbac-design.md)
        * [ ] [2.2 RBAC权限表操作](docs/api/2.db/2.2rbac-write.md)
        * [ ] [2.3 日志表设计](docs/api/2.db/2.3log-design.md)

    * 3.Common模块封装
        * [ ] [3.1 YML自定义配置](docs/api/3.module-common/3.1yml.md)
        * [ ] [3.2 Common-Core 模块封装](docs/api/3.module-common/3.2commmon-core.md)
        * [ ] [3.3 Common-Auth模块封装](docs/api/3.module-common/3.3common-auth.md)
        * [ ] [3.4 Common-Mybatis模块封装](docs/api/3.module-common/3.4common-mybatis.md)
        * [ ] [3.5 Common-Swagger模块封装](docs/api/3.module-common/3.5common-swagger.md)
        * [ ] [3.6 Common-Log模块封装](docs/api/3.module-common/3.6common-log.md)

    * 4.日志模块
        * [ ] [4.1 全局异常处理](docs/api/4.module-log/4.1global-exception.md)
        * [ ] [4.2 请求日志和接口日志打印](docs/api/4.module-log/4.2print-log.md)
        * [ ] [4.3 日志数据持久化](docs/api/4.module-log/4.3log-db.md)

    * 5.引入SpringSecurity
        * [ ] [5.1 Security基础配置](docs/api/5.module-security/5.1security-base.md)
        * [ ] [5.2 自定义OAuth2响应结构](docs/api/5.module-security/5.2rewrite-oauth-res.md)
        * [ ] [5.3 重写OAuth2异常](docs/api/5.module-security/5.3rewrite-oauth-error.md)
        * [ ] [5.4 权限功能设计](docs/api/5.module-security/5.4security-design.md)

    * 6.Auth模块开发
        * [ ] [6.1 登录、注销接口](docs/api/6.module-auth/6.1api-login.md)
        * [ ] [6.2 Vue前端对接登录接口](docs/api/6.module-auth/6.2api-login-res.md)
        * [x] [6.3 验证码登录](docs/api/6.module-auth/6.3auth-captcha.md)

    * 7.Upms模块开发
        * [ ] [7.1 用户模块开发](docs/api/7.module-upms/7.1user-dev.md)
        * [ ] [7.2 角色、部门模块开发](docs/api/7.module-upms/7.2role-dev.md)
        * [ ] [7.3 菜单模块开发](docs/api/7.module-upms/7.3menu-dev.md)

    * 8.代码生成



* **前端**
    * 1.项目设计
        * [x] [1.1 环境准备](docs/app/1.design/1.1environment.md)
        * [x] [1.2 如何使用Tumo-AntV项目](docs/app/1.design/1.2use-tumo-antv.md)
        * [x] [1.3Tumo-Boot-UI项目搭建](docs/app/1.design/1.3init-tumo-boot-ui.md)

    * 2.基础模块封装
        * [x] [2.1 项目Layout布局](docs/app/2.base/2.1layout.md)
        * [ ] [2.2 Axios、Router封装](docs/app/2.base/2.2axios-router-package.md)
        * [ ] [2.3 VueX封装](docs/app/2.base/2.3vuex-package.md)
        * [ ] [2.4 前端交互流程](docs/app/2.base/2.4request-res.md)

    * 3.数据交互
        * [ ] 3.1 数据交互
        * [ ] 3.2 登录流程
        * [ ] 3.3 动态菜单

    * 4.页面CRUD
        * [ ] 4.1 页面开发
        * [ ] 4.2 Vue组件交互
        * [ ] 4.3 和后端数据交互

    * 5.实践
        * [ ] 5.1 新增页面
        * [ ] 5.2 CRUD

    * 6.代码生成

## License

[MIT](https://github.com/Tumo-Team/Tumo-Boot/blob/master/LICENSE)

Copyright (c) 2021-present TyCoding

