/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.util.Arrays;

/**
 *
 * @author CIDER
 */
public final class NAMELIST {
    
    public static final String FD2FILE = "../FD2.bin";
    public static final String FIELDFILE = "../FDFIELD.bin";
    
    public static final String[] CHARACTER = {  "索尔",
                                                "哈诺",
                                                "铁诺",
                                                "哈瓦特",
                                                "亚雷斯",
                                                "洛娜",
                                                "莱汀",
                                                "兰斯洛特",
                                                "希莉亚",
                                                "悠妮",
                                                "玛琳",
                                                "索菲亚",
                                                "凯丽",
                                                "贝克威",
                                                "珊",
                                                "塞可邦勒",
                                                "凯拉斯",
                                                "米亚斯多德",
                                                "密蒂",
                                                "罗德曼",
                                                "莎拉",
                                                "约拿",
                                                "卡里斯",
                                                "罗兰",
                                                "希尔法",
                                                "谢多",
                                                "圣寇拉斯",
                                                "巴拿罗西亚",
                                                "达克塞",
                                                "亚齐梅吉",
                                                "盖亚",
                                                "渥德",
                                                "索尔(剑圣)",
                                                "哈诺(圣战士)",
                                                "铁诺(剑圣)",
                                                "哈瓦特(圣战士)",
                                                "亚雷斯(圣骑士)",
                                                "洛娜(圣骑士)",
                                                "莱汀(圣骑士)",
                                                "精灵长者",
                                                "希莉亚(狙击手)",
                                                "悠妮(大法师)",
                                                "玛琳(祭师)",
                                                "索菲亚(祭师)",
                                                "凯丽(斗士)",
                                                "贝克威(狙击手)",
                                                "珊(大法师)",
                                                "塞可邦勒(斗士)",
                                                "罗特帝亚国王",
                                                "米亚斯多德(龙剑士)",
                                                "索尔(英雄)",
                                                "哈诺(魔战士)",
                                                "悠妮(召唤师)",
                                                "哈瓦特(魔战士)",
                                                "亚雷斯(龙骑士)",
                                                "洛娜(龙骑士)",
                                                "莱汀(龙骑士)",
                                                "未使用",
                                                "希莉亚(神射手)",
                                                "悠妮(圣者)",
                                                "玛琳(圣者)",
                                                "索菲亚(圣者)",
                                                "凯丽(武圣)",
                                                "贝克威(神射手)",
                                                "珊(圣者)",
                                                "塞可邦勒(武圣)",
                                                "罗特迪亚王后",
                                                "米亚斯多德(战场)",
                                                "士兵",
                                                "王国正规军",
                                                "佣兵",
                                                "豹人",
                                                "精灵",
                                                "龙剑士",
                                                "恶魔",
                                                "解说员",
                                                "士兵",
                                                "精英战士",
                                                "铠甲武士",
                                                "佣兵",
                                                "黑暗战士",
                                                "狂战士",
                                                "骑兵",
                                                "突击骑兵",
                                                "地狱骑士",
                                                "黑暗骑士",
                                                "龙骑士",
                                                "弓箭手",
                                                "黑暗射手",
                                                "狙击手",
                                                "魔法师",
                                                "黑暗法师",
                                                "巫师",
                                                "僧侣",
                                                "黑暗僧侣",
                                                "大祭师",
                                                "盗贼",
                                                "盗贼头目",
                                                "影之忍者",
                                                "黑暗杀手",
                                                "武术家",
                                                "黑暗斗士",
                                                "兽人",
                                                "受人队长",
                                                "火龙",
                                                "雷龙",
                                                "龙人战士",
                                                "龙人法师",
                                                "魔鬼",
                                                "恶魔",
                                                "大恶魔",
                                                "机甲兵",
                                                "机甲射手",
                                                "光束炮座",
                                                "机甲突击兵",
                                                "机甲守卫",
                                                "机甲队长",
                                                "卡特那",
                                                "莱汀",
                                                "萨卡",
                                                "玛尔",
                                                "沼泽怪物",
                                                "地魔神",
                                                "水魔神",
                                                "风魔神",
                                                "火魔神",
                                                "空魔神",
                                                "暗黑龙",
                                                "武器店老板",
                                                "酒店老板",
                                                "道具店老板",
                                                "教堂牧师",
                                                "秘密商店老板",
                                                "男村民",
                                                "女村民",
                                                "卡恩那三世"    };
                                                
    public static final String[] ROLE =  Arrays.copyOfRange(CHARACTER, 0, 32);
    public static final String[] ENEMY = Arrays.copyOfRange(CHARACTER, 68, 128);                                            
                                            
