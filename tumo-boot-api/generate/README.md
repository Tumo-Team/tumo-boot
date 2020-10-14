## 代码生成模板

```
.
├── app                         --前端代码生成模板[ant-design-vue]
│   ├── api.js.vm           前端接口地址[axios]                   
│   ├── form.vue.vm         前端新增/修改model组件框
│   └── index.vue.vm        前端路由首页组件（包含Table模板+CRUD操作）
├── code                        --后端代码生成模板(不含Swagger)[Lombok、Mybatis、Mybatis-Plus、自定义common]
│   ├── controller.java.vm  
│   ├── entity.java.vm
│   ├── mapper.java.vm
│   ├── mapper.xml.vm
│   ├── service.java.vm
│   └── serviceImpl.java.vm
├── config                      --插件全局配置的变量
│   └── tumo-boot-define.vm
└── swagger-code                --后端代码生成模板(含Swagger)[Swagger]
    ├── controller.java.vm
    ├── entity.java.vm
    ├── mapper.java.vm
    ├── mapper.xml.vm
    ├── service.java.vm
    └── serviceImpl.java.vm
```

## 如何使用

### 新增配置

> 对应代码`/generate/config/tumo-boot-define.vm`

![](http://cdn.tycoding.cn/MIK-JuYa6Y.png)

### 新增代码模板分组

![](http://cdn.tycoding.cn/MIK-qAzUKG.png)

### 新增代码模板

> 对应代码`/generate/code/*.vm`（不含Swagger配置）

![](http://cdn.tycoding.cn/MIK-oS3e4G.png)

如上，创建一个模板文件即可，创建模板后可以在右侧进行编辑操作：

![](http://cdn.tycoding.cn/MIK-HVbFX9.png)

然后，只需要按照`/generate/code/*.vm`文件的名称去创建文件并copy代码模板即可。

> 包含Swagger配置：`/generate/swagger-code.vm`，和之前的区别主要在`entity.java.vm`以及`controller.java.vm`注解的不同。

同理，可以创建`tumo-boot-swagger`分组，再导入代码即可：

![](http://cdn.tycoding.cn/MIK-RCq6Ko.png)

## 代码生成

首先需要在IDEA上配置好数据库，然后右键点击想要生成代码的表，选择`EasyCode/Generate Code`选项：

![](http://cdn.tycoding.cn/MIK-ipdW6m.png)

再选择要生成的代码即可：

![](http://cdn.tycoding.cn/MIK-oTs83A.png)

效果：

![](http://cdn.tycoding.cn/MIK-S07AHw.png)
