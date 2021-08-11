/*
 Navicat Premium Data Transfer

 Source Server         : food
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : divinechoice

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/08/2021 09:47:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for leader_info
-- ----------------------------
DROP TABLE IF EXISTS `leader_info`;
CREATE TABLE `leader_info`  (
  `leader_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '领导-工号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '居民身份证号',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生年月日',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`leader_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leader_info
-- ----------------------------
INSERT INTO `leader_info` VALUES ('13131313', '123', '主任', ' ', '男', '1999-08-30', ' ', ' ', '我是主任！主任是也');

-- ----------------------------
-- Table structure for project_type
-- ----------------------------
DROP TABLE IF EXISTS `project_type`;
CREATE TABLE `project_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `project_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_type
-- ----------------------------
INSERT INTO `project_type` VALUES (1, '微信小程序');
INSERT INTO `project_type` VALUES (2, 'JAVA');
INSERT INTO `project_type` VALUES (3, 'ANDROID');
INSERT INTO `project_type` VALUES (4, 'ECHARTS');
INSERT INTO `project_type` VALUES (5, 'WEB');
INSERT INTO `project_type` VALUES (6, 'PHP');
INSERT INTO `project_type` VALUES (46, 'JAVASCRIPT');
INSERT INTO `project_type` VALUES (51, 'UI');

-- ----------------------------
-- Table structure for student_birth
-- ----------------------------
DROP TABLE IF EXISTS `student_birth`;
CREATE TABLE `student_birth`  (
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号（作为student表的外键）',
  `department` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学院',
  `professional` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专业',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_birth
-- ----------------------------
INSERT INTO `student_birth` VALUES ('121212121', '电气信息学院', '计科');
INSERT INTO `student_birth` VALUES ('123123123', '电气信息学院', '计科');
INSERT INTO `student_birth` VALUES ('131313131', '电气信息学院', '计科');
INSERT INTO `student_birth` VALUES ('174594105', '电气信息学院', '计科');
INSERT INTO `student_birth` VALUES ('2019454', '电气信息学院', '计科');
INSERT INTO `student_birth` VALUES ('2028166', '电气信息学院', '软工');
INSERT INTO `student_birth` VALUES ('42025912', '电气信息学院', '计科');

-- ----------------------------
-- Table structure for student_choice
-- ----------------------------
DROP TABLE IF EXISTS `student_choice`;
CREATE TABLE `student_choice`  (
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号',
  `num` int(11) NOT NULL COMMENT '次数(最多只能选两个)',
  `project_one_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '第一志愿',
  `one_choice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '待选' COMMENT '第一志愿被选情况',
  `project_two_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '第二志愿',
  `two_choice` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '待选' COMMENT '第二志愿被选情况',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_choice
-- ----------------------------
INSERT INTO `student_choice` VALUES ('121212121', 1, '', '待选', '基于PHP的重大城科健身房管理信息系统设计及实现', '已选择');
INSERT INTO `student_choice` VALUES ('123123123', 1, '', '待选', '基于B/S模式的毕业设计选题系统设计与实现', '已选择');
INSERT INTO `student_choice` VALUES ('131313131', 0, '', '待选', '', '待选');
INSERT INTO `student_choice` VALUES ('174594105', 1, '', '待选', '基于UI', '已选择');
INSERT INTO `student_choice` VALUES ('2019454', 0, '', '待选', '', '待选');
INSERT INTO `student_choice` VALUES ('2028166', 0, '', '待选', '', '待选');
INSERT INTO `student_choice` VALUES ('42025912', 1, '', '待选', '基于Android的大学生入学指南系统设计及实现', '待选');

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '居民身份证号',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生年月日',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注---个人简历',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES ('121212121', '123', '秦2', '12312', '213', NULL, '123', NULL, NULL);
INSERT INTO `student_info` VALUES ('123123123', '123', '秦', ' ', '男', '1999-08-30', ' ', ' ', '12312312123123213');
INSERT INTO `student_info` VALUES ('131313131', '123', '333', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student_info` VALUES ('174594105', '123', '2', ' ', '男', '1999-08-30', ' ', ' ', '我最喜欢UI了，UI是我的一生');
INSERT INTO `student_info` VALUES ('2019454', '123', '小秦3', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student_info` VALUES ('2028166', '123', '小秦', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `student_info` VALUES ('42025912', '123', '小秦4', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for student_it_computer
-- ----------------------------
DROP TABLE IF EXISTS `student_it_computer`;
CREATE TABLE `student_it_computer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题名称',
  `project_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题类型(根据外面另外一个表)',
  `project_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '结合生产' COMMENT '课题来源',
  `project_content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题内容',
  `num` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '被选了几次',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 165 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_it_computer
-- ----------------------------
INSERT INTO `student_it_computer` VALUES (127, '基于Android的大学生入学指南系统设计及实现', 'ANDROID', '结合生产', '课题任务包括：主要功能包含：学院介绍、专业介绍、专业建设、竞赛介绍、实验室建设、学科讲座、精品课程、招办、地图服务等。', 1);
INSERT INTO `student_it_computer` VALUES (128, '基于Android的师生交流平台系统设计及实现', 'ANDROID', '结合生产', '主要功能包括：新用户注册、会员找回密码、会员登录、用户信息修改、管理员删除用户、游客浏览、会员新增留言、管理员删除留言、会员发布帖子、管理员审查、发布、删除帖子等功能。', 0);
INSERT INTO `student_it_computer` VALUES (129, '基于Android的校园社交APP设计及实现', 'ANDROID', '结合生产', '主要功能包含：用户登录、注册、好友聊天、发表说说、点赞、好友添加与删除等基本功能。', 0);
INSERT INTO `student_it_computer` VALUES (130, '基于Android的在线调问卷系统设计及实现', 'ANDROID', '结合生产', '主要功能包含：管理登录、问卷调查题目及内容选项的添加、修改和查询，调查结果统计等。分为管理员用户、普通用户这两种用户。', 0);
INSERT INTO `student_it_computer` VALUES (131, '基于B/S模式的毕业设计选题系统设计与实现', 'JAVA', '结合生产', '1、本系统由3个功能模块组成，分别是学生功能模块、教师功能模块、教研室主任和副主任功能模块。附加一个独立的高级查询模块。\n2、学生功能模块主要实现：学生可以在选题功能模块中浏览到自己心仪的题目并且选择该题目，之后等待出题老师录取。每个学生只能选择一条题目。在老师没有录取之前学生能退掉自己已经选择的题目并且从新选择。学生能修改自己注册时的个人信息，方便信息更新准确。学生能通过高级查询功能根据关键字查询整个毕业设计的进展情况。\n3、教师功能模块主要实现：教师可以查询到已经选择了自己建立的题目的学生，并且有权利根据学生具体情况决定是否录取还是退选。教师可以退选掉已经和学生达成双项选择的题目。教师可以建立新题目供学生选择。教师可以修改自己已经建立的题目信息，确保信息的准确性。教师可以修改自己的注册资料，确保个人信息的准确性。教师可以通过高级查询功能根据关键字查询整个毕业设计的进展情况。\n4、教研室主任和副主任功能模块：教研室领导可以查看、修改和删除系统内的所有学生个人信息，教师个人信息，所有题目的信息。教研室领导可以通过可选题目的查询，待录取题目的查询和已录取题目的查询了解整个毕业设计的总体工作进展情况。教研室领导可以执行打印操作，直接生成表格提交学校而不需要象以往一样需要大量的工作时间来制表。教研室领导拥有备份和恢复整个系统数据库的权利。教研室领导也可以使用高级查询功能精确了解毕业设计的工作进展情况。\n', 2);
INSERT INTO `student_it_computer` VALUES (132, '基于Java的在线答疑系统设计及实现', 'JAVA', '结合生产', '普通用户可以完成用户的注册、登录、退出、发帖、跟帖、修改帖子、删除帖子、修改密码等功能。管理员可以对板块进行维护，对帖子和用户进行管理等。', 0);
INSERT INTO `student_it_computer` VALUES (133, '基于PHP的重大城科健身房管理信息系统设计及实现', 'PHP', '结合生产', '前台和后台，其中后台主要包含系统管理员信息管理、会员信息管理、教练信息管理、会员卡类型信息管理、衣柜信息管理、商城信息管理、财务管理等。', 2);
INSERT INTO `student_it_computer` VALUES (134, '基于Web的多媒体素材管理库系统设计及实现', 'WEB', '结合生产', '前台系统和后台系统的设计。展现出多媒体各种素材的浏览、搜索、分类、预览、下载、上传等基本功能。后台系统包含：用户管理、数据管理、日志管理等。', 0);
INSERT INTO `student_it_computer` VALUES (135, '基于Web的重大城科IT技术论坛设计及实现', 'WEB', '结合生产', '前台功能主要有用户登录、用户注册、搜索帖子、查看帖子内容、发布帖子、回复帖子、模糊查询帖子、删除帖子、统计功能、板块分类等基本功能。后台功能自行设计。', 0);
INSERT INTO `student_it_computer` VALUES (136, '基于微信小程序的房屋租赁小程序设计及实现', '微信小程序', '结合生产', '主要功能包括：前台主要包含：首页、我的。其中首页进行多种排序方式显示出租情况。单击进入某个房屋出租情况后，显示房屋照片、价格、位置、房屋配置、联系方式、房源描述等信息。下方显示关注和电话联系。我的包含：我的发布、我的关注、联系客服等。', 0);
INSERT INTO `student_it_computer` VALUES (137, '基于微信小程序的购物商城系统设计与实现', '微信小程序', '结合生产', '该微信小程序商城功能模块包含：首页、分类、商品详情页面，包含加入购物车、收藏商品、商品评论功能、用户登录、微信登录、订单管理、地址管理；完整的购物流程，商品的加入、编辑、删除、批量选择，收货地址的选择，下单、会员中心（订单、收藏、足迹、收货地址、意见反馈）。', 0);
INSERT INTO `student_it_computer` VALUES (138, '基于微信小程序的外卖订餐小程序设计及实现', '微信小程序', '结合生产', '首页、购物车、我的。首页包含：第一食堂、第二食堂等食堂的选项。单击某个食堂之后，下面罗列出该食堂的所有餐饮信息和所售的餐类、价格等信息，可以在此页面选择所购买的食物。当食物拉进购物车之后，购物车功能包含：结算等信息。我的功能包含：全部订单，待付款、待评价、收货地址、我的留言和点赞。后台程序自行设计。', 0);
INSERT INTO `student_it_computer` VALUES (139, '基于微信小程序的洗衣店管理小程序设计及实现', '微信小程序', '结合生产', '前台主要包含：主页、订单、我的。其中主页包含：洗衣服的类别选择、关于我们、服务范围、服务价格、留言咨询等。订单主要包含：全部订单、已经完成订单。我的主要包含：会员卡、我的订单、我的地址、账单显示等功能。', 0);
INSERT INTO `student_it_computer` VALUES (140, '基于微信小程序的校园广播点歌系统设计及实现', '微信小程序', '结合生产', '前台主要包含：首页、点歌（输入歌名、歌手名、留言等信息）提交、消息（已处理、未处理）、个人中心。后台主要包含：搜索歌曲、点歌消息管理、用户管理、用户评价、发布公告、统计。', 0);
INSERT INTO `student_it_computer` VALUES (141, '基于微信小程序的音乐播放器设计及实现', '微信小程序', '结合生产', '歌曲列表加载、刷新、更多、返回顶部；歌曲播放，下一曲、上一曲、暂停、循环播放、单曲播放、列表播放；查看歌曲封面图片、保存封面图片；歌曲时间进度拖放，记录播放进度；最近播放歌曲记录，删除歌曲；分享歌曲；清空缓存。', 0);
INSERT INTO `student_it_computer` VALUES (164, '基于JAVA', 'JAVA', '结合生产', '这个项目应该注意做', 0);
INSERT INTO `student_it_computer` VALUES (165, '基于UI', 'UI', '结合生产', '我要做成这样一个样子', 2);

-- ----------------------------
-- Table structure for student_it_softwareengineering
-- ----------------------------
DROP TABLE IF EXISTS `student_it_softwareengineering`;
CREATE TABLE `student_it_softwareengineering`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题名称',
  `project_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题类型(根据外面的一个表)',
  `project_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '结合生产' COMMENT '课题来源',
  `project_content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课题内容',
  `num` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '被选了几次',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_it_softwareengineering
-- ----------------------------
INSERT INTO `student_it_softwareengineering` VALUES (219, '基于微信小程序的重大城科桶装水管理系统设计及实现', '微信小程序', '结合生产', '前台主要包含：商城、分类、购物车、我的。商城功能包括：显示所有桶装水类型和价格等信息。分类功能包括：大包装水、小包装水、饮水机套餐。购物车功能包括：结算。我的功能包含：代付款、待发货、待收货、已完成、收货地址、绑定手机号码、我的收藏等信息。后台管理自行设计。', 0);
INSERT INTO `student_it_softwareengineering` VALUES (220, '基于微信小程序的重大城科导览系统设计及实现', '微信小程序', '结合生产', '1、实现地图的接口使用 2、地图上标志出学校的所有建筑、学院、景点、教学楼等信息 3、信息分类 【景点，教学楼，食堂等】4、学校简介页面 5、信息搜索【并对搜索出的信息显示简介】6、游览校园路线', 0);
INSERT INTO `student_it_softwareengineering` VALUES (221, '基于微信小程序的朗萨家私系统设计及实现', '微信小程序', '结合生产', '主要功能：1、全部商品展示 2、新品上市 3、热销商品 4、特价商品 5、关于我们、6、购物车 7、订单生成 8、分类 9、我的', 0);
INSERT INTO `student_it_softwareengineering` VALUES (222, '基于微信小程序的象喜自习室系统设计及实现', '微信小程序', '结合生产', '1、自习室的展示 2、自主约定 3、办理其显卡 4、领劵中心 5、桌型展示 6、地址 6、桌位分布图 6、注意事项 7、入座 8、学习报告 9、我的 10、普通用户和管理员账号的功能区分和页面区分。', 0);
INSERT INTO `student_it_softwareengineering` VALUES (223, '基于微信小程序的美家买菜系统设计及实现', '微信小程序', '结合生产', '1、首页 2、分类 3、购物车 4、我的 5、普通用户和管理员账号的功能区分和页面区分。', 0);
INSERT INTO `student_it_softwareengineering` VALUES (224, '基于微信小程序的永川美食圈系统设计及实现', '微信小程序', '结合生产', '1、首页 2、分类 3、好店 4、购物车 5、我的 6、普通用户和管理员账号的功能区分和页面区分。', 0);
INSERT INTO `student_it_softwareengineering` VALUES (225, '基于微信小程序的急转店系统设计及实现', '微信小程序', '结合生产', '1、首页 2、转让信息 3、 发布 4、生意圈 5、个人中心 6、普通用户和管理员账号的功能区分和页面区分。', 0);
INSERT INTO `student_it_softwareengineering` VALUES (226, '基于微信小程序的穷游行程助手', '微信小程序', '结合生产', '1、首页 2、小编推荐 3、分类 4、我的行程 5、周边 6、个人中心 7、普通用户和管理员账号的功能区分和页面区分。', 0);
INSERT INTO `student_it_softwareengineering` VALUES (227, '基于微信小程序的活动抽签系统设计及实现', '微信小程序', '结合生产', '1、来抓阄 2、去接龙 3、来分组 4、排顺序 5、分任务 6、 去抽奖 7、去投票 8、来抽签 9、做选择 10、我的 11、普通用户和管理员账号的功能区分和页面区分。', 0);
INSERT INTO `student_it_softwareengineering` VALUES (228, '基于Web的计算机专业就业技能、面试技巧、笔试题目系统设计及实现', 'WEB', '结合生产', '1、首页 2、分类 3、就业技能 4、重点推荐 5、面试技巧 6、笔试题目 7、留言板 8、我的 9、分普通用户和管理员用户注意功能和界面的区别。', 0);
INSERT INTO `student_it_softwareengineering` VALUES (229, '基于Echarts的重大城科就业数据可视化系统设计及实现', 'ECHARTS', '结合生产', '1、毕业生信息管理：毕业生的信息维护,上传毕业生信息(Excel)\n2、毕业生档案管理：毕业生的档案维护\n3、毕业就业去向信息管理：就业信息的填写导入修改\n4、就业公告文件服务管理：平台提供的资讯服务，方便毕业生了解就业相关资料\n5、就业招聘信息管理：提供招聘信息整合\n6、学校基础信息维护：学院管理、专业管理、班级管理\n7、用户管理：5个角色（管理员,就业处,辅导员,学院领导,学生）\n8、日志管理：记录关键操作\n9、就业数据分析，分析历年毕业生就业数据分析对比\n10、就业数据可视化展现：采用直观的图表展现就业数据，去向，薪资，单位，专业，行业等数据展示\n11、前端整套界面\n', 0);
INSERT INTO `student_it_softwareengineering` VALUES (230, '基于Echarts的新冠疫情数据显示系统设计及实现', 'ECHARTS', '结合生产', '主题功能是展示国内疫情实时动态。具体实现如下：1、国家卫健委公布实时数据 2、疫情地图 3、疫情发展趋势图 4、新增境外确诊图 5、 新增治愈和死亡人数图 6、国内病例数据表 7、最近进展信息', 0);
INSERT INTO `student_it_softwareengineering` VALUES (231, '基于Echarts的重大城科食堂美食统计、分析和推荐系统设计及实现', 'ECHARTS', '结合生产', '1、实现学校食堂的每个档口的食物的统计功能 2、实现每个档口每种食物的数据分析 3、用Echarts将展示每个档口的每种食物的售卖情况和评价情况 4、用Echarts展示美食推荐top排行榜等', 0);

-- ----------------------------
-- Table structure for student_project
-- ----------------------------
DROP TABLE IF EXISTS `student_project`;
CREATE TABLE `student_project`  (
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '老师姓名',
  `project_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目编号',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_project
-- ----------------------------
INSERT INTO `student_project` VALUES ('121212121', '颜', '133');
INSERT INTO `student_project` VALUES ('123123123', '颜', '131');
INSERT INTO `student_project` VALUES ('131313131', '', '');
INSERT INTO `student_project` VALUES ('174594105', '颜', '165');
INSERT INTO `student_project` VALUES ('2019454', '', '');
INSERT INTO `student_project` VALUES ('2028166', NULL, NULL);
INSERT INTO `student_project` VALUES ('42025912', NULL, NULL);

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info`  (
  `teacher_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '工号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `id_card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '居民身份证号',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生年月日',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_info
-- ----------------------------
INSERT INTO `teacher_info` VALUES ('12121212', '1234', '颜', ' ', '男', '1999-08-30', ' ', ' ', ' ');

-- ----------------------------
-- Table structure for teacher_project
-- ----------------------------
DROP TABLE IF EXISTS `teacher_project`;
CREATE TABLE `teacher_project`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '老师姓名',
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '学生姓名',
  `project_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目编号（作为student_it_xxx表的外键）',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目名',
  `project_content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目内容',
  `professional` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '给哪个专业( 计科 、软工)',
  `num` int(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '选择次数'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher_project
-- ----------------------------
INSERT INTO `teacher_project` VALUES ('颜', '', '128', '基于Android的师生交流平台系统设计及实现', '主要功能包括：新用户注册、会员找回密码、会员登录、用户信息修改、管理员删除用户、游客浏览、会员新增留言、管理员删除留言、会员发布帖子、管理员审查、发布、删除帖子等功能。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', '', '129', '基于Android的校园社交APP设计及实现', '主要功能包含：用户登录、注册、好友聊天、发表说说、点赞、好友添加与删除等基本功能。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '130', '基于Android的在线调问卷系统设计及实现', '主要功能包含：管理登录、问卷调查题目及内容选项的添加、修改和查询，调查结果统计等。分为管理员用户、普通用户这两种用户。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '131', '基于B/S模式的毕业设计选题系统设计与实现', '1、本系统由3个功能模块组成，分别是学生功能模块、教师功能模块、教研室主任和副主任功能模块。附加一个独立的高级查询模块。\n2、学生功能模块主要实现：学生可以在选题功能模块中浏览到自己心仪的题目并且选择该题目，之后等待出题老师录取。每个学生只能选择一条题目。在老师没有录取之前学生能退掉自己已经选择的题目并且从新选择。学生能修改自己注册时的个人信息，方便信息更新准确。学生能通过高级查询功能根据关键字查询整个毕业设计的进展情况。\n3、教师功能模块主要实现：教师可以查询到已经选择了自己建立的题目的学生，并且有权利根据学生具体情况决定是否录取还是退选。教师可以退选掉已经和学生达成双项选择的题目。教师可以建立新题目供学生选择。教师可以修改自己已经建立的题目信息，确保信息的准确性。教师可以修改自己的注册资料，确保个人信息的准确性。教师可以通过高级查询功能根据关键字查询整个毕业设计的进展情况。\n4、教研室主任和副主任功能模块：教研室领导可以查看、修改和删除系统内的所有学生个人信息，教师个人信息，所有题目的信息。教研室领导可以通过可选题目的查询，待录取题目的查询和已录取题目的查询了解整个毕业设计的总体工作进展情况。教研室领导可以执行打印操作，直接生成表格提交学校而不需要象以往一样需要大量的工作时间来制表。教研室领导拥有备份和恢复整个系统数据库的权利。教研室领导也可以使用高级查询功能精确了解毕业设计的工作进展情况。\n', '计科', 2);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '230', '基于Echarts的新冠疫情数据显示系统设计及实现', '主题功能是展示国内疫情实时动态。具体实现如下：1、国家卫健委公布实时数据 2、疫情地图 3、疫情发展趋势图 4、新增境外确诊图 5、 新增治愈和死亡人数图 6、国内病例数据表 7、最近进展信息', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '229', '基于Echarts的重大城科就业数据可视化系统设计及实现', '1、毕业生信息管理：毕业生的信息维护,上传毕业生信息(Excel)\n2、毕业生档案管理：毕业生的档案维护\n3、毕业就业去向信息管理：就业信息的填写导入修改\n4、就业公告文件服务管理：平台提供的资讯服务，方便毕业生了解就业相关资料\n5、就业招聘信息管理：提供招聘信息整合\n6、学校基础信息维护：学院管理、专业管理、班级管理\n7、用户管理：5个角色（管理员,就业处,辅导员,学院领导,学生）\n8、日志管理：记录关键操作\n9、就业数据分析，分析历年毕业生就业数据分析对比\n10、就业数据可视化展现：采用直观的图表展现就业数据，去向，薪资，单位，专业，行业等数据展示\n11、前端整套界面\n', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '231', '基于Echarts的重大城科食堂美食统计、分析和推荐系统设计及实现', '1、实现学校食堂的每个档口的食物的统计功能 2、实现每个档口每种食物的数据分析 3、用Echarts将展示每个档口的每种食物的售卖情况和评价情况 4、用Echarts展示美食推荐top排行榜等', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '132', '基于Java的在线答疑系统设计及实现', '普通用户可以完成用户的注册、登录、退出、发帖、跟帖、修改帖子、删除帖子、修改密码等功能。管理员可以对板块进行维护，对帖子和用户进行管理等。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '133', '基于PHP的重大城科健身房管理信息系统设计及实现', '前台和后台，其中后台主要包含系统管理员信息管理、会员信息管理、教练信息管理、会员卡类型信息管理、衣柜信息管理、商城信息管理、财务管理等。', '计科', 2);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '134', '基于Web的多媒体素材管理库系统设计及实现', '前台系统和后台系统的设计。展现出多媒体各种素材的浏览、搜索、分类、预览、下载、上传等基本功能。后台系统包含：用户管理、数据管理、日志管理等。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '228', '基于Web的计算机专业就业技能、面试技巧、笔试题目系统设计及实现', '1、首页 2、分类 3、就业技能 4、重点推荐 5、面试技巧 6、笔试题目 7、留言板 8、我的 9、分普通用户和管理员用户注意功能和界面的区别。', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '135', '基于Web的重大城科IT技术论坛设计及实现', '前台功能主要有用户登录、用户注册、搜索帖子、查看帖子内容、发布帖子、回复帖子、模糊查询帖子、删除帖子、统计功能、板块分类等基本功能。后台功能自行设计。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '136', '基于微信小程序的房屋租赁小程序设计及实现', '主要功能包括：前台主要包含：首页、我的。其中首页进行多种排序方式显示出租情况。单击进入某个房屋出租情况后，显示房屋照片、价格、位置、房屋配置、联系方式、房源描述等信息。下方显示关注和电话联系。我的包含：我的发布、我的关注、联系客服等。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '137', '基于微信小程序的购物商城系统设计与实现', '该微信小程序商城功能模块包含：首页、分类、商品详情页面，包含加入购物车、收藏商品、商品评论功能、用户登录、微信登录、订单管理、地址管理；完整的购物流程，商品的加入、编辑、删除、批量选择，收货地址的选择，下单、会员中心（订单、收藏、足迹、收货地址、意见反馈）。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '227', '基于微信小程序的活动抽签系统设计及实现', '1、来抓阄 2、去接龙 3、来分组 4、排顺序 5、分任务 6、 去抽奖 7、去投票 8、来抽签 9、做选择 10、我的 11、普通用户和管理员账号的功能区分和页面区分。', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '225', '基于微信小程序的急转店系统设计及实现', '1、首页 2、转让信息 3、 发布 4、生意圈 5、个人中心 6、普通用户和管理员账号的功能区分和页面区分。', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '221', '基于微信小程序的朗萨家私系统设计及实现', '主要功能：1、全部商品展示 2、新品上市 3、热销商品 4、特价商品 5、关于我们、6、购物车 7、订单生成 8、分类 9、我的', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '223', '基于微信小程序的美家买菜系统设计及实现', '1、首页 2、分类 3、购物车 4、我的 5、普通用户和管理员账号的功能区分和页面区分。', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '226', '基于微信小程序的穷游行程助手', '1、首页 2、小编推荐 3、分类 4、我的行程 5、周边 6、个人中心 7、普通用户和管理员账号的功能区分和页面区分。', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '138', '基于微信小程序的外卖订餐小程序设计及实现', '首页、购物车、我的。首页包含：第一食堂、第二食堂等食堂的选项。单击某个食堂之后，下面罗列出该食堂的所有餐饮信息和所售的餐类、价格等信息，可以在此页面选择所购买的食物。当食物拉进购物车之后，购物车功能包含：结算等信息。我的功能包含：全部订单，待付款、待评价、收货地址、我的留言和点赞。后台程序自行设计。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '139', '基于微信小程序的洗衣店管理小程序设计及实现', '前台主要包含：主页、订单、我的。其中主页包含：洗衣服的类别选择、关于我们、服务范围、服务价格、留言咨询等。订单主要包含：全部订单、已经完成订单。我的主要包含：会员卡、我的订单、我的地址、账单显示等功能。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '222', '基于微信小程序的象喜自习室系统设计及实现', '1、自习室的展示 2、自主约定 3、办理其显卡 4、领劵中心 5、桌型展示 6、地址 6、桌位分布图 6、注意事项 7、入座 8、学习报告 9、我的 10、普通用户和管理员账号的功能区分和页面区分。', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '140', '基于微信小程序的校园广播点歌系统设计及实现', '前台主要包含：首页、点歌（输入歌名、歌手名、留言等信息）提交、消息（已处理、未处理）、个人中心。后台主要包含：搜索歌曲、点歌消息管理、用户管理、用户评价、发布公告、统计。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '141', '基于微信小程序的音乐播放器设计及实现', '歌曲列表加载、刷新、更多、返回顶部；歌曲播放，下一曲、上一曲、暂停、循环播放、单曲播放、列表播放；查看歌曲封面图片、保存封面图片；歌曲时间进度拖放，记录播放进度；最近播放歌曲记录，删除歌曲；分享歌曲；清空缓存。', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '224', '基于微信小程序的永川美食圈系统设计及实现', '1、首页 2、分类 3、好店 4、购物车 5、我的 6、普通用户和管理员账号的功能区分和页面区分。', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '220', '基于微信小程序的重大城科导览系统设计及实现', '1、实现地图的接口使用 2、地图上标志出学校的所有建筑、学院、景点、教学楼等信息 3、信息分类 【景点，教学楼，食堂等】4、学校简介页面 5、信息搜索【并对搜索出的信息显示简介】6、游览校园路线', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', NULL, '219', '基于微信小程序的重大城科桶装水管理系统设计及实现', '前台主要包含：商城、分类、购物车、我的。商城功能包括：显示所有桶装水类型和价格等信息。分类功能包括：大包装水、小包装水、饮水机套餐。购物车功能包括：结算。我的功能包含：代付款、待发货、待收货、已完成、收货地址、绑定手机号码、我的收藏等信息。后台管理自行设计。', '软工', 0);
INSERT INTO `teacher_project` VALUES ('颜', '', '127', '基于Android的大学生入学指南系统设计及实现', '课题任务包括：主要功能包含：学院介绍、专业介绍、专业建设、竞赛介绍、实验室建设、学科讲座、精品课程、招办、地图服务等。', '计科', 1);
INSERT INTO `teacher_project` VALUES ('颜', '', '164', '基于JAVA', '这个项目应该注意做', '计科', 0);
INSERT INTO `teacher_project` VALUES ('颜', '', '165', '基于UI', '我要做成这样一个样子', '计科', 2);

SET FOREIGN_KEY_CHECKS = 1;
