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
    
    物品(NAMELIST.FD2FILE, 0x792C1L, NAMELIST.ITEM, SEGTYPE.物品类型,
            SEGTYPE.攻击, SEGTYPE.命中, SEGTYPE.防御, SEGTYPE.速度, SEGTYPE.附加属性,
            SEGTYPE.命中率, SEGTYPE.攻击距离, SEGTYPE.使用效果, SEGTYPE.施放范围,
            SEGTYPE.作用对象, SEGTYPE.影响范围, SEGTYPE.价格, SEGTYPE.未使用, SEGTYPE.未使用),
    
    商店(NAMELIST.FD2FILE, 0x7B3A4L, Arrays.copyOfRange(NAMELIST.CHAPTER, 1, 22), SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, 
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品),
    
    法术(NAMELIST.FD2FILE, 0x7AA11L, NAMELIST.MAGIC, SEGTYPE.攻击, SEGTYPE.命中率, SEGTYPE.施放范围,
            SEGTYPE.影响范围, SEGTYPE.法力消耗, SEGTYPE.作用对象),
    
    角色(NAMELIST.FD2FILE, 0x7ADB5L, NAMELIST.ROLE, SEGTYPE.种族, SEGTYPE.职业, SEGTYPE.等级, SEGTYPE.生命,
            SEGTYPE.法力, SEGTYPE.移动, SEGTYPE.已知魔法, SEGTYPE.物品, SEGTYPE.物品,
            SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.物品, SEGTYPE.未使用, 
            SEGTYPE.未使用, SEGTYPE.未使用, SEGTYPE.未使用, SEGTYPE.未使用, SEGTYPE.未使用),
    
    
    ;
    

    private final String fileName;
    private final long fileOffset;
    private final String[] nameList;
    private final SEGTYPE[] segList;
    
    private RECORDTYPE(String fName, long fOffset, String[] nList, SEGTYPE... sList) {
        this.fileName = fName;
        this.fileOffset = fOffset;
        this.nameList = nList;
        this.segList = sList;
    }
    
    public String getFileName(){
        return this.fileName;
    }
    
    public long getFileOffset(){
        return this.fileOffset;
    }
    
    public String[] getNameList(){
        return this.nameList.clone();
    }
    
    public SEGTYPE[] getSegList(){
        return this.segList;
    }
 
}
