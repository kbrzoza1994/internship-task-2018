package pl.codewise.internships;

import com.sun.xml.internal.bind.v2.TODO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MessageQueueTest {
    private MessageQueue messageQueue;
    private Snapshot snapshot;

    @Before
    public void setUp() {
        messageQueue = new MessageOueue();
        messageQueue.snapshot().getSnapshot().clear();
        snapshot = new Snapshot();
    }

    @Test
    public void addOneMessageWithoutError() {
        Message message = new Message("Test", 200);
        messageQueue.add(message);
        snapshot.add(message);
        Assert.assertEquals(messageQueue.snapshot().getSnapshot().get(0), snapshot.getSnapshot().get(0));
    }

    @Test
    public void AddOneMessageWithErrorCode() {
        Message message = new Message("Test", 404);
        messageQueue.add(message);
        snapshot.add(message);
        Assert.assertEquals(messageQueue.snapshot().getSnapshot(), snapshot.getSnapshot());
        Assert.assertEquals(messageQueue.snapshot().getSnapshot(), snapshot.getSnapshot());
    }

    @Test
    public void ReturnEmptyMapWhenNoMessagesAdded() {
        Assert.assertEquals(messageQueue.snapshot().getSnapshot(), snapshot.getSnapshot());
    }

    @Test
    public void KeepOnlyOneHundredMessages() {
        for (int i = 0; i < 101; i++) {
            messageQueue.add(new Message("Test", 200));
        }
        Assert.assertTrue(messageQueue.snapshot().getSnapshot().size() <= 100);
    }

    @Test
    public void queryForErrorsShouldReturnFortyErrors() {
        for (int i = 0; i < 45; i++) {
            if (i > 4)
                messageQueue.add(new Message("Test", 404));
            else
                messageQueue.add(new Message("Test1", 200));
        }
        Assert.assertEquals(messageQueue.numberOfErrorMessages(), 40);
    }

    @Test
    public void queryForErrorShouldReturnZeroWhenNoErrorCodesWasPassed() {
        for (int i = 0; i < 50; i++) {
            messageQueue.add(new Message("Test", 200));
        }
        Assert.assertEquals(messageQueue.numberOfErrorMessages(), 0);
    }

    @Test
    public void queryForMessagesShouldReturnOnlyMessagesYoungerThanFiveMinutes() {
        for (int i = 0; i < 40; i++) {
            messageQueue.add(new Message("Test", 200));
        }

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 40; i++) {
            long diffInSeconds = ChronoUnit.SECONDS.between(messageQueue.snapshot().getSnapshot().get(i).getTime(), LocalDateTime.now());
            Assert.assertTrue(diffInSeconds < 300);
        }
    }


    //TODO optional test when You uncommentIt will take 5 minutes

    /*@Test
    public void ShouldReturnEmptyArrayListWhenQueryForMessagesAfterLongerThanFiveMinutes(){
        for (int i = 0; i < 20; i++) {
            messageQueue.add(new Message("Test", 200));
        }

        try {
            TimeUnit.SECONDS.sleep(301);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < snapshot.getSnapshot().size(); i++) {
            Assert.assertEquals(snapshot.getSnapshot(), new ArrayList<>());
        }
    }*/


}