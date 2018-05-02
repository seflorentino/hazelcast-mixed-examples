package seflorentino.poc.hz;

import com.hazelcast.core.QueueStore;
import com.hazelcast.core.QueueStoreFactory;

import java.util.Properties;

public class BinaryQueueStoreFactory implements QueueStoreFactory {
    @Override
    public QueueStore newQueueStore(String name, Properties properties) {
        return new HashMapQueueStore();
    }
}
