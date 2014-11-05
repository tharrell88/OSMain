/**
 * Created by swema_000 on 10/8/2014.
 */
public class Core implements Runnable {
    private Job currentJob;
	private Object[] register;

    public Core(){
        //0-3 registers A-D, 4 Accumulator 
        register = new Object[5];
    }

    public void run(){

    }
}
