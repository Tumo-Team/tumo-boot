<p align="center">
    <img src="http://cdn.tycoding.cn/MIK-WxRzP9.png" />
</p>
<p align="center">
    <a href="https://github.com/Tumo-Team" target="_blank">
        <strong>Tumo Team —— Tumo-Boot</strong>
    </a>
</p>

# 代码生成

项目开源地址：[https://github.com/Tumo-Team/Tumo-Boot](https://github.com/Tumo-Team/Tumo-Boot)。

以下示例中的代码生成模板文件地址：[https://github.com/Tumo-Team/Tumo-Boot/tree/master/tumo-boot-api/generate](https://github.com/Tumo-Team/Tumo-Boot/tree/master/tumo-boot-api/generate)



对于**代码生成**功能，想必大家很熟悉了。无论是`Mybatis Generate`还是`Mybatis-Plus Generate`插件，都挺常见的，并且基于以上插件开源的各种项目也非常多。

而在一些热门的开源项目如：Pig、BladeX、等SaaS微服务项目中都集成了代码生成工具，并且提供了Web端可视化编辑功能。（可能一些同学并不太了解提供Web端可视化界面的代码生成功能是如何设计的，这里举例PigX项目集成的代码生成功能：[https://pigx.pig4cloud.com/#/gen/index](https://pigx.pig4cloud.com/#/gen/index)）

如上，随便在一些Java热门开源的后端管理项目中都能看到代码生成功能的身影。但笔者在使用过这些开源项目的代码生成功能后发现分为两类：

> 1.手动引入`mybatis generate`的依赖，手动写相应的配置代码

**缺点：** 1.学习成本高；2.需要添加`generate`依赖；3.所有的配置包括连接数据库的配置都要手写

**优点：** 所有有关代码生成的配置都是自定义的，定制化非常强

> 2.集成一套提供Web端管理的代码生成功能，Web端可视化编辑，自动将代码生成到指定位置

**缺点：** 定制化功能差（当然如果能实现从本地获取代码生成模板文件也可解决这一缺点），且需要依赖前端环境（也就是保证先启动项目）

**缺点：** 提供了Web端的可视化操作界面，学习成本低一些，可实现动态管理数据源、可视化编辑代码生成位置以及包名等配置。

## 为什么？

看完上述内容，相信你应该明白：对于代码生成功能，你需要关注什么？它能帮你解决什么？如何实现？

### 可定制化

1. **可自定义代码生成模板：** 不得不说，每个人的代码风格都不同，亦不能片面的定义一套标准，想要符合大众口味，必须能让开发者自己编辑生成代码的风格。并且，在基于Spring开发的项目一般都遵循MVC设计原则，代码生成模板应该至少包括：`controller`、`service`、`dao`、`entity`。
2. **可自定义数据库字段映射模板：** 什么是数据库字段映射？例如数据库中定义`datetime create_time`字段，那么生成Entity实体类属性该用什么数据类型？`Date/DateTime/LocalDateTime`应该都可以映射，而这显然不能写死，应该根据系统的不同修改为相应的数据类型。

### 可视化

1. **提供代码生成的GUI界面：** 方便修改生成代码的位置、所在package包名、是否全部（`controller`、`service`、`dao`、`entity`）都生成。
2. **提供选择数据源、数据表的可视化界面：** 方便开发者勾选生成哪个数据源下的哪张表。

### 优化

1. **配置模板要实时更新：** 作为一个新人，学习一套代码生成工具的自定义配置，肯定无法做到 一次配置成功，对于代码模板，可能要配置很多次才能达到自己满意的代码风格。不能像Web端界面那样，只有重启了服务器，配置才能生效。
2. **可预览生成代码的风格：** 一般我们会使用模板引擎来自定义代码生成模板，而模板代码无法直观看到效果，最好提供一个工具预览该模板生成的代码效果。

**那么，有没有办法可以同时兼顾上述三点呢？**

## EasyCode

[EasyCode](https://gitee.com/makejava/EasyCode) 是一个基于IDEA的代码生成插件。开源地址：[https://gitee.com/makejava/EasyCode](https://gitee.com/makejava/EasyCode) 。关于此插件的介绍以及如何安装此插件请看他的官方文档，这里不再赘述。

### 它能做什么？

> 自定义数据库字段映射类型

![image-20201010130044713](http://cdn.tycoding.cn/20201010130044.png)

你可以在此界面灵活编辑字段类型映射的Java数据类型，比如之前我们提到的`datetime`类型字段，我们可以手动修改它是映射为`java.util.Date`还是映射为`java.time.LocalDateTime`类型。

> 代码生成模板高度自定义

![image-20201010125826808](http://cdn.tycoding.cn/20201010125832.png)

可以看到，上图中`entity`、`dao`、`service`、`serviceImpl`、`controller`、`mapper.xml`层的代码都是可以自定义修改的，并且提供了详细的文档说明，我们可以根据自己的代码风格定义一套模板，最后copy到这里就好。

> 提供很多获取字段属性的工具类

![image-20201010130857347](http://cdn.tycoding.cn/20201010130857.png)

从上图`Description`中可以看到，此插件封装了很多工具类，比如：获取字段表名、表字段名、表字段属性、主键key等等，根据需要我们可以动态的生成各种代码。

> 可自定义（mybatis）代码生成配置

![image-20201010131032064](http://cdn.tycoding.cn/20201010131032.png)

根据需要可以修改Mybatis生成代码配置，或者定义一些全局变量，在`Templete Setting`代码生成模板中可以引入并调用这些全局变量。

> 提供自定义代码生成位置、包名的GUI界面

首先我们要在IDEA中配置要连接的数据库：

![image-20201010131411337](http://cdn.tycoding.cn/20201010131411.png)

然后输入数据库连接信息：

![image-20201010131440736](http://cdn.tycoding.cn/20201010131440.png)

这样便可以在右侧看到自己的数据库信息了。

选择想要生成的表，右键：

![image-20201010131604940](http://cdn.tycoding.cn/20201010131605.png)

选择`Generate Code`：

![image-20201010131634552](http://cdn.tycoding.cn/20201010131634.png)

你可以根据需要修改：`package`、`path`等配置。

更多的配置项，请看官方的文档。

### 自定义一套模板

**下面的模板示例都是基于本Tumo-Boot项目的，仅供参考，各位根据需求自定义**

> 先定义一些全局变量，方便在`Templete Setting`中快速拿到

![image-20201010132044016](http://cdn.tycoding.cn/20201010132044.png)

新建`tumo-boot-define`，写入：

```velocity
##自定义一些全局变量。引用方式，直接在code中用`$!define`方式引入即可

##定义Entity名称
#set($TumoBootEntity = $tableInfo.name)
##定义首字母小写Entity名称
#set($TumoBootLowerCaseEntity = $!tool.firstLowerCase($tableInfo.name))

##定义Service名称
#set($TumoBootService = $!{tableInfo.name} + "Service")
##定义首字母小写Service名称
#set($TumoBootLowerCaseService = $!tool.firstLowerCase($tableInfo.name) + "Service")

##定义Dao名称
#set($TumoBootDao = $!{tableInfo.name} + "Dao")
##定义首字母小写Dao名称
#set($TumoBootLowerCaseDao = $!tool.firstLowerCase($tableInfo.name) + "Dao")
```

> 定义各层代码模板

1. `entity.java`

```velocity
##引入宏定义
$!define

##使用宏定义设置回调（保存位置与文件后缀）
#save("/entity", ".java")

##使用宏定义设置包后缀
#setPackageSuffix("entity")

##使用全局变量实现默认包导入
$!autoImport
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
##使用宏定义实现类注释信息
#tableComment("实体类")
@Data
@TableName("$tool.hump2Underline($!{tableInfo.name})")
public class $!{tableInfo.name} implements Serializable{
private static final long serialVersionUID= $!tool.serial();

#foreach($column in $tableInfo.pkColumn)
/**
 * 主键
 */
@TableId(value = "$tool.hump2Underline($!{column.name})", type = IdType.AUTO)
private $!{tool.getClsNameByFullName($column.type)} $!{column.name};
    #break
#end
#foreach($column in $tableInfo.otherColumn)

    #if(${column.comment})/**
     * ${column.comment}
     */#end

private $!{tool.getClsNameByFullName($column.type)} $!{column.name};
#end

}
```

2. `controller.java`

```velocity
##引入自定义全局变量
$!tumo-boot-define

##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Controller"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/controller"))
##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}controller;

import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
##以下`cn.tycoding`开头的都是我自定义的类，请根据情况做相应修改
import cn.tycoding.boot.common.core.controller.BaseController;
import cn.tycoding.boot.common.core.api.QueryPage;
import cn.tycoding.boot.common.core.api.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表控制层
 *
 * @author $!author
 * @since $!time.currTime()
 */
@RestController
@AllArgsConstructor
@RequestMapping("/${TumoBootLowerCaseEntity}")
public class $!{tableName} extends BaseController {

    private final $!{TumoBootService} $!{TumoBootLowerCaseService};

    /**
    * 条件查询
    */
    @PostMapping("/filter/list")
    public R<List<$!{TumoBootEntity}>> list(@RequestBody $!{TumoBootEntity} $!{TumoBootLowerCaseEntity}) {
        return new R<>($!{TumoBootLowerCaseService}.list($!{TumoBootLowerCaseEntity}));
    }

    /**
    * 分页、条件查询
    */
    @PostMapping("/list")
    public R<Map<String, Object>> list(@RequestBody $!{TumoBootEntity} $!{TumoBootLowerCaseEntity}, QueryPage queryPage) {
        return new R<>(super.getData($!{TumoBootLowerCaseService}.list($!{TumoBootLowerCaseEntity}, queryPage)));
    }

    /**
    * 根据ID查询
    */
    @GetMapping("/{id}")
    public R<$!{TumoBootEntity}> findById(@PathVariable $!pk.shortType id) {
        return new R<>($!{TumoBootLowerCaseService}.getById(id));
    }

    /**
    * 新增
    */
    @PostMapping
    public R add(@RequestBody $!{TumoBootEntity} $!{TumoBootLowerCaseEntity}) {
        $!{TumoBootLowerCaseService}.add($!{TumoBootLowerCaseEntity});
        return new R();
    }

    /**
    * 修改
    */
    @PutMapping
    public R update(@RequestBody $!{TumoBootEntity} $!{TumoBootLowerCaseEntity}) {
        $!{TumoBootLowerCaseService}.update($!{TumoBootLowerCaseEntity});
        return new R();
    }

    /**
    * 根据ID删除
    */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable $!pk.shortType id) {
        $!{TumoBootLowerCaseService}.delete(id);
        return new R();
    }
}
```

3. `service.java`

```velocity
##引入自定义全局变量
$!tumo-boot-define

##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Service"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/service"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}service;

import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
##以下`cn.tycoding`开头的都是我自定义的类，请根据情况做相应修改
import cn.tycoding.boot.common.core.api.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表服务接口
 *
 * @author $!author
 * @since $!time.currTime()
 */
public interface $!{tableName} extends IService<$!{TumoBootEntity}> {

    /**
    * 条件查询
    */
    List<$!{TumoBootEntity}> list($!{TumoBootEntity} $!{TumoBootLowerCaseEntity});

    /**
    * 分页、条件查询
    */
    IPage<$!{TumoBootEntity}> list($!{TumoBootEntity} $!{TumoBootLowerCaseEntity}, QueryPage queryPage);

    /**
    * 新增
    */
    void add($!{TumoBootEntity} $!{TumoBootLowerCaseEntity});

    /**
    * 修改
    */
    void update($!{TumoBootEntity} $!{TumoBootLowerCaseEntity});

    /**
    * 删除
    */
    void delete($!pk.shortType id);
}
```

4. `serviceImpl.java`

```velocity
##引入自定义全局变量
$!tumo-boot-define

##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "ServiceImpl"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/service/impl"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}service.impl;

import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import $!{tableInfo.savePackageName}.dao.$!{tableInfo.name}Dao;
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
##以下`cn.tycoding`开头的都是我自定义的类，请根据情况做相应修改
import cn.tycoding.boot.common.core.api.QueryPage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表服务实现类
 *
 * @author $!author
 * @since $!time.currTime()
 */
@Service
@AllArgsConstructor
public class $!{tableName} extends ServiceImpl<$!{TumoBootDao}, $!{TumoBootEntity}> implements $!{TumoBootService} {

    private final $!{TumoBootDao} $!{TumoBootLowerCaseDao};

    @Override
    public List<$!{TumoBootEntity}> list($!{TumoBootEntity} $!{TumoBootLowerCaseEntity}) {
        LambdaQueryWrapper<$!{TumoBootEntity}> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<$!{TumoBootEntity}> list($!{TumoBootEntity} $!{TumoBootLowerCaseEntity}, QueryPage queryPage) {
        IPage<$!{TumoBootEntity}> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<$!{TumoBootEntity}> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.like(StringUtils.isNotBlank($!{TumoBootLowerCaseEntity}.getName()), $!{TumoBootEntity}::getName, $!{TumoBootLowerCaseEntity}.getName());
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add($!{TumoBootEntity} $!{TumoBootLowerCaseEntity}) {
        baseMapper.insert($!{TumoBootLowerCaseEntity});
    }

    @Override
    @Transactional
    public void update($!{TumoBootEntity} $!{TumoBootLowerCaseEntity}) {
        baseMapper.updateById($!{TumoBootLowerCaseEntity});
    }

    @Override
    @Transactional
    public void delete($!pk.shortType id) {
        baseMapper.deleteById(id);
    }
}
```

5. `dao.java`

```velocity
##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Dao"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/dao"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}dao;

import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表数据库访问层
 *
 * @author $!author
 * @since $!time.currTime()
 */
@Mapper
public interface $!{tableName} extends BaseMapper<$!{tableInfo.name}> {

}
```

6. `mapper.xml`

```velocity
##引入mybatis支持
$!mybatisSupport

##设置保存名称与保存位置
$!callback.setFileName($tool.append($!{tableInfo.name}, "Dao.xml"))
$!callback.setSavePath($tool.append($modulePath, "/src/main/resources/mapper"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="$!{tableInfo.savePackageName}.dao.$!{tableInfo.name}Dao">

</mapper>
```



上面代码示例中我引入了一些自己定义的类，比如：`R`、`QueryPage`对象，我在模板文件的注释中都写明了，请大家根据需要做相应修改。

以上所有的模板文件都在GitHub上保存：[https://github.com/Tumo-Team/Tumo-Boot/tree/master/tumo-boot-api/generate](https://github.com/Tumo-Team/Tumo-Boot/tree/master/tumo-boot-api/generate) 

### 安装Velocity插件

因为EasyCode插件的代码生成模板使用的是Velocity模板引擎（和Thymeleaf一个道理），并且GitHub存储的是`.vm`文件，IDEA是能够识别Velocity语法的，在写代码生成模板时也方便一些：

![image-20201010141121491](http://cdn.tycoding.cn/20201010141121.png)