    public static final String[] ITEM = {   "短剑",
                                            "阔剑",
                                            "长剑",
                                            "巨剑",
                                            "黑暗剑",
                                            "战士剑",
                                            "巨魔剑",
                                            "斩铁剑",
                                            "流水剑",
                                            "大地之剑",
                                            "龙神剑",
                                            "炎龙剑",
                                            "小刀",
                                            "匕首",
                                            "淬毒刀",
                                            "盗贼匕首",
                                            "暗杀剑",
                                            "护手刀",
                                            "忍者刀",
                                            "地狱刀",
                                            "刺矛",
                                            "骑枪",
                                            "长戟",
                                            "先锋矛",
                                            "斩马刀",
                                            "恶魔之矛",
                                            "黄金之戟",
                                            "龙之枪",
                                            "破魔枪",
                                            "战神戟",
                                            "圣之枪",
                                            "巨神戟",
                                            "手斧",
                                            "回旋斧",
                                            "战斧",
                                            "战锤",
                                            "巨斧",
                                            "血之斧",
                                            "闪电锤",
                                            "狂暴之斧",
                                            "光之斧",
                                            "力量之斧",
                                            "大地之锤",
                                            "魔神斧",
                                            "短弓",
                                            "长弓",
                                            "黑暗弓",
                                            "巨弓",
                                            "精灵弓",
                                            "狙击弓",
                                            "封魔弓",
                                            "风神弓",
                                            "长棍",
                                            "钉头锤",
                                            "巨锤",
                                            "连枷",
                                            "魔法杖",
                                            "封咒杖",
                                            "力之杖",
                                            "黑暗杖",
                                            "龙之杖",
                                            "光之杖",
                                            "铁指套",
                                            "铁爪",
                                            "皇帝指环",
                                            "毒爪",
                                            "僵尸爪",
                                            "碎岩爪",
                                            "金钢指环",
                                            "裂刃爪",
                                            "巨神指环",
                                            "魔龙爪",
                                            "威力手臂",
                                            "金钢手臂",
                                            "雷神手臂",
                                            "冲击手臂",
                                            "光束剑",
                                            "狙击枪",
                                            "光束枪",
                                            "光束炮",
                                            "拳头",
                                            "利爪",
                                            "触手",
                                            "巨岩手臂",
                                            "激水炮",
                                            "风牙斩",
                                            "魔波",
                                            "空雷震",
                                            "圣者之戒",
                                            "勇者徽章",
                                            "精灵契印",
                                            "领司之书",
                                            "心眼之书",
                                            "白金徽章",
                                            "生命之宝",
                                            "魔力水晶",
                                            "风精之羽",
                                            "十字弓",
                                            "水晶弓",
                                            "雷神鞭",
                                            "天空之钥",
                                            "传送法杖",
                                            "修理套件",
                                            "火",
                                            "火神弓",
                                            "斗神指环",
                                            "冰",
                                            "黑暗波",
                                            "巨岩铠",
                                            "魔神甲",
                                            "魔岩铠",
                                            "布衣",
                                            "旅行装",
                                            "精灵披风",
                                            "元素护体",
                                            "皮甲",
                                            "硬皮甲",
                                            "夜行装",
                                            "魔法皮甲",
                                            "盗贼之衣",
                                            "忍者装",
                                            "夜行服",
                                            "神龙装",
                                            "暗杀服",
                                            "黑暗之衣",
                                            "雷神服",
                                            "环状甲",
                                            "锁子甲",
                                            "鳞甲",
                                            "连环钢甲",
                                            "合金锁甲",
                                            "魔法鳞甲",
                                            "血环甲",
                                            "金钢锁甲",
                                            "黑暗鳞甲",
                                            "力量锁甲",
                                            "恶魔鳞甲",
                                            "龙麟甲",
                                            "铠甲",
                                            "银铠甲",
                                            "重铠甲",
                                            "地狱铠甲",
                                            "圣铠甲",
                                            "大地铠甲",
                                            "龙神铠甲",
                                            "长袍",
                                            "法师袍",
                                            "僧侣袍",
                                            "祭司袍",
                                            "圣者袍",
                                            "雾之力",
                                            "黑暗之袍",
                                            "天之袍",
                                            "武道服",
                                            "武斗装",
                                            "斗士服",
                                            "武者铠甲",
                                            "妖魔斗服",
                                            "武神扩甲",
                                            "战斗装甲",
                                            "突击装甲",
                                            "重装甲",
                                            "特殊装甲",
                                            "硬皮",
                                            "青色硬皮",
                                            "龙麟",
                                            "勒皮",
                                            "虚无护甲",
                                            "王袍",
                                            "斗士护甲",
                                            "地之袍",
                                            "水之袍",
                                            "大地装甲",
                                            "药草",
                                            "回复剂",
                                            "再生药",
                                            "神圣之水",
                                            "解毒剂",
                                            "退麻剂",
                                            "力量药水",
                                            "耐力药水",
                                            "速度药水",
                                            "绿宝石",
                                            "红宝石",
                                            "蓝宝石",
                                            "钻石",
                                            "飞龙卵",
                                            "魔法水",
                                            "水晶粒",
                                            "控制中枢",
                                            "黄金徽章",
                                            "星之眼",
                                            "光之眼",
                                            "暗之眼",
                                            "冰之眼",
                                            "火之眼"  };
    public static final String[] MAGIC = {  "火炎术",
                                            "烈炎术",
                                            "炎龙术",
                                            "天火术",
                                            "电击术",
                                            "落雷术",
                                            "轰雷术",
                                            "神雷术",
                                            "圣光弹",
                                            "咒杀术",
                                            "碎岩术",
                                            "地震术",
                                            "裂地术",
                                            "治疗术",
                                            "回复术",
                                            "再生术",
                                            "神恩术",
                                            "魔刃术",
                                            "魔铠术",
                                            "风行术",
                                            "解毒术",
                                            "祛麻术",
                                            "封咒术",
                                            "传送术",
                                            "破龙击",
                                            "行动术",
                                            "毒击术",
                                            "麻庳术",
                                            "凄煌斩",
                                            "炽炎刀",
                                            "音速刃",
                                            "未使用",
                                            "炽天使",
                                            "风妖精",
                                            "破坏神",
                                            "暗邪鬼"    };
    public static final String[] CLASS = {  "龙",
                                            "剑士",
                                            "战士",
                                            "骑士",
                                            "弓兵",
                                            "法师",
                                            "僧侣",
                                            "盗贼",
                                            "武者",
                                            "剑圣",
                                            "圣战士",
                                            "圣骑士",
                                            "狙击手",
                                            "大法师",
                                            "祭师",
                                            "龙剑士",
                                            "斗士",
                                            "英雄",
                                            "魔战士",
                                            "龙骑士",
                                            "神射手",
                                            "召唤师",
                                            "圣者",
                                            "忍者",
                                            "武圣",
                                            "机兵",
                                            "未知",
                                            "未使用",
                                            "达可赛"};
    
