/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : shixun

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

<<<<<<< HEAD
Date: 2020-06-03 15:22:28
=======
Date: 2020-06-03 09:59:42
Date: 2020-06-03 10:32:00
>>>>>>> db9c99bacba2194f92478b851f2ec59134604747
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '123', '123');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `mv_name` varchar(255) DEFAULT NULL,
  `mv_account` varchar(255) DEFAULT NULL,
  `mv_path` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `mv_pic` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author` (`author`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '1', '10:20', '水煮肉片', '比饭店的更美味，经典菜品', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode-cae/25849441_21deada5607b96572a672a7dc11066bd_0_cae.mp4', '蔬菜，咸', '9');
INSERT INTO `course` VALUES ('2', '1', '10:11', '肉夹馍', '微观世界，迷你小厨房今日份肉夹馍', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode-cae/4697084_5029c50f221d0a70bc7a7af1af1a116d_0_cae.mp4', '中式料理，辣', '5');
INSERT INTO `course` VALUES ('3', '1', '前天', '花蛤', '辣炒花蛤要好吃有秘诀，新东方大厨教你一招', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode-crf/16656688_c807358cad7b4aa64b745d27fdb53840_0.mp4', '中式料理，辣', '4');
INSERT INTO `course` VALUES ('4', '1', '昨天', '香茅姜茶', '比男友可靠的治愈饮品，好喝简单香茅姜茶', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/10293825_8dff862ea4d788b4d57ccb95a58fdc64_1.mp4', '饮品，甜', '0');
INSERT INTO `course` VALUES ('5', '1', '昨天', '甜点', '胡萝卜蛋糕，一个电饭煲就可以搞定的寒假甜点', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/24384143_f67f125c3cadd4923a2253869849cabd_1.mp4', '甜点，甜，蛋糕', '2');
INSERT INTO `course` VALUES ('6', '1', '2020/2/22', '心形蛋糕', '收到这样的蛋糕，你会开心吗？', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/4296225_5dae61c0cab53345004bdbae861274be_2.mp4', '蛋糕，甜', '3');
INSERT INTO `course` VALUES ('7', '1', '前天', '西红柿米饭', '超级无敌美味炒米饭', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/3809301_861212ffa4a4a492987d8d98d4cc0133_6695d854f22c_3.mp4', '米饭，酸，甜', '7');
INSERT INTO `course` VALUES ('8', '1', '昨天', '青辣椒', '贵州女孩半斤青辣椒加上一点盐，三碗米饭不够吃', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/9801893_1234f55e50d52656080587c0cc33314b_3.mp4', '中式料理，辣，蔬菜', '6');
INSERT INTO `course` VALUES ('9', '1', '前天', '米饭披萨料理', '少女也爱的米饭披萨料理', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/16373534_3fc80e18e5c7145523185b2ee2867848_1.mp4', '米饭', '8');
INSERT INTO `course` VALUES ('10', '1', '2020/2/15', '美味炒面', '这一锅面条，在校门口10分钟就能卖完.', 'https://gss3.baidu.com/6LZ0ej3k1Qd3ote6lo7D0j9wehsv/tieba-smallvideo-transcode/1715317_5edf14aad856582d15e44d048eb2445d_2.mp4', '面条', '1');

-- ----------------------------
-- Table structure for `findcomment`
-- ----------------------------
DROP TABLE IF EXISTS `findcomment`;
CREATE TABLE `findcomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authorid` int(11) DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `findfriendid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `authorid` (`authorid`),
  KEY `findfriendid` (`findfriendid`),
  CONSTRAINT `findcomment_ibfk_1` FOREIGN KEY (`authorid`) REFERENCES `user` (`id`),
  CONSTRAINT `findcomment_ibfk_2` FOREIGN KEY (`findfriendid`) REFERENCES `findfriend` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of findcomment
-- ----------------------------
INSERT INTO `findcomment` VALUES ('1', '2', '真的好吃', '1');
INSERT INTO `findcomment` VALUES ('2', '11', '感谢感谢', '1');
INSERT INTO `findcomment` VALUES ('3', '1', '你们做了嘛，是不是很好吃', '1');
INSERT INTO `findcomment` VALUES ('4', '11', '很好吃', '3');
INSERT INTO `findcomment` VALUES ('5', '11', '真的香', '2');
INSERT INTO `findcomment` VALUES ('6', '11', '这种做法也太轻松了', '2');
INSERT INTO `findcomment` VALUES ('7', '11', '我也觉得这个很不错', '2');
INSERT INTO `findcomment` VALUES ('8', '123', '今天天气真好', '3');
INSERT INTO `findcomment` VALUES ('9', '11', '太好吃了8', '1');
INSERT INTO `findcomment` VALUES ('10', '11', '不会吧不会吧', '28');
INSERT INTO `findcomment` VALUES ('11', '123', '真的太好吃了，感谢分享', '29');
INSERT INTO `findcomment` VALUES ('12', '123', '图片与实物不符', '30');
INSERT INTO `findcomment` VALUES ('13', '2', '差评', '10');
INSERT INTO `findcomment` VALUES ('14', '123', '嗯好', '14');
INSERT INTO `findcomment` VALUES ('15', '1', '很好', '29');
INSERT INTO `findcomment` VALUES ('16', '123', '不错', '28');
INSERT INTO `findcomment` VALUES ('17', '123', '还行吧也就那样', '1');

