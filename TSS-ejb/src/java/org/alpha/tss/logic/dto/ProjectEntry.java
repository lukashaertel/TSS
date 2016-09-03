package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class ProjectEntry extends AbstractTransferObject {
	private static final long serialVersionUID = 1L;

	private java.lang.Integer hoursDue;

	private java.lang.String name;

	private java.lang.String description;

	public ProjectEntry() { }

	public ProjectEntry(long id
		, java.lang.Integer hoursDue
		, java.lang.String name
		, java.lang.String description
	) {
		super(id
		);
		this.hoursDue = hoursDue;
		this.name = name;
		this.description = description;
	}

	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.Set<ProjectEntry> wrapProjectEntry(java.util.Set<org.alpha.tss.entities.ProjectEntryEntity> ins) {
		if(ins == null) return null;
		java.util.Set<ProjectEntry> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.ProjectEntryEntity in : ins)
			out.add(wrapProjectEntry(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.List<ProjectEntry> wrapProjectEntry(java.util.List<org.alpha.tss.entities.ProjectEntryEntity> ins) {
		if(ins == null) return null;
		java.util.List<ProjectEntry> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.ProjectEntryEntity in : ins)
			out.add(wrapProjectEntry(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static ProjectEntry wrapProjectEntry(org.alpha.tss.entities.ProjectEntryEntity in) {
		if(in == null) return null;
		return new ProjectEntry(in.getId()
			, in.getHoursDue()
			, in.getName()
			, in.getDescription()
		);
	}
	public java.lang.Integer getHoursDue() {
		return hoursDue;
	}

	public void setHoursDue(java.lang.Integer hoursDue) {
		this.hoursDue = hoursDue;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

}
