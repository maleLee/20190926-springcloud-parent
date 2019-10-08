package com.aaa.lee.springcloud.config;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/9/26 16:53
 * @Description
 **/
public class Test {

    // 随机算法
    /*public Server choose(ILoadBalancer lb, Object key) {
        // 如果负载均衡器为null的时候，直接返回(其实就是标识了该consumer开启了负载均衡@LoadBalance)
        if (lb == null) {
            return null;
        }
        // 创建Server对象，没有初始化
        Server server = null;

        // 循环进入条件是server为null-->也就是说第一次一定会进入循环
        while (server == null) {
            // Thread.interrupted():标识线程所处于的状态，如果线程的状态为存在,返回值就是true，如果不存在返回值就是false
            // 什么时候线程会断开？？-->网络故障，网络延迟，各种阻塞
            if (Thread.interrupted()) {
                return null;
            }
            // getReachableServers():获取当前正在存活的服务(一共有20个服务，其中有4个宕机，存活的服务就是16个)
            // getAllServers():获取所有的服务个数(包含宕机的)
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            // allList.size() == 0 说明没有服务，直接return null
            int serverCount = allList.size();
            if (serverCount == 0) {
                *//*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 *//*
                return null;
            }

            // serverCount:就是所有服务的个数(包含宕机的服务)
            // 假设:30台
            int index = chooseRandomInt(serverCount);
            // upList:存活的服务
            server = upList.get(index);

            if (server == null) {
                *//*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 *//*
                *//**
                 * Thread.yield():线程谦让
                 *      什么叫多线程之间的谦让呢？
                 *          当并发出现的时候，线程和线程是互不相让的(线程阻塞)，其中一个线程就会出现问题,就会处于等待状态(线程阻塞会更严重)
                 *          当调用yield()方法的时候，出现问题的线程就开始谦让,这个线程会再次被唤醒处于就绪状态，需要重新抢夺客户端所发送过来的并发
                 *//*
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        // Math.random.nextInt(999):随机获取到从1到999的随机数
        // ThreadLocalRandom.current().nextInt(30):就是获取的是随机数(从1到30进行随机数)
        *//**
         *
         * Random和ThreadLocalRandom都是随机数，那么为什么使用ThreadLocalRandom不使用Random？
         *  !!Random就是线程安全的!!
         *  ThreadLocalRandom:是jdk7的新特性！！
         *  虽然random是线程安全的，但是在多线程的情况，Random效率就会非常低
         *      random在处理多线程的情况下是线程安全的，会受到线程保护，就会降低效率(队列)
         *  Random所随机出来的数字是可以预测的(Random有规律)
         *
         *  TreadLocalRandom官方给出的解释，当项目期望在多线程中运行的时候，如果使用到了随机数，可以直接使用该类，因为该
         *  类就是针对于并发所开发的，和Random相比TreadLocalRandom可以减少线程之间的竞争，性能可以达到最优！
         *
         *
         *//*
        return ThreadLocalRandom.current().nextInt(serverCount);
    }*/

}
