package com.muSWS.core;

public class Mutant implements Comparable<Mutant>{	
	private String mutantId;
	private String mutantName;
	public Mutant(String mutantId, String mutantName) {
		super();
		this.mutantId = mutantId;
		this.mutantName = mutantName;
	}
	
	
	public String getMutantId() {
		return mutantId;
	}
	public void setMutantId(String mutantId) {
		this.mutantId = mutantId;
	}
	public String getMutantName() {
		return mutantName;
	}
	public void setMutantName(String mutantName) {
		this.mutantName = mutantName;
	}
	
	public String toString(){	
		//return "Mutant Id ="+this.mutantId+", Mutant Name ="+this.mutantName;
		return "Mutant Id ="+this.mutantId;
	}
	
	public boolean equals(Mutant m){
		return this.getMutantId().equals(m.getMutantId()); 	
	}
	
	@Override
	public int compareTo(Mutant m) {
		return this.getMutantId().compareTo(m.getMutantId());
	}
	
	
}
