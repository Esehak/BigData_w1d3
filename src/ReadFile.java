import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Esehak on 5/4/2017.
 */
public class ReadFile {

    public static String readFile(String filename) {
        String content = null;
        File file = new File(filename);
        FileReader reader= null;

        try{
            reader= new FileReader(file);
            char[] chars= new char[(int) file.length()];
            reader.read(chars);
            content= new String(chars);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }
}