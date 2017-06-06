/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;

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
    /** 数据段所含纪录的数目 */
    private int length;
    /** 数据段纪录字节长度 */
    private int recordLength;
    /** 纪录的名字列表 */
    private String[] nameList;
    /** 纪录列表, 因为有重复键值比如未使用,所以没法用LinkedHashMap */
    private Record[] recordList;
    /** 纪录所含字段的列表 */
    private SEGTYPE[] segTypeList;
    
    /**
     * 构造器, 从一个RECORDTYPE enum纪录创建实例
     * @param rType: 数据段所含纪录的基本信息
     */
    public Block(RECORDTYPE rType){
        this.fileName = rType.getFileName();
        this.blockName = rType.name();
        this.fileOffset = rType.getFileOffset();
        this.nameList = rType.getNameList();
        this.segTypeList = rType.getSegList(); 
        if(this.nameList != null){
            this.length = this.nameList.length;
        }
    }
    
    /**
     * 空白构造器
     */
    public Block(){
        this(null);
    }
    
    /**
     * 读取一个RECORDTYPE并初始化数据段
     * @param rType 
     */
    public final void initBlock(RECORDTYPE rType){
        this.setFileName(rType.getFileName());
        this.setBlockName(rType.name());
        this.setFileOffset(rType.getFileOffset());
        this.nameList = rType.getNameList();
        this.segTypeList = rType.getSegList(); 
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
     * 获取纪录包含的segTypeList
     * @return SEGTYPE[]: 纪录包含的所有字段
     */
    public final SEGTYPE[] getSegTypeList(){
        return this.segTypeList.clone();
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
        return this.recordList.clone();
    }
}
