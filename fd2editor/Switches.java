/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * 所有的开关类
 * @author CIDER
 */
public class Switches {
    /** 文件名 */
    private String fileName;
    /** 开关名 */
    private String switchName;
    /** 数据缓冲区 */
    private MappedByteBuffer fileBuffer;
    /** 字段说明 */
    public enum ADDRESSKEY {地址, 关闭, 开启};
    /** 开关字段 */
    private ArrayList<BaseSeg> flipSegs;
    /** 开关字段的数据结构, 用于格式化输出 */
    private ArrayList<FlipTuple> flipTuples;

    /**
     * 构造器
     * @param sType: SWITCHTYPE纪录
     * @param fBuffer: 数据缓冲区 
     */
    public Switches(SWITCHTYPE sType, MappedByteBuffer fBuffer){
        this.setFileName(sType.getFileName());
        this.setSwitchName(sType.name());
        this.linkBuffer(fBuffer); 
        this.initFlips(sType.getAddress());
        this.initSegs();
    }
    
    /**
     * 初始化所有的开关, 或者有偏移地址的, 或者没有偏移地址则由字节数值在文件里面搜索
     * @param fAddress: 字符串形式的开关数据
     */
    public final void initFlips(String[][] fAddress){
        int length = fAddress.length;
        flipTuples = new ArrayList<>();
        for(int i=0;i<length;i++){
            if(fAddress[i][0] == null){
                // no address provided, search the file for the offset of offValues;
                for(int oneAddress: searchHexCodes(this.fileBuffer, fAddress[i][1])){
                    FlipTuple newTuple = new FlipTuple(oneAddress, 
                                                       string2Bytes(fAddress[i][1]),
                                                       string2Bytes(fAddress[i][2]));
                    flipTuples.add(newTuple); 
                }
                //s earch the file for the offset of onValues;
                for(int oneAddress: searchHexCodes(this.fileBuffer, fAddress[i][2])){
                    FlipTuple newTuple = new FlipTuple(oneAddress, 
                                                       string2Bytes(fAddress[i][1]),
                                                       string2Bytes(fAddress[i][2]));
                    flipTuples.add(newTuple); 
                }
            } 
            // address provided, add the new flip
            else {FlipTuple newTuple = new FlipTuple(Long.parseLong(fAddress[i][0]), 
                                                       string2Bytes(fAddress[i][1]),
                                                       string2Bytes(fAddress[i][2]));
                    flipTuples.add(newTuple);   
            }    
        }   
    }
    
    /**
     * 由建立的开关单元列表简历开关字节列表, 对比开和关两个字节列表里不同的字节
     */
    public final void initSegs(){
        this.flipSegs = new ArrayList<>();
        int count = 0;
        for(FlipTuple flip: this.getFlipTuples()){
            for(int i=0;i<flip.getByteLength();i++){
                if(flip.getOffValue()[i] != flip.getOnValue()[i]){
                    BaseSeg fSeg = new SegFlip(this.getFileName(),
                                               this.getBuffer(),
                                               this.getSwitchName(),
                                               String.format("%02d", ++count),
                                               flip.getFileOffset() + i,
                                               0);
                    fSeg.setDefaultValue((int)flip.getOffValue()[i] & 0xFF);
                    fSeg.setRecommendValue((int)flip.getOnValue()[i] & 0xFF);
                    fSeg.read();
                    this.flipSegs.add(fSeg);
                }
            }
        }
    }
    /**
     * 输出开关类的文档
     * @return StringBuilder: 开关的输出格式
     */
    public final StringBuilder switchDoc(){
        StringBuilder sDoc = new StringBuilder(this.switchName);
        sDoc.append(":\n");
        flipTuples.forEach((flip)->{
            sDoc.append(ADDRESSKEY.地址.name()).append(": ");
            sDoc.append(String.format("0x%07X\n", flip.getFileOffset()));
            sDoc.append(ADDRESSKEY.关闭.name()).append(": ");
            sDoc.append(Arrays.toString(bytes2String(flip.getOffValue())));
            sDoc.append("\n");
            sDoc.append(ADDRESSKEY.开启.name()).append(": ");
            sDoc.append(Arrays.toString(bytes2String(flip.getOnValue())));
            sDoc.append("\n");
        });
        return sDoc;
    }
    
    /**
     * 把纪录中的各个字段输出成表格模式
     * @return StringBuilder: 表格模式的字符串
     */
    public final StringBuilder segString(){
        int tWidth = BaseSeg.getTitleWidth();
        int length = flipSegs.size();
        StringBuilder dString = new StringBuilder(BaseSeg.alignString(switchName, 
                                                                      BaseSeg.ALIGN.CENTER,
                                                                      tWidth, '_'));
        LinkedHashMap<String, String> [] docArray = new LinkedHashMap[length];
        for (int i=0; i<length; i++){
            docArray[i] = flipSegs.get(i).alignedDocStringMap();
        }
        if(length == 0 || docArray[0].isEmpty()) {
            dString.append(":  The Switch is Empty!");
        } else {
            flipSegs.forEach((bSeg) -> {
                dString.append(bSeg.alignString(bSeg.getSegName(), BaseSeg.ALIGN.CENTER, '_'));
            });
            dString.append("\n");
            docArray[0].keySet().forEach((keyString) -> {
                dString.append(keyString);
                for (LinkedHashMap dMap: docArray){
                    dString.append(dMap.get(keyString));
                }
                dString.append("\n");
            });
        }
        dString.append("\n");
        return dString;
    }
    
