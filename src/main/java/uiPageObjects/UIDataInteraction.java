package uiPageObjects;

import java.io.FileNotFoundException;

import generalUtils.Data;

//This class is used to interact with json file for getter and setter

public class UIDataInteraction extends Data{
	
	private String toCity;
	private String fromCity;
	private String way;
	private String departDate;
	private String returnDate;
	
	public UIDataInteraction(String id) {
		super("TestData/UIData.json",id);
	}
	
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}

	public String getDepartDate() {
		return departDate;
	}
	public void setDepartDate(String departDate) {
		this.departDate = departDate;
	}
	
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
