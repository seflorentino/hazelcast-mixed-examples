package seflorentino.poc.hz;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Selfish client. Never gives; only takes
 */
public class QueueConsumerApplication {

    private static final Logger logger = LoggerFactory.getLogger(QueueConsumerApplication.class);

    public static void main(String[] args) {

        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);

        IQueue<Object> queue = hazelcastInstance.getQueue("binary-queue");


        while (hazelcastInstance.getLifecycleService().isRunning()) {
            try {
                logger.debug("{}", queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
