import java.util.ArrayList;

public class RAM {
	private ArrayList<String> _CONTENTS;
	private int _MEMORY;
	
	//Constructor using a maximum hard drive size
	public RAM(int max_size){
		_MEMORY = max_size;
		_CONTENTS = new ArrayList<String>();
	}
	
	//loads contents to hard drive via external source
	public void addContents(String content){
		_CONTENTS.add(content);
	}
	
	//pulls content off the hard drive
	public String load(int index){
		return _CONTENTS.get(index);
	}

	public int memorySize(){
		return _MEMORY;
	}
	
	//
	public int remainingMemory(){
			int t = _MEMORY - _CONTENTS.size();
			return t;
	}
	
	public boolean contains(String query){
		return _CONTENTS.contains(query);
	}
	
	//removes item from contents arraylist
	public String remove(int index){
		return _CONTENTS.remove(index);
	}
	
	public int space(){
		return _MEMORY - _CONTENTS.size();
	}
	
	public void setMemorySize(int x){
		_MEMORY = x;
	}
	
	public String toString(){
		String ts = "";
		
		for(int x=0; x < _CONTENTS.size(); x++){
			ts += x + ": " +  _CONTENTS.get(x) + "\n";
		}
		return ts;
	}
	
	public boolean isEmpty(){
		return _CONTENTS.isEmpty();
	}
}
