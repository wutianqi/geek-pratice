import java.util.Map;

/**
 *
 * LRU实现类
 */
public class LruCacheImpl<K,V> extends LruCache<K,V> {
    //采用链表存储数据
    private final MyLinkedList<K,V> linkedList = new MyLinkedList<>();


    public LruCacheImpl(int capacity, Storage<K, V> lowSpeedStorage) {
        super(capacity, lowSpeedStorage);
    }

    @Override
    public V get(K key) {
        if(linkedList.get(key) == null){
            //数据不存在，需要从磁盘中获取并放入缓存中
            V value = lowSpeedStorage.get(key);
            if(value == null){
                return null;
            }
            if(linkedList.size() < capacity){
                //容量未达上限，直接将数据存在链表头部
                linkedList.addFirst(key, value);
            } else {
                //容量已达上限，将链表尾部数据删除，将新数据存在链表头部。
                linkedList.removeLast();
                linkedList.addFirst(key,value);
            }

            return value;
        } else {
            //数据存在，将该数据所在节点删除，然后将该数据所在节点移到链表头部
            return linkedList.moveToFirst(key);
        }
    }
}
