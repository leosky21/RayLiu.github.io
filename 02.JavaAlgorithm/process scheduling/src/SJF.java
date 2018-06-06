import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 32706 on 2016/12/8.
 * 最短时间优先算法
 */
public class SJF {

    private static int task_num = 8;
    public static List<double[]> task_time = new ArrayList<>();
    private static SimpleDateFormat df = new SimpleDateFormat("HHmmss");
    private static SimpleDateFormat tm = new SimpleDateFormat("HH:mm:ss");
    private static List<double[]> execute_time = new ArrayList<>();



    public static void SJF() {

        for (int i = 0; i < task_num; i++) {
            try {
                double[] t = get_task(task_time);
                int current_task_time = (int) t[3];
                int task_NO = (int) t[0];
                System.out.print(tm.format(new Date()) + "第" + task_NO + "号进程开始执行====");

                Thread.sleep(1000 * current_task_time);
                System.out.println("  " + tm.format(new Date()) + "执行完成=====用时为" + current_task_time + "S");


                double exe_time =System.currentTimeMillis() - t[1];
                double[] e = new double[2];
                e[0] = task_NO;
                e[1] = exe_time;
                execute_time.add(e);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }


       show_time();

    }


    public  static void show_time()
    {
        double sum_time=0;
        for(int i=0;i<execute_time.size();i++)
        {
            double[] t=execute_time.get(i);
            System.out.println("task:"+t[0]+":周转时间="+(int)(t[1]/1000)+"S");
            sum_time+=t[1];
        }
        System.out.println("使用最短作业优先的策略，平均周转时间为："+(int)(sum_time/execute_time.size()/1000)+"S");
    }

    public static double[] get_task(List<double[]> task_time) {
        double[] rt = new double[4];
        double smallest_time = 50;

        int t = -1;
        for (int i = 0; i < task_time.size(); i++) {
            if (task_time.get(i)[3] < smallest_time) {
                smallest_time = task_time.get(i)[3];
                t = i;
            }
        }
        rt = task_time.get(t);
        task_time.remove(t);

        return rt;
    }

    static void init_task(List<double[]> task_info, int tn) {
        task_num = tn;
        for (int i = 0; i < task_num; i++) {
            double[] t = task_info.get(i);
            t[1] = System.currentTimeMillis();

            task_time.add(t);

        }
    }


}
