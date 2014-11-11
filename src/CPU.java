import java.util.ArrayList;

/**
 * Created by swema_000 on 10/8/2014.
 */
public class CPU {
    private Core[] _cores;

    public CPU(){
        _cores = new Core[4];
        _cores[0] = new Core();
        _cores[1] = new Core();
        _cores[2] = new Core();
        _cores[3] = new Core();
    }
    
    //For phase 3, modify this to send to any open core
    public boolean fetch(ArrayList<PCB> readyQueue){
    	if(readyQueue.isEmpty()){return false;}
    	
    	_cores[0].setPCB(readyQueue.get(0));
    	return true;
    }
}
