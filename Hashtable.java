import java.lang.*;
public class Hashtable<k, v> {
    class HashNode<k, v> {
        public String key;
        public String value;
        public HashNode next;

        public HashNode(String key, String value) {
            // declaring the Hash Node class with all the essential data
            this.key = key;
            this.value = value;
            this.next = null;
            size = 0;
        }
    }
    HashNode[] buckets; // declares an array of hash node buckets
    int number_of_buckets; // creates the int value of the size of table
    int size; // creates the int value of the number of items
    public Hashtable()
    {
        number_of_buckets = 250000; // creates the no of buckets.
        //bigger size so it runs faster
        // fix the bug where the num_buckets if too small, takes too long. 
        buckets = new HashNode[number_of_buckets];
        // makes the buckets and initializes the array to the num buckets size
        size = 0;
        // keeps track of all the hash odes that are in the array.
    }
    public int getBucket(String key) {
        int thehashCode = key.hashCode();
        return Math.abs((thehashCode % number_of_buckets));
    }

    public String get(String key) {
        // this function goes through and returns the value of the node based on the string key give to it.
        int bucketidfinder = getBucket(key);
        HashNode currentnode = buckets[bucketidfinder];
        while (currentnode != null) {
            if (currentnode.key.equals(key))
                return currentnode.value;
            currentnode = currentnode.next;
        }
        return null;
    }
    public boolean containsKey(String key) {
        // this is similar to get key
        // returns a boolean to see if the ket exits or not. 
        int bucketidfinder = getBucket(key);
        HashNode currentnode = buckets[bucketidfinder];
        while (currentnode != null) {
            if (currentnode.key.equals(key))
                return true;
            //node = node.next;
        }
        return false;
    }
    // put function places something inside
    // if it exists, update the value
    // if it does not create a new one
    public void put(String key, String value) {
        int bucketidfinder = getBucket(key);
        HashNode currentnode = buckets[bucketidfinder];
        while (currentnode != null) {
            if (currentnode.key.equals(key)) {
                currentnode.key = key;
                currentnode.value = value;
                return;
            }
            currentnode = currentnode.next;
        }
        size++;
        // make size bigger
        if (buckets.length == size)
            // create a new array with larger buckets
        {
            grow_array();
        }
        // put new hash node into the buckets
        HashNode thenewnode = new HashNode(key, value);
        thenewnode.next = buckets[bucketidfinder];
        buckets[bucketidfinder] = thenewnode;
    }

    private void grow_array() {
        // grow array function that grows the array.
        HashNode newArr[] = new HashNode[buckets.length*2];
        for(int i = 0; i < size; i++){
            newArr[i] = buckets[i];
        }
        buckets = newArr;
    }
    public String remove(String key) {
        // remove function that goes through and removes the specific key value
        // hash node pair
        int bucketidfinder = getBucket(key);
        HashNode currentnode = buckets[bucketidfinder];
        HashNode prevnode = null;
        while (currentnode.next != null && !currentnode.key.equals(key)) {
            prevnode = currentnode;
            currentnode = currentnode.next;
        }
            if (currentnode.key.equals(key))
                if (currentnode == null) {
                    return null;
                }
                if (prevnode == null) {
                    buckets[bucketidfinder] = currentnode.next;
                }
                else {
                    prevnode.next = currentnode.next;
                    size--;
                }
        return currentnode.value;
    }
}