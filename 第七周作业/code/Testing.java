package code;

import org.junit.Test;

public class Testing {

    @Test
    public void test_correction() throws InterruptedException{
        //Given
        AbstractWebPerformanceTestingTool tool = new WebPerformanceTestingTool("https://www.baidu.com", 10, 100, 95);

        //When
        tool.doTesting();

        //Then
    }

}
