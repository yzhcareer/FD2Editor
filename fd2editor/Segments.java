/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.nio.MappedByteBuffer;

/**
 *
 * @author CIDER
 */

/** 单字节, 未使用的数据 */
class SegUnused extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "未使用";
        NAMES = null;
    }
    
    public SegUnused(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegUnused(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 单字节, 用于开关字节 */
class SegFlip extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "开关";
        NAMES = null;
    }
    
    public SegFlip(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegFlip(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}


/** 单字节, 用于选择数值型字节 */
class SegSelector extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "选择";
        NAMES = null;
    }
    
    public SegSelector(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegSelector(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 单字节, 物品名称 */
class SegItem extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "物品";
        NAMES = NAMELIST.ITEM;
    }
    
    public SegItem(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegItem(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 单字节, 物品类型 */
class SegItemType extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "物品类型";
        NAMES = NAMELIST.ITEMTYPE;
    }
    
    public SegItemType(String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegItemType(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 双字节, 攻击 */
class SegAP extends BaseSeg2Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "攻击";
        NAMES = null;
    }
    
    public SegAP(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegAP(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 双字节, 命中 */
class SegHT extends BaseSeg2Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "命中";
        NAMES = null;
    }
    
    public SegHT(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegHT(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 双字节, 防御 */
class SegDP extends BaseSeg2Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "防御";
        NAMES = null;
    }
    
    public SegDP(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegDP(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 双字节, 闪避 */
class SegEV extends BaseSeg2Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "速度";
        NAMES = null;
    }
    
    public SegEV(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegEV(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 单字节, 武器攻击附加属性 */
class SegWeaponEff extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "附加属性";
        NAMES = NAMELIST.WEAPONEFFCT;
    }
    
    public SegWeaponEff(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegWeaponEff(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 单字节, 命中或者效果出现几率 */
class SegHitChance extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "命中率";
        NAMES = null;
    }
    
    public SegHitChance(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(100);
    }
    
    public SegHitChance(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 双字节, 武器攻击距离 */
class SegAttRange extends BaseSegRange {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "攻击距离";
        NAMES = null;
    }
    
    public SegAttRange(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegAttRange(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }  
}

/** 三字节, 物品使用效果
* 第一字节表示效果种类
* 如果是魔法则二三字节是魔法编号,
* 如果是其他效果则二三字节是效果数值
*/
class SegItemEffect extends BaseSeg3Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "使用效果";
        NAMES = NAMELIST.ITEMEFFECT;
    }
    
    public SegItemEffect(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegItemEffect(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; } 
}

/** 单字节, 高位1表示直线,否则是菱形范围,低四位元表示实际半径 */
class SegEffectRange extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "施放范围";
        NAMES = NAMELIST.RANGETYPE;
    }
    
    public SegEffectRange(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegEffectRange(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; } 
    
    @Override
    public String displayString(){
        int rIndex = getValue() >> 4;
        return String.format("%s: %d", getNameList()[Math.min(1, rIndex)], rIndex&0x7F);
    }
}
 
/** 单字节, 作用对象, 修改无效 */
class SegTarget extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "作用对象";
        NAMES = NAMELIST.ALIGNMENT;
    }
    
    public SegTarget(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegTarget(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; } 
    
    @Override
    public String displayString(){
        int rIndex = getValue() >> 4;
        return getNameList()[Math.min(1, rIndex)];
    }
}

/** 单字节, 影响范围, 最大为3 */
class SegRange extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "影响范围";
        NAMES = null;
    }
    
    public SegRange(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(3);
    }
    
    public SegRange(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; } 
}

/** 双字节, 物品价格 */
class SegPrice extends BaseSeg2Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "价格";
        NAMES = null;
    }
    
    public SegPrice(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        if(NAMES!=null){
            setMax(NAMES.length - 1);
        }
    }
    
    public SegPrice(){
        super(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){ 
        if (NAMES!= null) {
            return NAMES.clone();} 
        else {return null;} 
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; } 
}