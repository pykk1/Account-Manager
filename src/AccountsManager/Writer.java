package AccountsManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private Account account;
    private File file;

    public Writer(Account account, File file) {
        this.account = account;
        this.file = file;
    }

    public void writeToFile(){
        try {
            FileWriter fileWriter = new FileWriter(this.file,true);
            fileWriter.write(account.toString());
            fileWriter.write("\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
