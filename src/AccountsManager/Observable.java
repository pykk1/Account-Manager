package AccountsManager;

import java.util.ArrayList;
import java.util.List;

class Observable {
    private List<Observer> observers = new ArrayList<Observer>();

    public void changeState() {
        notifyAllObservers();
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
