import java.util.ArrayList;

/**
 * Created by swema_000 on 9/15/2014.
 */
public class PCB {
    private String _HEADER;
    private int _JOB_POS;
    private int _JOB_SIZE;
    private Object data;

    public PCB(){

    }

    public PCB(String header, int initial_position, int job_size){
        _HEADER = header;
        _JOB_POS = initial_position;
        _JOB_SIZE = job_size;
    }

    //BEGIN GETTERS AND SETTERS
    public void set_HEADER(String _HEADER) {
        this._HEADER = _HEADER;
    }

    public void updatePos(int _JOB_POS) {
        this._JOB_POS = _JOB_POS;
    }

    public void set_JOB_SIZE(int _JOB_SIZE) {
        this._JOB_SIZE = _JOB_SIZE;
    }

    public int get_JOB_POS() {
        return _JOB_POS;
    }

    public int get_JOB_SIZE() {
        return _JOB_SIZE;
    }

    public String get_HEADER() {
        return _HEADER;
    }
    //END GETTERS AND SETTERS

    public void setData(Object o){
        data = o;
    }

    public static boolean exists(ArrayList<PCB> pcb_arr, String header){
        for(int x = 0; x < pcb_arr.size(); x++){
            System.out.println("Element " + x + " of pcb_arr: " + pcb_arr.get(x) + " compared to " + header);
            if(header.equalsIgnoreCase(pcb_arr.get(x).get_HEADER())){return true;}}
        return false;
    }

    public String toString(){
        return "PCB: " + _HEADER + " Current Pos: " + _JOB_POS;
    }
}
