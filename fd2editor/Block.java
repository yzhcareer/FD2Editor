/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.nio.MappedByteBuffer;
import java.util.LinkedHashMap;

/**
 * 数据段类, 是一系列Record的集合
 * @author CIDER
 */
public class Block {
    /** 数据段所属文件名 */
    private String fileName;
    /** 数据段名 */
    private String blockName;
    /** 数据段在文件中的偏移地址 */
    private long fileOffset;
    /** 数据段在缓冲区中的偏移地址 */
    private int bufferOffset;
    /** 缓冲区在文件中的起始地址 */
    private long bufferStart;
    /** 数据段所含纪录的数目 */
    private int length;
    /** 数据段纪录字节长度 */
    private int recordLength;
    /** 数据段总长度 */
    private int blockLength;
    /** 纪录的名字列表 */
    private String[] nameList;
    /** 纪录列表, 因为有重复键值比如未使用,所以没法用LinkedHashMap */
    private Record[] recordList;
    /** 纪录所含字段的列表 */
    private SEGTYPE[] segTypeList;
    /** 数据段文件缓冲区指针 */
    private MappedByteBuffer fileBuffer;
    /** 输出内容选择器 */
    public enum CONTENT {BYTES, CONTENTS, VALUES};
    
    /**
     * 构造器, 从一个RECORDTYPE enum纪录创建实例
     * @param rType: 数据段所含纪录的基本信息
     * @param fBuffer: 缓冲区指针
     * @param bStart: 缓冲区在文件中的开始地址
     */
    public Block(RECORDTYPE rType, MappedByteBuffer fBuffer, long bStart){
        this.setFileName(rType.getFileName());
        this.setBlockName(rType.name());
        this.setFileOffset(rType.getFileOffset());
        this.setBufferStart(bStart);
        this.setNameList(rType.getNameList());
        this.setSegTypeList(rType.getSegList()); 
        this.linkBuffer(fBuffer);
        this.setBlockLength(rType.getBlockLength());
        this.initRecords();
    }
    
    /**
     * 空白构造器
     */
    public Block(){
    }
    
