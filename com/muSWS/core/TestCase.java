package com.muSWS.core;

public class TestCase implements Comparable<TestCase>{	
	private int testId;
	private String testInput;
	private String expectedOutput;
	public TestCase(int testId, String testInput, String expectedOutput) {
		super();
		this.testId = testId;
		this.testInput = testInput;
		this.expectedOutput = expectedOutput;
	}
	
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestInput() {
		return testInput;
	}
	public void setTestInput(String testInput) {
		this.testInput = testInput;
	}
	public String getExpectedOutput() {
		return expectedOutput;
	}
	public void setExpectedOutput(String expectedOutput) {
		this.expectedOutput = expectedOutput;
	}	
	
	public String toString(){
		//return "test case Id\t"+this.testId+"test Input\t"+this.testInput+"expected Output\t"+this.expectedOutput;
		return "test case Id\t"+this.testId;
		
	}
	
	public boolean equals(TestCase test){
		return test.getTestId()==this.getTestId();		
	}

	@Override
	public int compareTo(TestCase test) {
		if (this.getTestId() > test.getTestId()) return 1;
		else if (this.getTestId() < test.getTestId()) return -1;
		else return 0;
	}
}
