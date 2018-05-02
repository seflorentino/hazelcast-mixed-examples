package seflorentino.poc.hz;

import com.hazelcast.config.Config;
import com.hazelcast.config.QueueConfig;
import com.hazelcast.config.QueueStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class QueueProducerApplication {

    public static void main(String[] args) {

        QueueStoreConfig queueStoreConfig = new QueueStoreConfig()
                .setEnabled(true)
                .setFactoryImplementation(new BinaryQueueStoreFactory())
                .setFactoryClassName("")
                .setProperty("binary", "true");

        QueueConfig queueConfig = new QueueConfig("binary-queue")
                .setQueueStoreConfig(queueStoreConfig);

        Config config = new Config()
                .addQueueConfig(queueConfig);

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(new QueueBinaryProducer(hazelcastInstance), 0, 2, TimeUnit.SECONDS);
    }

}
