import java.util.ArrayList;

public class HDD {
	private ArrayList<Job> _CONTENTS;
	private int _DISK_SPACE;
	private boolean _USING_DISK_SPACE;
	
	//Constructor using a maximum hard drive size
	public HDD(int max_size){
		_USING_DISK_SPACE = true;
		_DISK_SPACE = max_size;
		_CONTENTS = new ArrayList<Job>();
	}
	
	//Constructor with no disk space limitations, sets _MAX_SIZE to 0
	public HDD(){
		_USING_DISK_SPACE = false;
		_DISK_SPACE = -1;
		_CONTENTS = new ArrayList<Job>();
	}
	
	//loads contents to hard drive via external source
	public void addContents(Job content){
		if(_USING_DISK_SPACE != true){
			_CONTENTS.add(content);
		}else{
			//TO DO: Add remaining space check statement
			_CONTENTS.add(content);
		}
	}
	
	//pulls content off the hard drive
	public Job get(int index){
		return _CONTENTS.get(index);
	}
	
	//calculates remaining hard drive space if the object is using 
	// disk space option, returns -1 if not.
	public int remainingDiskSpace(){
		if(_USING_DISK_SPACE){
			int t = _DISK_SPACE - _CONTENTS.size();
			return t;
		}
		return -1;
	}
	
	//removes item from contents arraylist
	public void remove(int index){
		_CONTENTS.remove(index);
	}
	
	public int contentSize(){
		return _CONTENTS.size();
	}
	
	public String toString(){
		String r = "";
		for(int x = 0; x < _CONTENTS.size(); x++){
			r += _CONTENTS.get(x) + "\n";
		}
		return r;
	}
	
}
