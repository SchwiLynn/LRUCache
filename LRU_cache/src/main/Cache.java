package main;

public abstract class Cache {
    public int capacity;

    public Cache(int capacity) {
        this.capacity = capacity;
    }
    //these should work in O(1) time
    //Need: fast lookups by key ->HashMap<Integer, Node>
    //Need: Track order of usage -> reorder nodes on every
    // access (move most recently used to front
    // and also need to remove the least recently used item
    // => DLL of Nodes (allows insert/delete in O(1) at beginning and at the end of the dsa)
    public abstract int get(int key);
    public abstract void put(int key, int value);
}


