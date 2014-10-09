import java.util.ArrayList;

public class Job {
	private String _HEADER;
	private String _JOB_NUM;
	private int _INS_NUM, _PRIORITY;
	private ArrayList<String> _INSTRUCTIONS;
	
	public Job(){
		_HEADER = null;
		_INSTRUCTIONS = new ArrayList<String>();
	}
	
	//Use this constructor
	public Job(String header, ArrayList<String> ins){
		_HEADER = header;
		_INSTRUCTIONS = ins;
	}
	
	public void setHeader(String head){
		_HEADER = head;
		
		String[] splitArr = head.split(", ");
		_JOB_NUM = splitArr[0];
		_INS_NUM = Integer.parseInt(splitArr[1]);
		_PRIORITY = Integer.parseInt(splitArr[2]);
	}
	
	public void addInstruction(String ins){
		_INSTRUCTIONS.add(ins);
	}
	
	public String getHeader(){
		return _HEADER;
	}
	
	public ArrayList<String> ins(){
		return _INSTRUCTIONS;
	}
	
	public String get(int index){
		return _INSTRUCTIONS.get(index);
	}
	
	public int numIns(){
		return _INS_NUM;
	}
	
	public int priority(){
		return _PRIORITY;
	}
	
	public String toString(){
		String t = "";
		
		t += "\nHeader: " + _HEADER + "\n";
		
		for(int o=0; o < _INSTRUCTIONS.size(); o++){
			t += _INSTRUCTIONS.get(o) + "\n";
		}
		
		return t;
	}
}
