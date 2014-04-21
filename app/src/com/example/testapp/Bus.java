package com.example.testapp;

import java.util.ArrayList;
import java.util.List;

public class Bus {
	private List<String> stopListing;
	private List<List<String>> timeListing;
	private String busName;
	
	public Bus(int busNum){
		stopListing = new ArrayList<String>();
		timeListing = new ArrayList<List<String>>();
		
		switch (busNum) {
			case 0:
				genBusPRCN();
				break;
			case 1:
				genBusPRCS();
				break;
			default:
				break;
		}
	}
	
	private void genBusPRCN(){
		// Add Stops
		stopListing.add("Stop A");
		timeListing.add(new ArrayList<String>());
		stopListing.add("Stop B");
		timeListing.add(new ArrayList<String>());
		
		// Add Times
		timeListing.get(0).add("Time A");
		timeListing.get(0).add("Time B");
		timeListing.get(0).add("Time C");
		timeListing.get(1).add("Time A");
		timeListing.get(1).add("Time B");
		timeListing.get(1).add("Time C");
	}
	
	private void genBusPRCS(){
		// Add Stops
		stopListing.add("Stop A");
		timeListing.add(new ArrayList<String>());
		stopListing.add("Stop B");
		timeListing.add(new ArrayList<String>());
		
		// Add Times
		timeListing.get(0).add("Time A");
		timeListing.get(0).add("Time B");
		timeListing.get(0).add("Time C");
		timeListing.get(1).add("Time A");
		timeListing.get(1).add("Time B");
		timeListing.get(1).add("Time C");
	}
	
	public String getName()
	{
		return busName;
	}
	
	public String getStop(int stopNum) {
		return stopListing.get(stopNum);
	}
	
	public String getTime(int stopNum, int timeNum) {
		return timeListing.get(stopNum).get(timeNum);
	}
}