    public static final String[] RACE = {   "人类",
                                            "精灵",
                                            "豹人",
                                            "龙人",
                                            "魔族",
                                            "机械",
                                            "元素",
                                            "兽人",
                                            "其他"  };
    
    public static final String[] ALIGNMENT = {"敌方", "友军", "我方"};
    public static final String[] RANGETYPE = {"直线", "范围"};
    public static final String[] DROP = {"物品", "金钱"};
    public static final String[] WEAPONEFFCT = {"无",
                                                "未使用",
                                                "中毒",
                                                "双击",
                                                "爆击" };
    
    public static final String[] ITEMEFFECT = { "无",
                                            "未使用",
                                            "未使用",
                                            "未使用",
                                            "未使用",
                                            "恢复生命道具",
                                            "解毒",
                                            "怯麻",
                                            "永久增加力量",
                                            "永久增加耐力",
                                            "永久增加速度",
                                            "恢复魔力",
                                            "速度术",
                                            "恢复生命法术",
                                            "麻痹术",
                                            "防御术",
                                            "攻击术",
                                            "永久增加生命",
                                            "永久增加法力",
                                            "永久增加移动力",
                                            "攻击法术",
                                            "攻击法术",
                                            "封咒术",
                                            "传送术",
                                            "攻击法术"  };
    
    public static final String[] ITEMTYPE = {  
                                                "剑",
                                                "刀",
                                                "枪",
                                                "斧",
                                                "弓",
                                                "杖",
                                                "爪",
                                                "机械手臂",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "未知",
                                                "道具"  };
    
    public static final String[] CHAPTER = {"第一关",
                                            "第二关",
                                            "第三关",
                                            "第四关",
                                            "第五关",
                                            "第六关",
                                            "第七关",
                                            "第八关",
                                            "第九关",
                                            "第十关",
                                            "第十一关",
                                            "第十二关",
                                            "第十三关",
                                            "第十四关",
                                            "第十五关",
                                            "第十六关",
                                            "第十七关",
                                            "第十八关",
                                            "第十九关",
                                            "第二十关",
                                            "第二十一关",
                                            "第二十二关",
                                            "第二十三关",
                                            "第二十四关",
                                            "第二十五关",
                                            "第二十六关",
                                            "第二十七关",
                                            "第二十八关",
                                            "第二十九关",
                                            "第三十关"  };

}
