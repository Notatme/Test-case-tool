package gettable;

import java.util.List;


public class Getrule implements  Comparable<Getrule>{
	
	private List<String> list;
	private int n;
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
		n = list.size(); 
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n=n;
	}

	@Override
	public int compareTo(Getrule o) {
		return this.list.size()-o.list.size();
	}
}
