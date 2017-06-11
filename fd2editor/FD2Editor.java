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
import java.util.Arrays;

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
        String fdName = "../FD2.bin";
        MappedByteBuffer fdBuffer;
        try 
        { RandomAccessFile fdFile = new RandomAccessFile(fdName, "rw");
          FileChannel fdChannel = fdFile.getChannel();
          fdBuffer = fdChannel.map(FileChannel.MapMode.READ_WRITE, 0, fdChannel.size());
        } 
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        fdBuffer.order(ByteOrder.BIG_ENDIAN);
        
        Block b1 = new Block();
        b1.initBlock(RECORDTYPE.法术, fdBuffer, 0);
        b1.read();
        //System.out.print(b1);
        //System.out.print(b1.getBlockLength());
        
        String fieldName = "../FDFIELD.dat";
        MappedByteBuffer fieldBuffer;
        try 
        { RandomAccessFile fieldFile = new RandomAccessFile(fieldName, "rw");
          FileChannel fieldChannel = fieldFile.getChannel();
          fieldBuffer = fieldChannel.map(FileChannel.MapMode.READ_WRITE, 0, fieldChannel.size());
        } 
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
        fieldBuffer.order(ByteOrder.BIG_ENDIAN);
        
        Block stage1 = new Block();
        for(String stage: Arrays.copyOfRange(NAMELIST.CHAPTER, 0, 5)){
            stage1.initBlock(RECORDTYPE.valueOf(stage), fieldBuffer, 0);
            stage1.read();
            System.out.print(stage1);
            System.out.print(stage1.getBlockLength());
            System.out.print("\n");
        }
    }    
}
