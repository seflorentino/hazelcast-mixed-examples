package seflorentino.poc.hz;

import com.hazelcast.core.QueueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Dummy {@code QueueStore} for emulating a persistent store
 */
public class HashMapQueueStore implements QueueStore<byte[]> {

    private static final Logger logger = LoggerFactory.getLogger(HashMapQueueStore.class);

    private final Map<Long, byte[]> map;

    public HashMapQueueStore() {
        map = new HashMap<>();
    }

    @Override
    public void store(Long key, byte[] value) {
        logger.debug("Storing {}: {}", key, value);
        map.put(key, value);
    }

    @Override
    public void storeAll(Map map) {
        logger.debug("Storing {} entries", map.size());
        this.map.putAll(map);
    }

    @Override
    public void delete(Long key) {
        logger.debug("Deleting entry {}", key);
        map.remove(key);
    }

    @Override
    public byte[] load(Long key) {
        byte[] o = map.get(key);
        logger.debug("Loading entry {}: {}", key, o);
        return o;
    }

    @Override
    public Set<Long> loadAllKeys() {
        logger.debug("Loading all keys");
        return map.keySet();
    }

    @Override
    public Map loadAll(Collection keys) {
        logger.debug("Loading all entries");
        return map;
    }

    @Override
    public void deleteAll(Collection keys) {
        logger.debug("Deleting all entries");
        map.clear();
    }
}
