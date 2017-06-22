/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.util.ArrayList;

/**
 * 简单的开关类, 包括偏移地址, 原字节数据, 和修改的字节数据
 * @author CIDER
 */
public class SwitchTuple {
    private long fileOffset;
    private byte[] offValue;
    private byte[] onValue;
    private Integer[] byteLocs;

    public SwitchTuple(long fOffset, byte[] offV, byte[] onV){
        this.setFileOffset(fOffset);
        this.setOffValue(offV);
        this.setOnValue(onV);
    }

    public SwitchTuple(long fOffset, byte[] offV, byte[] onV, Integer[] byteLocs){
        this.setFileOffset(fOffset);
        this.setOffValue(offV);
        this.setOnValue(onV);
        this.setByteLocs(byteLocs);
    }

    public SwitchTuple(long fOffset, byte[] offV){
        this.setFileOffset(fOffset);
        this.setOffValue(offV);
        this.setOnValue(null);
    }
    
    public final void setFileOffset(long fOffset){
        this.fileOffset = fOffset;
    }
    
    public final void setOffValue(byte[] offV){
        if (offV != null) {
            this.offValue = offV.clone();
        } else {
            this.offValue = null;
        }
    }

    public final void setOnValue(byte[] onV){
        if (onV != null) {
            this.onValue = onV.clone();
        } else {
            this.onValue = null;
        }
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

    public final Integer[] getByteLocs() {
        return byteLocs;
    }

    public final void setByteLocs(Integer[] byteLocs) {
        this.byteLocs = byteLocs;
    }

    public final void setByteLocs() {
        ArrayList<Long> differentLocs = new ArrayList<>();
        for(int i=0; i<Math.min(offValue.length, onValue.length); i++) {
            if(onValue[i] != offValue[i]) {
                differentLocs.add(getFileOffset() + i);
            }
        }
        setByteLocs(differentLocs.toArray(new Integer[differentLocs.size()]));
    }
}
