package code;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;

public class WebPerformanceTestingTool extends AbstractWebPerformanceTestingTool{
    final private ArrayList<Long> resTimeResult = new ArrayList<>();

    public WebPerformanceTestingTool(String url, int concurrentNum, int totalRequestNum, int resRate) {
        super(url, concurrentNum, totalRequestNum, resRate);
    }

    @Override
    public HttpRequest assembleHttpRequest () {
        return HttpRequest.newBuilder().
                GET().
                uri(URI.create(url)).
                build();
    }

    @Override
    public void handleResponse (HttpResponse response, long startTime, long endTime) {
        if (response != null && response.statusCode() == 200) {
            resTimeResult.add(endTime - startTime);
            System.out.println("Request completed with status [" + response.statusCode() + "], and took [" + (endTime - startTime)+"] ms");
        } else {
            System.out.println("Request failed with status " + response != null ? response.statusCode() : -1);
        }
    }

    @Override
    public void handleFinalStatistics() {
        Collections.sort(resTimeResult, (t, t1) -> (int) (t - t1));
        resTimeResult.stream().forEach(System.out::println);
        System.out.println("final position is " + (totalRequestNum*concurrentNum*resRate/100));
        System.out.println("The " + resRate + "% response time is [" + resTimeResult.toArray()[totalRequestNum*concurrentNum*resRate/100] + "]");
    }
}
