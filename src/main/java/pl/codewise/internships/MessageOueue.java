package pl.codewise.internships;

import java.util.ArrayList;

public class MessageOueue implements MessageQueue {


    public void add(Message message) {
        snapshot.add(message);
    }

    public Snapshot snapshot() {
        return snapshot;
    }

    public int numberOfErrorMessages() {
        ArrayList<Message> listOfMessages = snapshot.getSnapshot();
        int numberOfErrorMessages = 0;

        for (int i = 0; i < listOfMessages.size(); i++) {
            if (listOfMessages.get(i).getErrorCode() >= 400)
                numberOfErrorMessages = numberOfErrorMessages + 1;
        }
        return numberOfErrorMessages;
    }
}
