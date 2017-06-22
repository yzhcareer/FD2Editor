/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;

/**
 *
 * @author CIDER
 */

public enum SELECTTYPE {
    升级经验(NAMELIST.FD2FILE, 1, 255, new String[] {"0x0044402", "836C240464", "836C2404??"},
                                      new String[] {"0x0044428", "837C240464", "837C2404??"}),
    机兵上限(NAMELIST.FD2FILE, 1, 255, new String[] {"0x0044300", "63EB0383F8", "??EB0383F8"}),
    等级上限(NAMELIST.FD2FILE, 1, 255, new String[] {"0x0044303", "83F8280F84", "83F8??0F84"}),
    熾炎刀倍率(NAMELIST.FD2FILE, 1, 255, new String[] {"0x004D804", "B00000000C000000", "B0000000??000000"}),
    破龍擊倍率(NAMELIST.FD2FILE, 1, 255, new String[] {"0x004D7D6", "B00000000F000000", "B0000000??000000"}),
    音速刃倍率(NAMELIST.FD2FILE, 1, 255, new String[] {"0x004D811", "B000000012000000", "B0000000??000000"}),
    淒煌斬倍率(NAMELIST.FD2FILE, 1, 255, new String[] {"0x004D7ED", "B000000014000000", "B0000000??000000"}),
    ;
    
    private final String fileName;
    private final int min;
    private final int max;
    private final int selectNum;
    private final String[][] address;
    public final String UNKNOWNLOC = "??";
    
    private SELECTTYPE(String fName, int max, int min, String[]... address){
        this.fileName = fName;
        this.max = max;
        this.min = min;
        this.address = address;
        this.selectNum = address.length;
    } 
    
    public final String getFileName(){
            return this.fileName;
        }
    
    public final int getLength(){
            return this.selectNum;
        }
    
    public final String[][] getAddress(){
            return this.address;
        }
    
    public final int getMax(){
            return this.max;
        }
    
    public final int getMin(){
            return this.min;
        }
    
}
