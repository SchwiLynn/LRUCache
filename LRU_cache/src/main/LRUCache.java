package main;

import java.util.HashMap;

/* Operation of LRU cache:
* + get(): operations to get an item from the cache
* + put(): an operation to put a new item to the front of the cache
* + moveToFront(): an operation to move a used item to the front
* + remove: remove the last item in the cache when the cache hits capacity
* How it works:
* + whenever interact with a node, move it to the front (including get() and put()
* node with same key to the cache (aka update)
* + if the size of the cache > capacity, remove the last node.**/
public class LRUCache extends Cache {
    //define Linked list node with key and value
    private class Node {
        int key;
        int value;
        Node prev, next;

        public Node(int k, int v) {
            key = k;
            value = v;
        }
    }
    //HashMap
    private HashMap<Integer, Node> map;
    private Node head, tail;

    public LRUCache(int capacity) {
        super(capacity);
        map = new HashMap<>();
        head = new Node(-1, -1); // dummy head
        tail = new Node(-1, -1); // dummy tail
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        moveToFront(node);
        return node.value;
    }

    @Override
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToFront(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            insertToFront(newNode);
            map.put(key, newNode);
        }
    }

    private void moveToFront(Node node) {
        remove(node);
        insertToFront(node);
    }

    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insertToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
