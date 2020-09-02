package uiPageObjects;

import generalUtils.Data;

public class ApiDataInteraction extends Data{
	
	public String name;
	public String salary;
	public String age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ApiDataInteraction(String id){
		super("TestData/apiData.json",id);
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
