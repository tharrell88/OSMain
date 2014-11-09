/**
 * Created by swema_000 on 10/8/2014.
 */
public class Core {
    private PCB currentJob;
	private int[] register;
	private boolean running;

    public Core(){
        //0-3 registers A-D, 4 Accumulator 
        register = new int[5];
        register[0] = 1;
        register[1] = 3;
        register[2] = 5;
        register[3] = 7;
        register[4] = 9;
    }
    
    public void setPCB(PCB pcb){
    	currentJob = pcb;
    }
    
    private void run(){
    	while(running){
    		decode(currentJob.next());
    	}
    }
    
    //Step 1: Explode string, find action, target regs, val
    //Step 2: Execute action
    public void decode(String instruction){
    	String[] explodeIns = instruction.split(", ");
    	int jobPos = Integer.parseInt(explodeIns[0]);
    	String action = explodeIns[1];
    	int reg1 = getRegID(explodeIns[2]);
    	int reg2 = getRegID(explodeIns[3]);
    	int value = Integer.parseInt(explodeIns[4]);
    	
    	action(action, reg1, reg2, value, 1);
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
    private void action(String action, int reg1, int reg2, int val, int processID){
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
    	register[4] += (register[reg2] + register[reg1]);
    }
    
    //sets registers back to default
    private void reset(){
    	register[0] = 1;
        register[1] = 3;
        register[2] = 5;
        register[3] = 7;
        register[4] = 9;
    }
    
    public String dump(){
    	return "Ending Process " + currentJob.ID + ". Register Dump: " + register[0] + ", " + register[1] + ", " + register[2] + ", " + register[3] + ", " + register[4];
    }
}
