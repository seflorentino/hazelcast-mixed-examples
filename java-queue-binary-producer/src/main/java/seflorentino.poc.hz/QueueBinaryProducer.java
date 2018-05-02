package seflorentino.poc.hz;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;

import java.util.Date;

public class QueueBinaryProducer implements Runnable {

    private final HazelcastInstance hazelcastInstance;

    public QueueBinaryProducer(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    public void run() {
        // Add something to the queue
        JsonObject json = new JsonObject();
        json.put("message", "A message from " + new Date().toString());
        json.put("timestamp", System.currentTimeMillis());

        try {
            IQueue<byte[]> queue = hazelcastInstance.getQueue("binary-queue");
            queue.put(json.toString().getBytes());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
