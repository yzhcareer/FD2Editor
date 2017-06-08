/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.nio.MappedByteBuffer;
import java.util.LinkedHashMap;

/**
 * 纪录类, 是一系列Segment的集合
 * @author CIDER
 */
public class Record {
    /** 未初始化提示信息 */
    static final String NOTINTIATED = "Not Initiated";
    /** 文件名 */
    private String fileName;
    /** 数据段名称 */
    private String blockName;
    /** 纪录名称 */
    private String recordName;
    /** 纪录在文件中的偏移地址 */
    private long fileOffset;
    /** 纪录在缓冲区中的偏移地址 */
    private int bufferOffset;
    /** 数据段缓冲区在文件起始地址 */
    private long bufferStart;
    /** 文件字节缓冲区 */
    private MappedByteBuffer fileBuffer;
    /** 纪录的字段数目 */
    private int length;
    /** 纪录的字节长度 */
    private int recordLength;
    /** 字段名称的Array */
    private SEGTYPE[] segTypeList;
    /** 数据的Array, 用LinkedHashMap当然更好,可是SEGTYPE有重复的 */
    private BaseSeg[] segList;
    
    /** 构造器
     * @param fName: 文件名
     * @param fBuffer: 数据段缓冲区
     * @param bName: 数据段名
     * @param rName: 纪录名
     * @param fOffset: 纪录在文件中的偏移量
     * @param bStart: 数据段在文件中的起始偏移量
     * @param tList: 纪录包含的Segment列表
     */
    public Record(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart, SEGTYPE[] tList) {
        this.setFileName(fName);
        this.setBlockName(bName);
        this.setRecordName(rName);
        this.linkBuffer(fBuffer);
        this.setFileOffset(fOffset);
        this.setBufferStart(bStart);
        this.setSegTypeList(tList);
        this.initSegs();
    }
    
    /** 空白构造器 
     */
    public Record(){
        this.setFileName(NOTINTIATED);
        this.setBlockName(NOTINTIATED);
        this.setRecordName(NOTINTIATED);
        this.setFileOffset(0);
        this.setBufferStart(0);
    }
    
    /**
     * 用空白构造器的情况下初始化字段,调用fileName, recordName, fileOffset, bStart的setter
     * 配合无参数构造器使用
     * @param fName: 字段所在文件名
     * @param fBuffer: 数据段缓冲区
     * @param bName: 字段所在数据段名
     * @param rName: 字段所属纪录名
     * @param fOffset: 字段在文件中的偏移地址
     * @param bStart: 字段在数据段中的偏移地址
     * @param tList: 纪录包含的所有字段
     */
    public final void initRecord(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart, SEGTYPE[] tList){
        this.setFileName(fName);
        this.setBlockName(bName);
        this.setRecordName(rName);
        this.linkBuffer(fBuffer);
        this.setFileOffset(fOffset);
        this.setBufferStart(bStart);
        this.setSegTypeList(tList);
        this.initSegs();
    }
    
    /** 
     * 已经有赋值的情况下的初始化 
     */
    public final void initRecord(){
        this.initSegs();
    }
   
    /** 
     * 链接数据缓冲区 
     * @param bBuffer: 缓冲区指针 
     */
    public final void linkBuffer(MappedByteBuffer bBuffer){
        this.fileBuffer = bBuffer;
    }

    /** 
     * 获取数据缓冲区
     * @return MappedByteBuffer: 缓冲区指针 
     */
    public final MappedByteBuffer getBuffer(){
        return this.fileBuffer;
    }
    
    /** 
     * 获取纪录包含的segTypeList
     * @return SEGTYPE[]: 纪录包含的所有字段
     */
    public final SEGTYPE[] getSegTypeList(){
        if(this.segTypeList != null){
            return this.segTypeList.clone();
        } else {
            return null;
        }
    }
    
    /** 
     * 读入纪录包含的segTypeList
     * 同时更新纪录包含的字段数
     * @param tList : 纪录包含的所有字段
     */
    public final void setSegTypeList(SEGTYPE[] tList){
        this.segTypeList = tList.clone();
        this.length = tList.length;
        segList = new BaseSeg[length];
    }
    
    /** 
     * 获取纪录包含的segList
     * @return SEGTYPE[]: 纪录包含的所有字段
     */
    public final BaseSeg[] getSegList(){
        if(this.segList != null){
            return this.segList.clone();
        } else {
            return null;
        }
    }
    
