package chapter7.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ServerTest {
    @Test
    void shouldSaveTask() throws InterruptedException {
        //DOC of SUT
        ExecutorService executorService = Executors.newCachedThreadPool();
        var taskService = mock(TaskService.class);


        var task = mock(Task.class);

        Collection<Task> listOfTasks = Arrays.asList(task);

        //SUT
        Server server = new Server(executorService, taskService);

        server.serve(listOfTasks);

        // flag that allow us to verify whether the handle() has been executed.
        boolean handleMethodInvoked =false;
        for (int i = 0; i<10 && !handleMethodInvoked; i++) {
            try {
                verify(taskService).handle(task);
                handleMethodInvoked = true;
            } catch (AssertionError e) {
                // nothing
            }
            TimeUnit.MICROSECONDS.sleep(100);
        }
        assertThat(handleMethodInvoked).isTrue();
    }
}