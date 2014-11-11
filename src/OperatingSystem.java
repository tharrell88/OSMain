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
        ArrayList<PCB> readyQueue = new ArrayList<PCB>();
        ArrayList<PCB> waitQueue = new ArrayList<PCB>();
        ArrayList<PCB> termQueue = new ArrayList<PCB>();
        ArrayList<PCB> ioQueue = new ArrayList<PCB>();
        CPU cpu = new CPU();
		
		boolean ready = false;
		boolean done = false;
		
		//emulator settings
		String method = "SJF";
		
		//cant use relative paths without some extra changes so need to explicitly tell Java where the file is
		String path = "C:\\Users\\swema_000\\Desktop\\Eclipse Workspace\\Operating Systems\\OSMain\\src\\ugradPart1.txt";
		//String path = "I:\\Users\\swema_000\\Documents\\GitHub\\OSMain\\src\\ugradPart1.txt";
		Scanner s = new Scanner(System.in);
		
		System.out.print("Operating System Emulator\nBy: Travis Harrell\n");
		
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
				default:
					System.out.println("Sorry, invalid choice.\n");
					break;
			}
		}
		
		//Run boot loader (see OperatingSystemFunctions function class)
		OperatingSystemFunctions.BootLoader(hardDrive, path);
		
		while(!done){	        
	        //MAIN LOOP START
	        
	        //1) Start of loop. Check RAM.
				//a) Is there anything ready to come out of wait or I/O queues? If so, move to ReadyQueue
	        	//b) Is there enough room in the RAM for a new job? If so, run LTS.
				//c) If no jobs are left on the HDD, RQ/IO/WQ, then finish running commands on core
				//d) If there CPU is empty of jobs, end program and dump results
			//2) Fetch jobs for CPU if CPU is ready for one
				//a) Is there a core available? If so, fetch a job for it
				//b) I
		}  
	}
}
