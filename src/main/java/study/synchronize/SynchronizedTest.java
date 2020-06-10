package study.synchronize;

import java.util.ArrayList;
import java.util.Random;

/**
 * 高并发场景
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        Race r = new Race();
        Thread thread1 = new Thread(r, "兔子");
        Thread thread2 = new Thread(r, "乌龟");
        thread1.start();
        thread2.start();
    }


}

/**
 * （题目编号7179）
 * 利用多线程技术编写一个模拟龟兔赛跑的程序，要求如下：
 * （1）乌龟每次跑一个单位，兔子每次跑10个单位；
 * （2）每个线程运行时，判断是否达到终点，如果到达终点，给出提示信息，
 * 未到终点则提示目前距离终点的距离，并判断是否领先；
 * （3）如果兔子领先，则显示“我跑得快，睡一觉”信息，并睡一会。
 */
class Race implements  Runnable{
    final int total=100;//赛道长度
    volatile boolean isOver = false;//比赛结束标志
    volatile int disRabbit = 0; // 兔子已走距离
    volatile int disTortoise = 0; // 乌龟已走距离

    @Override
    public void run() {
        for (int time = 0; isOver !=true ; time++) {
            if (Thread.currentThread().getName().equals("兔子")) {
                disRabbit = time * 10;//跑的距离
                if (disRabbit >= total) {
                    isOver = true;
                    System.out.println("兔子获胜");
                    break;
                }
                System.out.println("兔子距离终点：" + (total - disRabbit) + "米");

                if (disRabbit > disTortoise) {
                    System.out.println("兔子跑的快,睡一觉");
                    try {
                        Thread.sleep((long) (Math.random()) * 2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }else{
                disTortoise=time;
                if (disTortoise>=total){
                    isOver = true;
                    System.out.println("乌龟获胜");
                    break;
                }
                System.out.println("乌龟距离终点："+(total-disTortoise)+"米");
            }
            synchronized (this){
                if (!isOver){
                    if (disRabbit>disTortoise){
                        System.out.println("兔子领先");
                    }else if(disRabbit<disTortoise){
                        System.out.println("乌龟领先");
                    }else{
                        System.out.println("兔子乌龟一样快");
                    }
                }
            }
        }

    }
}

/**
 * （题目编号8690）
 * 编写多线程应用程序，模拟多人过独木桥。
 * 独木桥每次只能通过一个人，每个人通过木桥的时间为5秒，随机生成10个人，同时准备过此独木桥，
 * 显示一下每次通过独木桥人的姓名。需要用到随机数。
 * 注意：（1）在开始过桥时输出：**开始过桥！过完桥后输出：**已过桥！
 * （2）随机选人的时候，每个人都要选到，不能重复选。
 */
class Test2{
    static final int N = 10;//人数
    public static void main(String[] args) {
        SingleBridge s = new SingleBridge();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i <N ; i++) {
            threads.add(new Thread(s,"name"+i));
        }
        Random random = new Random();
        while(threads.size()>0){
            int index = random.nextInt(threads.size());
            threads.get(index).start();
            threads.remove(index);
        }
    }
}

class SingleBridge implements Runnable{

    @Override
    public void run() {
        synchronized (this){

            System.out.println(Thread.currentThread().getName()+"开始过桥");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"已过桥");
        }
    }
}


/**（题目编号7180）
 哈尔滨火车站下面有三个火车票代售点：哈站、哈东站、哈西站，
 假如哈尔滨到北京的火车票总共是200张，如何用程序来实现三个售票点同时卖票的功能。
 注意：考虑线程同步问题，避免出现重复卖票问题。需要考虑同步问题。
 *
 */
class Test3{
    public static void main(String[] args) {
        TicketApp app = new TicketApp(2000);
        Thread t1 = new Thread(app, "哈站");
        Thread t2 = new Thread(app, "哈东站");
        Thread t3 = new Thread(app, "哈西站");
        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketApp implements Runnable{
    volatile  int ticket=200;
    volatile boolean canSell = true;

    public TicketApp(int ticket) {
        this.ticket = ticket;
    }

    public TicketApp() {
    }

    synchronized void sell(){
        if (ticket>0){
            ticket--;
            System.out.println(Thread.currentThread().getName()+"售出一张票,现有余票："+ticket+"张");
        }else{
            canSell= false;
        }
    }
    @Override
    public void run() {
     while (canSell){
         sell();
     }
    }
}