//import jdk.internal.util.xml.impl.ReaderUTF16;

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
		
		//start loop boolean
		boolean ready = false;
		
		//emulator settings
		String method = "SJF";
		
		//cant use relative paths without some extra changes so need to explicitly tell Java where the file is
		//String path = "C:\\Users\\swema_000\\Desktop\\Eclipse Workspace\\Operating Systems\\src\\ugradPart1.txt";
		String path = "I:\\Users\\swema_000\\Documents\\GitHub\\OSMain\\src\\ugradPart1.txt";
		
		//scanner
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
		
		System.out.println("Moving jobs from HD to RAM using " + method);
		OperatingSystemFunctions.LongTermScheduler(method, hardDrive, ram, readyQueue);
        System.out.println("Printing out PCB ReadyQueue:\n");
        for(int x = 0; x < readyQueue.size(); x++){
            System.out.println(readyQueue.get(x));
        }
        
        //Enter main loop if jobs in Readyqueue (should always evaluate to true?)
        if(!readyQueue.isEmpty()){
        	//main loop
        	while(true){
        		//pass arguments to CPU 1-4
        		//if process is done, move to terminate queue
        		//else move to wait queue or I/O queue
        		//if enough space for a new process in ram, add it
        	}
        }
        
	}
}
