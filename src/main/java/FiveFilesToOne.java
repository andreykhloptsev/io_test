import java.io.*;
import java.util.*;

/**
 * Created by DIO
 */
public class FiveFilesToOne {

    public static void main(String[] args) {
        List<File> list = fiveFiles();
        try {
            sequanceFiles(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<File> fiveFiles(){
        List<File> list = new ArrayList<>();
        for (byte i = 0; i <5 ; i++) {
            try {
                list.add(createFile("text "+i,i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static File createFile(String name, byte text) throws IOException {
        File f= new File("./src/main/resources/"+name+".txt");
        try (OutputStream fos = new FileOutputStream(f)) {
            for (Integer i = 0; i <50 ; i++) {
                fos.write(text);
            }
        }
        return f;
    }

    public static <T extends File> File sequanceFiles(Collection<T> files) throws IOException{
        File file = null;
        ArrayList<InputStream> inputStreams = new ArrayList<>(files.size());
        for (T f:files
             ) {
            try {InputStream fis= new FileInputStream(f);
                inputStreams.add(fis);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            Enumeration<InputStream> er = Collections.enumeration(inputStreams);
            InputStream sis = new SequenceInputStream(er);
            file = new File("./src/main/resources/res.txt");
            OutputStream fos = new DataOutputStream(new FileOutputStream(file));
            while(sis.read()!=-1){
                fos.write(sis.read());
            }
            fos.flush();
            fos.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            for (InputStream is :inputStreams
                 ) {
                is.close();
            }
        }
        return file;
    }
}
