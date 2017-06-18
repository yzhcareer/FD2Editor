/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;

import java.util.Arrays;

/**
 * 定义每个纪录的数据结构,包括文件,偏移地址,名称的列表,和构成字段的列表
 * @author CIDER
 */
public enum RECORDTYPE {
    
    物品(NAMELIST.FD2FILE, 0x792C1L, 4945, NAMELIST.ITEM, SEGTYPE.物品类型,
            SEGTYPE.攻击, SEGTYPE.命中, SEGTYPE.防御, SEGTYPE.速度, SEGTYPE.附加属性,
            SEGTYPE.命中率, SEGTYPE.攻击距离, SEGTYPE.使用效果, SEGTYPE.施放范围,
            SEGTYPE.阵营, SEGTYPE.影响范围, SEGTYPE.价格, SEGTYPE.未使用, SEGTYPE.未使用),
    
    商店(NAMELIST.FD2FILE, 0x7B3A4L, 644, Arrays.copyOfRange(NAMELIST.CHAPTER, 1, 24), SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品),
    
    法术(NAMELIST.FD2FILE, 0x7AA11L, 252, NAMELIST.MAGICNONULL, 
            SEGTYPE.攻击, SEGTYPE.命中率, SEGTYPE.施放范围, SEGTYPE.影响范围, SEGTYPE.法力消耗, SEGTYPE.阵营),
    
    角色(NAMELIST.FD2FILE, 0x7ADB5L, 768, NAMELIST.ROLE, SEGTYPE.种族, SEGTYPE.职业, SEGTYPE.等级, SEGTYPE.生命,
            SEGTYPE.法力, SEGTYPE.移动, SEGTYPE.已知魔法, SEGTYPE.物品, SEGTYPE.物品,
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.未使用, SEGTYPE.未使用, SEGTYPE.未使用, 
            SEGTYPE.未使用, SEGTYPE.未使用, SEGTYPE.未使用),
    
    成长(NAMELIST.FD2FILE, 0x7B0B5L, 726, NAMELIST.CLASSPATH, SEGTYPE.攻击增长, SEGTYPE.防御增长, SEGTYPE.速度增长,
            SEGTYPE.生命增长, SEGTYPE.法力增长, SEGTYPE.法术序列),
    
    法术序列(NAMELIST.FD2FILE, 0x7B6C7L, 240, NAMELIST.MAGICSEQ, SEGTYPE.等级, SEGTYPE.法术, SEGTYPE.等级, SEGTYPE.法术,
            SEGTYPE.等级, SEGTYPE.法术, SEGTYPE.等级, SEGTYPE.法术, SEGTYPE.等级, SEGTYPE.法术, SEGTYPE.等级, SEGTYPE.法术),
    
    魔抗(NAMELIST.FD2FILE, 0x76FAAL, 112, NAMELIST.CLASSNODRAGON, SEGTYPE.魔防, SEGTYPE.未使用, SEGTYPE.未使用, SEGTYPE.未使用),
    
    爆击(NAMELIST.FD2FILE, 0x773AF, 28, NAMELIST.CLASSNODRAGON, SEGTYPE.命中率),
    
    敌军(NAMELIST.FD2FILE, 0x7AB0D, 680, NAMELIST.ENEMY, SEGTYPE.种族, SEGTYPE.职业, SEGTYPE.敌体增长, SEGTYPE.敌法增长,
            SEGTYPE.敌攻增长, SEGTYPE.敌防增长, SEGTYPE.敌速增长, SEGTYPE.移动, SEGTYPE.敌经验),
    
