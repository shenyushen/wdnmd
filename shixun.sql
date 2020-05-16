# Host: localhost  (Version 5.5.6-rc)
# Date: 2020-05-16 09:45:52
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "course"
#

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `mv_name` varchar(255) DEFAULT NULL,
  `mv_account` varchar(255) DEFAULT NULL,
  `mv_path` varchar(255) DEFAULT NULL,
  `if_vip` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "course"
#


#
# Structure for table "findlable"
#

DROP TABLE IF EXISTS `findlable`;
CREATE TABLE `findlable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

#
# Data for table "findlable"
#

INSERT INTO `findlable` VALUES (1,'早餐'),(2,'晚餐'),(3,'中餐'),(4,'甜点'),(5,'饮料'),(6,'火锅'),(7,'泡面'),(8,'猜你喜欢');

#
# Structure for table "label"
#

DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `label_id` int(11) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Data for table "label"
#

INSERT INTO `label` VALUES (1,'酸'),(2,'甜'),(3,'苦'),(4,'辣'),(5,'咸');

#
# Structure for table "market_commentshow"
#

DROP TABLE IF EXISTS `market_commentshow`;
CREATE TABLE `market_commentshow` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `comment_show` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "market_commentshow"
#

INSERT INTO `market_commentshow` VALUES (1,'Jane0702','很好切，放点油煎一下，淋上糖浆，就很好吃','1.png'),(2,'静儿家的猫','生吃甜，煮着吃更甜','2.png'),(3,'偶然的旅行','握在手上实诚，切开满满的只是陷儿，而中间是最可爱的草莓','3.png');

#
# Structure for table "market_type"
#

DROP TABLE IF EXISTS `market_type`;
CREATE TABLE `market_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

#
# Data for table "market_type"
#

INSERT INTO `market_type` VALUES (1,'果蔬生鲜'),(2,'器具'),(3,'领券'),(4,'方便食品'),(5,'饮品茶酒'),(6,'零食'),(7,'腌制腊肉'),(8,'南北干货'),(9,'进口食品'),(10,'米面粮油'),(11,'厨房电器'),(12,'礼盒'),(13,'调味品'),(14,'烘培');

#
# Structure for table "goods"
#

DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `little_content` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sale_volume` int(11) DEFAULT NULL,
  `price` double(11,0) DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `market_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

#
# Data for table "goods"
#

INSERT INTO `goods` VALUES (1,'良润面包粉5kg','大包装更实惠',138,50,'mianbao.png',0),(2,'亮润面包','大包装更实惠',138,50,'mianbao.png',0),(3,'亮润面包','大包装更实惠',138,50,'mianbao.png',0),(4,'亮润面包','大包装更实惠',150,50,'mianbao.png',4);

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `vip` int(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `home` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'一只肥罗','123',NULL,'yizhifeiluo.jpg','b',NULL,NULL,NULL,NULL),(11,'3','11',1,'tiny-462-2020-05-15-00-51-30.jpg','男','学生','2020','河北','gag'),(123,'3','123',1,'tiny-684-2020-05-15-01-17-35.jpg','3','3','3','3','3');

#
# Structure for table "mv_comment"
#

