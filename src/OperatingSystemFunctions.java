import java.io.*;
import java.util.ArrayList;

public class OperatingSystemFunctions {

//	// Boot Loader function. Takes two params, hd is the target harddrive object, path is the path to the boot file 
	public static void BootLoader(HDD hd, String path){
		try{
			//Standard buffered reader format, will support larger files than standard reader streams
			//Credit: Java official documents, StackExchange
			
			String line;
			ArrayList<String> input = new ArrayList<String>();
			Job job = new Job();
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while((line = br.readLine()) != null){
				input.add(line);
			}
			br.close();
			
			/* So we need to take the array created, and break it down
			 * for each element, we need to 1) examine the element and see if it is a header, or instruction
			 * 2) If it is a header, we save the previous job to the hard drive, and create a new job
			 * */
			if(!input.isEmpty()){
				//This loops processes the input
				for(int x=0; x < input.size(); x++){
					//if we hit a job line we need to create a new job, and add the old one if it exists
					if( input.get(x).startsWith("Job")){
						if(job.getHeader() != null){
							//System.out.print("Adding job: " + job.toString());
							hd.addContents(job);
							job = new Job();
							}
						//Creates new header for job
						job.setHeader(input.get(x));
					}
					else{
						//otherwise add instruction line
						job.addInstruction(input.get(x));
					}
				}
				
				// Add final job to hard drive
				//System.out.print("Adding last job: " + job.toString());
				hd.addContents(job);
			}
			
		//Catch begins	
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	// LongTermScheduler algorithm
	// Notes: Job header format -> Job #, numInstructions, priority
	public static void LongTermScheduler(String method, HDD hd, RAM ram, ArrayList<PCB> pcb_arr){
		boolean ram_is_full = false, done = false;
		System.out.println("HELLO FROM LTS");
		
		switch(method){
			case "SJF":
				ArrayList<String> jobsRun = new ArrayList<String>();

				while((ram.remainingMemory() != 0 || !ram_is_full) && !done){
		
					Job toDump = new Job();
					boolean haveJob = false;
					int curr_sj = 1000000000;
					
					//begin SJF loop
					//linear iteration since contents will not be sorted
					for(int x = 0; x < hd.contentSize(); x++){
						//is there room for the job, is the job smaller than other jobs found, and has it not been dumped onto RAM yet
						if(hd.get(x).numIns() < ram.remainingMemory() && hd.get(x).numIns() < curr_sj && !jobsRun.contains(hd.get(x).getHeader())){

							toDump = hd.get(x);
							curr_sj = hd.get(x).numIns();
							haveJob = true;
						}						
					}
					
					if(haveJob){
						//Once we find a job, we need to dump the contents to the RAM
                        pcb_arr.add(new PCB(toDump.getHeader(), 100-ram.remainingMemory(), toDump.numIns()));
                        //System.out.println("Line 91: " + toDump.getHeader());
                        jobsRun.add(toDump.getHeader());

                        //Dump the job instructions onto the RAM
						for(int i=0; i < toDump.numIns(); i++){
							ram.addContents(toDump.get(i));
						}
					//if we HAVENT found a job, exit loop
					}else{done=true;}
				}

				//end SJF
			break;
			//begin Priority Queue
			case "Priority":
                jobsRun = new ArrayList<String>();
				while((ram.remainingMemory() != 0 || !ram_is_full) && !done){
					
					Job toDump = new Job();
					boolean haveJob = false;
					int curr_priority = 0;
                    //String last_job = "";
					
					//begin SJF loop
					//linear iteration since contents will not be sorted
					for(int x = 0; x < hd.contentSize(); x++){
						//is there room for the job, is the job smaller than other jobs found, and has it not been dumped onto RAM yet
						if(hd.get(x).numIns() < ram.remainingMemory() && hd.get(x).priority() > curr_priority && !jobsRun.contains(hd.get(x).getHeader())){
							
							toDump = hd.get(x);
							curr_priority = hd.get(x).priority();
							haveJob = true;
						}						
					}
					
					if(haveJob){
						//Once we find a job, we need to dump the contents to the RAM
						//???: Do we need to add the header for the job?
						pcb_arr.add(new PCB(toDump.getHeader(), 0, toDump.numIns()));
                        jobsRun.add(toDump.getHeader());
                        //last_job = toDump.getHeader();
						
						//Dump the job instructions onto the RAM
						for(int i=0; i < toDump.numIns(); i++){
							ram.addContents(toDump.get(i));
						}
					//if we HAVENT found a job, exit loop
					}else{done=true;}
					
				}
				
				//System.out.println("\nFinished Priority task scheduling algorithm. Printing out results.");
				//System.out.println(ram.toString());
			break;
			//
			case "FCFS":
				int x = 0;
               //jobsRun = new ArrayList<String>();
				
				while((ram.remainingMemory() != 0 || !ram_is_full) && !done){
					if(hd.get(x).numIns() < ram.remainingMemory()){
						ram.addContents(hd.get(x).getHeader());
						
						for(int i=0; i < hd.get(x).numIns(); i++){
							ram.addContents(hd.get(x).get(i));
						}
						
						x++;
					}else{done=true;}
				}
				
				//System.out.println("\nFinished FCFS task scheduling algorithm. Printing out results.");
				//System.out.println(ram.toString());
			break;
			default:
				System.out.println("Invalid method choice!");
			break;
		}
	}
	
	public static void STS(String method, ArrayList<PCB> rdyQ, CPU cpu){
		switch(method){
		case "SJF":
			int index = 0;
			
			for(int x = 1; x < rdyQ.size(); x++){
				//Equal shortest lengths will be eval'd as FCFS
				if(rdyQ.get(x).getsize() < rdyQ.get(index).getsize()){
					index = x;
				}
			}
			
			cpu.core(0).setPCB(rdyQ.remove(index));
			break;
		case "Priority":
			break;
		case "FCFS":
			break;
		default:
			System.out.println("Error: Invalid STS Method");
			break;
		}
	}
	
	public static void reposition(ArrayList<PCB> rdyQ, int pos){
		for(int x = 0; x < rdyQ.size(); x++){
			if(rdyQ.get(x).get_JOB_POS() < pos){
				rdyQ.get(x).decPosition();
			}
		}
	}
	
	public static void decWaitIO(ArrayList<PCB> waitQ, ArrayList<PCB> ioQ, ArrayList<PCB> rdyQ){
		for(int x = 0; x < waitQ.size(); x++){
			waitQ.get(x).counter--;
			
			if(waitQ.get(x).counter == 0){
				rdyQ.add(waitQ.remove(x));
			}
		}
		
		for(int x = 0; x < ioQ.size(); x++){
			ioQ.get(x).counter--;
			
			if(ioQ.get(x).counter == 0){
				rdyQ.add(ioQ.remove(x));
			}
		}
	}
}
