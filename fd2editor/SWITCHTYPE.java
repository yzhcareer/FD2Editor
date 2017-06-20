/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
/**
 * 所有的开关地址
 * @author CIDER
 */


public enum SWITCHTYPE {
    罗德曼加入(NAMELIST.FD2FILE, new String[] {"0x4A84B", "0D", "13"}),
    行走加速(NAMELIST.FD2FILE, new String[] {null, "FCFFFF6804", "FCFFFFC304"},
                              new String[] {null, "E8A741", "EB0341"}),
    文字加速(NAMELIST.FD2FILE, new String[] {null, "E85414", "EB0315"}),
    回合加速(NAMELIST.FD2FILE, new String[] {"0x453DA", "E8DE86", "EB0386"},
                              new String[] {"0x452F8", "E8C087", "EB0387"},
                              new String[] {"0x454DB", "E8DD85", "EB0385"}), 
    游标加速(NAMELIST.FD2FILE, new String[] {null, "E8714D", "EB034D"},
                              new String[] {null, "E8384D", "EB034D"}),
    战斗加速(NAMELIST.FD2FILE, new String[] {null, "C5FFFF6804", "C5FFFFC304"}),
    最小成长(NAMELIST.FD2FILE, new String[] {"0x44559", "01", "00"}),
    最大成长(NAMELIST.FD2FILE, new String[] {null, "740EE8A4", "EB0EE8A4"}),
    无限法术(NAMELIST.FD2FILE, new String[] {"0x436BA", "C67C19B8", "C69090B8"}),
    秘密商店(NAMELIST.FD2FILE, new String[] {"0x4C535", "04", "05"},
                              new String[] {"0x4C55E", "04", "05"}),
    中途存档(NAMELIST.FD2FILE, new String[] {"0x3D883", "7523C705", "9090C705"}),
    无限行动(NAMELIST.FD2FILE, new String[] {null, "8075080F", "8090900F"}),
    再次行动(NAMELIST.FD2FILE, new String[] {null, "80C36808", "00C36808"}),
    控制敌人(NAMELIST.FD2FILE, new String[] {null, "750EF6", "9090F6"}),
    恢复无效(NAMELIST.FD2FILE, new String[] {null, "909031", "740631"}),
    防御无效(NAMELIST.FD2FILE, new String[] {null, "02EB04", "020F8E"}),
    无限宝箱(NAMELIST.FD2FILE, new String[] {null, "C680D840000001", "C680D840000000"}),
    莱汀状态(NAMELIST.FD2FILE, new String[] {null, "E897DDFEFF6A0BE82FDBFEFF83C4046A06E825DBFEFF83C404",
                                                  "6A0BE834DBFEFF83C4046A06E82ADBFEFF83C404E883DDFEFF"}),
    拿全神器(NAMELIST.FD2FILE, new String[] {null, "023A00023A00023A00023A00023A",
                                                  "001D00002B00003300003D000047"}),
    经验上限(NAMELIST.FD2FILE, new String[] {null, "637E0AC7", " 63EB0AC7"}),
    领悟之书(NAMELIST.FD2FILE, new String[] {null, "6000005C", "6000005B"}),

    ;
    private final String fileName;
    private final int switchNum;
    private final String[][] address;


    private SWITCHTYPE(String fName, String[]... address){
        this.fileName = fName;
        this.switchNum = address.length;
        this.address = address;
    }
    
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
