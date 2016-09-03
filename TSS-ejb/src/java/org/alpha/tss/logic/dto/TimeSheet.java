package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class TimeSheet extends AbstractTransferObject {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer hoursDue;

	private java.time.LocalDate start;

	private java.time.LocalDate end;

	private java.util.Set<TimeSheetEntry> timeSheetEntries;

	private org.alpha.tss.entities.TimeSheetStatus status;

	public TimeSheet() { }

	public TimeSheet(long id
		, java.lang.Integer hoursDue
		, java.time.LocalDate start
		, java.time.LocalDate end
		, java.util.Set<TimeSheetEntry> timeSheetEntries
		, org.alpha.tss.entities.TimeSheetStatus status
	) {
		super(id
		);
		this.hoursDue = hoursDue;
		this.start = start;
		this.end = end;
		this.timeSheetEntries = timeSheetEntries;
		this.status = status;
	}

	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.Set<TimeSheet> wrapTimeSheet(java.util.Set<org.alpha.tss.entities.TimeSheetEntity> ins) {
		if(ins == null) return null;
		java.util.Set<TimeSheet> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.TimeSheetEntity in : ins)
			out.add(wrapTimeSheet(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.List<TimeSheet> wrapTimeSheet(java.util.List<org.alpha.tss.entities.TimeSheetEntity> ins) {
		if(ins == null) return null;
		java.util.List<TimeSheet> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.TimeSheetEntity in : ins)
			out.add(wrapTimeSheet(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static TimeSheet wrapTimeSheet(org.alpha.tss.entities.TimeSheetEntity in) {
		if(in == null) return null;
		return new TimeSheet(in.getId()
			, in.getHoursDue()
			, in.getStart()
			, in.getEnd()
			, TimeSheetEntry.wrapTimeSheetEntry(in.getTimeSheetEntries())
			, in.getStatus()
		);
	}
	public java.lang.Integer getHoursDue() {
		return hoursDue;
	}

	public void setHoursDue(java.lang.Integer hoursDue) {
		this.hoursDue = hoursDue;
	}

	public java.time.LocalDate getStart() {
		return start;
	}

	public void setStart(java.time.LocalDate start) {
		this.start = start;
	}

	public java.time.LocalDate getEnd() {
		return end;
	}

	public void setEnd(java.time.LocalDate end) {
		this.end = end;
	}

	public java.util.Set<TimeSheetEntry> getTimeSheetEntries() {
		return timeSheetEntries;
	}

	public void setTimeSheetEntries(java.util.Set<TimeSheetEntry> timeSheetEntries) {
		this.timeSheetEntries = timeSheetEntries;
	}

	public org.alpha.tss.entities.TimeSheetStatus getStatus() {
		return status;
	}

	public void setStatus(org.alpha.tss.entities.TimeSheetStatus status) {
		this.status = status;
	}

}
