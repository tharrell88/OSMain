import java.util.ArrayList;

/**
 * Created by swema_000 on 10/8/2014.
 */
public class Core {
	
    private PCB currentJob;
	private int[] register;
	
	//False for not running, true for running something [PHASE 3?]
	//private boolean flag;

    public Core(){
        //0-3 registers A-D, 4 Accumulator
        register = new int[5];
        register[0] = 1;
        register[1] = 3;
        register[2] = 5;
        register[3] = 7;
        register[4] = 9;
        
        currentJob = null;
        
        //flag = false;
    }
    
    public void setPCB(PCB pcb){
    	currentJob = pcb;
    }
    
    public int next(){
    	return currentJob.get_JOB_POS();
    }
    
    public boolean hasJob(){
    	return (currentJob != null ? true : false);
    }
    
    public PCB job(){
    	return currentJob;
    }
    
    //Step 1: Explode string, find action, target regs, val
    //Step 2: Execute action
    public void decodeExecute(String instruction, ArrayList<PCB> ioQ, ArrayList<PCB> waitQ, ArrayList<PCB> termQ, ArrayList<PCB> rdyQ){
    	System.out.println(instruction);
    	String[] explodeIns = instruction.split(", ");
    	int jobPos = Integer.parseInt(explodeIns[0]);
    	String action = explodeIns[1];
    	int reg1 = getRegID(explodeIns[2]);
    	int reg2 = getRegID(explodeIns[3]);
    	int value = Integer.parseInt(explodeIns[4]);
    	
    	execute(action, reg1, reg2, value, 1, ioQ, waitQ, termQ, rdyQ);
    }
    
    //Give it a letter to get which register you need
    private int getRegID(String reg){
    	int i = -1;
    	switch (reg){
    	case "A":
    	case "a":
    		i = 0;
    		break;
    	case "B":
    	case "b": 
    		i = 1;
    		break;
    	case "C":
    	case "c":
    		i = 2;
    		break;
    	case "D":
    	case "d":
    		i = 3;
    		break;
    	default:
    		break;
    	}
    	return i;
    }
    
    //commands
private void execute(String action, int reg1, int reg2, int val, int processID, ArrayList<PCB> rdyQ, ArrayList<PCB> waitQ, ArrayList<PCB> termQ, ArrayList<PCB> ioQ){
	System.out.println(action);
    	switch(action){
    	case "add":
    		add(reg1, reg2);
    		break;
    	case "sub":
    		sub(reg1, reg2);
    		break;
    	case "mul":
    		mul(reg1, reg2);
    		break;
    	case "div":
    		div(reg1, reg2);
    		break;
    	case "_rd":
    		ioMove(ioQ);
    		break;
    	case "_wr":
    		ioMove(ioQ);
    		break;
    	case "_wt":
    		wtMove(waitQ);
    		break;
    	case "sto":
    		store(val);
    		break;
    	case "rcl":
    		copyTo(reg1);
    		break;
    	case "nul":
    		reset();
    		break;
    	case "stp":
    		stop(rdyQ);
    		break;
    	case "err":
    		err(termQ);
    		break;
    	default:
    		System.out.println("ERROR IN ACTION");
    		break;
    	}
    }
    
    private void add(int reg1, int reg2){
    	register[4] += (register[reg1] + register[reg2]);
    }
    
    private void sub(int reg1, int reg2){
    	register[4] += (register[reg1] - register[reg2]);
    }
    
    private void mul(int reg1, int reg2){
    	register[4] += (register[reg1] * register[reg2]);
    }
    
    private void div(int reg1, int reg2){
    	register[4] += (register[reg2] / register[reg1]);
    }
    
    private void ioMove(ArrayList<PCB> ioQ){
    	ioQ.add(currentJob);
    	System.out.println(ioQ.size());
    	currentJob = null;
    }
    
    private void wtMove(ArrayList<PCB> waitQ){
    	waitQ.add(currentJob);
    	currentJob = null;
    }
    
    private void store(int val){
    	register[4] = val;
    }
    
    private void copyTo(int reg){
    	register[reg] = register[4];
    }
    
    private void stop(ArrayList<PCB> rdyQ){
    	currentJob.saveState(register);
    	rdyQ.add(currentJob);
    	currentJob = null;
    }
    //sets registers back to default
    public void reset(){
    	register[0] = 1;
        register[1] = 3;
        register[2] = 5;
        register[3] = 7;
        register[4] = 9;
    }
    
    public void err(ArrayList<PCB> termQ){
    	currentJob.saveState(register);
    	termQ.add(currentJob);
    	currentJob = null;
    }
    
    public String dump(){
    	return "Ending Process " + currentJob.ID + ". Register Dump: " + register[0] + ", " + register[1] + ", " + register[2] + ", " + register[3] + ", " + register[4];
    }
}
