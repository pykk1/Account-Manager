package AccountsManager;

import java.util.List;

public interface ControllerInterface {

    void addAccount(Account account);

    List<Account> loadAccounts();

    void saveAccountList();

    List<Account> getAccounts();

    void removeAccount(int index);
}
