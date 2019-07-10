import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * Created by DIO
 */
public class BookReader {

    final static int PAGE_SIZE=1800;
    static long first_page=1;
    static long  last_page=1;
    static long page=1;
    static boolean flag=true;
    private static RandomAccessFile raf= null;
    public  static Scanner scanner= new Scanner(System.in);

    static {
        try {raf = new RandomAccessFile("./src/main/resources/lotr.txt", "r");
            last_page=raf.length()/PAGE_SIZE;
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void readBook(){
        while(flag){
            print(page);
            System.out.println("Start reading book");
            System.out.println("If you want to go to previous page print /p");
            System.out.println("If you want to go to next page print /n");
            System.out.println("If you want to go to n page print /page");
            System.out.println("where n is number of page");
            System.out.println("Print /e to exit");
            switch (scanner.next()) {
                case "/p":
                    if (page>1){
                        page--;
                    } else System.out.println("First page");
                    break;
                case "/n":
                    if (page<last_page){
                        page++;
                    }else System.out.println("Last page");
                    break;
                case "/e":
                    flag=false;
                    break;
                case "/page":
                    System.out.println("print number of page:");
                    page=scanner.nextInt();
                    break;
            }
        }
    }

    private static void print(long page){
        try {
            raf.seek((page-1)*PAGE_SIZE);
            for (int i = 0; i <PAGE_SIZE ; i++) {
                System.out.print((char)raf.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
