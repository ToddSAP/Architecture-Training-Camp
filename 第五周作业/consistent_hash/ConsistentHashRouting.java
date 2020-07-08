package consistent_hash;

import java.util.*;

/**
 * 利用TreeMap作为环，将虚拟节点散列后添加到TreeMap，而TreeMap是默认有序的，故天然称为一个有序列表。
 * 利用key的Hash值在TreeMap里查找最小大于它的虚拟节点，再利用虚拟节点和真实节点的映射关系找到最终真实节点。
 */
public class ConsistentHashRouting {
    // 真实服务器列表
    private final List<ServerNode> serverNodeList = new ArrayList<>();
    // 环
    private final static SortedMap<Integer, VirtualServerNode> circle = new TreeMap();
    // 哈希函数实现
    private final HashFunction hashFunction;
    // 每个真实服务器的虚拟节点个数
    private final int numberOfVirtualReplicas;

    public ServerNode getRealServer (String key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hashCode = hashFunction.hash(key);
        // key的hash值不在环中，说明未命中虚拟节点，需要找最近的虚拟节点
        if (!circle.containsKey(hashCode)) {
            // 获取大于hash值的所有虚拟节点列表
            SortedMap<Integer, VirtualServerNode> tailMap = circle.tailMap(hashCode);
            // 没有大于hash值的虚拟节点，则找环的第一个节点
            hashCode = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        // 得到真实节点
        ServerNode realServer = circle.get(hashCode).getRealServer();
        //System.out.println("["+key+" ("+hashCode+")] 被路由到真实服务器 "+ realServer.getIP());

        return realServer;
    }

    public ConsistentHashRouting (HashFunction hashFunction, int numberOfVirtualReplicas, Collection<ServerNode> servers) {
        if (hashFunction == null) {
            this.hashFunction = new FNV1_32_HASH_strategy();
        } else {
            this.hashFunction = hashFunction;
        }
        if (numberOfVirtualReplicas <= 0) {
            this.numberOfVirtualReplicas = 5;
        } else {
            this.numberOfVirtualReplicas = numberOfVirtualReplicas;
        }
        serverNodeList.addAll(servers);
        for (ServerNode server : serverNodeList) {
            addVirtualNode(server);
        }
    }

    private void addVirtualNode (ServerNode realServer) {
        for (int i = 0; i < numberOfVirtualReplicas; i++) {
            int hashCode = hashFunction.hash(realServer.getIP()+i);
            circle.put(hashCode,
                    new VirtualServerNode(hashCode, realServer.getName(), realServer));
        }
    }
}
