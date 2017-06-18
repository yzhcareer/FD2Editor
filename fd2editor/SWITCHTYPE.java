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
