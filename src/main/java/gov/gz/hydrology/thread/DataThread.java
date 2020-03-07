package gov.gz.hydrology.thread;

import gov.gz.hydrology.constant.CommonConst;
import gov.gz.hydrology.service.common.DataService;

import java.text.SimpleDateFormat;

public class DataThread extends Thread{

    private static DataService dataService;

    /**
     * 线程安全锁
     */
    private static Object lock = new Object();

    private static final Long timeout = 5*60*1000L;

    private static String dataTime = "";

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public DataThread(){
        dataService = CommonConst.APPLICATION_CONTEXT.getBean(DataService.class);
        System.out.println("开启数据线程");
        this.start();
    }

    @Override
    public void run() {
        while (true){
            synchronized (lock){
                try {
                    System.out.println("开始");
                    dataService.rainfallDaily();
                    System.out.println("降雨趋势");
                    dataService.rainfallTotal();
                    System.out.println("累计雨量");
                    dataService.riverTime();
                    System.out.println("水位过程");
                    lock.wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
