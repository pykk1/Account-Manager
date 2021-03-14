package AccountsManager;

public class ObservableObject implements Observer {

    @Override
    public void update() {
        if (UI.password.getText().length() < 5) {
            UI.passwordCheck.setText("Weak");
        } else if (UI.password.getText().length() >= 5 && UI.password.getText().length() < 10) {
            UI.passwordCheck.setText("Medium");
        } else if (UI.password.getText().length() > 10)
            UI.passwordCheck.setText("Strong");
    }
}