    @Override
    public String toString(){
        return switchDoc().append("\n").append(segString()).toString();
    }
    
    /**
     * 所有flip设成开
     */
    public final void turnON(){
        this.flipSegs.forEach((flip)->{
            flip.optimize();
        });
    }
    
    /**
     * 所有flip设成关
     */
    public final void turnOFF(){
        this.flipSegs.forEach((flip)->{
            flip.reset();
        });  
    }
    
    /**
     * 把设定的flip状态写回
     */
    public final void write(){
        this.flipSegs.forEach((flip)->{
            flip.write();
        });   
    }
    
    /**
     * 静态方法,从一个字节缓冲区里查找给定的byteArray
     * @param fBuffer: 字节缓冲区
     * @param hexString: 字符串形式的byteArray
     * @return Integer[]: 找到的所有地址
     */
    
    public static final Integer[] searchHexCodes(MappedByteBuffer fBuffer, String hexString){
        ArrayList<Integer> foundPos = new ArrayList<>();
        byte[] hexCodes = string2Bytes(stringParse(hexString));
        int byteLength = hexCodes.length;
        byte[] readCodes = new byte[byteLength];
        for(int i=0;i<fBuffer.capacity()-byteLength;i++){
            fBuffer.position(i);
            fBuffer.get(readCodes);
            if(Arrays.equals(hexCodes, readCodes)){
                foundPos.add(i);
            }
        }
        Integer[] address = foundPos.toArray(new Integer[foundPos.size()]);    
        return address;
    }
    
    /**
     * 静态方法, 字符串转换为字符串形式的字节数组
     * @param bString: 十六进制字符串
     * @return String[]: 字符串字节数组
     */
    public static final String[] stringParse(String bString){
        bString = bString.replaceAll("^0x|^0X|[^0-9a-fA-F ]", "");
        int length = (int)Math.ceil(bString.length()/2.0);
        String hexString = String.format("%"+length*2+"s", bString).replace(" ", "0");
        String[] hexCodes = new String[length];
        for(int i = length-1; i>=0;i--){
            hexCodes[i] = hexString.substring(i*2, i*2+2);
        }     
        return hexCodes;     
    }
    
    /**
     * 静态方法, 字符串Array转换为字节数组
     * @param hCodes: 字符串Array
     * @return byte[]: 字节数组
     */
    public static final byte[] string2Bytes(String[] hCodes){
        byte[] hexBytes = new byte [hCodes.length];
        for(int i=0;i<hCodes.length;i++){
            hexBytes[i] = (byte)Integer.parseInt(hCodes[i], 16);
        }
        return hexBytes;
    }
    
    /**
     * 静态方法, 字节数组变成字符串数组
     * @param hBytes: 字节数组
     * @return String[]: 字符串数组
     */
    public static final String[] bytes2String(byte[] hBytes){
        String[] hexCodes = new String [hBytes.length];
        for(int i=0;i<hBytes.length;i++){
            hexCodes[i] = String.format("%02X", hBytes[i]);
        }
        return hexCodes;
    }
    
    /**
     * 静态方法, 字符串转换为字节数组
     * @param hString:  十六进制字符串
     * @return byte[]: 字节数组
     */
    public static final byte[] string2Bytes(String hString){
        return string2Bytes(stringParse(hString));
    }
    
    /** 
     * 链接数据缓冲区 
     * @param fBuffer: 缓冲区指针 
     */
    public final void linkBuffer(MappedByteBuffer fBuffer){
        this.fileBuffer = fBuffer;
    }

    /** 
     * 获取数据缓冲区
     * @return MappedByteBuffer: 缓冲区指针 
     */
    public final MappedByteBuffer getBuffer(){
        return this.fileBuffer;
    }
    
    /** 胶水方法:文件名
     * @return String: 返回文件名 
     */
    public final String getFileName(){
        return this.fileName;
    }
   
    /** 胶水方法:文件名
     * @param fName: 设定文件名 
     */
    public final void setFileName(String fName){
        this.fileName = fName;
    }  
    
    /** 胶水方法:开关名
     * @return String: 返回开关名 
     */
    public final String getSwitchName(){
        return this.switchName;
    }
   
    /** 胶水方法:文件名
     * @param sName: 设定开关名 
     */
    public final void setSwitchName(String sName){
        this.switchName = sName;
    } 
    /** 胶水方法:flip列表
     * @return FlipTuple[]: flipTuple的列表
     */ 
    public final FlipTuple[] getFlipTuples(){
        return this.flipTuples.toArray(new FlipTuple[this.flipTuples.size()]);
    }
    
    /** 胶水方法:flipSeg列表
     * @return SegFlip[]: flipSeg的列表
     */ 
    public final BaseSeg[] getSegFlips(){
        return this.flipSegs.toArray(new BaseSeg[this.flipSegs.size()]);
    }
}
