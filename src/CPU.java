/**
 * Created by swema_000 on 10/8/2014.
 */
public class CPU {
    private Core[] _cores;

    public CPU(int cores, int registers){
        _cores = new Core[cores];
        _cores[0] = new Core();
        _cores[1] = new Core();
        _cores[2] = new Core();
        _cores[3] = new Core();
    }

    public void send(Job j){
    	//Find available core
    	//Execute job on core
    }
}
