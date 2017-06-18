/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.nio.MappedByteBuffer;
import java.util.Arrays;
import java.util.Random;

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
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegUnused(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegFlip(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegSelector(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.LISTUNBOUND);
        setMin(0);
    }
    
    public SegItem(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    /**
     * 物品的设定值如果超出名称列表, 则设为FF. 然后调用父类的setValue()
     * @param whichValue: 设定哪个值
     * @param sValue: 目标值 
     */
    @Override
    public final void setValue(VALUE whichValue, int sValue){
        if (sValue >= getNameList().length) {
            sValue = getMax();
        } 
        super.setValue(whichValue, sValue);
    }
    
    /**
     * 缺省设定当前值. 物品的设定值如果超出名称列表, 则设为FF. 然后调用父类的setValue()
     * @param sValue: 目标值 
     */
    @Override
    public final void setValue(int sValue){
        this.setValue(VALUE.CURRENT, sValue);
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
        setMax(MAXMODE.LISTBOUND);
        setMin(0);
    }
    
    public SegItemType(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegAP(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegHT(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegDP(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegEV(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.LISTBOUND);
        setMin(0);
    }
    
    public SegWeaponEff(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMin(0);
        setMax(100);
    }
    
    public SegHitChance(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegAttRange(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.LISTBOUND);
        setMin(0);
    }
    
    public SegItemEffect(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.LISTBOUND);
        setMin(0);
    }
    
    public SegEffectRange(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    /** 
     * 获取字节前四位, 对应攻击覆盖方式 
     * @return 
     */
    public int getMode(){
        return this.getValue() >> 4;
    }
    
    /**
     * 获取字节后四位, 对应覆盖范围
     * @return 
     */
    public int getRange(){
        return this.getValue() & 0x0F;
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
        return String.format("%s: %2d", getNameList()[Math.min(1, getMode())], getRange());
    }
}
 
/** 单字节, 作用对象, 修改无效 */
class SegAlign extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "阵营";
        NAMES = NAMELIST.ALIGNMENT;
    }
    
    public SegAlign(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.LISTBOUND);
        setMin(0);
    }
    
    public SegAlign(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMin(0);
    }
    
    public SegRange(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegPrice(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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

/** 单字节, 法力消耗值 */
class SegMPCost extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "法力消耗";
        NAMES = null;
    }
    
    public SegMPCost(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegMPCost(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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

/** 单字节, 角色种族 */
class SegRace extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "种族";
        NAMES = NAMELIST.RACE;
    }
    
    public SegRace(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.LISTBOUND);
        setMin(0);
    }
    
    public SegRace(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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

/** 单字节, 角色职业 */
class SegClass extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "职业";
        NAMES = NAMELIST.CLASS;
    }
    
    public SegClass(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.LISTBOUND);
        setMin(0);
    }
    
    public SegClass(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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

/** 单字节, 角色出场等级, 有符号, 最大127 */
class SegLevel extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "等级";
        NAMES = null;
    }
    
    public SegLevel(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(127);
        setMin(0);
    }
    
    public SegLevel(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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

/** 双字节, 角色出场生命值 */
class SegHP extends BaseSeg2Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "生命";
        NAMES = null;
    }
    
    public SegHP(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegHP(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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

/** 双字节, 角色出场法力值 */
class SegMP extends BaseSeg2Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "法力";
        NAMES = null;
    }
    
    public SegMP(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegMP(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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

/** 单字节, 角色出场移动力 */
class SegMV extends BaseSeg1Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
     
    static {
        SEGNAME = "移动力";
        NAMES = null;
    }
    
    public SegMV(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegMV(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
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

/** 四字节, 角色出场已知魔法, 用bit位表示 */
class SegKnownMagic extends BaseSeg4Byte {
    
    private static final String SEGNAME;
    private static final String[] NAMES;
    private static final String[] MAGICS;
     
    static {
        SEGNAME = "已知魔法";
        NAMES = null;
        MAGICS = NAMELIST.MAGIC;
    }
    
    public SegKnownMagic(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegKnownMagic(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public String displayString(){
        return Arrays.toString(this.segBytes2Hex());
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

/** 双字节, 攻击增长最小/最大值 */
class SegAPInc extends BaseSegRange {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "攻击增长";
        NAMES = null;
    }
    
    public SegAPInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegAPInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 双字节, 防御增长最小/最大值 */
class SegDPInc extends BaseSegRange {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "防御增长";
        NAMES = null;
    }
    
    public SegDPInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegDPInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 双字节, 速度增长最小/最大值 */
class SegEVInc extends BaseSegRange {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "速度增长";
        NAMES = null;
    }
    
    public SegEVInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegEVInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 双字节, 生命增长最小/最大值 */
class SegHPInc extends BaseSegRange {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "生命增长";
        NAMES = null;
    }
    
    public SegHPInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegHPInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 双字节, 法力增长最小/最大值 */
class SegMPInc extends BaseSegRange {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "法术增长";
        NAMES = null;
    }
    
    public SegMPInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegMPInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 单字节, 可学习法术列表 */
class SegMagicSeq extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "法术序列";
        NAMES = NAMELIST.MAGICSEQ;
    }
    
    public SegMagicSeq(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.LISTUNBOUND);
        setMin(0);
    }
    
    public SegMagicSeq(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    /**
     * 法术序列的设定值如果超出名称列表, 则设为FF. 然后调用父类的setValue()
     * @param whichValue: 设定哪个值
     * @param sValue: 目标值 
     */
    @Override
    public final void setValue(VALUE whichValue, int sValue){
        if (sValue >= getNameList().length) {
            sValue = getMax();
        } 
        super.setValue(whichValue, sValue);
    }
    
    /**
     * 缺省设定当前值. 物品的设定值如果超出名称列表, 则设为FF. 然后调用父类的setValue()
     * @param sValue: 目标值 
     */
    @Override
    public final void setValue(int sValue){
        this.setValue(VALUE.CURRENT, sValue);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 单字节, 法术名称 */
class SegMagic extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "法术";
        NAMES = NAMELIST.MAGICNONULL;
    }
    
    public SegMagic(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.LISTBOUND);
        setMin(0);
    }
    
    public SegMagic(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 职业魔防数据段, 每个纪录4 bytes, 四字节, 后面三个似乎没用到 */
class SegMagicResist extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    private static final byte BYTEMAX;
    
    static {
        SEGNAME = "魔防";
        NAMES = null;
        BYTEMAX = 10;
    }
    
    public SegMagicResist(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(BYTEMAX);
        setMin(0);
    }
    
    public SegMagicResist(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    /**
     *  按四个位输出魔法抗性
     * @return String: 魔法抗性组成的字符串
     */
    @Override
    public final String displayString(){
        return(String.format("%d%%,", (10-getValue())*10));
    }
    
    @Override
    public final void setValue(int sValue){
        this.setValue(VALUE.CURRENT, sValue);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 单字节, 敌人每级生命增长 */
class SegEnemyHPInc extends BaseSeg2Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "生命增长";
        NAMES = null;
    }
    
    public SegEnemyHPInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegEnemyHPInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}


/** 单字节, 敌人每级法力增长 */
class SegEnemyMPInc extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "法力增长";
        NAMES = null;
    }
    
    public SegEnemyMPInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegEnemyMPInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 单字节, 敌人每级攻击增长 */
class SegEnemyAPInc extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "攻击增长";
        NAMES = null;
    }
    
    public SegEnemyAPInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegEnemyAPInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 单字节, 敌人每级防御增长 */
class SegEnemyDPInc extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "防御增长";
        NAMES = null;
    }
    
    public SegEnemyDPInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegEnemyDPInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 单字节, 敌人每级速度增长 */
class SegEnemyEVInc extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "速度增长";
        NAMES = null;
    }
    
    public SegEnemyEVInc(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegEnemyEVInc(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 单字节, 敌人对应经验值 */
class SegEnemyExp extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "经验值";
        NAMES = null;
    }
    
    public SegEnemyExp(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegEnemyExp(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 单字节, 肖像 */
class SegPotrait extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "头像";
        NAMES = null;
    }
    
    public SegPotrait(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegPotrait(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 单字节, 出场时的回合数 */
class SegRound extends BaseSeg1Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    
    static {
        SEGNAME = "出场回合";
        NAMES = null;
    }
    
    public SegRound(String fName, MappedByteBuffer fBuffer,  String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.NUM);
        setMin(0);
    }
    
    public SegRound(){
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    @Override
    public final String getSegName(){ return SEGNAME;}
}

/** 四字节, 第一字节是0则是物品,1则是金钱, 后三字节, 如果是物品则第一字节是编号, 如果是金钱则是数目 */
class SegDrop extends BaseSeg4Byte {
    private static final String SEGNAME;
    private static final String[] NAMES;
    private static final String[] ITEMS;
    private static final int[] GOODITEMS;
    
    static {
        SEGNAME = "掉落";
        NAMES= NAMELIST.DROP;
        ITEMS = NAMELIST.ITEM;
        GOODITEMS = NAMELIST.GOODITEM;
    }
    
    public SegDrop (String fName, MappedByteBuffer fBuffer, String bName, String rName, long fOffset, long bStart){
        super(fName, fBuffer, bName, rName, fOffset, bStart);
        setMax(MAXMODE.LISTBOUND);
        setMin(0);
    }
    
    public SegDrop() {
        this(BaseSeg.NOTINTIATED, null, BaseSeg.NOTINTIATED, BaseSeg.NOTINTIATED, 0, 0);
    }
 
    public final String getMode(){
        switch(getSegBytes()[0]){
            case 1:
                return NAMES[1];
            case (byte) 0xFF:
                return BaseSeg.VOIDNAME;
            default:
                return NAMES[0];
        }
    }
    
    public final String getContent(){
        switch(getSegBytes()[0]) {
            case 1:
                return String.valueOf(BaseSeg.bytes2Int(Arrays.copyOfRange(getSegBytes(), 1, 4)));
            case (byte) 0xFF:
                return BaseSeg.VOIDNAME;   
            default:
                int index = getSegBytes()[1]&0xFF;
                if(index <ITEMS.length & index >= 0) {
                    return ITEMS[index];
                } else {
                    return BaseSeg.VOIDNAME;
                }
        }
        
    }
    
    @Override
    public void setValue(VALUE whichValue, int vNumber){
        byte[] bArray = BaseSeg.int2Bytes(vNumber);
        switch (bArray[0]) {
            case 1:
                break;
            case (byte)0xFF:
                for(int i=1;i<4;i++){
                    bArray[i] = 0;
                }       
            default:
                bArray[0] = 0;
                int index = bArray[1] & 0xFF;
                if(index >= ITEMS.length | index < 0 ) {
                    int rIndex = new Random().nextInt(GOODITEMS.length);
                    bArray[1] = (byte) GOODITEMS[rIndex];
                }  
        }
        super.setValue(whichValue, BaseSeg.bytes2Int(bArray));   
    }
    
    @Override
    public void setValue(int vNumber){
        this.setValue(VALUE.CURRENT, vNumber);
    }
    
    @Override
    public final String displayString(){
        return String.format("%s: %s", getMode(), getContent());
    }
    
    @Override
    public final String[] getNameList(){
        if (NAMES == null) {
            return null;
        } else {
            return NAMES.clone();
        }
    }
    
    @Override
    public final String getSegName(){ return SEGNAME; }
    
}