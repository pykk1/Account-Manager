package AccountsManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Keychecker extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {
        UI.observedObject.changeState();
    }
}