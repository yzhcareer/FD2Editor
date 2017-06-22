/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.util.EnumSet;

/**
 * 所有的开关地址
 * @author CIDER
 */

public enum FLIPTYPE {
    罗德曼加入(NAMELIST.FD2FILE, new String[] {"0x4A84B", "0D", "13"}),
    行走加速(NAMELIST.FD2FILE, new String[] {"0x0039471", "FCFFFF6804", "FCFFFFC304"},
                              new String[] {"0x0039911", "E8A741", "EB0341"}),
    文字加速(NAMELIST.FD2FILE, new String[] {"0x003C562", "6A01E85415", "6A01EB0315"}),
    回合加速(NAMELIST.FD2FILE, new String[] {"0x453DA", "E8DE86", "EB0386"},
                              new String[] {"0x452F8", "E8C087", "EB0387"},
                              new String[] {"0x454DB", "E8DD85", "EB0385"}),
    回合动画(NAMELIST.FD2FILE, new String[] {"0x0040536", "83C4086A52E8A0", "83C4EB0352E8A0"},
                              new String[] {"0x00405E0", "83C4086A50E8F6", "83C4EB1F50E8F6"}),
    游标加速(NAMELIST.FD2FILE, new String[] {"0x0038D47", "E8714D", "EB034D"},
                              new String[] {"0x0038D80", "E8384D", "EB034D"}),
    战斗加速(NAMELIST.FD2FILE, new String[] {"0x003DABA", "C5FFFF6804", "C5FFFFC304"}),
    最小成长(NAMELIST.FD2FILE, new String[] {"0x0044559", "01", "00"}),
    最大成长(NAMELIST.FD2FILE, new String[] {"0x004455C", "740EE8A4", "EB0EE8A4"}),
    无限法术(NAMELIST.FD2FILE, new String[] {"0x00436BA", "C67C19B8", "C69090B8"}),
    秘密商店(NAMELIST.FD2FILE, new String[] {"0x004C535", "04", "05"},
                              new String[] {"0x004C55E", "04", "05"}),
    中途存档(NAMELIST.FD2FILE, new String[] {"0x003D883", "7523C705", "9090C705"}),
    无限行动(NAMELIST.FD2FILE, new String[] {"0x003792E", "8075080F", "8090900F"}),
    控制敌人(NAMELIST.FD2FILE, new String[] {"0x0037929", "750EF6", "9090F6"}),
    恢复无效(NAMELIST.FD2FILE, new String[] {"0x003A024", "909031", "740631"}),
    防御无效(NAMELIST.FD2FILE, new String[] {"0x003A5A2", "02EB04", "020F8E"}),
    无限宝箱(NAMELIST.FD2FILE, new String[] {"0x003F25F", "C6040701", "C6040700"},
                             new String[] {"0x003F507", "C6040701", "C6040700" }),
    莱汀状态(NAMELIST.FD2FILE, new String[] {"0x004977E", "E897DDFEFF6A0BE82FDBFEFF83C4046A06E825DBFEFF83C404",
                                                        "6A0BE834DBFEFF83C4046A06E82ADBFEFF83C404E883DDFEFF"}),
    拿全神器(NAMELIST.FIELDFILE, new String[] {"0x002D900", "023A00023A00023A00023A00023A",
                                                  "001D00002B00003300003D000047"}),
    经验上限(NAMELIST.FD2FILE, new String[] {"0x0037972", "637E0AC7", "63EB0AC7"}),
    领悟之书(NAMELIST.FIELDFILE, new String[] {"0x001AC72", "6000005C", "6000005B"}),

    ;


    private final String fileName;
    private final int switchNum;
    private final String[][] address;

    private FLIPTYPE(String fName, String[]... address){
        this.fileName = fName;
        this.switchNum = address.length;
        this.address = address;
    }
    public static final EnumSet <FLIPTYPE> FD2FLIPS = EnumSet.of(罗德曼加入,
            行走加速,
            文字加速,
            回合加速,
            回合动画,
            游标加速,
            战斗加速,
            最小成长,
            最大成长,
            无限法术,
            秘密商店,
            中途存档,
            无限行动,
            控制敌人,
            恢复无效,
            防御无效,
            无限宝箱,
            莱汀状态,
            经验上限);

    public static final EnumSet <FLIPTYPE> FDFIELDFLIPS = EnumSet.of(拿全神器, 领悟之书);

    public final String getFileName(){
            return this.fileName;
        }
    
    public final int getLength(){
            return this.switchNum;
        }
    
    public final String[][] getAddress(){
            return this.address;
        }
}
