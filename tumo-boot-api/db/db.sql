/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : tumo_boot

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 15/11/2020 15:51:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `author` varchar(100) NOT NULL COMMENT '文章作者',
  `title` varchar(100) NOT NULL COMMENT '文章标题',
  `des` varchar(255) NOT NULL COMMENT '文章简介',
  `content` text NOT NULL COMMENT '文章内容',
  `cover` varchar(100) DEFAULT NULL COMMENT '文章封面',
  `eyes` bigint(20) DEFAULT NULL COMMENT '文章阅读量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of blog_article
-- ----------------------------
BEGIN;
INSERT INTO `blog_article` VALUES (1, 'admin', 'WeChat Template01项目介绍', 'WeChat Template01项目介绍', '# WeChat Template\n\n> 一套基于SpringBoot & Shiro 以及 Uni-app构建的微信小程序脚手架\n\n**开源地址：** [https://github.com/TyCoding/wechat-template](https://github.com/TyCoding/wechat-template) 欢迎Star、Fork支持作者。\n\n<!--more-->\n\n## 项目介绍\n\n本 [仓库](https://github.com/TyCoding/wechat-template) 中包含了两个项目：\n\n- `wechat-api`: 基于SpringBoot、Shiro构建的仅提供Restful api的后端项目\n- `wechat-app`: 基于Uni-app构建的微信小程序\n\n**你能学到什么**\n\n1. Uni-app与Java Web项目结合的最佳实践\n2. SpringBoot与Shiro的集成，以及身份校验、Token交互的实现\n3. 如何优雅的封装一套Uni-app(微信小程序)脚手架，包括：request api封装、vuex存储、router页面路由、与Java后端数据交互\n4. 如何优雅的封装一套SpringBoot&Shiro权限项目脚手架，如何处理前后端分离Token交互\n5. 如何处理小程序端Request请求，后续将从Login请求展开逐步分析前后端交互流程\n\n## 技术选型\n\n开发环境：\n\n| Name           | Version |\n| -------------- | ------- |\n| JDK            | 1.8     |\n| MySql          | 5.7     |\n| IDEA           | 2020.1  |\n| HBuilderX      | 2.7.9   |\n| 微信开发者工具 | 1.03    |\n\n`wechat-api`小程序后端：\n\n| Name         | Version       |\n| ------------ | ------------- |\n| SpringBoot   | 2.1.3.RELEASE |\n| Spring-Shiro | 1.3.2         |\n| Mybatis      | 2.1.0         |\n| Mybatis-Plus | 3.2.0         |\n\n`wechat-app`小程序前端：\n\n| Name             | Version |\n| ---------------- | ------- |\n| uni-app          | 2.7.9   |\n| thorui           | 1.5.0   |\n| luch-request     | 3.0.2   |\n| uni-simple-route | 1.8.4   |\n\n## 目录结构\n\n### wechat-api\n\n源码：[https://github.com/TyCoding/wechat-template/tree/master/wechat-api](https://github.com/TyCoding/wechat-template/tree/master/wechat-api)\n\n```\n└── src\n    ├── main\n    │   ├── java\n    │   │   └── cn\n    │   │       └── tycoding\n    │   │           ├── Application.java\n    │   │           ├── biz\n    │   │           │   ├── controller	-- 控制层\n    │   │           │   ├── entity		-- 实体类\n    │   │           │   ├── mapper		-- Mybatis映射接口\n    │   │           │   └── service		-- 业务层\n    │   │           └── common			-- 公共层\n    │   │               ├── auth		-- Shiro鉴权相关\n    │   │               ├── config		-- 项目配置相关\n    │   │               ├── constants	-- 项目公共常量\n    │   │               ├── controller	-- 控制层公共方法提取\n    │   │               ├── exception	-- 异常类\n    │   │               ├── handler		-- 处理器类\n    │   │               ├── properties	-- 框架配置参数\n    │   │               └── utils		-- 工具类\n    │   └── resources\n    │       ├── application-dev.yml		-- 开发环境配置\n    │       ├── application-prod.yml	-- 生产环境配置\n    │       ├── application.yml			-- 项目基础配置\n    │       ├── mapper					-- Mybatis接口映射XML文件\n    │       └── tycoding.properties		-- 项目自定义参数配置\n```\n\n### wechat-app\n\n源码：[https://github.com/TyCoding/wechat-template/tree/master/wechat-app](https://github.com/TyCoding/wechat-template/tree/master/wechat-app)\n\n```\n├── App.vue			-- 小程序入口（Vue）\n├── api				-- 项目接口方法（对URL、Params的统一封装，仅需调用其下的方法便可发送请求）\n│   └── user.js		-- 用户层接口（建议命名和后端Controller对应）\n├── common			-- 通用文件\n├── components		-- 组件，HBuilderX新版支持easycom模式：无需手动引入组件仅需按照目录格式\n├── config.js		-- 项目配置（包含了项目生产环境和开发环境BaseURL配置）\n├── main.js			-- 项目入口\n├── manifest.json	-- 应用配置\n├── pages			-- 页面（vue组件）\n│   ├── common		-- 通用层页面\n│   └── tabbar		-- 小程序底部tarbar对应页面\n├── pages.json		-- 页面属性配置、编译配置\n├── permission.js	-- 路由守卫，页面Router将被路由守卫拦截处理（判断token是否失效等）\n├── router			-- 页面路由相关配置，页面的路由path需要和pages.json相对应\n│   └── index.js\n├── static			-- 静态文件\n├── store			-- vuex相关store缓存配置\n│   ├── getters.js	-- 定义getters，用于获取store中的参数属性\n│   ├── index.js	-- Vuex声明、定义\n│   └── modules		-- 具体需要缓存的数据对象\n└── utils			-- 工具类\n    ├── auth.js		-- 管理Token\n    ├── request.js	-- 全局请求封装\n    └── util.js		-- 一些方法的封装\n```\n\n## 文档\n\n项目文档将在公众号：**程序员涂陌** 中陆续发布。首先作为后端程序员我们要先阅读微信小程序相关文档：\n\n- 微信小程序官方文档：[https://developers.weixin.qq.com/miniprogram/dev/framework/](https://developers.weixin.qq.com/miniprogram/dev/framework/)\n- uni-app官方文档：[https://uniapp.dcloud.io/README](https://uniapp.dcloud.io/README)\n- Vue.js官方文档：[https://cn.vuejs.org/v2/guide/](https://cn.vuejs.org/v2/guide/)\n- ThorUI官方文档：[https://www.thorui.cn/doc/guide.html](https://www.thorui.cn/doc/guide.html)\n- lunc-request官方文档：[https://quanzhan.co/luch-request/guide/3.x/](https://quanzhan.co/luch-request/guide/3.x/)\n- uni-simple-router官方文档：[http://hhyang.cn/src/router/start/quickstart.html](http://hhyang.cn/src/router/start/quickstart.html)\n\n## Tips\n\n由于本人是做后端的，所以本小程序中服务端接口全部由`wechat-api`项目提供，请各位开发者把重点放在学习该项目是如何封装的、以及数据是如何交互的，这是核心。Uni-app最大的优势就是基于Vue实现，熟悉了Vue则对用Uni-app开发小程序也会很快熟悉；需要提各位，Uni-app框架与HBuilder软件是捆绑的，HBuilder的版本号即是Uni-app框架的版本号，而只能通过HBuilder编译uni-app项目，最后微信开发者工具上预览小程序界面。\n\n以上是对  [https://github.com/TyCoding/wechat-template](https://github.com/TyCoding/wechat-template) 项目的介绍，后续公众号会陆续发布该项目各个功能模块的实现逻辑，请大家持续关注~~\n\n<br/>\n\n\n\n\n# 交流\n\nQQGroup：671017003   \n\nWeChatGroup:  关注公众号查看\n\n# 联系\n\n- [http://www.tycoding.cn](http://www.tycoding.cn)\n- [https://github.com/TyCoding](https://github.com/TyCoding)\n\n', 'https://tycoding.cn/images/thumbs/2.jpg', NULL, '2020-10-31 12:22:34');
INSERT INTO `blog_article` VALUES (2, 'admin', 'WeChat-Template02环境搭建', 'WeChat-Template02环境搭建', '# WeChat Template\n\n> 一套基于SpringBoot & Shiro 以及 Uni-app构建的微信小程序脚手架\n\n**开源地址：** [https://github.com/TyCoding/wechat-template](https://github.com/TyCoding/wechat-template) 欢迎Star、Fork支持作者。\n\n<!--more-->\n\n**前言**\n\n第一次接触微信小程序，你可能通过一些教程了解到很多开发微信小程序的第三方框架或者是微信原生API。而从我一个Java后端开发者的角度而言，如果想要快速入门微信小程序开发，去研究微信原生的API文档可能不太合适。\n\n初学Java Web阶段，我也接触到了原生JS开发；后来我又顺着教程学习了JQuery，才发现JQuery比原生JS方便太多了；之后随着Java Web前端项目的逐渐复杂，例如分页、Modal、表格数据渲染等靠JQuery依然相当复杂，即使之后又学到了JSP、Thymeleaf数据渲染依然感觉很不方便。开发前端除了要学习UI框架的使用，还需要学习一套JavaScript框架的使用以便我们能快速开发项目。\n\n于是后来我接触了Vue，了解了双向绑定的概念才知道用Vue渲染前端数据是多么的方便快捷。\n\n那么今天我要介绍的Uni-app正是基于Vue.js开发的前端应用框架。因此，如果你已经熟悉了Vue框架，那么学习使用Uni-app开发微信小程序将非常方便。其中微信小程序的页面全部使用的Vue组件，Vue的语法几乎都适用（当然也有不适用的例如：页面的生命周期钩子函数等）。所以本系列教程前端将全部使用Vue语法分析项目实现逻辑，如果大家有不熟悉的请仔细阅读以下文档：\n\n- [https://uniapp.dcloud.io](https://uniapp.dcloud.io) Uni-app官方文档\n- [https://vuejs.org/](https://vuejs.org/) Vue官方文档\n- [https://developers.weixin.qq.com/miniprogram/dev/framework/](https://developers.weixin.qq.com/miniprogram/dev/framework/) 微信小程序开发文档\n- [https://github.com/TyCoding](https://github.com/TyCoding) 本人GitHub（里面有很多Vue实战开发的项目）\n\n# 环境搭建\n\n作为一个后端程序员，我相信大家已经可以熟练使用IDEA搭建SpringBoot框架开发环境了，这里不再过多赘述。\n\n**请在上期文档 [WeChat Template-01项目介绍](https://tycoding.cn/2020/06/11/project/wechat-template/wechat-template01/) 中仔细阅读我的开发软件版本，避免出现其他问题请尽量保持软件版本和我文档中介绍的相同**。\n\n本文的重点将放在如何搭建Uni-app的开发环境。\n\n---\n\n首先请仔细阅读 **Uni-app官方文档：** [https://uniapp.dcloud.io/quickstart](https://uniapp.dcloud.io/quickstart)。\n\n## 安装HBuilder\n\n也许你会像我一样迷惑，不是开发微信小程序吗？为什么非要再安装一个HBuilder啊？他好用吗？\n\n但你没得选择，**Uni-app和HBuilder是捆绑在一起的**，使用Uni-app写的Vue页面必须使用HBuilder才能编译。选择通过HBuilder将项目编译到微信开发者工具，项目编译后将自动打开微信开发者工具运行项目，由此就不需要用微信开发者工具编辑代码了，仅作为一个手机模拟器、页面调试工具。\n\nHBuilder下载地址：[https://www.dcloud.io/hbuilderx.html](https://www.dcloud.io/hbuilderx.html)\n\n## 微信开发者工具\n\n官方下载地址：[https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html](https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html) \n\n需要注意的是，开发微信小程序首先需要在 **微信公众平台** [https://mp.weixin.qq.com/](https://mp.weixin.qq.com/) 注意一个开发者账户：\n\n[https://mp.weixin.qq.com/cgi-bin/registermidpage?action=index&lang=zh_CN&token=](https://mp.weixin.qq.com/cgi-bin/registermidpage?action=index&lang=zh_CN&token=) \n\n![image-20200611152850139](http://cdn.tycoding.cn/20200611152850.png)\n\n选择 **小程序** 。上面的四种账号类型需要使用不同的邮箱注册。\n\n![image-20200611153021835](http://cdn.tycoding.cn/20200611153021.png)\n\n后续填写信息便可以注册成功了。\n\n注册成功后登录账户在右侧找到 **开发** ，便可以看到自己的**AppID**，这是我们使用微信开发者工具必备的东西：\n\n![image-20200611153557185](http://cdn.tycoding.cn/20200611153557.png)\n\n以上注册流程结束。但需要注意的是，新注册的微信小程序账户需要关联一个主体（管理员）微信账号，可以用自己的微信账号绑定这个小程序，下次登录直接用微信账号扫描登录二维码即可直接登录小程序管理平台。\n\n安装了微信开发者工具后打开，需要扫描二维码登录才能使用（用刚才绑定了小程序账户的微信号）：\n\n![image-20200611154137687](http://cdn.tycoding.cn/20200611154137.png)\n\n![image-20200611154250759](http://cdn.tycoding.cn/20200611154250.png)\n\n到此为止，我们就完成了微信开发者工具的全部安装。下面讲解如何配合HBuilder使用。\n\n# 环境配置\n\n按照uni-app官方文档：[https://uniapp.dcloud.io/quickstart](https://uniapp.dcloud.io/quickstart) 所写，我们先创建一个uni-app项目：\n\n![image-20200611154826437](http://cdn.tycoding.cn/20200611154826.png)\n\n选择创建`uni-app`项目，并选择`登录模板`（这里仅是示例，不同的模板内置了不同功能的小程序源码，其中登录模板应该还算简洁）。注意不要选择`启用uniCloud`，这个和微信开发者工具创建项目时的`云服务器`概念类似，不建议使用，他的目的的简化开发流程，消除前后端、运维的区别。而作为一个后端开发者，我们能独立开发一套后端项目必须再学习 `cloud` 提供的**云函数**。\n\n创建的`uni-app-login`项目有以下目录结构：\n\n![image-20200611155507606](http://cdn.tycoding.cn/20200611155507.png)\n\n在开始运行小程序之前，需要配置 `微信开发者工具 -> 设置 -> 安全设置` ，启用**服务端口**：\n\n![image-20200611155652394](http://cdn.tycoding.cn/20200611155652.png)\n\n上面介绍了开发微信小程序需要使用 App ID，在新创建的项目目录结构中找到 `uni-app-login -> mainfest.json`，配置微信小程序App ID：\n\n![image-20200611160200643](http://cdn.tycoding.cn/20200611160200.png)\n\n接下来打开HBuilder，找到 `HBuilderX -> 运行 -> 运行到小程序模拟器 -> 微信开发者工具`：\n\n![image-20200611155830709](http://cdn.tycoding.cn/20200611155830.png)\n\n然后，HBuilder编译后将自动打开微信开发者工具并运行编译后的微信小程序：\n\n![image-20200611160842063](http://cdn.tycoding.cn/20200611160842.png)\n\n到此为止，环境配置已经结束了。\n\n## 插件安装\n\n`Uni-app`本身提供的UI和API可能并不能满足我们日常开发的需求，`Uni-app`也提供了插件市场：[https://ext.dcloud.net.cn/](https://ext.dcloud.net.cn/) ，在里面可以找到很多第三方插件。那么如何将插件安装到我们的项目中呢？\n\n1. 方式一：特别简单，在插件详情页面右侧点击`使用HBuilderX导入插件`即可将插件自动下载到当前项目中\n\n![image-20200611174123878](http://cdn.tycoding.cn/20200611174123.png)\n\n在`HBuilderX`上将弹出：\n\n![image-20200611174233805](http://cdn.tycoding.cn/20200611174233.png)\n\n点击确定后，插件就被安装在本项目中：\n\n![image-20200611174327251](http://cdn.tycoding.cn/20200611174327.png)\n\n安装其他插件也会如此安装到项目根目录`js_sdk`文件夹中，为了规范起见，我建议大家把第三发插件都移动到`components`文件夹内：\n\n![image-20200611174517593](http://cdn.tycoding.cn/20200611174517.png)\n\n文件夹可以随意命名，因为后续需要单独`import`这个js才能声明对象。注意区别文中提到的`easycom`模式，这是JS插件并不是UI组件，是需要单独`import`的。\n\n2. 方式二：直接在GitHub下载该插件的源码文件，手动将文件夹复制到自己项目的`components`文件夹内，其他的和上面介绍的相同。\n\n# Tips\n\n> 除了上面介绍的基础环境配置，开发一套小程序还得使用一些第三方js库，下面是我个人不断挑选后觉得比较好用的。\n\n## UI\n\n虽然Uni-app内置了很多UI组件了，但是并不丰富，于是我在Github上搜索了uni-app第三方的UI库，选择了 ThorUI 。（实际上第三方的UI库并不多且star数量不太高），所以学习此套教程，你需要先熟悉下 ThorUI 文档：\n\n- 源码：[https://github.com/dingyong0214/ThorUI-uniapp](https://github.com/dingyong0214/ThorUI-uniapp)\n- 文档：[https://thorui.cn/doc/](https://thorui.cn/doc/)\n\n**注意**\n\n如果你仔细查阅了uni-app的文档或ThorUI的文档会发现Uni-app文档中 [https://uniapp.dcloud.io/collocation/pages?id=easycom](https://uniapp.dcloud.io/collocation/pages?id=easycom) 介绍的一个参数配置：\n\n![image-20200611162444379](http://cdn.tycoding.cn/20200611162444.png)\n\n`easycom`模式支持将存放在`components`文件夹下的第三方组件**自动注册到App**内，也就是我们无需再在`App.vue`中`import`注册组件了。\n\n**但是：** 并不是所有的第三方组件都适合 `easycom`模式的，所以你在选择第三方UI库时需要看介绍是否支持`easycom`模式。（显然ThorUI是支持这种模式的）。\n\n---\n\n需要注意的是，ThorUI官方源码仓库：[https://github.com/dingyong0214/ThorUI-uniapp](https://github.com/dingyong0214/ThorUI-uniapp) 其实是一个使用了ThorUI组件的Uni-app项目，ThorUI的源码是在该项目的`components`文件夹下的。那么前面介绍的使用HBuilder创建项目时的模板也可以替换为ThorUI的，好处就是作者已经帮我们配置好了项目，直接使用即可。\n\n**注意** \n\n虽然可以使用ThorUI提供的模板（HBuilder直接打开运行项目），但我并不建议直接使用其中封装的JS方法，因为本项目的目的是要让你学会自己封装一套小程序脚手架，ThorUI仅作为一个前端项目他封装的JS包括Request、Vuex、Login我觉得是不适合与Java后端交互的，请大家在后续文章中仔细阅读我的项目设计再做比较。\n\n## Router\n\n如果你使用过Vue开发Web端单页面项目，你应该会知道页面之间的路由是非常重要的。那么本项目脚手架也将封装一套Router路由，而Uni-app本身提供的页面路由我觉得并不友好（特别是对与我们习惯了Vue开发的Java后端程序员）。\n\n在前后端分离的项目中Router至少需要实现：\n\n1. 全局的路由封装，以及各个页面的路由配置\n2. 路由守卫，要拦截监听页面发生路由时当前账户登录状态\n3. 在路由守卫中要判断当前缓存中的Token是否失效，UserInfo信息是否被缓存（没有就异步获取UserInfo）\n\n> 在Vue项目中，官方推荐的 vue-router\n\n以上分析，我在Uni-app插件市场中找到一个非常形似`vue-router`的插件：`uni-simple-router`，官方文档：[http://hhyang.cn/](http://hhyang.cn/) 。这个插件提供的API几乎和`vue-router`中的API相同，更是减轻了我们的学习成本。\n\n后续我将介绍如何使用这个插件封装页面全局路由。\n\n## Request\n\n除了上面介绍的两个插件，在前后端分离的项目中最重要的是JS的异步请求，那么必不可少需要能够发送异步请求的API，Uni-app和微信官方都提供了相应的Request API，但我作为Java后端还是觉得他们提供的很不适合于Java后端交互，这里我找到了一个认为比较好用的插件：`luch-request` \n\n`luch-request`官方文档：[https://quanzhan.co/luch-request/](https://quanzhan.co/luch-request/)\n\n在前后端分离的项目中Request至少需要实现：\n\n1. 全局Request请求封装，以便能处理请求Token信息\n2. 全局Request应该能够配置`interceptor`，包括`Request interceptor`和`Response interceptor`\n3. 在`interceptor`中能够自定义鉴权、身份认证等配置\n4. 应该按照`Controller`接口命名规则配置与之相对应的`api`接口方法，方法中包含`Request Method`和`Request Params`\n5. 前后端分离项目中`Header`应该包含`Authorization Token`，并且`Header`中`Content-type=\'application/json\'`，前后端传递的都是JSON化的字符串\n\n后续我将讲解如何使用这个插件封装全局Request。\n\n\n# 交流\n\nQQGroup：671017003   \n\nWeChatGroup:  关注公众号查看\n\n# 联系\n\n- [http://www.tycoding.cn](http://www.tycoding.cn)\n- [https://github.com/TyCoding](https://github.com/TyCoding)\n\n', 'https://tycoding.cn/images/thumbs/2.jpg', NULL, '2020-10-31 12:22:34');
COMMIT;

