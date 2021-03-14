package AccountsManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ControllerInterface {

    private List<Account> accounts = new ArrayList<>();
    File file = new File("Accounts.txt");
    private String masterPassword = "tiamzissanouiti";
    private String userPassword = "1234";

    public Controller() {
    }

    @Override
    public void addAccount(Account account) {
        if(!accounts.contains(account)){
            accounts.add(account);
        }
    }

    @Override
    public List<Account> loadAccounts() {
        Decrypter decrypter = new Decrypter(file);
        accounts = decrypter.decryptAccount();
        return accounts;
    }

    @Override
    public void saveAccountList() {
        if (accounts != null &&this.file.delete()) {
            for (Account account : accounts) {
                Encrypter encrypter = new Encrypter(account, file);
                encrypter.encryptAccount();
            }
        }
    }

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public void removeAccount(int index) {
        accounts.remove(index);
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
