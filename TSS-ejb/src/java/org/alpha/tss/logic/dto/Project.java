package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class Project extends AbstractTransferObject {
	private static final long serialVersionUID = 1L;

	private java.util.Set<ProjectEntry> entries;

	private java.lang.String name;

	private java.util.Set<Assistant> owners;

	public Project() { }

	public Project(long id
		, java.util.Set<ProjectEntry> entries
		, java.lang.String name
		, java.util.Set<Assistant> owners
	) {
		super(id
		);
		this.entries = entries;
		this.name = name;
		this.owners = owners;
	}

	public static java.util.Set<Project> wrapProject(java.util.Set<org.alpha.tss.entities.ProjectEntity> ins) {
		if(ins == null) return null;
		java.util.Set<Project> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.ProjectEntity in : ins)
			out.add(wrapProject(in));
		return out;
	}
	public static java.util.List<Project> wrapProject(java.util.List<org.alpha.tss.entities.ProjectEntity> ins) {
		if(ins == null) return null;
		java.util.List<Project> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.ProjectEntity in : ins)
			out.add(wrapProject(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static Project wrapProject(org.alpha.tss.entities.ProjectEntity in) {
		if(in == null) return null;
		return new Project(in.getId()
			, ProjectEntry.wrapProjectEntry(in.getEntries())
			, in.getName()
			, Assistant.wrapAssistant(in.getOwners())
		);
	}
	public java.util.Set<ProjectEntry> getEntries() {
		return entries;
	}

	public void setEntries(java.util.Set<ProjectEntry> entries) {
		this.entries = entries;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.util.Set<Assistant> getOwners() {
		return owners;
	}

	public void setOwners(java.util.Set<Assistant> owners) {
		this.owners = owners;
	}

}
