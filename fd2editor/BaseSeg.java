/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;

import java.nio.MappedByteBuffer;
import java.util.LinkedHashMap;
import java.util.Arrays;

/**
 * 基本字段类, 对应数据段中的单字节,双字节,三字节和四字节变量
 * @author CIDER
 */
public abstract class BaseSeg {
    /** 四个属性会在子类中定义为static final, 并在子类中实现getter
     * 字段字节长度
     * 字段名称
     * 字段数据对应的名称列表
     * 字段输出宽度
     */
    // private int LENGTH;
    // private String SEGNAME;
    // private String[] NAMES;
    // private int COLWIDTH;
    
    /** 不同长度字节段允许的最大值, 转换到integer可以保证是无符号正值 */
    static final int[] MAXS = {0xFF, 0xFFFF, 0xFFFFFF, 0x7FFFFFFF};
    /** 未初始化提示信息 */
    static final String NOTINTIATED = "Not Initiated";
    /** 字段输出的打印对齐方式 */
    public static enum ALIGN {LEFT, RIGHT, CENTER};
    /** 字段选择关键字 */
    public static enum VALUE {RECOMMEND, DEFAULT, CURRENT};
    /** 标题栏宽度 */
    private static int TITLEWIDTH = 8;
    /** 输出宽度参数 */
    private static final int BYTEWIDTH = 4;
    private static final int BYTEPADDING = 4;
    private static final double CHINESEWIDTHRATIO = 60/37.0;
    /** 对应的文件名 */
    private String fileName;
    /** 对应的数据段名 */
    private String blockName;
    /** 对应的纪录名 */
    private String recordName;
    /** 字段在文件中的偏移地址 */
    private long fileOffset;
    /** 字段在读入数据段中的偏移地址 */
    private int blockOffset;
    /** 数据段起始地址 */
    private long blockStart;
    /** 对应的数据段字节缓冲区 */
    private MappedByteBuffer blockBuffer;
    /** 字段字节数据 */
    private byte[] segBytes;
    /** 字段最大允许值 */
    private int max;
    /** 字段最小允许值 */
    private int min;
    /** 字段缺省值 */
    private int defaultValue;
    /** 字段推荐值 */
    private int recommendValue;
    /** 字段数值 */
    private int value;
    
    /** 
     * 构造器      
     * @param fName: 文件名
     * @param fBuffer: 数据段缓冲区
     * @param bName: 数据段名
     * @param rName: 纪录名
     * @param fOffset: 文件偏移量
     * @param bStart: 缓冲区开始地址 
     */
    public BaseSeg(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart) {
        this.setFileName(fName);
        this.setBlockName(bName);
        this.setRecordName(rName);
        this.linkBuffer(fBuffer);
        this.setFileOffset(fOffset);
        this.setBlockStart(bStart);
        this.defaultValue = -1;
        this.recommendValue = -1;
    }
    
    /** 
     * 未知缓冲区的构造器      
     * @param fName: 文件名
     * @param fBuffer: 数据段缓冲区
     * @param bName: 数据段名
     * @param rName: 纪录名
     * @param fOffset: 文件偏移量 
     */
    public BaseSeg(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset) {
        this(fName, fBuffer, bName, rName, fOffset, 0);
    }
    
    /**
     * 空白构造器
     */
    public BaseSeg(){
        this(NOTINTIATED, null, NOTINTIATED, NOTINTIATED, 0, 0);
    }
    
    
    /** 
     * 抽象方法: segName getter
     * @return String: 字段名 
     */
    abstract public String getSegName();
    
    /** 
     * 抽象方法: length getter 
     * @return int: 字段字节长度 
     */
    abstract public int getLength();
    
    /** 
     * 抽象方法: nameList getter 
     * @return String[]: 名称列表 
     */
    abstract public String[] getNameList();
    
    /** 
     * 抽象方法: colWidth getter
     * @return String: 字段名 
     */
    abstract public int getColWidth();

    
    /**
     * 初始化字段,调用fileName, recordName, fileOffset, bStart的setter
     * 配合无参数构造器使用
     * @param fName: 字段所在文件名
     * @param fBuffer: 数据段缓冲区
     * @param bName: 字段所在数据段名
     * @param rName: 字段所属纪录名
     * @param fOffset: 字段在文件中的偏移地址
     * @param bStart: 字段在数据段中的偏移地址
     */
    public final void initSeg(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart) {
        this.setFileName(fName);
        this.setBlockName(bName);
        this.setRecordName(rName);
        this.linkBuffer(fBuffer);
        this.setFileOffset(fOffset);
        this.setBlockStart(bStart);
    }
    
