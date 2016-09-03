package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class Secretary extends Role {
	private static final long serialVersionUID = 1L;

	public Secretary() { }

	public Secretary(long id
	) {
		super(id
		);
	}

	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.Set<Secretary> wrapSecretary(java.util.Set<org.alpha.tss.entities.SecretaryEntity> ins) {
		if(ins == null) return null;
		java.util.Set<Secretary> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.SecretaryEntity in : ins)
			out.add(wrapSecretary(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static java.util.List<Secretary> wrapSecretary(java.util.List<org.alpha.tss.entities.SecretaryEntity> ins) {
		if(ins == null) return null;
		java.util.List<Secretary> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.SecretaryEntity in : ins)
			out.add(wrapSecretary(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static Secretary wrapSecretary(org.alpha.tss.entities.SecretaryEntity in) {
		if(in == null) return null;
		return new Secretary(in.getId()
		);
	}
}
