import java.util.*;
import java.io.*;


public class randomInput {
    private int x, y, z;
    private Random rand;
    randomInput(){
        x = 0;
        y = 0;
        z = 0;
        rand = new Random();
    }
    /**
     * @input n, k, f (1 => manhattan, 0 => euclidian)
     */
    public void write(int n, int k, int f) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        writer.write("" + n);
        writer.append(System.getProperty("line.separator"));
        writer.write("" + k);
        writer.append(System.getProperty("line.separator"));
        writer.write("" + f);
        writer.append(System.getProperty("line.separator"));
        for(int i = 0; i < n; i++){
            x = rand.nextInt(500);
            y = rand.nextInt(500);
            z = rand.nextInt(500);
            writer.append(x + " " + y + " " + z);
            writer.append(System.getProperty("line.separator"));
        }
        writer.close();
    }
    public void delete(){
        File output = new File("output.txt");
        output.delete();
    }
    public static void main(String[] args) throws IOException {
        randomInput generator = new randomInput();
        if(args.length != 3) {
            System.err.println("Usage: java randomInput n k f");
        }
        generator.delete();
        generator.write(new Integer (args[0]), 
            new Integer(args[1]),
            new Integer(args[2]));
        
    }
}