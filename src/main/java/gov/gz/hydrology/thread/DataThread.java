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
        System.out.println("start thread");
        this.start();
    }

    @Override
    public void run() {
        while (true){
            synchronized (lock){
                try {
                    System.out.println("start");
                    dataService.rainfallDaily();
                    System.out.println("rainfallDaily");
                    dataService.rainfallTotal();
                    System.out.println("rainfallTotal");
                    dataService.riverTime();
                    System.out.println("riverTime");
                    lock.wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