    第一关(NAMELIST.FIELDFILE, 0x00B1D, 0, 31),
    第二关(NAMELIST.FIELDFILE, 0x01874, 0, 40),
    第三关(NAMELIST.FIELDFILE, 0x0256B, 0, 40),
    第四关(NAMELIST.FIELDFILE, 0x03158, 0, 40),
    第五关(NAMELIST.FIELDFILE, 0x0450F, 0, 50),
    第六关(NAMELIST.FIELDFILE, 0x05422, 0, 40),
    第七关(NAMELIST.FIELDFILE, 0x06953, 0, 40),
    第八关(NAMELIST.FIELDFILE, 0x08376, 0, 60),
    第九关(NAMELIST.FIELDFILE, 0x099E5, 0, 59),
    第十关(NAMELIST.FIELDFILE, 0x0B7E2, 0, 60),
    第十一关(NAMELIST.FIELDFILE, 0x0D8C9, 0, 40),
    第十二关(NAMELIST.FIELDFILE, 0x0F480, 0, 60),
    第十三关(NAMELIST.FIELDFILE, 0x10C7D, 0, 70),
    第十四关(NAMELIST.FIELDFILE, 0x12F20, 0, 70),
    第十五关(NAMELIST.FIELDFILE, 0x15FD9, 0, 80),
    第十六关(NAMELIST.FIELDFILE, 0x183C2, 0, 60),
    第十七关(NAMELIST.FIELDFILE, 0x1AC83, 0, 60),
    第十八关(NAMELIST.FIELDFILE, 0x1C874, 0, 70),
    第十九关(NAMELIST.FIELDFILE, 0x1E1BD, 0, 70),
    第二十关(NAMELIST.FIELDFILE, 0x20466, 0, 70),
    第二十一关(NAMELIST.FIELDFILE, 0x227AF, 0, 80),
    第二十二关(NAMELIST.FIELDFILE, 0x255C0, 0, 70),
    第二十三关(NAMELIST.FIELDFILE, 0x27909, 0, 70),
    第二十四关(NAMELIST.FIELDFILE, 0x29A66, 0, 70),
    第二十五关(NAMELIST.FIELDFILE, 0x2B8C3, 0, 70),
    第二十六关(NAMELIST.FIELDFILE, 0x2D930, 0, 70),
    第二十七关(NAMELIST.FIELDFILE, 0x2FE75, 0, 80),
    第二十八关(NAMELIST.FIELDFILE, 0x31CDE, 0, 60),
    第二十九关(NAMELIST.FIELDFILE, 0x3445F, 0, 76),
    第三十关(NAMELIST.FIELDFILE, 0x36B00, 0, 70),
    ;
    

    private final String fileName;
    private final long fileOffset;
    private final String[] nameList;
    private final SEGTYPE[] segList;
    private final int blockLength;
    private final SEGTYPE[] STAGESEGS = {SEGTYPE.阵营, SEGTYPE.肖像, SEGTYPE.种族, SEGTYPE.职业, SEGTYPE.等级, SEGTYPE.物品, 
                                         SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品,
                                         SEGTYPE.物品, SEGTYPE.法术, SEGTYPE.法术, SEGTYPE.法术, SEGTYPE.法术, SEGTYPE.法术, 
                                         SEGTYPE.法术, SEGTYPE.法术, SEGTYPE.法术, SEGTYPE.回合, SEGTYPE.掉落};
    
    private RECORDTYPE(String fName, long fOffset, int bLength, String[] nList, SEGTYPE... sList) {
        this.fileName = fName;
        this.fileOffset = fOffset;
        this.nameList = nList;
        this.blockLength = bLength;
        this.segList = sList;
    }
    
    private RECORDTYPE(String fName, long fOffset, int bLength, int length) {
        this.fileName = fName;
        this.fileOffset = fOffset;
        this.blockLength = bLength;
        this.nameList = new String[length];
        for(int i=0;i<length;i++){
            this.nameList[i] = String.valueOf(i);
        }
        this.segList = STAGESEGS.clone();
    }
    
    public String getFileName(){
        return this.fileName;
    }
    
    public long getFileOffset(){
        return this.fileOffset;
    }
    
    public int getBlockLength(){
        return this.blockLength;
    }
    
    public String[] getNameList(){
        return this.nameList.clone();
    }
    
    public SEGTYPE[] getSegList(){
        return this.segList.clone();
    }
 
}