-- ----------------------------
-- Table structure for `findfriend`
-- ----------------------------
DROP TABLE IF EXISTS `findfriend`;
CREATE TABLE `findfriend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) DEFAULT NULL,
  `theme` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `data` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `date` varchar(255) DEFAULT NULL,
  `menuid` int(11) DEFAULT NULL,
  `likenum` int(11) DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `author` (`author`),
  KEY `menuid` (`menuid`),
  KEY `type` (`type`),
  CONSTRAINT `findfriend_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`id`),
  CONSTRAINT `findfriend_ibfk_3` FOREIGN KEY (`type`) REFERENCES `findlable` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of findfriend
-- ----------------------------
INSERT INTO `findfriend` VALUES ('1', '1', '不揉面，不发面，皮薄馅多三鲜馅饼', '今天给大家分享一款懒人版三鲜馅饼，不需要揉面发面，用饺子皮就可以做，咬上一口满满都是馅，太满足了', '2020-05-15', '1', '25', 'u=17521029,2307926959&fm=26&gp=0.jpg', '3');
INSERT INTO `findfriend` VALUES ('2', '2', '正宗麦当劳双吉牛肉汉堡', '看了麦当劳后厨制作食谱，看了麦当劳员工的食谱，才有的这么正宗的食谱，一次只做一个比较麻烦，我是提前做好了，拌好肉馅，弄成饼形状，冻起来每天早上拿出来煎一下就好了', '2020-05-18', '1', '7', 'u=3466873163,659824253&fm=26&gp=0.jpg', '1');
INSERT INTO `findfriend` VALUES ('3', '123', '珍珠奶茶', '用了三个房子，用这个方法的奶茶冲出来真的炒香，做了双倍的量，放了20g英红九号，大概有五六杯，用了triirrny的方子做的黑糖珍珠，超赞', '2020-05-19', '1', '10', 'u=3839931121,3410137979&fm=26&gp=0.jpg', '5');
INSERT INTO `findfriend` VALUES ('10', '2', '红糖米饭', '值得推荐一下，碗底铺上葡萄干，红枣，做出来的米饭超级好吃，香糯可口', '2020-05-20', '1', '15', 'u=129500147,2663705099&fm=26&gp=0.jpg', '3');
INSERT INTO `findfriend` VALUES ('14', '11', '蒸面', '豆角焖面是我们这里的一道特色，牛肉用土豆淀粉，蛋清酱油拌匀，入锅至变色，放上喜欢的而调料，十三香，干辣椒，下豆角酱油盐，放上面，小火闷，出锅加蒜末，我喜欢现在的你和今天的饭', '2020-05-16', '1', '102', 'u=725564182,1182291286&fm=26&gp=0.jpg', '2');
INSERT INTO `findfriend` VALUES ('28', '1', '自制巧克力', '经过长达是一天的晾晒，我的可可豆终于派上用场了，烘烤之后就到了去壳时间，外科酥脆，真的超级解压，去完壳之后，要把可可豆从固态研磨至液态，悄悄告诉你们一定要加很多很多的糖', '2020-05-19', '1', '66', 'u=3481895104,1416116302&fm=15&gp=0.jpg', '4');
INSERT INTO `findfriend` VALUES ('29', '11', '麻辣土豆', '家里的土豆不要老是炒着吃，今天我分享一个做法，土豆切成厚厚的薄片，水开加盐，碗里加上酱油辣椒盐白糖，浇在土豆上就能食用，学会了就点点关注，给个小红星，谢谢', '2020-05-24', '1', '40', 'tudou.jpg', '3');
INSERT INTO `findfriend` VALUES ('30', '2', '毛血旺', '自制的毛血旺干净有卫生，关键是做法还简单，你说气人不气人，老铁，点点关注不迷路，干了兄弟们', '2020-05-21', '1', '10', 'maoxuewang.jpg', '6');

