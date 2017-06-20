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
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CompareFile {
    
    FileInputStream fileStream1;
    FileInputStream fileStream2;

    public CompareFile(String file1, String file2) throws FileNotFoundException {
        this.fileStream1 = new FileInputStream(file1);
        this.fileStream2 = new FileInputStream(file2);
    }
    
    public final void proceed() throws IOException{
        int b1;
        int b2;
        int count = 0;
        int size = Math.min(fileStream1.available(), fileStream2.available());
        System.out.println(size);
        for(int i=0; i<size;i++){
            b1 = fileStream1.read();
            b2 = fileStream2.read();
            if (b1 != b2) {
                System.out.print(String.format("0x%07X", count) + ":  " + b1 + " -> " + b2 + "\n");
            } 
            count++;
        }
    }
}

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
        String fdName = "../FD2副本.bin";
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
        //b1.read();
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
        //stage1.initBlock(RECORDTYPE.爆击, fdBuffer, 0);
        //stage1.read();
        //System.out.print(stage1);
        //System.out.print(stage1.getBlockLength());
        //System.out.print("\n");
        
        /*
        SWITCHTYPE st = SWITCHTYPE.行走加速;
        Flips sch = new Flips(st, fdBuffer);
        System.out.print(sch);
        sch.turnON();
        sch.write();
        System.out.print(sch);
        sch.turnOFF();
        System.out.print(sch);
        sch = new Flips(st, fdBuffer);
        System.out.print(sch);
        sch.turnON();
        System.out.print(sch);
        sch = new Flips(st, fdBuffer);
        System.out.print(sch);*/
        
        Pattern sPattern = Pattern.compile("(?:0[xX])?([0-9a-fA-F]*(\\?\\?)*)+");
        Matcher sMatch = sPattern.matcher("0x56we7f??45?ff");
        StringBuilder s = new StringBuilder();
        while(sMatch.find()) {
            s.append(String.valueOf(sMatch.group()));
        }
        String s1 = s.toString().replaceAll("0x|0X", "");
        System.out.println(s1 + s1.length() + Pattern.compile(s1).toString().length());
        
        String[] sArray = new String[] {"ab", "cd", "11"};
        System.out.println(String.join("", sArray));
        
    }    
}
