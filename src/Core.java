/**
 * Created by swema_000 on 10/8/2014.
 */
public class Core implements Runnable {
    private Job currentJob;
    private int testInt;
    private int sleepInt;

    public Core(Job j){
        this.currentJob = j;
    }

    public Core(int num, int sleep){
        this.testInt = num;
        this.sleepInt = sleep;
    }

    public void run(){
        try{
            for(int x = testInt; x < testInt+100; x++){
                System.out.println("CORE " + coreNum)
        }}catch (Exception e){

        }

    }
}