    /** 
     * 从segTypeList 初始化 SegList
     * 同时更新纪录的字段长度
     */
    public final void initSegs(){
        recordLength = 0;
        BaseSeg bSeg;
        for(int i =0;i<length; i++){
            bSeg = segTypeList[i].createSeg();
            bSeg.initSeg(fileName, fileBuffer, blockName, recordName, fileOffset + recordLength, bufferStart);
            segList[i] = bSeg;
            recordLength += bSeg.getLength();
        }
    }
    
    /** 
     * 从缺省起始位置开始读字段内容
     * @throws NullPointerException: 没有连接到缓冲区则报错
     */
    public final void seqRead() throws NullPointerException {
        if(fileBuffer != null){
            for (BaseSeg bSeg: segList) {
                bSeg.seqRead();
            }
        } else {
            throw(new NullPointerException("Block buffer not connected!"));
        }
    }
    
    /**
     * 从blockOffset指定位置开始读取字段
     */
    public final void read() {
        fileBuffer.position(bufferOffset);
        seqRead();
    }
    
    /**
     * 从缺省位置开始写入字段, 没有连接到缓冲区则报错
     * @throws NullPointerException 
     */
    public final void seqWrite() throws NullPointerException {
        if(fileBuffer != null){
            for (BaseSeg bSeg: segList) {
                bSeg.seqWrite();
            } 
        } else {
            throw(new NullPointerException("Block buffer not connected!"));
        }
    }
    
    /**
     * 从blockOffset指定位置开始读取字段
     */
    public final void write(){
        fileBuffer.position(bufferOffset);
        seqWrite();
    }
   
    /**
     * 把纪录中的各个字段输出成表格模式
     * @return StringBuilder: 表格模式的字符串
     */
    public final StringBuilder docString(){
        int tWidth = BaseSeg.getTitleWidth();
        StringBuilder dString = new StringBuilder(BaseSeg.alignString(recordName, 
                                                                      BaseSeg.ALIGN.CENTER,
                                                                      tWidth, '_'));
        LinkedHashMap<String, String> [] docArray = new LinkedHashMap[length];
        for (int i=0; i<length; i++){
            docArray[i] = segList[i].alignedDocStringMap();
        }
        if(length == 0 || docArray[0].isEmpty()) {
            dString.append(":  The Record is Empty!");
        } else {
            for (BaseSeg bSeg: segList) {
                dString.append(bSeg.alignString(bSeg.getSegName(), BaseSeg.ALIGN.CENTER, '_'));
            }
            dString.append('\n');
            docArray[0].keySet().forEach((keyString) -> {
                dString.append(keyString);
                for (LinkedHashMap dMap: docArray){
                    dString.append(dMap.get(keyString));
                }
                dString.append('\n');
            });
        }
        return dString;
    }
    
    /** 改写toString
     * @return String: docString 
     */
    @Override
    public String toString(){
        return docString().toString();
    }
        
    /** 恢复value的值到defaultValue并update segBytes 
     */
    public final void reset(){
        if(segList != null) {
            for (BaseSeg bSeg: segList){
                bSeg.reset();
            }
        }
    }
    
    /** 修改value的值到recommendValue并update segBytes 
     */
    public final void optimize(){
        if(segList != null) {
            for (BaseSeg bSeg: segList){
                bSeg.optimize();
            }
        }
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
        if(segList != null){
            for(BaseSeg bSeg: segList) {
                bSeg.setFileOffset(fOffset);
            }
        }
    }
   
    /** 胶水方法:数据段偏移地址
     * @return long: 字段在缓冲区中的偏移地址 
     */
    public final long getBufferOffset(){
        return this.bufferOffset;
    }
    
    /** 胶水方法:数据段偏移地址
     * @param bOffset: 字段在缓冲区中的偏移地址
     */
    public final void setBufferOffset(int bOffset){
        this.bufferOffset = bOffset;
        if(segList != null){
            for(BaseSeg bSeg: segList) {
                bSeg.setBufferOffset(bOffset);
            }
        }
    }
    
    /** 胶水方法:数据段起始地址
     * @return long: 字段在缓冲区中的偏移地址 
     */
    public final long getBufferStart(){
        return this.bufferStart;
    }
    
    /** 胶水方法:数据段偏移地址
     * @param bStart: 数据段开始的偏移地址 
     */
    public final void setBufferStart(long bStart){
        this.bufferStart = bStart;
        this.bufferOffset = (int)(this.fileOffset - bStart);
        if(segList != null){
            for(BaseSeg bSeg: segList) {
                bSeg.setBufferStart(bStart);
            }
        }
    }  
    
    /** 胶水方法:纪录包含的字段数
     * @return int: 字段数
     */
    public final int getLength(){
        return this.length;
    }
    
    /** 胶水方法:纪录包含的字节数
     * @return int: 字节数
     */
    public final int getByteLength(){
        return this.recordLength;
    }
}
