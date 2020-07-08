package consistent_hash;

import java.util.Objects;

public class FNV1_32_HASH_strategy implements HashFunction{
    @Override
    public int hash(String key) {
        Objects.requireNonNull(key);
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }
}