-- ----------------------------
-- Table structure for blog_article_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_category`;
CREATE TABLE `blog_article_category` (
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `category_id` bigint(20) NOT NULL COMMENT '分类ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章分类关联表';

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag` (
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `tag_id` bigint(20) NOT NULL COMMENT '标签ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签关联表';

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '分类名称',
  `des` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Records of blog_category
-- ----------------------------
BEGIN;
INSERT INTO `blog_category` VALUES (1, 'Test2', '描述222', '2020-10-31 09:11:49');
INSERT INTO `blog_category` VALUES (2, 'Test22', '测试描述', '2020-10-31 09:13:47');
COMMIT;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `article_title` bigint(20) NOT NULL COMMENT '文章标题',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(100) NOT NULL COMMENT '评论人名称',
  `email` varchar(100) DEFAULT NULL COMMENT '评论人邮箱',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `name` varchar(100) NOT NULL COMMENT '标签名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
BEGIN;
INSERT INTO `blog_tag` VALUES (2, 'Test', '2020-10-31 09:07:15');
INSERT INTO `blog_tag` VALUES (3, 'Test2', '2020-10-31 13:09:28');
INSERT INTO `blog_tag` VALUES (4, 'Test3', '2020-10-31 16:05:00');
INSERT INTO `blog_tag` VALUES (5, 'Test4', '2020-10-31 16:07:38');
INSERT INTO `blog_tag` VALUES (7, 'Test5', '2020-10-31 16:26:47');
INSERT INTO `blog_tag` VALUES (8, 'Test6', '2020-10-31 16:29:37');
INSERT INTO `blog_tag` VALUES (9, 'Test7', '2020-10-31 16:33:10');
INSERT INTO `blog_tag` VALUES (11, 'Test8', '2020-10-31 16:53:30');
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('client', NULL, '$2a$10$22emI3a6/w3a4ZZIa0.pY.dvLsyx4GH.he37wULtW8nJ.TeiGUpC6', 'app', 'password,refresh_token', 'http://tycoding.cn', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `parent_id` bigint(20) NOT NULL COMMENT '上级部门ID',
  `name` varchar(20) NOT NULL COMMENT '部门名称',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, 0, '开发部', '项目开发部门', '2020-10-29 09:34:32');
INSERT INTO `sys_dept` VALUES (2, 1, '开发一部', '开发部门的下级部门', '2020-10-26 09:34:37');
INSERT INTO `sys_dept` VALUES (3, 1, '开发二部', '开发部门的下级部门', '2020-06-29 09:34:42');
INSERT INTO `sys_dept` VALUES (4, 0, '测试部', '负责测试验收项目', '2020-08-29 09:34:46');
INSERT INTO `sys_dept` VALUES (5, 4, '测试一部', '测试部门的子部门', '2020-11-29 09:34:49');
INSERT INTO `sys_dept` VALUES (6, 0, '人事部', '负责公司人员招聘', '2020-05-29 09:34:52');
INSERT INTO `sys_dept` VALUES (7, 0, '测试部2', '测试', '2020-10-29 13:08:44');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) DEFAULT NULL COMMENT '操作用户',
  `operation` varchar(20) DEFAULT NULL COMMENT '操作描述',
  `url` varchar(255) DEFAULT NULL COMMENT '请求URL',
  `time` bigint(20) DEFAULT NULL COMMENT '耗时(毫秒)',
  `method` varchar(100) DEFAULT NULL COMMENT '操作方法',
  `params` varchar(255) DEFAULT NULL COMMENT '操作参数',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(255) DEFAULT NULL COMMENT '用户代理',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_log` VALUES (1, 'admin', '查看用户列表', NULL, 20, 'cn.tycoding.system.controller.UserController.queryList()', ' queryPage\"QueryPage(pageCode=1, pageSize=6)\" user\"User(id=null, username=null, password=null, salt=null, deptId=null, deptName=null, createTime=null, modifyTime=null, avatar=null, phone=null, sex=null, description=null, status=null)\"', '127.0.0.1', NULL, '2019-03-13 00:42:34');
INSERT INTO `sys_log` VALUES (7, 'admin', '更新用户', NULL, 83, 'cn.tycoding.system.controller.UserController.update()', ' user\"UserWithRole(roleId=1, roleIds=[1])\"', '127.0.0.1', NULL, '2019-03-13 01:21:48');
INSERT INTO `sys_log` VALUES (10, 'admin', '删除用户', NULL, 65, 'cn.tycoding.system.controller.UserController.delete()', ' ids\"[9]\"', '127.0.0.1', NULL, '2019-03-13 05:00:56');
INSERT INTO `sys_log` VALUES (11, 'admin', '删除用户', NULL, 9, 'cn.tycoding.system.controller.UserController.delete()', ' ids\"[9]\"', '127.0.0.1', NULL, '2019-03-13 05:01:18');
INSERT INTO `sys_log` VALUES (12, 'admin', '删除登录日志', NULL, 39, 'cn.tycoding.monitor.controller.LoginLogController.delete()', ' ids\"[3]\"', '127.0.0.1', NULL, '2019-03-13 05:13:03');
INSERT INTO `sys_log` VALUES (13, 'admin', '删除日志', NULL, 44, 'cn.tycoding.monitor.controller.LogController.delete()', ' ids\"[8]\"', '127.0.0.1', NULL, '2019-03-13 05:15:54');
INSERT INTO `sys_log` VALUES (14, 'admin', '删除日志', NULL, 9, 'cn.tycoding.monitor.controller.LogController.delete()', ' ids\"[9]\"', '127.0.0.1', NULL, '2019-03-13 05:15:58');
INSERT INTO `sys_log` VALUES (15, 'admin', '新增文章标签', '/tumo-boot/blog/tag', 39, 'add', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:29:37');
INSERT INTO `sys_log` VALUES (16, 'admin', '新增文章标签', '/tumo-boot/blog/tag', 37, 'add', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:33:10');
INSERT INTO `sys_log` VALUES (17, 'admin', '新增文章标签', '/tumo-boot/blog/tag', 28, 'cn.tycoding.boot.modules.blog.controller.TagControlleradd()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:38:57');
INSERT INTO `sys_log` VALUES (18, 'admin', '删除文章标签', '/tumo-boot/blog/tag/10', 33, 'cn.tycoding.boot.modules.blog.controller.TagControllerdelete()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:46:54');
INSERT INTO `sys_log` VALUES (19, 'admin', '新增文章标签', '/tumo-boot/blog/tag', 27, 'cn.tycoding.boot.modules.blog.controller.TagController.add()', '', '127.0.0.1', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36', '2020-10-31 16:53:30');
COMMIT;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `location` varchar(255) DEFAULT NULL COMMENT '登录地点',
  `create_time` datetime DEFAULT NULL COMMENT '登录时间',
  `device` varchar(255) DEFAULT NULL COMMENT '登录设备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='登录日志表';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_login_log` VALUES (1, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 04:36:13', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (4, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 06:15:56', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (5, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 08:05:34', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (6, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 08:52:32', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (7, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 18:31:09', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (8, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 20:33:47', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (9, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-13 21:32:03', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
INSERT INTO `sys_login_log` VALUES (10, 'admin', '127.0.0.1', '内网IP|0|0|内网IP|内网IP', '2019-03-14 01:03:48', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `path` varchar(100) DEFAULT NULL COMMENT '菜单路径',
  `perms` text COMMENT '权限标识',
  `type` varchar(20) DEFAULT NULL COMMENT '类型：如button按钮 menu菜单',
  `icon` varchar(30) DEFAULT NULL COMMENT '菜单图标',
  `component` varchar(30) DEFAULT NULL COMMENT 'Vue组件',
  `hidden` tinyint(1) DEFAULT NULL COMMENT '是否隐藏',
  `frame` tinyint(1) DEFAULT NULL COMMENT '是否是外链',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '权限模块', 0, '/system', NULL, 'menu', 'safety-certificate', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (2, '用户管理', 1, 'user', 'user:list', 'menu', 'user', '/modules/system/user/index', 0, 0);
INSERT INTO `sys_menu` VALUES (3, '角色管理', 1, 'role', 'role:list', 'menu', 'audit', '/modules/system/role/index', 0, 0);
INSERT INTO `sys_menu` VALUES (4, '部门管理', 1, 'dept', 'dept:list', 'menu', 'apartment', '/modules/system/dept/index', 0, 0);
INSERT INTO `sys_menu` VALUES (5, '菜单管理', 1, 'menu', 'menu:list', 'menu', 'cluster', '/modules/system/menu/index', 0, 0);
INSERT INTO `sys_menu` VALUES (10, '新增用户', 2, NULL, 'user:add', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (11, '修改用户', 2, NULL, 'user:update', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (12, '删除用户', 2, NULL, 'user:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (13, '新增角色', 3, NULL, 'role:add', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (14, '修改角色', 3, NULL, 'role:update', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (15, '删除角色', 3, NULL, 'role:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (16, '新增部门', 5, NULL, 'dept:add', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (17, '修改部门', 5, NULL, 'dept:update', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (18, '删除部门', 5, NULL, 'dept:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (20, '新增菜单', 4, NULL, 'menu:add', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (21, '删除菜单', 4, NULL, 'menu:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (24, '删除用户', 3, NULL, 'role:delete', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (56, '修改菜单', 4, NULL, 'menu:update', 'button', NULL, NULL, 1, 0);
INSERT INTO `sys_menu` VALUES (100, '博客模块', 0, '/blog', NULL, 'menu', 'alert', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (101, '文章管理', 100, 'article', NULL, 'menu', 'read', '/modules/blog/article/index', 0, 0);
INSERT INTO `sys_menu` VALUES (102, '标签管理', 100, 'tag', NULL, 'menu', 'tags', '/modules/blog/tag/index', 0, 0);
INSERT INTO `sys_menu` VALUES (103, '分类管理', 100, 'category', NULL, 'menu', 'switcher', '/modules/blog/category/index', 0, 0);
INSERT INTO `sys_menu` VALUES (104, '评论管理', 100, 'comment', NULL, 'menu', 'message', '/modules/blog/comment/index', 0, 0);
INSERT INTO `sys_menu` VALUES (130, '系统模块', 0, '/setting', NULL, 'menu', 'setting', NULL, 0, 0);
INSERT INTO `sys_menu` VALUES (131, '日志管理', 130, 'log', NULL, 'menu', 'exception', '/modules/setting/log/index', 0, 0);
INSERT INTO `sys_menu` VALUES (132, 'Api文档', 130, 'http://localhost:8080/doc.html', NULL, 'menu', 'file-search', NULL, 0, 1);
INSERT INTO `sys_menu` VALUES (135, 'Test-1', 0, '/test/1', 'TEST-1', 'menu', 'radar-chart', '/modules/test/1', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `alias` varchar(20) DEFAULT NULL COMMENT '角色别名',
  `des` varchar(100) DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '管理员', 'ADMIN', '管理员', '2019-01-01 00:00:00', 0);
INSERT INTO `sys_role` VALUES (2, '测试账号', 'TEST', '测试，可查看所有页面，但无操作权限', '2019-01-01 00:00:00', 1);
INSERT INTO `sys_role` VALUES (3, '用户管理员', 'USER_ADMIN', '负责用户的增删改查操作', '2019-01-01 00:00:00', 1);
INSERT INTO `sys_role` VALUES (4, '系统监控员', 'MONITOR_ADMIN', '可查看系统监控信息', '2019-02-14 08:51:48', 0);
INSERT INTO `sys_role` VALUES (5, '天气预报员', 'TEST', '可查看天气预报信息', '2019-02-14 02:54:56', 4);
INSERT INTO `sys_role` VALUES (6, '用户查看', 'TEST', '查看用户，但无操作权限', '2019-02-14 02:59:17', 4);
INSERT INTO `sys_role` VALUES (8, '111', '1', '1', '2020-10-28 13:26:22', 0);
INSERT INTO `sys_role` VALUES (9, '111.1', '111', '1', '2020-10-28 13:26:37', 8);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单/按钮ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 56);
INSERT INTO `sys_role_menu` VALUES (1, 100);
INSERT INTO `sys_role_menu` VALUES (1, 101);
INSERT INTO `sys_role_menu` VALUES (1, 102);
INSERT INTO `sys_role_menu` VALUES (1, 103);
INSERT INTO `sys_role_menu` VALUES (1, 104);
INSERT INTO `sys_role_menu` VALUES (1, 130);
INSERT INTO `sys_role_menu` VALUES (1, 131);
INSERT INTO `sys_role_menu` VALUES (1, 132);
INSERT INTO `sys_role_menu` VALUES (1, 135);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 18);
INSERT INTO `sys_role_menu` VALUES (2, 19);
INSERT INTO `sys_role_menu` VALUES (2, 20);
INSERT INTO `sys_role_menu` VALUES (2, 21);
INSERT INTO `sys_role_menu` VALUES (2, 22);
INSERT INTO `sys_role_menu` VALUES (2, 23);
INSERT INTO `sys_role_menu` VALUES (2, 27);
INSERT INTO `sys_role_menu` VALUES (2, 28);
INSERT INTO `sys_role_menu` VALUES (2, 29);
INSERT INTO `sys_role_menu` VALUES (2, 30);
INSERT INTO `sys_role_menu` VALUES (2, 34);
INSERT INTO `sys_role_menu` VALUES (2, 35);
INSERT INTO `sys_role_menu` VALUES (2, 36);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (4, 18);
INSERT INTO `sys_role_menu` VALUES (4, 19);
INSERT INTO `sys_role_menu` VALUES (4, 20);
INSERT INTO `sys_role_menu` VALUES (4, 21);
INSERT INTO `sys_role_menu` VALUES (4, 22);
INSERT INTO `sys_role_menu` VALUES (4, 23);
INSERT INTO `sys_role_menu` VALUES (5, 101);
INSERT INTO `sys_role_menu` VALUES (6, 131);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0锁定 1有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', '男', '19809587839', 'tycoding@sina.com', 3, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` VALUES (2, 'tycoding', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', '男', '18798797687', 'tycoding@sina.com', 5, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2019-01-01 00:00:00');
INSERT INTO `sys_user` VALUES (3, 'tumo', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', '男', '781797907', 'tycoding@sina.com', 6, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2019-02-03 03:37:34');
INSERT INTO `sys_user` VALUES (4, 'monitor', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', NULL, '18798797687', 'tycoding@sina.com', 1, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2019-02-03 03:37:34');
INSERT INTO `sys_user` VALUES (5, 'synoptic', '123456', '女', '18798797687', 'tycoding@sina.com', 1, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 0, '2019-02-03 03:37:34');
INSERT INTO `sys_user` VALUES (6, 'user', '$2a$10$.Sb3c/st.CpxxLp5N1X7f.PTfJEUm/yHg3ZH4V5cjDaU1tYbup8Na', '男', '18798797687', 'tycoding@sina.com', 1, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 0, '2019-02-03 03:37:34');
INSERT INTO `sys_user` VALUES (8, 'test2', 'sd', '男', '12', 'tycoding@sina.com', 1, 'http://cdn.tycoding.cn/MIK-WxRzP9.png', 1, '2020-07-18 07:29:27');
INSERT INTO `sys_user` VALUES (9, '111', '1', '男', '17823787849', 'tycoding@sina.com', 5, NULL, 1, '2020-10-28 13:13:18');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (1, 3);
INSERT INTO `sys_user_role` VALUES (3, 3);
INSERT INTO `sys_user_role` VALUES (3, 4);
INSERT INTO `sys_user_role` VALUES (4, 4);
INSERT INTO `sys_user_role` VALUES (4, 5);
INSERT INTO `sys_user_role` VALUES (5, 5);
INSERT INTO `sys_user_role` VALUES (5, 6);
INSERT INTO `sys_user_role` VALUES (5, 9);
INSERT INTO `sys_user_role` VALUES (6, 6);
INSERT INTO `sys_user_role` VALUES (8, 4);
INSERT INTO `sys_user_role` VALUES (8, 6);
INSERT INTO `sys_user_role` VALUES (9, 6);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
