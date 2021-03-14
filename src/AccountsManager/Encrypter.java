package AccountsManager;

import java.io.File;

public class Encrypter {
    private Account account;
    private File file;

    public Encrypter(Account account, File file) {
        this.account = account;
        this.file = file;
    }

    public void encryptAccount() {
        String applicationName = account.getApplicationName();
        String loginId = account.getLoginId();
        String password = account.getPassword();
        String email = account.getEmail();
        String emailPassword = account.getEmailPassword();
        String securityQuestion1 = account.getSecurityQuestion1();
        String securityQuestion2 = account.getSecurityQuestion2();

        applicationName = encrypt(applicationName);
        loginId = encrypt(loginId);
        password = encrypt(password);
        email = encrypt(email);
        emailPassword = encrypt(emailPassword);
        securityQuestion1 = encrypt(securityQuestion1);
        securityQuestion2 = encrypt(securityQuestion2);

        Account encryptedAccount = new Account.AccountBuilder(applicationName, loginId, password)
                .email(email)
                .emailPassword(emailPassword)
                .securityQuestion1(securityQuestion1)
                .securityQuestion2(securityQuestion2)
                .build();

        Writer writer = new Writer(encryptedAccount, file);
        writer.writeToFile();
    }

    private static String encrypt(String string) {
        if (string == null) return "null";
        if(string.equals("")) return "null";

        String encryptedString = "";
        int ascii;

        for (int i = 0; i < string.length(); i++) {
            ascii = string.charAt(i) * 3 - 1;
            encryptedString += (char) ascii;
        }
        return encryptedString;
    }

}
