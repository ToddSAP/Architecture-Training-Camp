package consistent_hash.testing;

import consistent_hash.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ConsistentHashRoutingTest {
    @Test
    public void test_correction() {
        //Given
        List<ServerNode> serverNodeList = generateServers(5, false);
        ConsistentHashRouting consistentHashRouting =
                new ConsistentHashRouting(new FNV1_32_HASH_strategy(), 5, serverNodeList);

        //When
        ServerNode result1 = consistentHashRouting.getRealServer("上海");
        ServerNode result2 = consistentHashRouting.getRealServer("北京");
        ServerNode result3 = consistentHashRouting.getRealServer("南京");

        //Then
        Assert.assertTrue(result1.getIP().equals("10.129.126.31:1114"));
        Assert.assertTrue(result2.getIP().equals("10.129.126.30:1113"));
        Assert.assertTrue(result3.getIP().equals("10.129.126.28:1111"));
    }

    @Test
    public void test_balance_rating_with_FNV1_32() {
        //Given

        //When

        //Then
        System.out.println("标准差是： "+ testingBody(false, new FNV1_32_HASH_strategy()));
    }

    @Test
    public void test_performance_with_FNV1_32() {
        //Given
        int testingNums = 100;

        //When
        double[] standardDeviations = new double[testingNums];
        for (int i = 0; i < testingNums; i++) {
            standardDeviations[i] = testingBody(false, new FNV1_32_HASH_strategy() );
            System.out.println("第"+i+"次测试的标准差是： "+ standardDeviations[i]);
        }

        //Then
        Arrays.sort(standardDeviations);
        System.out.println(testingNums+"次测试的标准差中位数是： "+ (standardDeviations[49]+standardDeviations[50])/2);
    }

    @Test
    public void test_performance_with_RS() {
        //Given
        int testingNums = 100;

        //When
        double[] standardDeviations = new double[testingNums];
        for (int i = 0; i < testingNums; i++) {
            standardDeviations[i] = testingBody(false, new RS_HASH_strategy());
            System.out.println("第"+i+"次测试的标准差是： "+ standardDeviations[i]);
        }

        //Then
        Arrays.sort(standardDeviations);
        System.out.println(testingNums+"次测试的标准差中位数是： "+standardDeviations[49]);
    }

    private double testingBody(boolean needRandomServer, HashFunction hashFunction) {
        List<ServerNode> serverNodeList = generateServers(10, needRandomServer);
        ConsistentHashRouting consistentHashRouting =
                new ConsistentHashRouting(hashFunction, 20000, serverNodeList);
        int keyNums = 1000000;
        Map<ServerNode, Integer> payload = new HashMap<>();


        //When
        for (int i = 0; i < keyNums; i++) {
            ServerNode realServer = consistentHashRouting.getRealServer(UUID.randomUUID().toString());
            if (payload.get(realServer) == null) {
                payload.put(realServer, 1);
            }
            payload.put(realServer, payload.get(realServer)+1);
        }

        /*for (Map.Entry<ServerNode, Integer> entry : payload.entrySet()) {
            System.out.println(entry.getKey().getName() + " 缓存了 ["+entry.getValue()+"] 个key");
        }*/
        return standardDeviation(payload);
    }

    private List<ServerNode> generateServers (int num, boolean needRandom) {
        Random random = new Random();
        List<ServerNode> result = new ArrayList<>();
        if (needRandom) {
            for (int i = 0; i < num; i++) {
                result.add(new ServerNode("server" + i,
                        "10.129.126." + random.nextInt(254) + ":" + random.nextInt(9999)));
            }
        } else {
            int subNet = 28;
            int port = 1111;
            for (int i = 0; i < num; i++) {
                result.add(new ServerNode("server" + i,
                        "10.129.126."+subNet+++":"+port++));
            }
        }
        return result;
    }

    private double standardDeviation (Map<ServerNode, Integer> result) {
        double sum = 0;
        double average = 0;

        for (Map.Entry<ServerNode, Integer> entry : result.entrySet()) {
            sum += entry.getValue();
        }
        average = sum/result.values().size();
        sum = 0;
        for (Map.Entry<ServerNode, Integer> entry : result.entrySet()) {
            sum += Math.pow(entry.getValue() - average, 2);
        }
        return Math.sqrt(sum/result.values().size());
    }
}
