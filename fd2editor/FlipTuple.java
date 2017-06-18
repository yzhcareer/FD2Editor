/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;

/**
 * 简单的开关类, 包括偏移地址, 原字节数据, 和修改的字节数据
 * @author CIDER
 */
public class FlipTuple {
    private long fileOffset;
    private byte[] offValue;
    private byte[] onValue;
    
    public FlipTuple(long fOffset, byte[] offV, byte[] onV){
        this.setFileOffset(fOffset);
        this.setOffValue(offV);
        this.setOnValue(onV);
    }
    
    public final void setFileOffset(long fOffset){
        this.fileOffset = fOffset;
    }
    
    public final void setOffValue(byte[] offV){
        this.offValue = offV.clone();
    }
    
    
    public final void setOnValue(byte[] onV){
        this.onValue = onV.clone();
    }
    
    public final long getFileOffset(){
        return this.fileOffset;
    }
    
    public final byte[] getOffValue(){
        return this.offValue.clone();
    }
    
    public final byte[] getOnValue(){
        return this.onValue.clone();
    }
    
    public final int getByteLength(){
        return this.offValue.length;
    }
}
