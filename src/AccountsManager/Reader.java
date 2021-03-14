package AccountsManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private File file;

    public Reader(File file) {
        this.file = file;
    }

    public List<String> readFromFile(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<String> list = new ArrayList<>();
            String line = bufferedReader.readLine();
            list.add(line);
            while(line != null){
                line = bufferedReader.readLine();
                list.add(line);
            }
            bufferedReader.close();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
