
import java.io.*;

/**
 * Created by DIO
 */
public class ByteApp {

    public static void main(String[] args) {
        try {
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bInputStream();
    }

    public static void createFile() throws IOException{
        File f= new File("./src/main/resources/file.txt");
        try (OutputStream fos = new FileOutputStream(f)) {
            for (Integer i = 0; i <50 ; i++) {
                fos.write(i.byteValue());
            }
        }
    }

    public static void bInputStream(){
        byte[] barr= new byte[50];
        try( InputStream fis = new FileInputStream("./src/main/resources/file.txt")){
                fis.read(barr);
            for (int i = 0; i <barr.length ; i++) {
                System.out.println(barr[i]);
            }
        } catch (IOException e ){
            e.printStackTrace();
        }

    }



    public static void bOutputStream(){

    }

}
