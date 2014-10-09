/**
 * Created by swema_000 on 10/8/2014.
 */
public class CPU {
    private Core[] _cores;
    private Register _regis;

    public CPU(int cores, int registers){
        _cores = new Core[cores];
        _regis = new Register(registers+1);
    }

    public void send(Job j){

    }

    public void decodeExecute(){

    }
}