    /**
     * 读取一个RECORDTYPE并初始化数据段
     * @param rType: 数据段所含纪录的基本信息
     * @param fBuffer: 缓冲区指针
     * @param bStart: 缓冲区在文件中的开始地址
     */
    public final void initBlock(RECORDTYPE rType, MappedByteBuffer fBuffer, long bStart){
        this.setFileName(rType.getFileName());
        this.setBlockName(rType.name());
        this.setFileOffset(rType.getFileOffset());
        this.setBufferStart(bStart);
        this.setNameList(rType.getNameList());
        this.setSegTypeList(rType.getSegList()); 
        this.linkBuffer(fBuffer);
        this.setBlockLength(rType.getBlockLength());
        this.initRecords();
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
    
    /**
     * 从名称列表初始化各个纪录
     */
    public final void initRecords(){
        this.recordLength = 0;
        for (int i=0; i< this.length; i++){
            this.recordList[i] = new Record();
            this.recordList[i].initRecord(fileName, fileBuffer, blockName, nameList[i], 
                                          fileOffset + recordLength * i, bufferStart, segTypeList);
            if(this.recordLength == 0){
                this.recordLength = this.recordList[i].getByteLength();
            }
        }
        this.setBlockLength(this.length * this.recordLength);
    }
    
    /**
     * 定好初始的位置之后按顺序读入record纪录
     */
    public final void read(){
        this.fileBuffer.position(this.bufferOffset);
        for (Record record: this.recordList){
             record.seqRead();
        }   
    }
    
    /**
     * 定好初始的位置之后按顺序写出record纪录
     */
    public final void write(){
        this.fileBuffer.position(this.bufferOffset);
        for (Record record: this.recordList){
            record.seqWrite();
        }
    }
    
    /**
     * 把纪录中的各个字段输出成表格模式
     * @param docKey: 输出内容的选择器
     * @return StringBuilder: 表格模式的字符串
     */
    public final StringBuilder docString(BaseSeg.DOCKEY docKey){
        int tWidth = BaseSeg.getTitleWidth();
        LinkedHashMap<BaseSeg.DOCKEY, String> docMap;
        StringBuilder titleString = new StringBuilder();
        titleString.append((BaseSeg.alignString("序号", BaseSeg.ALIGN.CENTER, tWidth, '_'))); 
        titleString.append(BaseSeg.alignString(blockName, BaseSeg.ALIGN.CENTER,tWidth, '_'));  
        StringBuilder dString = new StringBuilder();
        if(length == 0 || recordLength == 0) {
            titleString.append(":  The Block is Empty!");
        } else {
            for(int i =0;i<length;i++){
                dString.append(BaseSeg.alignString(String.format("%02X", i), BaseSeg.ALIGN.RIGHT, tWidth));
                dString.append(BaseSeg.alignString(nameList[i], BaseSeg.ALIGN.LEFT, tWidth));
                for(BaseSeg bSeg: recordList[i].getSegList()){
                    if(i==0){
                        titleString.append(bSeg.alignString(bSeg.getSegName(), BaseSeg.ALIGN.CENTER, '_'));
                    }
                    docMap = bSeg.docStringMap();
                    dString.append(bSeg.alignString(docMap.get(docKey), BaseSeg.ALIGN.RIGHT));         
                }
                dString.append("\n");
            }
            titleString.append("\n");
        }
        dString.append("\n");
        return titleString.append(dString);
    }
    
    /**
     * 缺省输出字段内容
     * @return StringBuilder: 表格模式的字符串
     */
    public StringBuilder docString(){
        return docString(BaseSeg.DOCKEY.字段内容);
    }
    
    /** 改写toString
     * @return String: docString 
     */
    @Override
    public String toString(){
        return docString().toString();
    }
            
    /** 恢复value的值到defaultValue并update records 
     */
    public final void reset(){
        if(recordList != null) {
            for (Record record: recordList){
                record.reset();
            }
        }
    }
    
    /** 修改value的值到recommendValue并update records 
     */
    public final void optimize(){
        if(recordList != null) {
            for (Record record: recordList){
                record.optimize();
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
    
    /** 胶水方法:文件偏移地址
     * @return long: 数据段在文件中的偏移地址
     */
    public final long getFileOffset(){
        return this.fileOffset;
    }
   
    /** 胶水方法:文件偏移地址
     * @param fOffset: 数据段在文件中的偏移地址 
     */
    public final void setFileOffset(long fOffset){
        this.fileOffset = fOffset;
        if(recordList != null){
            for(Record record: recordList) {
                record.setFileOffset(fOffset);
            }
        }
    }
    
    /** 胶水方法:数据段包含的纪录数
     * @return int: 纪录数
     */
    public final int getLength(){
        return this.length;
    }
    
    /** 胶水方法:纪录包含的字节数
     * @return int: 字节数
     */
    public final int getRecordLength(){
        return this.recordLength;
    }
    
    /**
     * 胶水方法, 名称字符串列表
     * @return 
     */
    public final String[] getNameList(){
        if (this.nameList != null){
            return this.nameList.clone();
        } else {
            return null;
        }
    }
    
    /**
     * 胶水方法, 名称字符串列表 
     * @param nList: 名称列表
     */
    public final void setNameList(String[] nList){
        if (nList != null){
            this.nameList = nList.clone();
            this.length = nList.length;
            this.recordList = new Record[length];
        }
    }
      
    /** 
     * 获取纪录包含的segTypeList
     * @return SEGTYPE[]: 纪录包含的所有字段
     */
    public final SEGTYPE[] getSegTypeList(){
        if (segTypeList != null){
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
    }
    
    /** 
     * 获取纪录包含的segList
     * @return SEGTYPE[]: 纪录包含的所有字段
     */
    public final Record[] getRecordList(){
        if(recordList != null){
            return this.recordList.clone();
        } else {
            return null;
        }
    }
    /** 胶水方法:数据段偏移地址
     * @return long: 数据段在缓冲区中的偏移地址 
     */
    public final long getBufferOffset(){
        return this.bufferOffset;
    }
    
    /** 胶水方法:数据段偏移地址
     * @param bOffset: 字段在缓冲区中的偏移地址
     */
    public final void setBufferOffset(int bOffset){
        this.bufferOffset = bOffset;
        if(recordList != null){
            for(Record record: recordList) {
                record.setBufferOffset(bOffset);
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
        if(recordList != null){
            for(Record record: recordList) {
                record.setBufferStart(bStart);
            }
        }
    }
        
    /** 胶水方法:数据总长度
     * @return int: 数据段总长度,如果是0则设为整个文件的长度
     */
    public final int getBlockLength(){
        return this.blockLength;
    }
    
    /** 胶水方法:数据总长度
     * @param bLength: 数据段总长度
     */
    public final void setBlockLength(int bLength){
        if (bLength == 0 && this.fileBuffer != null){
            this.blockLength = this.fileBuffer.capacity();
        } else {
            this.blockLength = bLength;
        }
    }
}
