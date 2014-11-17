//THIS IS THE MAIN FOR THE OS EMULATOR
//Written by: Travis Harrell

import java.util.ArrayList;
import java.util.Scanner;

public class OperatingSystem {

	public static void main(String[] args) {
		//HDD and RAM objects to be used in testing
		//RAM has a default max size of 100
		HDD hardDrive = new HDD();
		RAM ram = new RAM(100);
        ArrayList<PCB> rdyQ = new ArrayList<PCB>();
        ArrayList<PCB> waitQ = new ArrayList<PCB>();
        ArrayList<PCB> termQ = new ArrayList<PCB>();
        ArrayList<PCB> ioQ = new ArrayList<PCB>();
        CPU cpu = new CPU();
        String STSmethod = "SJF";
        int insLine = 0;
		
		boolean ready = false;
		boolean done = false;
		
		//emulator settings
		String method = "SJF";
		
		//cant use relative paths without some extra changes so need to explicitly tell Java where the file is
		String path = "C:\\Users\\swema_000\\Desktop\\Eclipse Workspace\\Operating Systems\\OSMain\\src\\ugradPart1.txt";
		//String path = "I:\\Users\\swema_000\\Documents\\GitHub\\OSMain\\src\\ugradPart1.txt";
		Scanner s = new Scanner(System.in);
		
		System.out.print("Operating System Emulator\nBy: Travis Harrell\n");
		//System.out.println("ioQ size: " + ioQ.size());
		
		//Intro and settings loop
		//Things to be added
		// 1) Other scheduling algorithms as necessary
		// 2) Setpath function in case different files need to be used
		// 3) Set method functions for other schedulers
		while(!ready){
			System.out.println("Please select an option from the following list (Enter number):");
			System.out.println("1) Run Emulator"
					+ "\n2) Set LTS method"
					+ "\n3) Set RAM size");
			
			switch(s.nextInt()){
				//Run emulator
				case 1:
					System.out.println("Ready. Running Emulator.\n");
					ready = true;
					break;
				//select method to use for the Long Term Scheduler
				case 2:
					System.out.println("Which LTS method would you like to use? (Priority, SJF, and FCFS available)");
					String t = s.next();
					if(!t.equalsIgnoreCase("sjf") && !t.equalsIgnoreCase("fcfs") && !t.equalsIgnoreCase("priority")){
						System.out.println("Invalid method");}
					else{ method = t; }
					break;
				//Set max size for RAM
				case 3:
					System.out.println("What size do you wish to use for the RAM?");
					ram.setMemorySize(s.nextInt());
					break;
				case 4:
					System.out.println("What algorithm should be used for STS? (SJF, Priority, FCFS");
					String o = s.next();
					if(!o.equalsIgnoreCase("sjf") && !o.equalsIgnoreCase("fcfs") && !o.equalsIgnoreCase("priority")){
						System.out.println("Invalid method");}
					else{ method = o; }
					break;
				default:
					System.out.println("Sorry, invalid choice.\n");
					break;
			}
		}
		
		//Run boot loader (see OperatingSystemFunctions function class)
		OperatingSystemFunctions.BootLoader(hardDrive, path);
		System.out.println("Entering main loop"); 

		while(!done){
			OperatingSystemFunctions.LongTermScheduler(method, hardDrive, ram, rdyQ);
			
			//IF EVERYTHING IS EMPTY, DUMP AND EXIT LOOP
			if(rdyQ.isEmpty() && ioQ.isEmpty() && waitQ.isEmpty() && ram.isEmpty()){
				System.out.println("Dumping Term Q:");
				for(int x = 0; x < termQ.size(); x++){
					System.out.println(termQ.get(x));
				}
				break;
			}
			
			if(cpu.canAssign()){
				System.out.println("canAssign");
				OperatingSystemFunctions.STS(STSmethod, rdyQ, cpu);
			}
			
			insLine = cpu.core(0).next();
			cpu.core(0).decodeExecute(ram.remove(insLine), ioQ, waitQ, termQ, rdyQ);
			OperatingSystemFunctions.reposition(rdyQ, insLine);
			OperatingSystemFunctions.decWaitIO(waitQ, ioQ, rdyQ);
			
			//System.out.println(cpu.core(0).job());
			
			if(cpu.core(0).job() != null){
				if(cpu.core(0).job().insRun == cpu.core(0).job().getsize()){
					termQ.add(cpu.core(0).job());
					cpu.core(0).setPCB(null);
				}
			}
		}
		
		//END PROGRAM
		System.out.println("**********************************");
		System.out.println("PROGRAM EXITING. THAT'S ALL FOLKS.");	
	}
}
