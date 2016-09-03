package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class TimeSheetEntry extends AbstractTransferObject {
	private static final long serialVersionUID = 1L;

	private java.time.LocalDate date;

	private java.lang.Integer hours;

	private java.lang.String comment;

	private java.lang.String descriptionOfWork;

	public TimeSheetEntry() { }

	public TimeSheetEntry(long id
		, java.time.LocalDate date
		, java.lang.Integer hours
		, java.lang.String comment
		, java.lang.String descriptionOfWork
	) {
		super(id
		);
		this.date = date;
		this.hours = hours;
		this.comment = comment;
		this.descriptionOfWork = descriptionOfWork;
	}

	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.Set<TimeSheetEntry> wrapTimeSheetEntry(java.util.Set<org.alpha.tss.entities.TimeSheetEntryEntity> ins) {
		if(ins == null) return null;
		java.util.Set<TimeSheetEntry> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.TimeSheetEntryEntity in : ins)
			out.add(wrapTimeSheetEntry(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.List<TimeSheetEntry> wrapTimeSheetEntry(java.util.List<org.alpha.tss.entities.TimeSheetEntryEntity> ins) {
		if(ins == null) return null;
		java.util.List<TimeSheetEntry> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.TimeSheetEntryEntity in : ins)
			out.add(wrapTimeSheetEntry(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static TimeSheetEntry wrapTimeSheetEntry(org.alpha.tss.entities.TimeSheetEntryEntity in) {
		if(in == null) return null;
		return new TimeSheetEntry(in.getId()
			, in.getDate()
			, in.getHours()
			, in.getComment()
			, in.getDescriptionOfWork()
		);
	}
	public java.time.LocalDate getDate() {
		return date;
	}

	public void setDate(java.time.LocalDate date) {
		this.date = date;
	}

	public java.lang.Integer getHours() {
		return hours;
	}

	public void setHours(java.lang.Integer hours) {
		this.hours = hours;
	}

	public java.lang.String getComment() {
		return comment;
	}

	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}

	public java.lang.String getDescriptionOfWork() {
		return descriptionOfWork;
	}

	public void setDescriptionOfWork(java.lang.String descriptionOfWork) {
		this.descriptionOfWork = descriptionOfWork;
	}

}
