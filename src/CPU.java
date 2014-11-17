

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
    
    public boolean canAssign(){
    	if(_cores[0].hasJob()){
    		return false;
    	}else{return true;}
    }
    
    public Core core(int x){
    	return _cores[x];
    }
}
