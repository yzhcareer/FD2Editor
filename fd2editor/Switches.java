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
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 所有的开关类
 * @author CIDER
 */
public abstract class Switches {
    /** 文件名 */
    private String fileName;
    /** 开关名 */
    private String switchName;
    /** 数据缓冲区 */
    private MappedByteBuffer fileBuffer;
    /** 字段说明 */
    public enum ADDRESSKEY {地址, 关闭, 开启};
    /** 开关字段 */
    private ArrayList<BaseSeg> switchSegs;
    /** 开关字段的数据结构, 用于格式化输出 */
    private ArrayList<SwitchTuple> swtichTuples;

    /**
     * SWITCHTYPE构造器
     * @param sType: SWITCHTYPE纪录
     * @param fBuffer: 数据缓冲区 
     */
    public Switches(FLIPTYPE sType, MappedByteBuffer fBuffer){
        this.setFileName(sType.getFileName());
        this.setSwitchName(sType.name());
        this.linkBuffer(fBuffer); 
    }
    
    /**
     * SELECTORTYPE构造器
     * @param sType: SWITCHTYPE纪录
     * @param fBuffer: 数据缓冲区 
     */
    public Switches(SELECTTYPE sType, MappedByteBuffer fBuffer){
        this.setFileName(sType.getFileName());
        this.setSwitchName(sType.name());
        this.linkBuffer(fBuffer); 
    }
    
    /**
     * 初始化所有的开关, 或者有偏移地址的, 或者没有偏移地址则由字节数值在文件里面搜索
     * 不把fAddress传入到类属性, 不必担心被改写
     * @param fAddress: 字符串形式的开关数据
     */
    public abstract void initSwitches(String[][] fAddress);

    /**
     * 由建立的开关单元列表简历开关字节列表, 对比开和关两个字节列表里不同的字节
     */
    public abstract void initSegs();
    
    /**
     * 静态方法,从一个字节缓冲区里查找给定的byteArray
     * @param hexString: 字符串形式的byteArray
     * @return Integer[]: 找到的所有地址
     */
    //public abstract Integer[] searchHexCodes(String hexString);

    /**
     * 搜素两个地址字符串中的开关位置
     * @param oneAddress: 地址字符串的Array
     * @return: 找到位置的Array
     */
    public abstract Integer[] getByteLocs(String[] oneAddress);

