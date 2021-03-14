package AccountsManager;

public class Account {
    //required atributes
    private String applicationName;
    private String loginId;
    private String password;

    //optional atributes
    private String email;
    private String emailPassword;
    private String securityQuestion1;
    private String securityQuestion2;

    public String getApplicationName() {
        return applicationName;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public String getSecurityQuestion1() {
        return securityQuestion1;
    }

    public String getSecurityQuestion2() {
        return securityQuestion2;
    }


    private Account(AccountBuilder builder) {
        this.applicationName = builder.applicationName;
        this.loginId = builder.loginId;
        this.password = builder.password;
        this.email = builder.email;
        this.emailPassword = builder.emailPassword;
        this.securityQuestion1 = builder.securityQuestion1;
        this.securityQuestion2 = builder.securityQuestion2;
    }

    @Override
    public String toString() {
        return applicationName+" "+loginId+" "+password+" "+email+" "+emailPassword+" "+securityQuestion1+" "+securityQuestion2;
    }

    public static class AccountBuilder {
        private String applicationName;
        private String loginId;
        private String password;
        private String email;
        private String emailPassword;
        private String securityQuestion1;
        private String securityQuestion2;

        public AccountBuilder(String applicationName, String loginId, String password) {
            this.applicationName = applicationName;
            this.loginId = loginId;
            this.password = password;
        }
        public AccountBuilder email(String email) {
            this.email = email;
            return this;
        }
        public AccountBuilder emailPassword(String emailPassword) {
            this.emailPassword = emailPassword;
            return this;
        }
        public AccountBuilder securityQuestion1(String securityQuestion1) {
            this.securityQuestion1 = securityQuestion1;
            return this;
        }
        public AccountBuilder securityQuestion2(String securityQuestion2) {
            this.securityQuestion2 = securityQuestion2;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return applicationName.equals(account.applicationName) &&
                loginId.equals(account.loginId);
    }
}
