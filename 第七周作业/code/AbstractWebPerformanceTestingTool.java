package code;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.*;

public abstract class AbstractWebPerformanceTestingTool {
    final protected String url;
    final protected int concurrentNum;
    final protected int totalRequestNum;
    final protected int resRate;
    final private HttpClient client = HttpClient.
            newBuilder().
            version(HttpClient.Version.HTTP_2).
            build();

    public AbstractWebPerformanceTestingTool(String url, int concurrentNum, int totalRequestNum, int resRate) {
        this.url = url;
        this.concurrentNum = concurrentNum;
        this.totalRequestNum = totalRequestNum;
        this.resRate = resRate;
    }

    public void doTesting() throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(concurrentNum, concurrentNum, 10, TimeUnit.SECONDS, new ArrayBlockingQueue(10));
        for (int j = 0; j < totalRequestNum; j++) {
            System.out.println("round " + j + " started!");
            // 用CountDownLatch来区分每轮测试
            CountDownLatch latch = new CountDownLatch(concurrentNum);
            for (int i = 0; i < concurrentNum; i++) {
                pool.execute(() -> {
                    try {
                        testingBody();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    latch.countDown();
                });
            }
            latch.await();
            System.out.println("round " + j + " ended!");
        }
        handleFinalStatistics();
    }

    public void testingBody() throws ExecutionException, InterruptedException, TimeoutException {
        long start = System.currentTimeMillis();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(assembleHttpRequest(), HttpResponse.BodyHandlers.ofString());
        HttpResponse res = response.get(1, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        handleResponse(res, start, end);
    }

    // 拼装请求
    public abstract HttpRequest assembleHttpRequest ();
    // 处理每次请求
    public abstract void handleResponse (HttpResponse response, long startTime, long endTime);
    // 处理最终统计信息
    public abstract void handleFinalStatistics ();
}
