/**
 * 存储数据的链表
 */
public class MyLinkedList<K,V> {
    //数据量
    private Integer size;

    //根节点
    private Node<K,V> root;

    /**
     * 查找数据
     * @param key
     * @return
     */
    public V get(K key){
        if(root == null){
            return null;
        }

        Node<K,V> p = root;
        while (p != null){
            if(p.key.equals(key)){
                return p.value;
            }
            p = p.next;
        }

        return null;
    }

    /**
     * 添加数据
     * @param key
     * @param value
     */
    public void addFirst(K key, V value) {
        Node node = new Node(key,value);
        node.next = root;
        root = node;
        size++;
    }

    /**
     * 删除尾部元素
     */
    public void removeLast() {
        Node p = root;
        //记录当前节点的上一个节点
        Node pre = null;
        while (p.next != null){
            pre = p;
            p = p.next;
        }

        pre.next = null;
        size--;
    }

    /**
     * 将指定数据移到头部
     * @param key
     * @return
     */
    public V moveToFirst(K key) {
        Node<K,V> removeNode = remove(key);

        addFirst(removeNode.key,removeNode.value);

        return removeNode.value;
    }

    /**
     * 删除某个节点
     * @param key
     * @return
     */
    public Node<K,V> remove(K key) {
        Node p = root;
        //目标节点的上一个节点
        Node pre = null;
        //目标节点的下一个节点
        Node next = root.next;

        while (p != null && !p.key.equals(key)){
            pre = p;
            p = p.next;
            next = p.next;
        }

        if(p == null){
            //没找到
            return null;
        }

        //将和p关联的节点置为空
        p.pre = null;
        p.next = null;
        //将目标节点的上一个节点的下一个节点指向目标节点的下一个节点
        pre.next = next;
        //将目标节点的下一个节点的上一个节点指向目标节点的上一个几点
        next.pre = pre;

        size--;

        return p;
    }


    public int size(){
        return this.size;
    }




    /**
     * 链表中的几点
     */
    class Node<K,V>{
        K key;
        V value;
        //上一节点
        Node pre;
        //下一节点
        Node next;

        Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }
}
