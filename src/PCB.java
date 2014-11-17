import java.util.ArrayList;

/**
 * Created by swema_000 on 9/15/2014.
 */
public class PCB {
    private String _HEADER;
    private int _JOB_POS;
    private int size;
    private int priority;
    public int insRun;
    public int counter;
    public String ID;
    private int[] register;

    public PCB(){
    	_HEADER = "None";
    	_JOB_POS = -1;
    	size = 10000;
    	counter = 0;
    	priority = 0;
    	ID = "None";
    	register = null;
    	insRun = 0;
    }

    public PCB(String header, int initial_position, int job_size){
        _HEADER = header;
        _JOB_POS = initial_position;
        size = job_size;
         String[] temp = header.split(", ");
         ID = temp[0];
         register = null;
         priority = Integer.parseInt(temp[2]);
         counter = 0;
         insRun = 0;
    }

    //BEGIN GETTERS AND SETTERS
    public void set_HEADER(String _HEADER) {
        this._HEADER = _HEADER;
    }

    public void updatePos(int _JOB_POS) {
        this._JOB_POS = _JOB_POS;
    }

    public void setsize(int size) {
        this.size = size;
    }

    public int get_JOB_POS() {
        return _JOB_POS;
    }

    public int getsize() {
        return size;
    }

    public String get_HEADER() {
        return _HEADER;
    }
    
    public int counter(){
    	return counter;
    }
    
    public int prior(){
    	return priority;
    }
    
    public void decPosition(){
    	_JOB_POS--;
    }
    //END GETTERS AND SETTERS
    
    public String next(RAM ram){
    	String next = ram.load(_JOB_POS);
    	_JOB_POS++;
    	return next;
    }

    public static boolean exists(ArrayList<PCB> pcb_arr, String header){
        for(int x = 0; x < pcb_arr.size(); x++){
            System.out.println("Element " + x + " of pcb_arr: " + pcb_arr.get(x) + " compared to " + header);
            if(header.equalsIgnoreCase(pcb_arr.get(x).get_HEADER())){return true;}}
        return false;
    }
    
    public void saveState(int[] reg){
    	register = new int[5];
    	register[0] = reg[0];
    	register[1] = reg[1];
    	register[2] = reg[2];
    	register[3] = reg[3];
    	register[4] = reg[4];
    }
    
    public String toString(){
    	return "PCB " + ID + " final registers: " 
    			+ "\nRegister A: " + register[0]
    			+ "\nRegister B: " + register[1]
   				+ "\nRegister C: " + register[2]
   				+ "\nRegister D: " + register[3]
   				+ "\nAccumulator: " + register[4];
    }
}
