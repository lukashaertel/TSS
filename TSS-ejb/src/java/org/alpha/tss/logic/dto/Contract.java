package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class Contract extends AbstractTransferObject {
	private static final long serialVersionUID = 1L;

	private java.util.Set<Assistant> assistants;

	private java.lang.Integer hoursPerWeek;

	private java.lang.Integer workingDaysPerWeek;

	private java.time.LocalDate start;

	private java.lang.String description;

	private java.util.Currency salary;

	private Employee employee;

	private org.alpha.tss.entities.ContractType type;

	private java.lang.Integer totalHoursDue;

	private org.alpha.tss.entities.TimeSheetFrequency frequency;

	private java.util.Set<Secretary> secretaries;

	private java.time.LocalDate abort;

	private java.lang.Integer vacationHours;

	private java.util.Set<TimeSheet> timeSheets;

	private java.lang.String name;

	private java.lang.String comment;

	private java.time.LocalDate end;

	private java.lang.Integer vacationDaysPerYear;

	private Supervisor supervisor;

	private org.alpha.tss.entities.ContractStatus status;

	public Contract() { }

	public Contract(long id
		, java.util.Set<Assistant> assistants
		, java.lang.Integer hoursPerWeek
		, java.lang.Integer workingDaysPerWeek
		, java.time.LocalDate start
		, java.lang.String description
		, java.util.Currency salary
		, Employee employee
		, org.alpha.tss.entities.ContractType type
		, java.lang.Integer totalHoursDue
		, org.alpha.tss.entities.TimeSheetFrequency frequency
		, java.util.Set<Secretary> secretaries
		, java.time.LocalDate abort
		, java.lang.Integer vacationHours
		, java.util.Set<TimeSheet> timeSheets
		, java.lang.String name
		, java.lang.String comment
		, java.time.LocalDate end
		, java.lang.Integer vacationDaysPerYear
		, Supervisor supervisor
		, org.alpha.tss.entities.ContractStatus status
	) {
		super(id
		);
		this.assistants = assistants;
		this.hoursPerWeek = hoursPerWeek;
		this.workingDaysPerWeek = workingDaysPerWeek;
		this.start = start;
		this.description = description;
		this.salary = salary;
		this.employee = employee;
		this.type = type;
		this.totalHoursDue = totalHoursDue;
		this.frequency = frequency;
		this.secretaries = secretaries;
		this.abort = abort;
		this.vacationHours = vacationHours;
		this.timeSheets = timeSheets;
		this.name = name;
		this.comment = comment;
		this.end = end;
		this.vacationDaysPerYear = vacationDaysPerYear;
		this.supervisor = supervisor;
		this.status = status;
	}

	public static java.util.Set<Contract> wrapContract(java.util.Set<org.alpha.tss.entities.ContractEntity> ins) {
		if(ins == null) return null;
		java.util.Set<Contract> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.ContractEntity in : ins)
			out.add(wrapContract(in));
		return out;
	}
	public static java.util.List<Contract> wrapContract(java.util.List<org.alpha.tss.entities.ContractEntity> ins) {
		if(ins == null) return null;
		java.util.List<Contract> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.ContractEntity in : ins)
			out.add(wrapContract(in));
		return out;
	}
	public static Contract wrapContract(org.alpha.tss.entities.ContractEntity in) {
		if(in == null) return null;
		return new Contract(in.getId()
			, Assistant.wrapAssistant(in.getAssistants())
			, in.getHoursPerWeek()
			, in.getWorkingDaysPerWeek()
			, in.getStart()
			, in.getDescription()
			, in.getSalary()
			, Employee.wrapEmployee(in.getEmployee())
			, in.getType()
			, in.getTotalHoursDue()
			, in.getFrequency()
			, Secretary.wrapSecretary(in.getSecretaries())
			, in.getAbort()
			, in.getVacationHours()
			, TimeSheet.wrapTimeSheet(in.getTimeSheets())
			, in.getName()
			, in.getComment()
			, in.getEnd()
			, in.getVacationDaysPerYear()
			, Supervisor.wrapSupervisor(in.getSupervisor())
			, in.getStatus()
		);
	}
	public java.util.Set<Assistant> getAssistants() {
		return assistants;
	}

	public void setAssistants(java.util.Set<Assistant> assistants) {
		this.assistants = assistants;
	}

	public java.lang.Integer getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(java.lang.Integer hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public java.lang.Integer getWorkingDaysPerWeek() {
		return workingDaysPerWeek;
	}

	public void setWorkingDaysPerWeek(java.lang.Integer workingDaysPerWeek) {
		this.workingDaysPerWeek = workingDaysPerWeek;
	}

	public java.time.LocalDate getStart() {
		return start;
	}

	public void setStart(java.time.LocalDate start) {
		this.start = start;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.util.Currency getSalary() {
		return salary;
	}

	public void setSalary(java.util.Currency salary) {
		this.salary = salary;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public org.alpha.tss.entities.ContractType getType() {
		return type;
	}

	public void setType(org.alpha.tss.entities.ContractType type) {
		this.type = type;
	}

	public java.lang.Integer getTotalHoursDue() {
		return totalHoursDue;
	}

	public void setTotalHoursDue(java.lang.Integer totalHoursDue) {
		this.totalHoursDue = totalHoursDue;
	}

	public org.alpha.tss.entities.TimeSheetFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(org.alpha.tss.entities.TimeSheetFrequency frequency) {
		this.frequency = frequency;
	}

	public java.util.Set<Secretary> getSecretaries() {
		return secretaries;
	}

	public void setSecretaries(java.util.Set<Secretary> secretaries) {
		this.secretaries = secretaries;
	}

	public java.time.LocalDate getAbort() {
		return abort;
	}

	public void setAbort(java.time.LocalDate abort) {
		this.abort = abort;
	}

	public java.lang.Integer getVacationHours() {
		return vacationHours;
	}

	public void setVacationHours(java.lang.Integer vacationHours) {
		this.vacationHours = vacationHours;
	}

	public java.util.Set<TimeSheet> getTimeSheets() {
		return timeSheets;
	}

	public void setTimeSheets(java.util.Set<TimeSheet> timeSheets) {
		this.timeSheets = timeSheets;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getComment() {
		return comment;
	}

	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}

	public java.time.LocalDate getEnd() {
		return end;
	}

	public void setEnd(java.time.LocalDate end) {
		this.end = end;
	}

	public java.lang.Integer getVacationDaysPerYear() {
		return vacationDaysPerYear;
	}

	public void setVacationDaysPerYear(java.lang.Integer vacationDaysPerYear) {
		this.vacationDaysPerYear = vacationDaysPerYear;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public org.alpha.tss.entities.ContractStatus getStatus() {
		return status;
	}

	public void setStatus(org.alpha.tss.entities.ContractStatus status) {
		this.status = status;
	}

}
