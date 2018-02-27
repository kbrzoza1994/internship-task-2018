package pl.codewise.internships;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class Snapshot {

    private final ArrayList<Message> snapshot;

    public Snapshot() {
        snapshot = new ArrayList<>();
    }

    public ArrayList<Message> getSnapshot() {
        for (int i = 0; i < snapshot.size(); i++) {
            if (ChronoUnit.SECONDS.between(snapshot.get(i).getTime(), LocalDateTime.now()) > 300)
                snapshot.remove(i);
        }
        return snapshot;
    }

    public void add(Message message) {
        for (int i = 0; i < snapshot.size(); i++) {
            if (ChronoUnit.SECONDS.between(snapshot.get(i).getTime(), LocalDateTime.now()) > 300)
                snapshot.remove(i);
        }
        if (snapshot.size() < 100)
            snapshot.add(message);
        else {
            snapshot.remove(0);
            snapshot.add(message);
        }
    }

}