    /** 
     * 链接数据缓冲区 
     * @param bBuffer: 缓冲区指针 
     */
    public final void linkBuffer(MappedByteBuffer bBuffer){
        this.blockBuffer = bBuffer;
    }

    /** 
     * 获取数据缓冲区
     * @return MappedByteBuffer: 缓冲区指针 
     */
    public final MappedByteBuffer getBuffer(){
        return this.blockBuffer;
    }
    
    /** 
     * 初始化segBytes 
     * @param size: 字节长度
     */
    public final void initSegBytes(int size){
        segBytes = new byte[size];
    }
    
    /** 
     * 根据value的值改写segBytes 
     */
    private void updateSegBytes(){
        int vNumber = value;
        for(int i=0;i<getLength();i++){
            segBytes[i] = (byte)(vNumber & 0xFF);
            vNumber = vNumber >> 8;    
        }
    }
    
    /** 
     * 根据segBytes的值改写value 
     */
    private void updateValue(){
        value = 0;
        for(int i=getLength()-1;i>=0;i--){
            value = value << 8;
            value += segBytes[i] & 0xFF;
        }
        if(value < 0){
            value = MAXS[getLength()-1];
        }
    }   
    
    /** 
     * 从缓冲区指定地址填充segBytes 
     */
    public final void read(){
        blockBuffer.position(blockOffset);
        seqRead();
    }
    
    /** 
     * 从缓冲区当前地址填充segBytes, 并update value的值 
     * @throws NullPointerException: 没有连接到缓冲区则报错
     */
    public final void seqRead() throws NullPointerException {
        if(blockBuffer != null) {
            blockBuffer.get(segBytes);
            updateValue();
        } else {
            throw(new NullPointerException("Block buffer not connected!"));
        }
    }
    
    /** 
     * 将segBytes按指定地址写回缓冲区 
     * @throws NullPointerException: 没有连接到缓冲区则报错
     */
    public final void write() throws NullPointerException{
        if(blockBuffer != null) {
            blockBuffer.position(blockOffset);
            seqWrite();
        } else {
            throw(new NullPointerException("Block buffer not connected!"));
        }
    }
    
    /** 
     * 将segBytes按当前地址写回缓冲区 
     */
    public final void seqWrite(){
        blockBuffer.put(segBytes);
    }
    
    /** 修改value的值并update segBytes
     * @param sValue: 修改目标值,这个方法会在BaseSegRange里改写保证最大值大于等于最小值
     */ 
    public void setValue(int sValue){
        setValue(VALUE.CURRENT, sValue);
    }
    
    /** 
     * 修改制定value的值并update segBytes
     * @param whichValue: 需要更新哪个值
     * @param sValue: 修改目标值 
     */ 
    public final void setValue(VALUE whichValue, int sValue){
        if (sValue > max) {
            sValue = max;
        } else if (sValue < min) {
            sValue = min;
        }
        switch(whichValue){
            case DEFAULT:
                this.defaultValue = sValue;
                break;
            case RECOMMEND:
                this.recommendValue = sValue;
                break;
            case CURRENT:
                this.value = sValue;
                updateSegBytes();
                break;
            default:
                break;
        }
    }
    
    /** 恢复value的值到defaultValue并update segBytes 
     */
    public final void reset(){
        if (hasDefaultValue()) {
            setValue(defaultValue);
        }
    }
    
    /** 修改value的值到recommendValue并update segBytes 
     */
    public final void optimize(){
        if (hasRecommendValue()){
            setValue(recommendValue);
        }
    } 
    
    /** 把byteArray转化到hexcode String Array
     * @return String[]: 转换后的十六进制 
     */
    public final String[] segBytes2Hex(){
        String[] hArray = new String[getLength()];
        for(int i=0;i<getLength();i++){
            hArray[i] = String.format("%02X", segBytes[i]);
        }
        return hArray;
    }
    
    /** 取得简式字符串,如果是数值就显示数字,如果有对应名称列表就显示名称, 这个方法
     *  在SegEffctRange里面会重写,区分直线范围和面范围
     * @return String: 输出的字节内容字符串
     */    
    public String displayString(){
        if(getNameList() == null){
            return String.valueOf(value);
        } else {
            return getNameList()[Math.min(getMax(), value)];
        }
    }
    
