package com.datastructure;

import java.util.Map.Entry;

public class MyMap {
    protected static final Object NULL = new Object();
    /*Transient in Java is used to mark the member variable not to be serialized when it is persisted to streams of bytes. 
    This keyword plays an important role to meet security constraints in Java. 
    It ignores the original value of a variable and saves the default value of that variable data type.   
     */
    // Initialize the array of objects
    protected transient MyHashEntry[] data;
    protected transient int size;
    protected transient int threshold;
    protected transient int modCount;
    protected transient float loadFactor;


    public static void main(String[] args) {
        MyMap myMap = new MyMap();
        myMap.put("a", "b");
        System.out.println("value of key = " + myMap.get("a"));

        myMap.remove("a");
        System.out.println("value of key = " + myMap.get("a"));
    }

    public MyMap() {
        data = new MyHashEntry[5];
    }

    public Object put(Object key, Object value) {
        key = this.convertKey(key);
        int hashCode = this.hash(key);
        int index = this.hashIndex(hashCode, this.data.length);

        //If key already exists update the value
        for (MyHashEntry entry = this.data[index]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(key, entry.key)) {
                Object oldValue = entry.getValue();
                this.updateEntry(entry, value);
                return oldValue;
            }
        }

        //If key doesn't exist add a new Object to the map
        this.addMapping(index, hashCode, key, value);
        return null;
    }

    protected Object convertKey(Object key) {
        return key == null ? NULL : key;
    }

    protected int hashIndex(int hashCode, int dataSize) {
        return hashCode & dataSize - 1;
    }

    protected int hash(Object key) {
        int h = key.hashCode();
        h += ~(h << 9);
        h ^= h >>> 14;
        h += h << 4;
        h ^= h >>> 10;
        return h;
    }

    protected boolean isEqualKey(Object key1, Object key2) {
        return key1 == key2 || key1.equals(key2);
    }

    protected void updateEntry(MyHashEntry entry, Object newValue) {
        entry.setValue(newValue);
    }

    protected void addMapping(int hashIndex, int hashCode, Object key, Object value) {
        ++this.modCount;
        // Initialize the object
        MyHashEntry entry = this.createEntry(this.data[hashIndex], hashCode, key, value);
        //Add the object to the map
        this.addEntry(entry, hashIndex);
        ++this.size;
        //
        this.checkCapacity();
    }

    protected MyHashEntry createEntry(MyHashEntry next, int hashCode, Object key, Object value) {
        return new MyHashEntry(next, hashCode, key, value);
    }

    protected void addEntry(MyHashEntry entry, int hashIndex) {
        this.data[hashIndex] = entry;
    }

    protected void checkCapacity() {
        if (this.size >= this.threshold) {
            int newCapacity = this.data.length * 2;
            if (newCapacity <= 1073741824) {
                this.ensureCapacity(newCapacity);
            }
        }
    }

    // In ensureCapacity make sure to copy from old map to new map.
    protected void ensureCapacity(int newCapacity) {
        int oldCapacity = this.data.length;
        if (newCapacity > oldCapacity) {
            if (this.size == 0) {
                this.threshold = this.calculateThreshold(newCapacity, this.loadFactor);
                this.data = new MyHashEntry[newCapacity];
            } else {
                MyHashEntry[] oldEntries = this.data;
                MyHashEntry[] newEntries = new MyHashEntry[newCapacity];
                ++this.modCount;

                for (int i = oldCapacity - 1; i >= 0; --i) {
                    MyHashEntry entry = oldEntries[i];
                    if (entry != null) {
                        oldEntries[i] = null;

                        MyHashEntry next;
                        do {
                            next = entry.next;
                            int index = this.hashIndex(entry.hashCode, newCapacity);
                            entry.next = newEntries[index];
                            newEntries[index] = entry;
                            entry = next;
                        } while (next != null);
                    }
                }

                this.threshold = this.calculateThreshold(newCapacity, this.loadFactor);
                this.data = newEntries;
            }

        }
    }

    protected int calculateThreshold(int newCapacity, float factor) {
        return (int) ((float) newCapacity * factor);
    }

    public Object get(Object key) {
        key = this.convertKey(key);
        int hashCode = this.hash(key);

        for (MyHashEntry entry = this.data[this.hashIndex(hashCode, this.data.length)]; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(key, entry.key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public Object remove(Object key) {
        key = this.convertKey(key);
        int hashCode = this.hash(key);
        int index = this.hashIndex(hashCode, this.data.length);
        MyHashEntry entry = this.data[index];

        //Loop to get the entry
        for (MyHashEntry previous = null; entry != null; entry = entry.next) {
            if (entry.hashCode == hashCode && this.isEqualKey(key, entry.key)) {
                Object oldValue = entry.getValue();
                this.removeMapping(entry, index, previous);
                return oldValue;
            }
            previous = entry;
        }
        return null;
    }

    protected void removeMapping(MyHashEntry entry, int hashIndex, MyHashEntry previous) {
        ++this.modCount;
        this.removeEntry(entry, hashIndex, previous);
        --this.size;
        this.destroyEntry(entry);
    }

    protected void removeEntry(MyHashEntry entry, int hashIndex, MyHashEntry previous) {
        if (previous == null) {
            this.data[hashIndex] = entry.next;
        } else {
            previous.next = entry.next;
        }
    }

    protected void destroyEntry(MyHashEntry entry) {
        entry.next = null;
        entry.key = null;
        entry.value = null;
    }

    protected static class MyHashEntry {
        protected MyHashEntry next;
        protected int hashCode;
        protected Object key;
        protected Object value;

        protected MyHashEntry(MyHashEntry next, int hashCode, Object key, Object value) {
            this.next = next;
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return this.key == NULL ? null : this.key;
        }

        public Object getValue() {
            return this.value;
        }

        public Object setValue(Object value) {
            Object old = this.value;
            this.value = value;
            return old;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            } else if (!(obj instanceof Entry)) {
                return false;
            } else {
                boolean var10000;
                label43:
                {
                    label29:
                    {
                        Entry other = (Entry) obj;
                        if (this.getKey() == null) {
                            if (other.getKey() != null) {
                                break label29;
                            }
                        } else if (!this.getKey().equals(other.getKey())) {
                            break label29;
                        }

                        if (this.getValue() == null) {
                            if (other.getValue() == null) {
                                break label43;
                            }
                        } else if (this.getValue().equals(other.getValue())) {
                            break label43;
                        }
                    }

                    var10000 = false;
                    return var10000;
                }

                var10000 = true;
                return var10000;
            }
        }

        public int hashCode() {
            return (this.getKey() == null ? 0 : this.getKey().hashCode()) ^ (this.getValue() == null ? 0 : this.getValue().hashCode());
        }

        public String toString() {
            return "" + this.getKey() + '=' + this.getValue();
        }
    }
}
