package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class Employee extends Role {
	private static final long serialVersionUID = 1L;

	public Employee() { }

	public Employee(long id
	) {
		super(id
		);
	}

	public static java.util.Set<Employee> wrapEmployee(java.util.Set<org.alpha.tss.entities.EmployeeEntity> ins) {
		if(ins == null) return null;
		java.util.Set<Employee> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.EmployeeEntity in : ins)
			out.add(wrapEmployee(in));
		return out;
	}
	public static java.util.List<Employee> wrapEmployee(java.util.List<org.alpha.tss.entities.EmployeeEntity> ins) {
		if(ins == null) return null;
		java.util.List<Employee> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.EmployeeEntity in : ins)
			out.add(wrapEmployee(in));
		return out;
	}
	public static Employee wrapEmployee(org.alpha.tss.entities.EmployeeEntity in) {
		if(in == null) return null;
		return new Employee(in.getId()
		);
	}
}