    /** 静态方法, 按指定对齐方式打印字符串, 其中中文字符按三字节算, 用fillChar填补空白
     * @param s: 需要打印的字符串
     * @param align: 对齐方式
     * @param width: 栏目宽度
     * @param fillChar: 不足长度的地方填充的字符
     * @param withTab: 用Tab补足
     * @return String: 格式完毕的字符串 
     */
    public final static String alignString(String s, ALIGN align, int width, char fillChar, boolean withTab){
        int lenChinese3 = s.getBytes().length;
        int lenChinese1 = s.length();
        int stringLen = (int)((lenChinese3 - lenChinese1)/2 * CHINESEWIDTHRATIO + (3 * lenChinese1 - lenChinese3)/2);
        StringBuilder docString = new StringBuilder();
        switch(align){
            case LEFT:
                docString.append(s);
                for(int i=0;i<width-stringLen;i++){
                    docString.append(fillChar);
                }
                break;
            case RIGHT:
                for(int i=0;i<width-stringLen;i++){
                    docString.append(fillChar);
                }
                docString.append(s);
                break;
            case CENTER:
                for(int i=0;i<(width-stringLen)/2;i++){
                    docString.append(fillChar);
                }
                docString.append(s);
                for(int i=(width-stringLen)/2;i<width-stringLen;i++){
                    docString.append(fillChar);
                }
                break; 
            default:
                docString.append(s);
        }
        if(withTab){
            docString.append("\t");
        }
        return docString.toString();
    }
    
    /** 静态方法, 按指定对齐方式打印字符串, 其中中文字符按三字节算, 用fillChar填补空白, 用Tab补足
     * @param s: 需要打印的字符串
     * @param align: 对齐方式
     * @param width: 栏目宽度
     * @param fillChar: 不足长度的地方填充的字符
     * @return String: 格式完毕的字符串 
     */
    public final static String alignString(String s, ALIGN align, int width, char fillChar){
        return alignString(s, align, width, fillChar, true);
    }
    
    /** 静态方法, 按指定对齐方式缺省宽度打印字符串, 填空格
     * @param s: 需要打印的字符串
     * @param align: 对齐方式
     * @param width: 栏目宽度
     * @return String: 格式完毕的字符串
     */
    public final static String alignString(String s, ALIGN align, int width){
        return alignString(s, align, width, ' ');
    }
    
    /** 实例方法, 按 COLWIDTH 长度打印字符串, 填fillChar
     * @param s: 需要打印的字符串
     * @param align: 对齐方式
     * @param fillChar: 不足长度的地方填充的字符
     * @return String: 格式完毕的字符串
     */
    public final String alignString(String s, ALIGN align, char fillChar){
        return alignString(s, align, getColWidth(), fillChar);
    }
    
    /** 实例方法, 按 COLWIDTH 长度打印字符串, 填空格 
     * @param s: 需要打印的字符串
     * @param align: 对齐方式
     * @return String: 格式完毕的字符串 
     */
    public final String alignString(String s, ALIGN align){
        return alignString(s, align, getColWidth(), ' ');
    }
    
    /** 创建docString, 用LinkedHashMap格式方便子类添加
     * @return LinkedHashMap: 包含需要输出的键值对
     */
    public LinkedHashMap docStringMap(){
        LinkedHashMap<String, String> docMap = new LinkedHashMap<>();
        docMap.put("源文件名:", fileName);
        docMap.put("数据名称:", blockName);
        docMap.put("记录名称:", recordName);
        docMap.put("字段名称:", getSegName());
        docMap.put("文件偏移:", String.format("0x%06X", fileOffset));
        docMap.put("数段偏移:", String.format("0x%06X", blockOffset));
        docMap.put("字节长度:", String.valueOf(getLength()));
        docMap.put("字段内容:", displayString());
        docMap.put("原始数值:", String.valueOf(defaultValue));
        docMap.put("当前数值:", String.valueOf(value));
        docMap.put("优化数值:", String.valueOf(recommendValue));
        docMap.put("字节数据:", Arrays.toString(segBytes2Hex()));
        return docMap;
    }
    
    /**
     * 格式化docStringMap
     * @return LinkedHashMap: 格式化好的docStringMap
     */
    public LinkedHashMap alignedDocStringMap(){
        LinkedHashMap<String, String> docMap = docStringMap();
        LinkedHashMap<String, String> alignedDocMap = new LinkedHashMap<>();
        docMap.entrySet().forEach((entry) -> {
            alignedDocMap.put(alignString(entry.getKey(), ALIGN.LEFT, getTitleWidth()),
                              alignString(entry.getValue(), ALIGN.RIGHT));
        });
        return alignedDocMap;
    }
    
