package gov.gz.hydrology.thread;

public class DataThread extends Thread{

    /**
     * 线程安全锁
     */
    private static Object lock = new Object();

    private static final Long timeout = 24*60*60*1000L;

    public DataThread(){
        System.out.println("开启数据线程");
        this.start();
    }

    @Override
    public void run() {
        while (true){
            synchronized (lock){
                try {

                    lock.wait(timeout);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
