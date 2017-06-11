/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.nio.MappedByteBuffer;


/**
 * 单字节数据段模版, 固定字节长度和栏目宽度为类静态属性, 
 * 需要覆盖长度和栏目宽度的getter()
 * @author CIDER
 */
abstract class BaseSeg1Byte extends BaseSeg {
    
    private static final int LENGTH;
    private static final int COLWIDTH;
     
    static {
        LENGTH = 1;
        COLWIDTH = getByteWidth() * LENGTH + getBytePadding();
    }
    
    public BaseSeg1Byte(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        initSegBytes(LENGTH);
    }
    
    public BaseSeg1Byte(){
        this(NOTINTIATED, null, NOTINTIATED, NOTINTIATED, 0, 0);
    }
    
    @Override
    public final int getLength(){ return LENGTH; }
    
    @Override
    public final int getColWidth(){ return COLWIDTH; } 
}

/**
 * 双字节数据段模版, 固定字节长度和栏目宽度为类静态属性
 * 需要覆盖长度和栏目宽度的getter()
 * @author CIDER
 */
abstract class BaseSeg2Byte extends BaseSeg {
    
    private static final int LENGTH;
    private static final int COLWIDTH;
     
    static {
        LENGTH = 2;
        COLWIDTH = getByteWidth() * LENGTH + getBytePadding();
    }
    
    public BaseSeg2Byte(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        initSegBytes(LENGTH);
    }
        
    public BaseSeg2Byte(){
        this(NOTINTIATED, null, NOTINTIATED, NOTINTIATED, 0, 0);
    }
    
    @Override
    public final int getLength(){ return LENGTH; }
    
    @Override
    public final int getColWidth(){ return COLWIDTH; } 
}

/**
 * 三字节数据段模版, 固定字节长度和栏目宽度为类静态属性
 * 需要覆盖长度和栏目宽度的getter()
 * @author CIDER
 */

abstract class BaseSeg3Byte extends BaseSeg {
    
    private static final int LENGTH;
    private static final int COLWIDTH;
     
    static {
        LENGTH = 3;
        COLWIDTH = getByteWidth() * LENGTH + getBytePadding();
    }
    
    public BaseSeg3Byte(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        initSegBytes(LENGTH);
    }
    
    public BaseSeg3Byte(){
        this(NOTINTIATED, null, NOTINTIATED, NOTINTIATED, 0, 0);
    }
    
    @Override
    public final int getLength(){ return LENGTH; }
    
    @Override
    public final int getColWidth(){ return COLWIDTH; } 
}

/**
 * 四字节数据段模版, 固定字节长度和栏目宽度为类静态属性
 * 需要覆盖长度和栏目宽度的getter()
 * @author CIDER
 */
abstract class BaseSeg4Byte extends BaseSeg {
    
    private static final int LENGTH;
    private static final int COLWIDTH;
     
    static {
        LENGTH = 4;
        COLWIDTH = getByteWidth() * LENGTH + getBytePadding();
    }
    
    public BaseSeg4Byte(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        initSegBytes(LENGTH);
    }
    
    public BaseSeg4Byte(){
        this(NOTINTIATED, null, NOTINTIATED, NOTINTIATED, 0, 0);
    }
    
    @Override
    public final int getLength(){ return LENGTH; }
    
    @Override
    public final int getColWidth(){ return COLWIDTH; } 
}

/**
 * 作用范围模版, 需要重写setValue()保证最大值大于等于最小值
 * @author CIDER
 */

abstract class BaseSegRange extends BaseSeg2Byte {
    
    private static final int LENGTH;
    private static final int COLWIDTH;
     
    static {
        LENGTH = 2;
        COLWIDTH = getByteWidth() * LENGTH + getBytePadding();
    }
    
    public BaseSegRange(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        initSegBytes(LENGTH);
    }
        
    public BaseSegRange(){
        this(NOTINTIATED, null, NOTINTIATED, NOTINTIATED, 0, 0);
    }
    
    /**
     * 重写value setter(),保证写入的时候小的值在低位,大的值在高位,
     * 同时任何一位都应该是正Byte值
     * @param mValue: 新设定值
     */
    @Override
    public final void setValue(int mValue){
        int lowValue = mValue & 0x7F;
        int highValue = (mValue >> 8) & 0x7F;
        int newValue = Math.max(lowValue, highValue) << 8 + Math.min(lowValue, highValue);
        setValue(VALUE.CURRENT, newValue); 
    }
    
    @Override
    public final String displayString(){
        StringBuilder dString = new StringBuilder();
        dString.append("(");
        dString.append(getSegBytes()[0]);
        dString.append("\u2192");
        dString.append(getSegBytes()[1]);
        dString.append(")");
        return dString.toString();
    }
}