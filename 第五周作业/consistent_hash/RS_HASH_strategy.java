package consistent_hash;

public class RS_HASH_strategy implements HashFunction{

    @Override
    public int hash(String key) {
        int b = 378551;
        int a = 63689;
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = hash * a + key.charAt(i);
            a = a * b;
        }
        return (hash & 0x7FFFFFFF);
    }
}
