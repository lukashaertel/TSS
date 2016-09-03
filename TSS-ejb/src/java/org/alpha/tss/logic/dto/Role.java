package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class Role extends AbstractTransferObject {
	private static final long serialVersionUID = 1L;

	public Role() { }

	public Role(long id
	) {
		super(id
		);
	}

	public static java.util.Set<Role> wrapRole(java.util.Set<org.alpha.tss.entities.RoleEntity> ins) {
		if(ins == null) return null;
		java.util.Set<Role> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.RoleEntity in : ins)
			out.add(wrapRole(in));
		return out;
	}
	public static java.util.List<Role> wrapRole(java.util.List<org.alpha.tss.entities.RoleEntity> ins) {
		if(ins == null) return null;
		java.util.List<Role> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.RoleEntity in : ins)
			out.add(wrapRole(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static Role wrapRole(org.alpha.tss.entities.RoleEntity in) {
		if(in == null) return null;
		return new Role(in.getId()
		);
	}
}
