package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class Supervisor extends Role {
	private static final long serialVersionUID = 1L;

	public Supervisor() { }

	public Supervisor(long id
	) {
		super(id
		);
	}

	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.Set<Supervisor> wrapSupervisor(java.util.Set<org.alpha.tss.entities.SupervisorEntity> ins) {
		if(ins == null) return null;
		java.util.Set<Supervisor> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.SupervisorEntity in : ins)
			out.add(wrapSupervisor(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.List<Supervisor> wrapSupervisor(java.util.List<org.alpha.tss.entities.SupervisorEntity> ins) {
		if(ins == null) return null;
		java.util.List<Supervisor> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.SupervisorEntity in : ins)
			out.add(wrapSupervisor(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static Supervisor wrapSupervisor(org.alpha.tss.entities.SupervisorEntity in) {
		if(in == null) return null;
		return new Supervisor(in.getId()
		);
	}
}
