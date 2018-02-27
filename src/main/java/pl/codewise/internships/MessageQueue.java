package pl.codewise.internships;

public interface MessageQueue {

    Snapshot snapshot = new Snapshot();

    void add(Message message);

    Snapshot snapshot();

    int numberOfErrorMessages();
}
