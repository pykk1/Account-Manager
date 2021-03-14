package AccountsManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Decrypter {
    private Reader reader;
    private List<String> list = new ArrayList<>();
    private List<Account> accountList = new ArrayList<>();

    public Decrypter(File file) {
        this.reader = new Reader(file);
    }

    public List<Account> decryptAccount() {
        List<String> lines = reader.readFromFile();
        String line;
        for (int i = 0; i < lines.size() - 1; i++) {
            String word = "";
            line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (j != line.length()-1) {
                    if (line.charAt(j) != ' ') {
                        word += line.charAt(j);
                    } else {
                        list.add(word);
                        word = "";
                    }
                } else {
                    list.add(word + line.charAt(line.length() - 1));
                }
            }
            setAtributes();
        }
        return accountList;
    }



    private String decryptAtribute(String string) {
        if (string.equals("null")) return null;

        String decryptedString = "";
        int ascii;

        for (int i = 0; i < string.length(); i++) {
            ascii = string.charAt(i) / 3 + 1;
            decryptedString += (char) ascii;
        }
        return decryptedString;
    }

    private void setAtributes(){
        int increment = 0;
        String applicationName = list.get(increment);
        applicationName = decryptAtribute(applicationName);
        increment++;
        String loginId = list.get(increment);
        loginId = decryptAtribute(loginId);
        increment++;
        String password = list.get(increment);
        password = decryptAtribute(password);
        increment++;
        String email = list.get(increment);
        email = decryptAtribute(email);
        increment++;
        String emailPassword = list.get(increment);
        emailPassword = decryptAtribute(emailPassword);
        increment++;
        String securityQuestion1 = list.get(increment);
        securityQuestion1 = decryptAtribute(securityQuestion1);
        increment++;
        String securityQuestion2 = list.get(increment);
        securityQuestion2 = decryptAtribute(securityQuestion2);

        accountList.add(new Account.AccountBuilder(applicationName, loginId, password).email(email).emailPassword(emailPassword).securityQuestion1(securityQuestion1).securityQuestion2(securityQuestion2).build());
        list.removeAll(list);
    }
}