drop database if exists MedBoxDB;  
create database MedBoxDB default character set utf8; 
use MedBoxDB;
drop tables `user`;
create table `user`  
(  
/**
	private String username;
    private String password;
    private String name;//姓名
    private String sex;//性别
    private String age;//年龄
    private String weight;//体重 单位公斤
    private String length;//身高 单位厘米
    private String medicalhistory;//病史
    private String drugallergyhistory;//药物过敏史
    private String img_src;//头像地址

*/
   /* 用户编号,自动增长 */  
   -- `id`                  int primary key not null auto_increment,  
   
   /* 用户电话 */  
   `phone`              varchar(11)  primary key,
   /* 用户登录名 */  
   `nick`               varchar(20),  
   /* 用户真实姓名 */  
   `username`                varchar(20),  
   /* 用户登录密码 */  
   `password`               varchar(20),  
   /* 用户性别 */  
   `sex`                 varchar(20),  
  
   /*身高*/
   `height`				varchar(20),
   /*体重*/
   `weight`				varchar(20),
   /*药物过敏史*/
   `drugallergyhistory`   Text,
   /*病史*/
   `medicalhistory` 		Text,
   /*头像地址*/
   `img`				blob
);  
insert into `user`
(`phone`,`nick`,`username`,`password`,`sex`,`height`
	,`weight`,`drugallergyhistory`,`medicalhistory`) 
values('18812312312','医药大管家','真名','pass123','男','193','67','没有药物过敏史','没有病史');
drop tables `persistent_logins`;
CREATE TABLE `persistent_logins` (
	`id` INT(11)  PRIMARY KEY  AUTO_INCREMENT,
    /*uphone    用户登录手机号*/
    `uphone` VARCHAR(11)  NOT NULL,
    /* 用户登录名*/
    `unick`		VARCHAR(20) NOT NULL,
    /*series    用户使用密码登录成功之后获取的一个UUID值，
				同时用户端保存的cookie记录就是：
			EncryptionUtil.base64Encode(用户名:此UUID值)*/
    `series` VARCHAR(300) DEFAULT NULL,
    /*
    token    在拦截器中校验是否能够登录的密文，其加密方式是：
			EncryptionUtil.sha256Hex(用户手机号+"_"+用户登录名(nick) + “_” + 密码 + “_” + 
					自动登录失效的时间点的字符串 + “_” +  自定义的salt)
    */
    `token` VARCHAR(500) DEFAULT NULL,
    /*validTime    自动登录失效的时间，
			即：这个时间点之后只能重新用用户名、密码登录，
            如果在重新登录时勾选了“30天内自动登录”则更新该用户在persistent_logins这个表中的自动登录记录*/
    `validTime` DATETIME DEFAULT NULL
   
   --  constraint psis_FK foreign key(`uphone`) references user(`phone`)
	-- constraint fid_FK foreign key(`unick`) references forder(`nick`) 
);
drop TABLE `medicine`;
CREATE TABLE `medicine` (
	-- `id` int primary key  not null auto_increment ,
    /*药准字号*/
    `licensenumber` varchar(18) primary key,
    /*药通用名*/
    `medicinename` varchar(50),
    --  /*药商品名*/
    /*有效成分*/
    `activeingredient` text,
    /*性状*/
    `medcharacter`  varchar(255),
    /*规格/剂量*/
    `dose` 	varchar(50),
    /*用法用量*/
    `dosage` varchar(255),
    /*禁忌*/
    `contraindication` varchar(255),
    /*适应症/功能主治*/
	`indication`  text,
    /*医生建议用法用量*/
    `dosagefromdoc` varchar(255),
    /*不良反应*/
    `untowardeffect` varchar(255),
    /*注意事项*/
    
    /*药物相互作用*/
    `druginteraction` varchar(255),
    /*有效期*/
    `periodvalidity`  varchar(10),
    /*生产厂家*/
    `manufacturer`	varchar(40),
    /*存储条件*/
    `storageconditions` varchar(30)
);

insert into medicine(`licensenumber`, `medicinename`,`activeingredient`,
`medcharacter`,`dose`,`dosage`,`contraindication`,`indication`,`dosagefromdoc`,
`untowardeffect`,`druginteraction`,`periodvalidity`,`manufacturer`,`storageconditions`
) 
  values('国药准字Z45021599','桂林西瓜霜','西瓜霜、锻硼砂、黄柏、黄连、山豆根、射干、浙贝母、青黛、冰片、大黄、木汉果（炭）黄芩、甘草、薄荷脑',
	'本品为灰黄绿色的粉末；气香，味咸、微苦而辛凉','每瓶装2.5克','喷（吹）敷患处，一次适量，一日数次。
重症者兼服，一次1～2g，次数视情况而定。','忌烟酒、辛辣、鱼腥食物。','口腔溃疡','喷（吹）敷患处，一次适量，一日数次。',
'尚不明确','与其他药物可能发生相互作用,请询问医师','36个月','桂林三金药业股份有限公司','密闭，防潮。'
);