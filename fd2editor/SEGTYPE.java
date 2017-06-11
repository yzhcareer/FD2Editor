/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;

/**
 * 数据字节段的Enum
 * @param: Segment 类实例
 * @author CIDER
 */
public enum SEGTYPE {
    /** 单字节, 未使用的数据 */
    未使用(SegUnused.class),
    /* 开关类型数据 */
    /** 单字节, 用于开关字节 */
    开关(SegFlip.class),
    /** 单字节, 用于选择数值型字节 */
    选择(SegSelector.class),
    /* 物品类型数据段, 每个纪录23 bytes */
    /** 单字节, 物品名称 **/
    物品(SegItem.class),
    /** 单字节, 物品的类别, 剑, 刀, 枪, 斧... ...道具 */
    物品类型(SegItemType.class),
    /** 双字节, 攻击力或者魔法伤害 */
    攻击(SegAP.class),
    /** 双字节, 攻击命中 */
    命中(SegHT.class),
    /** 双字节, 防御力 */
    防御(SegDP.class),
    /** 双字节, 速度 */
    速度(SegEV.class),
    /** 单字节, 武器攻击的附加效果 */
    附加属性(SegWeaponEff.class),
    /** 单字节, 武器附加效果或者魔法击中几率 */
    命中率(SegHitChance.class),
    /** 双字节, 武器的攻击范围, 最小值/最大值 */
    攻击距离(SegAttRange.class),
    /** 三字节, 物品使用效果, 如果是攻击法术, 则后两字节是法术编号, 否则后两字节是使用效果的数值 */
    使用效果(SegItemEffect.class),
    /** 单字节, 高位1表示直线,否则是菱形范围,低四位元表示实际半径 */
    施放范围(SegEffectRange.class),  
    /** 单字节, 作用对象, 修改无效 */
    阵营(SegAlign.class),
    /** 单字节, 作用半径, 最大为3 */
    影响范围(SegRange.class),
    /** 双字节, 物品价格 */
    价格(SegPrice.class),
    // 未使用(1) x 2
    
    /* 法术类型数据段, 每个纪录7 bytes */
    // 攻击(2)
    // 几率(1)
    // 施放距离(1)
    // 影响范围(1, 3)
    /** 单字节, 法力消耗值 */
    法力消耗(SegMPCost.class),
    // 作用对象(1, NAMELIST.ALIGNMENT)
    
    /* 人物数据段, 每个纪录24 bytes */
    /** 单字节, 角色种族 */
    种族(SegRace.class),
    /** 单字节, 角色职业 */
    职业(SegClass.class),
    /** 单字节, 角色出场等级, 有符号, 最大127 */
    等级(SegLevel.class),
    /** 双字节, 角色出场生命值 */
    生命(SegHP.class),
    /** 双字节, 角色出场法力值 */
    法力(SegMP.class),
    /** 单字节, 角色出场移动力 */
    移动(SegMV.class),
    /** 四字节, 角色出场已知魔法, 用bit位表示 */
    已知魔法(SegKnownMagic.class),
    // 物品(1) x 6
    // 未使用(1) x 6
    
    /* 商店属性数据段, 武器店 x 12, 道具店 x 8, 神秘商店 x 8 */
    // 物品(1) x 12
    // 物品(1) x 8
    // 物品(1) x 8
    
    /* 升级属性数据段, 每个纪录11 bytes */
    /** 双字节, 攻击增长最小/最大值 */
    攻击增长(SegAPInc.class),
    /** 双字节, 防御增长最小/最大值 */
    防御增长(SegMPInc.class),
    /** 双字节, 速度增长最小/最大值 */
    速度增长(SegEVInc.class),
    /** 双字节, 生命增长最小/最大值 */
    生命增长(SegHPInc.class),
    /** 双字节, 法力增长最小/最大值 */
    法力增长(SegMPInc.class),
    /** 单字节, 可学习法术列表 */
    法术序列(SegMagicSeq.class),
    
    /* 法术学习数据段, 每个纪录12 bytes */
    // 等级(1)
    /** 单字节, 法术名称 */
    法术(SegMagic.class),
    // repeat 6 times
    
    /* 职业魔防数据段, 每个纪录4 bytes */
    /** 四字节, 应该分火抗, 电抗, 地抗, 魔抗, (10-x)/10 比如 7 表示 30% 抗性 */
    魔防(SegMagicResist.class),
    
    /* 职业爆击率数据段, 每个纪录1 bytes */
    //几率(1)
    
    /* 敌军等级数据段, 每个纪录10 bytes */
    // 种族(1, NAMELIST.RACE),
    // 职业(1, NAMELIST.CLASS),
    /** 单字节, 敌人每级生命增长 */
    敌体增长(SegEnemyHPInc.class),
    /** 单字节, 敌人每级法力增长 */
    敌法增长(SegEnemyMPInc.class),
    /** 单字节, 敌人每级攻击增长 */
    敌攻增长(SegEnemyAPInc.class),
    /** 单字节, 敌人每级防御增长 */
    敌防增长(SegEnemyDPInc.class),
    /** 单字节, 敌人每级速度增长 */
    敌速增长(SegEnemyEVInc.class),
    // 移动(1)
    /** 单字节, 敌人对应经验值 */
    敌经验(SegEnemyExp.class),
    
    /* 敌军出场数据段, 每个纪录26 bytes */
    /** 单字节, 敌军友军我军 */
    //阵营(),
    /** 单字节, 肖像 */
    肖像(SegPotrait.class),
    // 种族(1,  NAMELIST.RACE)
    // 职业(1, NAMELIST.CLASS)
    // 等级(1, 127)
    // 物品(1, NAMELIST.ITEM) x 8
    // 法术(1, NAMELIST.MAGIC) x 8
    /** 单字节, 出场时的回合数 */
    回合(SegRound.class),
    /** 四字节, 第一字节是0则是物品,1则是金钱, 后三字节, 如果是物品则第一字节是编号, 如果是金钱则是数目 */
    掉落(SegDrop.class);
    
    private final Class<?> newSeg;
    
    private SEGTYPE(Class<?> nSeg){
        this.newSeg = nSeg;
    }
    
    private SEGTYPE(){
        this.newSeg = SegUnused.class;
    }
   
    public BaseSeg createSeg(){
        try{
            return (BaseSeg)this.newSeg.newInstance();
        } catch(IllegalAccessException | InstantiationException e) {
            System.out.println("没有办法建立新实例!");
            return null;
        }
    }
}
