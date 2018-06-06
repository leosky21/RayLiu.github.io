package Imitate_Process;

public class enitity_PCB {
	private int id;
	private int cpuTime;
	private int allTime;
	private int startBlock;
	private int blockTime;
	private String state;
	private enitity_PCB next;
	/**
	 * @param id
	 * @param cpuTime
	 * @param allTime
	 * @param startBlock
	 * @param blockTime
	 * @param state
	 * @param next
	 */
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	
	public void setCpuTime(int cpuTime){
		this.cpuTime = cpuTime;
	}
	public int getCpuTime(){
		return cpuTime;
	}
	
	public void setAllTime(int allTime){
		this.allTime = allTime;
	}
	public int getAllTime(){
		return allTime;
	}
	
	public int getStartBlock(){
		return startBlock;
	}
	public void setStartBlock(int startBlock){
		this.startBlock = startBlock;
	}
	
	public int getBlockTime(){
		return blockTime;
	}
	public void setBlockTime(int blockTime){
		this.blockTime = blockTime;
	}
	
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}

	public enitity_PCB getNext(){
		return next;
	}
	public void setNext(enitity_PCB next){
		this.next = next;
	}
}
