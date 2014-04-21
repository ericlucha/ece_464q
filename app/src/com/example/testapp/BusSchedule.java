package com.example.testapp;

import java.util.ArrayList;
import java.util.List;

public class BusSchedule {
	private final int NUMBEROFBUSES = 2;
	private List<Bus> busListing;
	
	public BusSchedule()
	{
		busListing = new ArrayList<Bus>();
		
		for(int i = 0; i < NUMBEROFBUSES; i++)
		{
			busListing.add(new Bus(i));
		}
	}
	
	public String getBusName(int i)
	{
		return busListing.get(i).getName();
	}
	
	public String getBusStop(int bus, int stop)
	{
		return busListing.get(bus).getStop(stop);
	}
	
	public String getBusStopTime(int bus, int stop, int time)
	{
		return busListing.get(bus).getTime(stop, time);
	}
}