DROP TABLE IF EXISTS `mv_comment`;
CREATE TABLE `mv_comment` (
  `mvcomment_id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `mvcomment_time` datetime DEFAULT NULL,
  `mv_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`mvcomment_id`),
  KEY `mv_id` (`mv_id`),
  KEY `author` (`author`),
  CONSTRAINT `mv_comment_ibfk_1` FOREIGN KEY (`mv_id`) REFERENCES `course` (`id`),
  CONSTRAINT `mv_comment_ibfk_2` FOREIGN KEY (`author`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "mv_comment"
#


#
# Structure for table "menu"
#

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(100) NOT NULL AUTO_INCREMENT,
  `author` int(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `menu_photo` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `author` (`author`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "menu"
#

INSERT INTO `menu` VALUES (1,NULL,'2020-04-23','simuxue.jpg','思慕雪','菜'),(2,NULL,'2020-04-21','xiaolongxia.jpg','小龙虾','菜'),(3,NULL,'2020-04-14','xianxiadanchafan.jpg','蛋炒饭','米'),(4,NULL,'2020-04-27','tudousichaorou.jpg','土豆丝炒肉','菜'),(5,NULL,NULL,'labaicaiwuhuarou.jpg','辣白菜五花肉','菜'),(6,NULL,NULL,'kaishuibaicai.jpg','开水白菜','菜'),(7,NULL,NULL,'congyoubanmian.jpg','葱油拌面','面'),(8,NULL,NULL,'suantangfeiniu.jpg','酸汤肥牛','菜');

#
# Structure for table "step"
#

DROP TABLE IF EXISTS `step`;
CREATE TABLE `step` (
  `step_id` int(100) NOT NULL AUTO_INCREMENT,
  `stepnum` int(100) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `step_photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`step_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `step_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

#
# Data for table "step"
#

INSERT INTO `step` VALUES (1,1,1,'备好材料：酸奶150克、香蕉1根、橙子1个、冻蓝莓50克','simuxue_01.jpg'),(2,2,1,'将香蕉、冻蓝莓和酸奶都放在料理机桶中','simuxue_02.jpg'),(3,1,2,'龙虾处理干净','xiaolongxia_01.jpg'),(4,5,2,'放料酒，生抽，五料粉，甜椒油','xiaolongxia_05.jpg'),(5,3,2,'温热煸姜末','xiaolongxia_02.jpg'),(6,2,2,'放入沥干水分的龙虾','xiaolongxia_03.jpg'),(7,3,1,'橙子榨汁，没有新鲜的橙子，用橙汁也是可以的，但不是那种含添加剂的，而是100%的鲜榨果汁。\r\n橙子榨汁，没有新鲜的橙子，用橙汁也是可以的，但不是那种含添加剂的，而是100%的鲜榨果汁。\r\n好像这是废话，你怎么可以保证买的是百分之百的呢，还是自己榨果汁吧。','simuxue_03.jpg'),(8,4,1,'然后将橙汁连同果肉都放到料理机桶中，插入搅拌棒，上下打搅几下，就得到了非常细腻的，半流动的液体。','simuxue_04.jpg'),(9,5,1,'这就是思慕雪。欧美等国家都非常的流行，他们把它叫做液体面包，或者是减肥健身餐。','simuxue_05.jpg'),(11,4,2,'煸炒','xiaolongxia_04.jpg'),(12,6,2,'加水','xiaolongxia_06.jpg'),(13,7,2,'放入冰糖','xiaolongxia_07.jpg'),(14,8,2,'汤沸后小火煮30分钟捞出即可','xiaolongxia_08.jpg'),(16,1,3,'准备好材料（虾要先去头去虾肠）,各种材料切成丁（老人牙口不好，切细一些好嚼）','xianxiadanchaofan_01.jpg'),(17,2,3,'米饭加入鸡蛋酱油少许拌匀（胆固醇太高的老人不宜多吃鸡蛋，放两个的量是因为我也要吃哈哈哈）','xianxiadanchaofan_02.jpg'),(18,3,3,'锅热后倒入油，油热后倒入蔬菜丁，轻轻翻炒','xianxiadanchaofan_03.jpg'),(19,4,3,'因为切成丁，所以菜熟的快，这时就可以放入虾丁翻炒啦！大概翻炒2分钟就可以！','xianxiadanchaofan_04.jpg'),(20,5,3,'加入米饭，翻炒5分钟，就熟啦！然后就是孝顺奶奶的步骤啦！','xianxiadanchaofan_05.jpg'),(21,1,4,'土豆去皮切丝、青椒切丝、青葱切末、红椒切丝、猪肉切丁','tudousichaorou_01.jpg'),(22,2,4,'起锅将清水煮沸，放入土豆丝焯水30秒','tudousichaorou_02.jpg'),(23,3,4,'另起锅烧油，倒入食用油、加葱末、猪肉，炒至变色','tudousichaorou_03.jpg'),(24,4,4,'倒入土豆丝、青椒、红椒、鸡粉、盐、白砂糖、生抽、蚝油，翻炒均匀','tudousichaorou_04.jpg'),(25,5,4,'装盘出锅，即可食用','tudousichaorou_05.jpg'),(26,1,5,'准备好原料，辣白菜切宽6cm的段备用。','labaicaiwuhuarou_01.jpg'),(27,2,5,'将五花肉切成薄片，加入盐和料酒腌10分钟。','labaicaiwuhuarou_02.jpg'),(28,3,5,'锅中放油加热到5成，放入五花肉。慢慢煎出五花肉中的油，直到肉片变卷曲微微焦黄，这样吃起来肥而不腻。然后盛出五花肉，倒出锅中多余的油，剩少许油加到5成热。','labaicaiwuhuarou_03.jpg'),(29,4,5,'放入辣白菜翻炒出香味，放入炒好的五花肉加白糖炒匀后盛出。','labaicaiwuhuarou_04.jpg'),(30,1,6,'碗中放入排骨250g，倒入水500ml浸泡半小时','kaishuibaicai_01.jpg'),(31,2,6,'准备白菜芯1棵切条，大葱10g切段、生姜5g切片','kaishuibaicai_02.jpg'),(32,3,6,'锅中放入水1L倒入排骨葱段、姜片，炖制高汤','kaishuibaicai_03.jpg'),(33,4,6,'高汤中加入白菜，盐2g，搅拌均匀，大火煮沸','kaishuibaicai_04.jpg'),(34,5,6,'出锅，完成','kaishuibaicai_05.jpg'),(35,1,7,'材料准备好，葱白、葱绿分别切碎，小米椒切碎','congyoubanmian_01.jpg'),(36,2,7,'取一个大点的碗，放入1茶匙生抽，1/2茶匙醋','congyoubanmian_02.jpg'),(37,3,7,'放入1/2茶匙白糖，调成酱汁备用，锅里烧开水，放面条煮熟','congyoubanmian_03.jpg'),(38,4,7,'煮好的面条捞出放入碗中拌匀，锅里烧热油，爆香葱白和小米椒','congyoubanmian_04.jpg'),(39,5,7,'爆好的葱油淋在面上，撒上葱花即可','congyoubanmian_05.jpg'),(40,1,8,'金针菇焯水1分钟','sauntangfeiniu_01.jpg'),(41,2,8,'肥牛焯水 变色出锅','sauntangfeiniu_02.jpg'),(42,3,8,'锅底烧油 放入姜片 蒜片 泡椒 黄灯笼辣椒酱','sauntangfeiniu_03.jpg'),(43,4,8,'底料炒香 加水400ml 米醋50ml 白砂糖 盐 煮沸','sauntangfeiniu_04.jpg'),(44,5,8,'淋入肥牛中 完成','sauntangfeiniu_05.jpg');

#
# Structure for table "menu_label"
#

DROP TABLE IF EXISTS `menu_label`;
CREATE TABLE `menu_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `label_id` (`label_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `menu_label_ibfk_1` FOREIGN KEY (`label_id`) REFERENCES `label` (`label_id`),
  CONSTRAINT `menu_label_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

#
# Data for table "menu_label"
#

INSERT INTO `menu_label` VALUES (1,2,3),(2,5,3),(3,4,2),(4,5,2),(5,2,1),(6,4,4),(7,5,4),(8,2,5),(9,5,6),(10,4,7),(11,5,7),(12,1,8),(13,5,8);

#
# Structure for table "findfriend"
#

DROP TABLE IF EXISTS `findfriend`;
CREATE TABLE `findfriend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) DEFAULT NULL,
  `theme` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `data` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `menuid` int(11) DEFAULT NULL,
  `likenum` int(11) DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author` (`author`),
  KEY `menuid` (`menuid`),
  KEY `type` (`type`),
  CONSTRAINT `findfriend_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  CONSTRAINT `findfriend_ibfk_2` FOREIGN KEY (`menuid`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `findfriend_ibfk_3` FOREIGN KEY (`type`) REFERENCES `findlable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for table "findfriend"
#

INSERT INTO `findfriend` VALUES (1,1,'吃蛋糕','蛋糕真好吃','2020-04-29 11:05:47',1,0,'abc.jpeg',3),(2,1,'俺不会','恩么版','2020-04-30 10:05:42',1,5,'timg.jpg',1),(3,1,'饭好吃吗','这里是一堆发表意见JFK但是艰苦奋斗撒了解科室的JFK拉萨JFK但是JFK是分解啊飞机打瞌睡浪费你的时间爱看发动机上课上课了房间看风景开始了啊；附件是肯定拉风就卡死的回复杰克萨利返回空立法会手机卡了风华绝代斯科拉很费解的是克拉夫就是卡拉回复卡角度思考分离技术的看来附近的萨福克是撒娇开始了放假撒开了；上课艰苦奋斗撒了记录死了','2020-05-04 15:18:33',1,10,'add.png',2);

#
# Structure for table "find_photo"
#

DROP TABLE IF EXISTS `find_photo`;
CREATE TABLE `find_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photoid` int(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `photoid` (`photoid`),
  CONSTRAINT `find_photo_ibfk_1` FOREIGN KEY (`photoid`) REFERENCES `findfriend` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for table "find_photo"
#

INSERT INTO `find_photo` VALUES (1,1,'abc.jpeg'),(2,1,'add.png'),(3,1,'timg.jpg');

#
# Structure for table "user_menu"
#

DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "user_menu"
#