-- ----------------------------
-- Table structure for `findlable`
-- ----------------------------
DROP TABLE IF EXISTS `findlable`;
CREATE TABLE `findlable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of findlable
-- ----------------------------
INSERT INTO `findlable` VALUES ('3', '中餐');
INSERT INTO `findlable` VALUES ('1', '早餐');
INSERT INTO `findlable` VALUES ('2', '晚餐');
INSERT INTO `findlable` VALUES ('7', '泡面');
INSERT INTO `findlable` VALUES ('6', '火锅');
INSERT INTO `findlable` VALUES ('8', '猜你喜欢');
INSERT INTO `findlable` VALUES ('4', '甜点');
INSERT INTO `findlable` VALUES ('5', '饮料');

-- ----------------------------
-- Table structure for `find_photo`
-- ----------------------------
DROP TABLE IF EXISTS `find_photo`;
CREATE TABLE `find_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photoid` int(11) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `photoid` (`photoid`),
  CONSTRAINT `find_photo_ibfk_1` FOREIGN KEY (`photoid`) REFERENCES `findfriend` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of find_photo
-- ----------------------------
INSERT INTO `find_photo` VALUES ('1', '1', 'u=17521029,2307926959&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('2', '1', 'u=1428715908,536701528&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('3', '2', 'u=2185814375,2877686947&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('4', '2', 'u=3466873163,659824253&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('5', '2', 'u=1949058239,41794123&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('6', '3', 'u=2411487190,553359466&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('7', '3', 'u=1088457151,1292423557&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('8', '3', 'u=3839931121,3410137979&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('9', '10', 'u=129500147,2663705099&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('10', '10', 'u=1451406452,1147530419&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('11', '10', 'u=2400358395,483609747&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('12', '14', 'u=725564182,1182291286&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('13', '14', 'u=3010837528,1898778718&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('14', '14', 'u=3026162289,494658415&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('15', '28', 'u=3481895104,1416116302&fm=15&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('16', '28', 'u=4041945325,2541620191&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('17', '28', 'qiaokeli.jpg');
INSERT INTO `find_photo` VALUES ('18', '29', 'tudou.jpg');
INSERT INTO `find_photo` VALUES ('19', '29', 'tudou2.jpg');
INSERT INTO `find_photo` VALUES ('20', '29', 'u=1262128205,348831020&fm=26&gp=0.jpg');
INSERT INTO `find_photo` VALUES ('21', '30', 'maoxuewang.jpg');
INSERT INTO `find_photo` VALUES ('22', '30', 'maoxuewang2.jpg');
INSERT INTO `find_photo` VALUES ('23', '30', 'u=904887750,2844595113&fm=26&gp=0.jpg');

-- ----------------------------
-- Table structure for `find_user_like`
-- ----------------------------
DROP TABLE IF EXISTS `find_user_like`;
CREATE TABLE `find_user_like` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `findfriendid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `findfriendid` (`findfriendid`),
  KEY `userid` (`userid`),
  CONSTRAINT `find_user_like_ibfk_1` FOREIGN KEY (`findfriendid`) REFERENCES `findfriend` (`id`),
  CONSTRAINT `find_user_like_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of find_user_like
-- ----------------------------
INSERT INTO `find_user_like` VALUES ('13', '2', '123');
INSERT INTO `find_user_like` VALUES ('14', '14', '123');
INSERT INTO `find_user_like` VALUES ('16', '1', '11');
INSERT INTO `find_user_like` VALUES ('17', '3', '123');
INSERT INTO `find_user_like` VALUES ('18', '28', '123');
INSERT INTO `find_user_like` VALUES ('19', '2', '11');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `little_content` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sale_volume` int(11) DEFAULT '0',
  `price` double(11,0) DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`goods_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `market_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '良润面包粉,经典实惠，大包装等你来拿', '大包装更实惠', '138', '50', 'mianbao.png', '0');
INSERT INTO `goods` VALUES ('2', 'sql6寸8寸圆形蛋糕烤盘高级模具', '高级不沾加厚碳钢烤盘', '133', '39', 'guo.png', '0');
INSERT INTO `goods` VALUES ('3', 'JohnsonVille尊乐大包装,好口味', '纯鲜猪肉 芝士爆浆 每根独立', '421', '248', 'chang.png', '1');
INSERT INTO `goods` VALUES ('4', '日本进口不锈钢食品托盘/烧烤盘（多款可选）', '超上镜的多功能托盘', '1100', '15', 'qi.png', '2');
INSERT INTO `goods` VALUES ('5', '深井锦记腊肉，广东腊味熟食蜜汁叉烧肉', '皮脆肉嫩，口感爽滑', '613', '138', 'rou.png', '4');
INSERT INTO `goods` VALUES ('6', '三顿半，冷萃冷泡拿铁咖啡', '超即溶精品咖啡', '2162', '189', 'bei.png', '5');
INSERT INTO `goods` VALUES ('7', '冯氏牛将军，雪花软牛肉袋', '健身必备零食', '1998', '99', 'cc.png', '6');
INSERT INTO `goods` VALUES ('8', '铁蛋米店|东北土特产玉米', '农民自产绿色食品认证', '0', '40', 'yumi1.png', '1');
INSERT INTO `goods` VALUES ('9', '【正常发货】长白山秋木耳', '块块香脆，长白山秋木耳', '0', '38', 'gan1.png', '8');
INSERT INTO `goods` VALUES ('10', '雁门清高|苦荞藜麦石头饼休闲零食', '控糖星人的专属零食', '0', '40', 'qiao.png', '6');
INSERT INTO `goods` VALUES ('11', '网红罐子蛋糕马口铁盒冰淇淋豆乳', '300ml的铁罐容量刚刚好的大小', '0', '10', 'dangao.png', '0');