    /** 改写toString
     * @return String: docString 
     */
    @Override
    public String toString(){
        LinkedHashMap<String, String> docMap = alignedDocStringMap();
        StringBuilder dString = new StringBuilder();
        docMap.entrySet().forEach((entry) -> {
            dString.append(entry.getKey());
            dString.append(entry.getValue());
            dString.append("\n");
        });
        return dString.toString();
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
        
    /** 胶水方法:数据段名
     * @return String: 返回文件名 
     */
    public final String getblockName(){
        return this.blockName;
    }
   
    /** 胶水方法:数据段名
     * @param bName: 
     */
    public final void setBlockName(String bName){
        this.blockName = bName;
    }
   
    /** 胶水方法:纪录名
     * @return String: 纪录名 
     */
    public final String getRecordName(){
        return this.recordName;
    }
   
    /** 胶水方法:纪录名
     * @param rName: 设定纪录名
     */
    public final void setRecordName(String rName){
        this.recordName = rName;
    }
   
    /** 胶水方法:文件偏移地址
     * @return long: 字段在文件中的偏移地址
     */
    public final long getFileOffset(){
        return this.fileOffset;
    }
   
    /** 胶水方法:文件偏移地址
     * @param fOffset: 字段在文件中的偏移地址 
     */
    public final void setFileOffset(long fOffset){
        this.fileOffset = fOffset;
    }
   
    /** 胶水方法:数据段偏移地址
     * @return long: 字段在数据段中的偏移地址 
     */
    public final int getBlockOffset(){
        return this.blockOffset;
    }
    
    /** 胶水方法:数据段偏移地址
     * @param bOffset: 字段在数据段中的偏移地址
     */
    public final void setBlockOffset(int bOffset){
        this.blockOffset = bOffset;
    }
    
    /** 胶水方法:数据段起始地址
     * @return long: 字段在数据段中的偏移地址 
     */
    public final long getBlockStart(){
        return this.blockStart;
    }
       
    /** 胶水方法:数据段偏移地址
     * @param bStart: 数据段开始的偏移地址 
     */
    public final void setBlockStart(long bStart){
        this.blockStart = bStart;
        this.blockOffset = (int)(this.fileOffset - bStart);
    }
    
   
    /** 胶水方法:BytesArray, 写方法是updateSegByte()
     * @return byte[]: 实际字节数据, 低位在前
     */
    public final byte[] getSegBytes(){
        return this.segBytes.clone();
    }
   
    /** 胶水方法:允许的最大值,
     * @return int: 最大允许值 
     */
    public final int getMax(){
        return this.max;
    }
   
    /** 胶水方法:允许的最大值,
     * @param mValue: 更新最大值 
     */
    public final void setMax(int mValue){
        this.max = Math.min(mValue, MAXS[getLength()-1]);
    }
   
    /** 胶水方法:允许的最小值,
     * @return int: 最小允许值 
     */
    public final int getMin(){
        return this.min;
    }
   
    /** 胶水方法:允许的最小值,
     * @param mValue: 更新最小值 
     */
    public final void setMin(int mValue){
        this.max = Math.max(mValue, 0);
    }
   
    /** 胶水方法:default值
     * @return int: 缺省值
     */
    public final int getDefaultValue(){
        return this.defaultValue;
    }
   
    /** 胶水方法:default值
     * @param dValue: 缺省值
     */
    public final void setDefaultValue(int dValue){
        setValue(VALUE.DEFAULT, dValue);
    }
    
    /** 胶水方法:有无default值
     * @return boolean: defaultValue是否从-1的缺省值被改写过
     */
    public final boolean hasDefaultValue(){
        return this.defaultValue == -1;
    }
   
    /** 胶水方法:优化值, 没有写方法
     * @return int: 推荐值
     */
    public final int getRecommendValue(){
        return this.recommendValue;
    }
   
    /** 胶水方法:default值
     * @param rValue: 推价值
     */
    public final void setRecommendValue(int rValue){
        setValue(VALUE.RECOMMEND, rValue);
    }
    
    /** 胶水方法:有无recommend值
     * @return boolean: recommendValue是否从-1的缺省值被改写过
     */
    public final boolean hasRecommendValue(){
        return this.recommendValue == -1;
    }
   
    /** 胶水方法: 返回当前值, 写方法是setValue()
     * @return int: 当前值
     */
    public final int getValue(){
        return this.value;
    }  
    
    /** 静态方法: TITLEWIDTH
     * @return int: 标题栏输出宽度
     */
    public static final int getTitleWidth(){
        return TITLEWIDTH;
    }  
    
    /** 静态方法: BYTEWIDTH
     * @return int: 每个BYTE输出宽度
     */
    public static final int getByteWidth(){
        return BYTEWIDTH;
    }  
    
    /** 静态方法: BYTEPADDING
     * @return int: 每个col输出的两端空白
     */
    public static final int getBytePadding(){
        return BYTEPADDING;
    }  
}
