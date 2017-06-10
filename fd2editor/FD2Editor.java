/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fd2editor;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.io.FileNotFoundException;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

/**
 *
 * @author CIDER
 */
public class FD2Editor {

    /**
     * @param args the command line arguments
     * @throws FileNotFoundException
     * @throws IOException
     * @throws java.lang.NoSuchMethodException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchMethodException{
        String fileName = "../FD2.bin";
        MappedByteBuffer fBuffer;
        try 
        { RandomAccessFile fdFile = new RandomAccessFile(fileName, "rw");
          FileChannel fChannel = fdFile.getChannel();
          fBuffer = fChannel.map(FileChannel.MapMode.READ_WRITE, 0, fChannel.size());
        } 
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        fBuffer.order(ByteOrder.BIG_ENDIAN);
        SegItem si = new SegItem("FD2.bin", fBuffer, "TestBlock", "TestRecord", 0x00792C1, 0x00792C1);
        si.read();
        //System.out.print(si);
        
        SEGTYPE st1 = SEGTYPE.未使用;
        BaseSeg bs1 = st1.createSeg();
        bs1.initSeg(fileName, fBuffer, "test", "test1", 890, 0);
        bs1.read();
        //System.out.print(bs1);
        
        Record rc1 = new Record();
        SEGTYPE[] tList = {SEGTYPE.物品类型, SEGTYPE.攻击, SEGTYPE.命中, SEGTYPE.防御, SEGTYPE.速度, SEGTYPE.附加属性,
            SEGTYPE.命中率, SEGTYPE.攻击距离, SEGTYPE.使用效果, SEGTYPE.施放范围, SEGTYPE.作用对象, SEGTYPE.影响范围, SEGTYPE.价格, SEGTYPE.未使用, SEGTYPE.未使用};
        rc1.initRecord("FD.bin", fBuffer, "物品", "长剑", 0x00792d8, 0, tList);
        rc1.read();
        //System.out.print(rc1);
        
        Block b1 = new Block();
        b1.initBlock(RECORDTYPE.角色, fBuffer, 0);
        b1.read();
        System.out.print(b1);
        System.out.print(b1.getBlockLength());
   
    }    
}