-- ----------------------------
-- Table structure for `goods_xiangqing`
-- ----------------------------
DROP TABLE IF EXISTS `goods_xiangqing`;
CREATE TABLE `goods_xiangqing` (
  `goods_x_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_img` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `goods_type` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `return_goods` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `if_freeshiiping` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `goods_score` varchar(255) CHARACTER SET utf8 DEFAULT '4.0',
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`goods_x_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `goods_xiangqing_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goods_xiangqing
-- ----------------------------
INSERT INTO `goods_xiangqing` VALUES ('1', 'mianbao.png,mianbao1.png,mianbao2.png,mianbao3.png,mianbao4.png', '新良原味面包粉5kg;50,新良原面包粉10kg;100', 'yes', 'yes', '4.9', '1');
INSERT INTO `goods_xiangqing` VALUES ('2', 'guo.png,guo1.png,guo2.png,guo3.png,guo4.png', '6寸蛋糕圆盘;39,8寸蛋糕圆盘;45', 'yes', 'no', '5.0', '2');
INSERT INTO `goods_xiangqing` VALUES ('3', 'chang.png,chang1.png,chang2.png,chang3.png,chang4.png', '尊乐牌考场1950g;248,百搭车达肠;320,焦香蜜汁肠1950g;260', 'no', 'yes', '4.9', '3');
INSERT INTO `goods_xiangqing` VALUES ('4', 'qi.png,qi1.png,qi2.png,qi3.png,qi4.png', '（可照亮镜子面）长方形托盘;19,（可照亮镜子面）圆边矩形托盘;23,（哑光亮面）长方形托盘;21', 'yes', 'no', '4.8', '4');
INSERT INTO `goods_xiangqing` VALUES ('5', 'rou.png,rou1.png,rou2.png,rou3.png,rou4.png', '一份五花肉一份里脊;138,里脊肉（偏瘦肉）;138,五花肉（半肥瘦）;138', 'yes', 'yes', '4.8', '5');
INSERT INTO `goods_xiangqing` VALUES ('6', 'bei.png,bei1.png,bei2.png,bei3.png,bei4.png', '混合24颗装;189,1号24颗装;189,2号24颗装;189,3号24颗装;189', 'no', 'yes', '4.9', '6');
INSERT INTO `goods_xiangqing` VALUES ('7', 'cc.png,cc1.png,cc2.png,cc3.png,cc4.png', '原味208g*一袋;99,孜然208g*一袋;99,香辣味208g*一袋;99', 'yes', 'yes', '4.8', '7');
INSERT INTO `goods_xiangqing` VALUES ('8', 'yumi1.png,yumi2.png,yumi3.png,yumi4.png,', '粉糯玉米;39.9,', 'no', 'yes', '4', '8');
INSERT INTO `goods_xiangqing` VALUES ('9', 'gan1.png,gan2.png,gan3.png,gan4.png,', '尝鲜【50g/袋】;23.8,实惠【100g/袋*1】;37.9', 'yes', 'yes', '4', '9');
INSERT INTO `goods_xiangqing` VALUES ('10', 'qiao.png,qiao1.png,qiao2.png,qiao3.png,qiao4.png,', '石头饼【120g*5】;39.9,石头饼【200g*5】;80', 'yes', 'yes', '4', '10');
INSERT INTO `goods_xiangqing` VALUES ('11', 'dangao.png,dangao1.png,dao2.png,daogao3.png,', '马口铁蛋糕罐6个;15,白色马白铁长蛋糕;10;10', 'yes', 'yes', '4', '11');

-- ----------------------------
-- Table structure for `label`
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `label_id` int(11) NOT NULL AUTO_INCREMENT,
  `label_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`label_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES ('1', '酸');
INSERT INTO `label` VALUES ('2', '甜');
INSERT INTO `label` VALUES ('3', '苦');
INSERT INTO `label` VALUES ('4', '辣');
INSERT INTO `label` VALUES ('5', '咸');

-- ----------------------------
-- Table structure for `market_comments`
-- ----------------------------
DROP TABLE IF EXISTS `market_comments`;
CREATE TABLE `market_comments` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `r1` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `r2` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `r3` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `market_comments_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of market_comments
-- ----------------------------
INSERT INTO `market_comments` VALUES ('1', '宝贝收到了，做面包的不二选择', '2019-06-25', '1001.png,1002.png', '3', '4', '5', '11', '1');
INSERT INTO `market_comments` VALUES ('3', '已经购买很多次了，很好的面粉', '2018-06-30', '1003.png', '2', '5', '2', '2', '1');
INSERT INTO `market_comments` VALUES ('4', '袋子下面开了一个口子，包装啥时候能给力点', '2018-01-28', '1004.png', '1', '1', '1', '1', '1');
INSERT INTO `market_comments` VALUES ('5', '宝贝很好啊', '2020-02-28', '1005.png', '5', '5', '5', '1', '2');
INSERT INTO `market_comments` VALUES ('6', '这款摸具真的好脱膜哦', '2019-05-24', '1006.png', '3', '3', '3', '1', '2');
INSERT INTO `market_comments` VALUES ('7', '质感不错是我喜欢的', '2019-04-03', '1007.png', '5', '5', '5', '11', '2');
INSERT INTO `market_comments` VALUES ('8', '收到后就冷冻了，冻好后再拿出来到化为冰沙状态，棒极了！', '2020-05-01', '1.png,1.png,', '2.0', '2.0', '2.0', '2', '3');
INSERT INTO `market_comments` VALUES ('9', '收到后就冷冻了，冻好后再拿出来到化为冰沙状态，棒极了！', '2020-05-02', '3.png,1.png,', '2.0', '2.0', '2.0', '1', '3');
INSERT INTO `market_comments` VALUES ('10', '收到后就冷冻了，冻好后再拿出来到化为冰沙状态，棒极了！', '2020-05-05', '3.png,1.png,', '2.0', '2.0', '2.0', '2', '4');
INSERT INTO `market_comments` VALUES ('11', '收到后就冷冻了，冻好后再拿出来到化为冰沙状态，棒极了！', '2020-05-011', '2.png,1.png,', '2.0', '2.0', '2.0', '1', '4');
INSERT INTO `market_comments` VALUES ('12', '大话设计模式（带目录完整版）', '2020-05-11', '2.png', '2.0', '2.0', '2.0', '2', '5');

-- ----------------------------
-- Table structure for `market_commentshow`
-- ----------------------------
DROP TABLE IF EXISTS `market_commentshow`;
CREATE TABLE `market_commentshow` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `comment_show` char(255) CHARACTER SET utf8 DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of market_commentshow
-- ----------------------------
INSERT INTO `market_commentshow` VALUES ('1', 'Jane0702', '很好切，放点油煎一下，淋上糖浆，就很好吃', '1.png', '1');
INSERT INTO `market_commentshow` VALUES ('2', '静儿家的猫', '好吃到爆酱', '2.png', '5');
INSERT INTO `market_commentshow` VALUES ('3', '偶然的旅行', '握在手上实诚，切开满满的只是陷儿，而中间是最可爱的草莓', '3.png', '3');

-- ----------------------------
-- Table structure for `market_court`
-- ----------------------------
DROP TABLE IF EXISTS `market_court`;
CREATE TABLE `market_court` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `goods_content` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `goods_type` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `goods_price` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `goods_count` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `market_court_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of market_court
-- ----------------------------
INSERT INTO `market_court` VALUES ('11', '良润面包粉,经典实惠，大包装等你来拿', '新良原味面包粉5kg', '￥50', '4', '1', '31');
INSERT INTO `market_court` VALUES ('11', '良润面包粉,经典实惠，大包装等你来拿', '新良原面包粉10kg', '￥100', '6', '1', '32');
INSERT INTO `market_court` VALUES ('11', 'sql6寸8寸圆形蛋糕烤盘高级模具', '6寸蛋糕圆盘', '￥39', '3', '2', '33');
INSERT INTO `market_court` VALUES ('2', '良润面包粉,经典实惠，大包装等你来拿', '新良原味面包粉5kg', '￥50', '4', '1', '34');
INSERT INTO `market_court` VALUES ('123', '良润面包粉,经典实惠，大包装等你来拿', '请选择规格', '￥50.0', '1', '1', '35');
INSERT INTO `market_court` VALUES ('123', 'sql6寸8寸圆形蛋糕烤盘高级模具', '请选择规格', '￥39.0', '1', '2', '36');

-- ----------------------------
-- Table structure for `market_order`
-- ----------------------------
DROP TABLE IF EXISTS `market_order`;
CREATE TABLE `market_order` (
  `id` int(20) NOT NULL DEFAULT '-1',
  `user_id` int(20) DEFAULT NULL,
  `price` char(50) CHARACTER SET utf8 DEFAULT NULL,
  `address` char(50) CHARACTER SET utf8 DEFAULT NULL,
  `context` char(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of market_order
-- ----------------------------
INSERT INTO `market_order` VALUES ('0', '123', '地区选择                              ------', '89.0', 'null');
INSERT INTO `market_order` VALUES ('1', '12', '10', '辽宁省丹东市东港市仁达路88-25号', 'null');
INSERT INTO `market_order` VALUES ('2', '1', '1', '江苏省苏州市吴中区东方大道1233号', 'null');
INSERT INTO `market_order` VALUES ('3', '123', '89.0', '河北省石家庄裕华区河北师范大学', '记得加送我一包辣条');
INSERT INTO `market_order` VALUES ('4', '123', '77.2', '四川省成都市青羊区青华路10号之附10-12', 'null');
INSERT INTO `market_order` VALUES ('5', '123', '33.2', '陕西省安康市汉滨区大桥路2号', 'null');
INSERT INTO `market_order` VALUES ('6', '123', '66.6', '孝感市云梦县梦馨佳园b区', 'null');

-- ----------------------------
-- Table structure for `market_type`
-- ----------------------------
DROP TABLE IF EXISTS `market_type`;
CREATE TABLE `market_type` (
  `id` int(11) NOT NULL DEFAULT '-1',
  `type` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of market_type
-- ----------------------------
INSERT INTO `market_type` VALUES ('0', '烘培');
INSERT INTO `market_type` VALUES ('1', '果蔬生鲜');
INSERT INTO `market_type` VALUES ('2', '器具');
INSERT INTO `market_type` VALUES ('3', '领券');
INSERT INTO `market_type` VALUES ('4', '方便食品');
INSERT INTO `market_type` VALUES ('5', '饮品茶酒');
INSERT INTO `market_type` VALUES ('6', '零食');
INSERT INTO `market_type` VALUES ('7', '腌制腊肉');
INSERT INTO `market_type` VALUES ('8', '南北干货');
INSERT INTO `market_type` VALUES ('9', '进口食品');
INSERT INTO `market_type` VALUES ('10', '米面粮油');
INSERT INTO `market_type` VALUES ('11', '厨房电器');
INSERT INTO `market_type` VALUES ('12', '礼盒');
INSERT INTO `market_type` VALUES ('13', '调味品');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(100) NOT NULL AUTO_INCREMENT,
  `text` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `menu_photo` varchar(255) DEFAULT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
<<<<<<< HEAD
  KEY `author` (`text`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
=======
  KEY `author` (`author`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`author`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
>>>>>>> db9c99bacba2194f92478b851f2ec59134604747

-- ----------------------------
-- Records of menu
-- ----------------------------
<<<<<<< HEAD
INSERT INTO `menu` VALUES ('4', '土豆丝的正宗做法，学会这个三个技巧，土豆又脆有下饭', '2020-06-03', 'tudousichaorou.jpg', '土豆丝炒肉', '米');
INSERT INTO `menu` VALUES ('5', '一定要用甜口的辣白菜，太酸的辣白菜炒出来不好吃', '2020-06-03', 'labaicaiwuhuarou.jpg', '辣白菜五花肉', '菜');
INSERT INTO `menu` VALUES ('6', '开水白菜以北方的大白菜心来制作，配以用鸡，鸭，排骨熬煮，\r\n并用鸡肉蓉，猪肉蓉澄澈的高汤调味，最后浇汤时在汤里淋一\r\n些鸡油。成菜后，清鲜淡雅，香味浓醇，汤味浓厚，不油不腻，\r\n却清香爽口。', '2020-06-03', 'kaishuibaicai.jpg', '开水白菜', '菜');
INSERT INTO `menu` VALUES ('7', '本菜谱是简易的葱油拌面，做得不是很讲究。', '2020-06-03', 'congyoubanmian.jpg', '葱油拌面', '菜');
INSERT INTO `menu` VALUES ('13', '仅记录自己方便使用，侵权删除', '2020-06-03', 'xcf_recipe_1591166507773.jpg', '木耳炒腐竹', '菜');
INSERT INTO `menu` VALUES ('14', '夏天不想做菜，那就来一道简单好吃又快手的凉拌豆皮吧、酸辣开胃，做法简单，喜欢的你一定要试试哦？', '2020-06-03', 'xcf_recipe_1591167750972.jpg', '巨好吃的凉拌豆皮❗️香辣过瘾连吃三天都不腻', '菜');
INSERT INTO `menu` VALUES ('15', '时间：约20-25分钟 难度：零厨艺✌️ 份量：4人份', '2020-06-03', 'xcf_recipe_1591168098748.jpeg', '鲜味香菇芋头饭', '米');
INSERT INTO `menu` VALUES ('16', '简单又爽口的热汤面 加入自己喜欢的青菜，十分钟就能搞定的早餐&午餐&晚餐', '2020-06-03', 'xcf_recipe_1591168309586.jpeg', '十分钟搞定的热汤面', '面');
=======
INSERT INTO `menu` VALUES ('1', null, '2020-04-23', 'simuxue.jpg', '思慕雪', '菜');
INSERT INTO `menu` VALUES ('2', null, '2020-04-21', 'xiaolongxia.jpg', '小龙虾', '菜');
INSERT INTO `menu` VALUES ('4', null, '2020-04-27', 'tudousichaorou.jpg', '土豆丝炒肉', '菜');
INSERT INTO `menu` VALUES ('5', null, '2020-06-17', 'labaicaiwuhuarou.jpg', '辣白菜五花肉', '菜');
INSERT INTO `menu` VALUES ('6', null, '2020-05-27', 'kaishuibaicai.jpg', '开水白菜', '菜');
INSERT INTO `menu` VALUES ('7', null, '2020-04-17', 'congyoubanmian.jpg', '葱油拌面', '面');
>>>>>>> db9c99bacba2194f92478b851f2ec59134604747

-- ----------------------------
-- Table structure for `menu_comment`
-- ----------------------------
DROP TABLE IF EXISTS `menu_comment`;
CREATE TABLE `menu_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `menu_id` (`menu_id`),
  KEY `author` (`author`),
  CONSTRAINT `menu_comment_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`),
  CONSTRAINT `menu_comment_ibfk_2` FOREIGN KEY (`author`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `menu_label`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu_label
-- ----------------------------
INSERT INTO `menu_label` VALUES ('14', '1', '9');
INSERT INTO `menu_label` VALUES ('42', '4', '13');
INSERT INTO `menu_label` VALUES ('43', '5', '13');
INSERT INTO `menu_label` VALUES ('44', '1', '4');
INSERT INTO `menu_label` VALUES ('45', '4', '4');
INSERT INTO `menu_label` VALUES ('46', '5', '4');
INSERT INTO `menu_label` VALUES ('47', '1', '5');
INSERT INTO `menu_label` VALUES ('48', '4', '5');
INSERT INTO `menu_label` VALUES ('49', '5', '5');
INSERT INTO `menu_label` VALUES ('51', '5', '6');
INSERT INTO `menu_label` VALUES ('54', '4', '7');
INSERT INTO `menu_label` VALUES ('55', '5', '7');
INSERT INTO `menu_label` VALUES ('56', '1', '14');
INSERT INTO `menu_label` VALUES ('57', '4', '14');
INSERT INTO `menu_label` VALUES ('58', '5', '14');
INSERT INTO `menu_label` VALUES ('59', '4', '15');
INSERT INTO `menu_label` VALUES ('60', '5', '15');
INSERT INTO `menu_label` VALUES ('61', '1', '16');
INSERT INTO `menu_label` VALUES ('62', '4', '16');
INSERT INTO `menu_label` VALUES ('63', '5', '16');

-- ----------------------------
-- Table structure for `mv_comment`
-- ----------------------------
DROP TABLE IF EXISTS `mv_comment`;
CREATE TABLE `mv_comment` (
  `mvcomment_id` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `mvcomment_time` varchar(255) DEFAULT NULL,
  `mv_id` int(11) NOT NULL,
  PRIMARY KEY (`mv_id`),
  KEY `author` (`author`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mv_comment
-- ----------------------------
INSERT INTO `mv_comment` VALUES ('水煮肉片', 'sion', '好想吃', 'http://img2.imgtn.bdimg.com/it/u=1985706169,239838421&fm=26&gp=0.jpg', '1');
INSERT INTO `mv_comment` VALUES ('米饭披萨料理', '九星毒奶', '比我有才，求大神多发点', 'http://img0.imgtn.bdimg.com/it/u=2589794277,4289567791&fm=26&gp=0.jpg', '2');
INSERT INTO `mv_comment` VALUES ('西红柿米饭', '云天厚谊', '楼上的小姐姐来我家，我也会做', 'http://pic1.win4000.com/pic/5/40/ce69876873_130_170.jpg', '3');
INSERT INTO `mv_comment` VALUES ('西红柿米饭', '沧海一笑', '才艺不止你一人有', 'http://img0.imgtn.bdimg.com/it/u=2468400349,963670781&fm=26&gp=0.jpg', '4');
INSERT INTO `mv_comment` VALUES ('米饭披萨料理', '手电筒', '图片说明一切', 'http://b-ssl.duitang.com/uploads/item/201808/13/20180813224158_wfcbm.thumb.224_0.jpg', '5');
INSERT INTO `mv_comment` VALUES ('心形蛋糕', '站住别走', '我也想要', 'http://img0.imgtn.bdimg.com/it/u=1621815320,325751128&fm=11&gp=0.jpg', '6');
INSERT INTO `mv_comment` VALUES ('甜点', '炸天帮徐缺', '虽然不懂，但是好想吃', 'http://img5.imgtn.bdimg.com/it/u=1915900830,2104806041&fm=26&gp=0.jpg', '7');
INSERT INTO `mv_comment` VALUES ('甜点', '清华门', null, 'http://img3.imgtn.bdimg.com/it/u=1152582376,1311957215&fm=26&gp=0.jpg', '8');
INSERT INTO `mv_comment` VALUES ('甜点', '网络喷子', '还有其他的甜点做法吗', null, '9');
INSERT INTO `mv_comment` VALUES ('甜点', '24K', '求大神赐教', 'http://img1.imgtn.bdimg.com/it/u=237801882,3588233167&fm=15&gp=0.jpg', '10');
INSERT INTO `mv_comment` VALUES ('青辣椒', '8sda', '看着就好辣，但是好爽', null, '11');
INSERT INTO `mv_comment` VALUES ('香茅姜茶', 'qwes', '清凉解渴小宝贝，谁吃谁得劲', 'http://img3.imgtn.bdimg.com/it/u=1666262756,1936982353&fm=26&gp=0.jpg', '12');
INSERT INTO `mv_comment` VALUES ('肉夹馍', 'zxf', '为什么我做出来的那么难吃？', 'http://img1.imgtn.bdimg.com/it/u=2921143277,2752102458&fm=15&gp=0.jpg', '13');
INSERT INTO `mv_comment` VALUES ('花蛤', 'asdwq', '真的好吃吗？这么简单？', 'http://img0.imgtn.bdimg.com/it/u=41706827,3907250713&fm=15&gp=0.jpg', '14');
INSERT INTO `mv_comment` VALUES ('美味炒面', 'csaz', '口水都流出来了', null, '15');
INSERT INTO `mv_comment` VALUES ('水煮肉片', '蓑头翁', '老板给我一份', null, '16');
INSERT INTO `mv_comment` VALUES ('水煮肉片', '寒江雪', '四川人的最奈', null, '17');
INSERT INTO `mv_comment` VALUES ('水煮肉片', '江小皮皮不皮', '请开始你的表演', null, '18');
INSERT INTO `mv_comment` VALUES ('肉夹馍', '夏研', '大厨，上菜', null, '19');

-- ----------------------------
-- Table structure for `step`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of step
-- ----------------------------
INSERT INTO `step` VALUES ('21', '1', '4', '土豆去皮切丝、青椒切丝、青葱切末、红椒切丝、猪肉切丁', 'tudousichaorou_01.jpg');
INSERT INTO `step` VALUES ('22', '2', '4', '起锅将清水煮沸，放入土豆丝焯水30秒', 'tudousichaorou_02.jpg');
INSERT INTO `step` VALUES ('23', '3', '4', '另起锅烧油，倒入食用油、加葱末、猪肉，炒至变色', 'tudousichaorou_03.jpg');
INSERT INTO `step` VALUES ('24', '4', '4', '倒入土豆丝、青椒、红椒、鸡粉、盐、白砂糖、生抽、蚝油，翻炒均匀', 'tudousichaorou_04.jpg');
INSERT INTO `step` VALUES ('25', '5', '4', '装盘出锅，即可食用', 'tudousichaorou_05.jpg');
INSERT INTO `step` VALUES ('26', '1', '5', '准备好原料，辣白菜切宽6cm的段备用。', 'labaicaiwuhuarou_01.jpg');
INSERT INTO `step` VALUES ('27', '2', '5', '将五花肉切成薄片，加入盐和料酒腌10分钟。', 'labaicaiwuhuarou_02.jpg');
INSERT INTO `step` VALUES ('28', '3', '5', '锅中放油加热到5成，放入五花肉。慢慢煎出五花肉中的油，直到肉片变卷曲微微焦黄，这样吃起来肥而不腻。然后盛出五花肉，倒出锅中多余的油，剩少许油加到5成热。', 'labaicaiwuhuarou_03.jpg');
INSERT INTO `step` VALUES ('29', '4', '5', '放入辣白菜翻炒出香味，放入炒好的五花肉加白糖炒匀后盛出。', 'labaicaiwuhuarou_04.jpg');
INSERT INTO `step` VALUES ('30', '1', '6', '碗中放入排骨250g，倒入水500ml浸泡半小时', 'kaishuibaicai_01.jpg');
INSERT INTO `step` VALUES ('31', '2', '6', '准备白菜芯1棵切条，大葱10g切段、生姜5g切片', 'kaishuibaicai_02.jpg');
INSERT INTO `step` VALUES ('32', '3', '6', '锅中放入水1L倒入排骨葱段、姜片，炖制高汤', 'kaishuibaicai_03.jpg');
INSERT INTO `step` VALUES ('33', '4', '6', '高汤中加入白菜，盐2g，搅拌均匀，大火煮沸', 'kaishuibaicai_04.jpg');
INSERT INTO `step` VALUES ('34', '5', '6', '出锅，完成', 'kaishuibaicai_05.jpg');
INSERT INTO `step` VALUES ('35', '1', '7', '材料准备好，葱白、葱绿分别切碎，小米椒切碎', 'congyoubanmian_01.jpg');
INSERT INTO `step` VALUES ('36', '2', '7', '取一个大点的碗，放入1茶匙生抽，1/2茶匙醋', 'congyoubanmian_02.jpg');
INSERT INTO `step` VALUES ('37', '3', '7', '放入1/2茶匙白糖，调成酱汁备用，锅里烧开水，放面条煮熟', 'congyoubanmian_03.jpg');
INSERT INTO `step` VALUES ('38', '4', '7', '煮好的面条捞出放入碗中拌匀，锅里烧热油，爆香葱白和小米椒', 'congyoubanmian_04.jpg');
INSERT INTO `step` VALUES ('39', '5', '7', '爆好的葱油淋在面上，撒上葱花即可', 'congyoubanmian_05.jpg');
INSERT INTO `step` VALUES ('42', '1', '13', '备料，木耳温水泡发，腐竹凉水泡法，腹逐找个东西压着泡', 'xcf_recipe_1591166514106.jpg');
INSERT INTO `step` VALUES ('43', '2', '13', '调料，半勺老抽，一勺生抽，一勺生蚝，少许白糖，盐，寂静，两勺玉米淀粉，导入半碗清水', 'xcf_recipe_1591166520664.jpg');
INSERT INTO `step` VALUES ('44', '3', '13', '葱姜蒜下锅煸炒出香味，下入腐竹木耳，加两碗清水煮两分钟', 'xcf_recipe_1591166527591.jpg');
INSERT INTO `step` VALUES ('45', '4', '13', '加入辣椒，翻炒后加入料汁，开火收汁后出锅即可', 'xcf_recipe_1591166532565.jpg');
INSERT INTO `step` VALUES ('46', '1', '14', '豆皮切丝，大蒜拍碎，小米辣切碎，黄瓜切丝、香菜切段备用。 锅中水开放豆皮焯水1分钟、捞出过凉水！ 碗中➕蒜末、小米椒碎、浇上两勺热油，➕两勺生抽、两勺香醋，一勺蚝油，适量盐', 'xcf_recipe_1591167757355.jpg');
INSERT INTO `step` VALUES ('47', '2', '14', '两勺辣椒油拌匀备用', 'xcf_recipe_1591167764324.jpg');
INSERT INTO `step` VALUES ('48', '3', '14', '碗中放黄瓜丝和香菜段，再淋上调好的料汁，拌匀即可！', 'xcf_recipe_1591167768101.jpg');
INSERT INTO `step` VALUES ('49', '4', '14', '简单爽口的凉拌豆皮就做好了', 'xcf_recipe_1591167771715.jpg');
INSERT INTO `step` VALUES ('50', '1', '15', '炒锅中倒入油，放入泡发好的海米和干香菇，煸炒出香味。干货泡发后，本身的水分很多，所以要煸炒的透彻一些。泡发香菇的水留用，不要倒掉。 小贴士：干香菇和海米最好能提前一天泡发。', 'xcf_recipe_1591168107360.jpeg');
INSERT INTO `step` VALUES ('51', '2', '15', '将淘洗好的米、炒好的芋头香菇、泡香菇的水，倒入电饭锅内。 小贴士：泡香菇的水，大约要200g。', 'xcf_recipe_1591168102334.jpeg');
INSERT INTO `step` VALUES ('52', '3', '15', '开启一个正常的焖饭流程，大约37分钟-40分钟，根据自己家的锅情况。流程结束，饭就熟了，翻拌均匀就可以吃了。', 'xcf_recipe_1591168098748.jpeg');
INSERT INTO `step` VALUES ('53', '1', '16', '香葱切碎放入大碗中，锅里烧热油淋在香葱上呛出香味（喜欢辣可以放点辣椒粉）', 'xcf_recipe_1591168283456.jpeg');
INSERT INTO `step` VALUES ('54', '2', '16', '加入生抽，陈醋，盐，鸡精', 'xcf_recipe_1591168290221.jpeg');
INSERT INTO `step` VALUES ('55', '3', '16', '加入烧开的热水（煮面的水多烧点，烧开了先倒一些在料碗里做热汤，剩的煮面。或者一个锅烧水，一个锅煮面同时进行）', 'xcf_recipe_1591168296087.jpeg');
INSERT INTO `step` VALUES ('56', '4', '16', '水烧开，煮熟面条（这时青菜可一起煮熟）', 'xcf_recipe_1591168299962.jpeg');
INSERT INTO `step` VALUES ('57', '5', '16', '将煮熟的面条捞入热汤中，放点香菜，爽口的热汤面就做好啦', 'xcf_recipe_1591168305524.jpeg');
INSERT INTO `step` VALUES ('58', '6', '16', '也可以放点紫菜，或者自己喜欢的青菜和鸡蛋，如果今天你不想大动干戈做顿饭，就来试试吧', 'xcf_recipe_1591168309586.jpeg');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=1235 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '一只肥罗', '123', null, 'yizhifeiluo.jpg', null, null, null, null, null);
INSERT INTO `user` VALUES ('2', 'ss', '2', '1', 'abc.jpeg', null, null, null, null, null);
INSERT INTO `user` VALUES ('11', 'zs', '11', '1', 'tiny-77-2020-05-16-03-27-47.jpg', 'nan', 'it', '2017', 'hebei', 'sss');
INSERT INTO `user` VALUES ('123', '123', '123', '1', 'abc.jpeg', null, null, null, null, null);

-- ----------------------------
-- Table structure for `user_menu`
-- ----------------------------
DROP TABLE IF EXISTS `user_menu`;
CREATE TABLE `user_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_menu
-- ----------------------------
