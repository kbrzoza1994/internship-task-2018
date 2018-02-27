package pl.codewise.internships;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SnapshotTest {
    private List<Message> testSnapshot;
    private Snapshot snapshot;

    @Before
    public void setUp() {
        testSnapshot = new ArrayList<>();
        snapshot = new Snapshot();
    }

    @Test
    public void snapshotShouldAddMessageWithoutError() {
        Message testMessage = new Message("Test", 200);

        snapshot.add(testMessage);
        testSnapshot.add(testMessage);

        Assert.assertEquals(snapshot.getSnapshot(), testSnapshot);
    }

    @Test
    public void snapshotShouldAddMessageWithError() {
        Message testMessage = new Message("Test", 404);

        snapshot.add(testMessage);
        testSnapshot.add(testMessage);

        Assert.assertEquals(snapshot.getSnapshot(), testSnapshot);
    }

    @Test
    public void snapshotShouldReturnProperSnapshot() {
        for (int i = 0; i < 45; i++) {
            snapshot.add(new Message("Test", 200));
        }

        Assert.assertEquals(snapshot.getSnapshot().size(), 45);
    }

    @Test
    public void shouldReturnEmptyListWhenQueryForEmptySnapshot() {
        Assert.assertEquals(snapshot.getSnapshot(), new ArrayList<>());
    }

}