    /**
     * 输出开关类的文档
     * @return StringBuilder: 开关的输出格式
     */
    public final StringBuilder switchDoc(){
        StringBuilder sDoc = new StringBuilder(this.switchName);
        sDoc.append(":\n");
        swtichTuples.forEach((flip)->{
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
        int length = switchSegs.size();
        StringBuilder dString = new StringBuilder(BaseSeg.alignString(switchName, 
                                                                      BaseSeg.ALIGN.CENTER,
                                                                      tWidth, '_'));
        ArrayList<LinkedHashMap<String, String>>  docArray = new ArrayList<>();
        for (int i=0; i<length; i++){
            docArray.add(switchSegs.get(i).alignedDocStringMap());
        }
        if(length == 0 || docArray.get(0).isEmpty()) {
            dString.append(":  The Switch is Empty!");
        } else {
            switchSegs.forEach((bSeg) -> {
                dString.append(bSeg.alignString(bSeg.getSegName(), BaseSeg.ALIGN.CENTER, '_'));
            });
            dString.append("\n");
            docArray.get(0).keySet().forEach((keyString) -> {
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
     * 把设定的flip状态写回
     */
    public final void write(){
        this.switchSegs.forEach(BaseSeg::write);
    }
    
    /**
     * 把设定的flip状态读出
     */
    public final void read(){
        this.switchSegs.forEach(BaseSeg::read);
    }

    /**
     * 判断一个数字地址是不是已经在地址Array里
     * @param address： 数字表示的偏移地址
     * @return boolean： 在不在
     */
    public boolean notInTuples(long address){
        for (SwitchTuple sTuple : getSwitchTuples()) {
            if (sTuple.getFileOffset() == address) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断一个字符串地址是不是已经在地址Array里
     * @param address： 字符串表示的偏移地址
     * @return boolean： 在不在
     */
    public boolean notInTuples(String address){
        return notInTuples(Long.parseLong(address.replaceAll("^0X|0x", ""), 16));
    }

    /**
     * 判断一个地址是不是已经在地址Array里
     * @param sTuple： 要检查的地址Tuple
     * @return boolean： 在不在
     */
    public boolean notInTuples(SwitchTuple sTuple){
        return notInTuples(sTuple.getFileOffset());
    }
    
    /**
     * 静态方法, 字符串转换为字符串形式的字节数组
     * @param bString: 十六进制字符串
     * @return String[]: 字符串字节数组
     */
    public static final String[] string2Array(String bString){
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
     * 静态方法整理一个hexString, 保留??统配符号并转化为"\d\d"
     * @param hexString: 输入的字符串
     * @return String: 整理好的字符串
     */
    public static final String parseString(String hexString){
        Pattern sPattern = Pattern.compile("(?:0[xX])?([0-9a-fA-F]*(\\?\\?)*)+");
        Matcher sMatch = sPattern.matcher(hexString);
        StringBuilder sString = new StringBuilder();
        while(sMatch.find()){
            sString.append(String.valueOf(sMatch.group()));
        }
        String parsedString = sString.toString().replaceAll("0x|0X", "");
        int byteLength = (int)Math.ceil(parsedString.length()/2.0);
        return String.format("%" + byteLength*2 + "s", parsedString).replace(" ", "0");
    }
    
    /**
     * 静态方法, 字符串转换为字节数组
     * @param hString:  十六进制字符串
     * @return byte[]: 字节数组
     */
    public static final byte[] string2Bytes(String hString){
        return string2Bytes(string2Array(hString));
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
    
    /** 胶水方法:switch列表
     * @return SwitchTuple[]: switchTuple的列表
     */ 
    public final SwitchTuple[] getSwitchTuplesAsArray(){
        return this.swtichTuples.toArray(new SwitchTuple[this.swtichTuples.size()]);
    }
    
    /** 胶水方法:switch列表
     * @return SwitchTuple[]: switchTuple的列表
     */ 
    public final ArrayList<SwitchTuple> getSwitchTuples(){
        return this.swtichTuples;
    }
    
    /** 胶水方法:switch列表
     * @param sTuples: 传入的Switchuple Array
     */ 
    public final void setSwitchTuples(ArrayList<SwitchTuple> sTuples){
        this.swtichTuples = sTuples;
    }
    
    /** 胶水方法:switchSeg列表
     * @return BaseSeg[]: switchSeg的列表
     */ 
    public final BaseSeg[] getSwitchSegsAsArray(){
        return this.switchSegs.toArray(new BaseSeg[this.switchSegs.size()]);
    }
    
    /** 胶水方法:switchSeg列表
     * @return BaseSeg[]: switchSeg的列表
     */ 
    public final ArrayList<BaseSeg> getSwitchSegs(){
        return this.switchSegs;
    }
    
    /** 胶水方法:switchSeg列表
     * @param sSegs: switchSeg的列表
     */ 
    public final void setSwitchSegs(ArrayList<BaseSeg> sSegs){
        this.switchSegs = sSegs;
    }
}





class Flips extends Switches{
    
    public Flips(FLIPTYPE sType, MappedByteBuffer fBuffer){
        super(sType, fBuffer);
        this.initSwitches(sType.getAddress());
        this.initSegs();
    }
    
    /**
     * 初始化所有的开关, 或者有偏移地址的, 或者没有偏移地址则由字节数值在文件里面搜索
     * 不把fAddress传入到类属性, 不必担心被改写
     * @param fAddress: 字符串形式的开关数据
     */
    @Override
    public final void initSwitches(String[][] fAddress){
        setSwitchTuples(new ArrayList<>());
        Integer[] byteLoc;
        for(String[] oneAddress: fAddress){
            byteLoc = getByteLocs(oneAddress);
            if(oneAddress[0] == null){
                // no address provided, search the file for the offset of offValues;
                for(int onePos: searchHexCodes(oneAddress[1])){
                    if(notInTuples(onePos)) {
                        getSwitchTuples().add(new SwitchTuple(onePos,
                                string2Bytes(oneAddress[1]),
                                string2Bytes(oneAddress[2]),
                                byteLoc));
                    }
                }
                // search the file for the offset of onValues;
                for(int onePos: searchHexCodes(oneAddress[2])){
                    if(notInTuples(onePos)) {
                        getSwitchTuples().add(new SwitchTuple(onePos,
                                string2Bytes(oneAddress[1]),
                                string2Bytes(oneAddress[2]),
                                byteLoc));
                    }
                }
            } 
            // address provided, add the new flip
            else {
                long numAddress = Long.parseLong(oneAddress[0].replaceAll("^0x|^0X", ""), 16);
                if (notInTuples(numAddress)) {
                    getSwitchTuples().add(new SwitchTuple(numAddress,
                            string2Bytes(oneAddress[1]),
                            string2Bytes(oneAddress[2]),
                            byteLoc));
                }
            }    
        }   
    }
    
    /**
     * 由建立的开关单元列表简历开关字节列表, 对比开和关两个字节列表里不同的字节
     */
    @Override
    public final void initSegs(){
        setSwitchSegs(new ArrayList<>());
        int count = 0;
        for(SwitchTuple flip: this.getSwitchTuplesAsArray()){
            for(int i=0;i<flip.getByteLocs().length;i++){
                BaseSeg fSeg = new SegFlip(this.getFileName(),
                                           this.getBuffer(),
                                           this.getSwitchName(),
                                           String.format("%02d", ++count),
                                           flip.getFileOffset() + flip.getByteLocs()[i],
                                           0);
                fSeg.setDefaultValue((int)flip.getOffValue()[flip.getByteLocs()[i]] & 0xFF);
                fSeg.setRecommendValue((int)flip.getOnValue()[flip.getByteLocs()[i]] & 0xFF);
                fSeg.read();
                this.getSwitchSegs().add(fSeg);
            }
        }
    }
    
    /**
     * 静态方法,从一个字节缓冲区里查找给定的byteArray
     * @param hexString: 字符串形式的byteArray
     * @return Integer[]: 找到的所有地址
     */
    public final Integer[] searchHexCodes(String hexString){
        ArrayList<Integer> foundPos = new ArrayList<>();
        byte[] hexCodes = string2Bytes(string2Array(hexString));
        int byteLength = hexCodes.length;
        byte[] readCodes = new byte[byteLength];
        for(int i=0;i<getBuffer().capacity()-byteLength;i++){
            getBuffer().position(i);
            getBuffer().get(readCodes);
            if(Arrays.equals(hexCodes, readCodes)){
                foundPos.add(i);
            }
        }
        return foundPos.toArray(new Integer[foundPos.size()]);
    }

    /**
     * 搜索两个字符串里等于??的位置
     * @param oneAddress: 地址字符串的tuple
     * @return Integer[]: 位置的Array
     */

    @Override
    public Integer[] getByteLocs(String[] oneAddress){
        ArrayList<Integer> byteLocs = new ArrayList<>();
        String[] off = string2Array(oneAddress[1]);
        String[] on = string2Array(oneAddress[2]);
        for(int i=0;i<Math.min(off.length, on.length);i++) {
            if(! on[i].equals(off[i])) {
                byteLocs.add(i);
            }
        }
        return byteLocs.toArray(new Integer[byteLocs.size()]);
    }
    
    /**
     * 所有flip设成开
     */
    public final void turnON(){
        getSwitchSegs().forEach(BaseSeg::optimize);
    }
    
    /**
     * 所有flip设成关
     */
    public final void turnOFF(){
        getSwitchSegs().forEach(BaseSeg::reset);
    }
}


class Selectors extends Switches {
    
    private int max;
    private int min;
      
    public Selectors(SELECTTYPE sType, MappedByteBuffer fBuffer){
        super(sType, fBuffer);
        this.setMax(sType.getMax());
        this.setMin(sType.getMin());
        this.initSwitches(sType.getAddress());
        this.initSegs();
    }


    /**
     * 从一个字节缓冲区里查找给定的byteArray, 可以带??通配符
     * @param hexString: 字符串形式的byteArray
     * @return Integer[]: 找到的所有地址
     */
   public final SwitchTuple[] searchHexCodes(String hexString){
       String parsedString = parseString(hexString);
       String arrayString;
       Pattern sPattern = Pattern.compile(parsedString.replaceAll("\\?", "[0-9a-fA-F]"));
       ArrayList<SwitchTuple> foundPos = new ArrayList<>();
       int byteLength = parsedString.length()/2;
       byte[] readCodes = new byte[byteLength];
       for(int i=0; i<getBuffer().capacity()-byteLength;i++){
           getBuffer().position(i);
           getBuffer().get(readCodes);
           arrayString = String.join("", bytes2String(readCodes));
           if(sPattern.matcher(arrayString).matches()){
               foundPos.add(new SwitchTuple(i, readCodes));
           }
       }
       return foundPos.toArray(new SwitchTuple[foundPos.size()]);
   }

    /**
     * 搜索两个字符串里等于??的位置
     * @param oneAddress: 地址字符串的tuple
     * @return Integer[]: 位置的Array
     */
    @Override
    public Integer[] getByteLocs(String[] oneAddress){
        ArrayList<Integer> byteLocs = new ArrayList<>();
        String off = parseString(oneAddress[1]);
        String on = parseString(oneAddress[2]);
        for(int i=0;i<Math.min(off.length(), on.length())/2;i++) {
            if(on.substring(i*2,i*2+2).equals("??")) {
             byteLocs.add(i);
            }
        }
        return byteLocs.toArray(new Integer[byteLocs.size()]);
    }
    
    /**
     * 初始化所有的开关, 或者有偏移地址的, 或者没有偏移地址则由字节数值在文件里面搜索
     * 不把fAddress传入到类属性, 不必担心被改写
     * @param fAddress: 字符串形式的开关数据
     */
    @Override
    public final void initSwitches(String[][] fAddress){
        int byteLength;
        byte[] readCodes;
        Integer[] byteLocs;
        setSwitchTuples(new ArrayList<>());
        for(String[] oneAddress: fAddress){
            byteLocs = getByteLocs(oneAddress);
            if(oneAddress[0] == null) {
                for(SwitchTuple onePos: searchHexCodes(oneAddress[1])){
                    if(notInTuples(onePos)) {
                        getSwitchTuples().add(new SwitchTuple(onePos.getFileOffset(),
                                string2Bytes(oneAddress[1]),
                                onePos.getOffValue(),
                                byteLocs));
                    }
                }
                for(SwitchTuple onePos: searchHexCodes(oneAddress[2])){
                    if(notInTuples(onePos)) {
                        getSwitchTuples().add(new SwitchTuple(onePos.getFileOffset(),
                                string2Bytes(oneAddress[1]),
                                onePos.getOffValue(),
                                byteLocs));
                    }
                }
            } else {
                int numAddress = Integer.parseInt(oneAddress[0].replaceAll("^0X|^0x", ""), 16);
                if(notInTuples(numAddress)) {
                    byteLength = parseString(oneAddress[1]).length()/2;
                    readCodes = new byte[byteLength];
                    getBuffer().position(numAddress);
                    getBuffer().get(readCodes);
                    getSwitchTuples().add(new SwitchTuple(numAddress,
                            string2Bytes(oneAddress[1]),
                            readCodes,
                            byteLocs));
                }
            }
        }
    }

    /**
     * 由建立的开关单元列表简历开关字节列表, 对比开和关两个字节列表里不同的字节
     */
    @Override
    public final void initSegs(){
        setSwitchSegs(new ArrayList<>());
        int count = 0;
        for(SwitchTuple flip: this.getSwitchTuplesAsArray()){
            for(int i=0;i<flip.getByteLocs().length;i++){
                BaseSeg sSeg = new SegSelector(this.getFileName(),
                        this.getBuffer(),
                        this.getSwitchName(),
                        String.format("%02d", ++count),
                        flip.getFileOffset() + flip.getByteLocs()[i],
                        0);
                sSeg.setDefaultValue((int)flip.getOffValue()[flip.getByteLocs()[i]] & 0xFF);
                sSeg.setRecommendValue((int)flip.getOnValue()[flip.getByteLocs()[i]] & 0xFF);
                sSeg.setMax(max);
                sSeg.setMin(min);
                sSeg.read();
                this.getSwitchSegs().add(sSeg);
                }
            }
    }

    /**
     * 给选择器赋值
     * @param mValue: 所有选择器赋一样的值
     */
    public final void setValue(int mValue){
        getSwitchSegs().forEach(sSeg-> sSeg.setValue(mValue));
    }

    /**
     * 给选择器赋值
     * @param mValue: 所有选择器赋不一样的值
     */
    public final void setValue(int... mValue){
        int count = 0;
        int length = mValue.length;
        for(BaseSeg sSeg: getSwitchSegs()){
            sSeg.setValue(mValue[count++]);
            if(count==length){
                count = 0;
            }
        }
    }

    /**
     * 胶水方法: 最大值
     * @param mValue: 最大值
     */
    public final void setMax(int mValue){
        this.max = mValue;
    }
    
    /**
     * 胶水方法: 最大值
     * @return int: 最小值
     */
    public final int getMax(){
        return this.max;
    }
    
    /**
     * 胶水方法: 最小值
     * @param mValue: 最大值
     */
    public final void setMin(int mValue){
        this.min = mValue;
    }
    
    /**
     * 胶水方法: 最大值
     * @return int: 最小值
     */
    public final int getMin(){
        return this.min;
    }
    
    /**
     * 设为优化值
     */
    public final void optimize(){
        getSwitchSegs().forEach(BaseSeg::optimize);
    }
    
    /**
     * 设为缺省值
     */
    public final void reset(){
        getSwitchSegs().forEach(BaseSeg::reset);
    }
}