package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class Person extends AbstractTransferObject {
	private static final long serialVersionUID = 1L;

	private java.lang.String firstname;

	private boolean groupReminders;

	private java.util.Set<Role> roles;

	private java.time.LocalDate dateOfBirth;

	private java.lang.String title;

	private java.lang.String email;

	private java.lang.String lastname;

	private java.util.Locale preferredLocale;

	public Person() { }

	public Person(long id
		, java.lang.String firstname
		, boolean groupReminders
		, java.util.Set<Role> roles
		, java.time.LocalDate dateOfBirth
		, java.lang.String title
		, java.lang.String email
		, java.lang.String lastname
		, java.util.Locale preferredLocale
	) {
		super(id
		);
		this.firstname = firstname;
		this.groupReminders = groupReminders;
		this.roles = roles;
		this.dateOfBirth = dateOfBirth;
		this.title = title;
		this.email = email;
		this.lastname = lastname;
		this.preferredLocale = preferredLocale;
	}

	public static java.util.Set<Person> wrapPerson(java.util.Set<org.alpha.tss.entities.PersonEntity> ins) {
		if(ins == null) return null;
		java.util.Set<Person> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.PersonEntity in : ins)
			out.add(wrapPerson(in));
		return out;
	}
	public static java.util.List<Person> wrapPerson(java.util.List<org.alpha.tss.entities.PersonEntity> ins) {
		if(ins == null) return null;
		java.util.List<Person> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.PersonEntity in : ins)
			out.add(wrapPerson(in));
		return out;
	}
	// Abstract TO conversion is not implemented
	@java.lang.Deprecated
	public static Person wrapPerson(org.alpha.tss.entities.PersonEntity in) {
		if(in == null) return null;
		return new Person(in.getId()
			, in.getFirstname()
			, in.isGroupReminders()
			, Role.wrapRole(in.getRoles())
			, in.getDateOfBirth()
			, in.getTitle()
			, in.getEmail()
			, in.getLastname()
			, in.getPreferredLocale()
		);
	}
	public java.lang.String getFirstname() {
		return firstname;
	}

	public void setFirstname(java.lang.String firstname) {
		this.firstname = firstname;
	}

	public boolean isGroupReminders() {
		return groupReminders;
	}

	public void setGroupReminders(boolean groupReminders) {
		this.groupReminders = groupReminders;
	}

	public java.util.Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(java.util.Set<Role> roles) {
		this.roles = roles;
	}

	public java.time.LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(java.time.LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getLastname() {
		return lastname;
	}

	public void setLastname(java.lang.String lastname) {
		this.lastname = lastname;
	}

	public java.util.Locale getPreferredLocale() {
		return preferredLocale;
	}

	public void setPreferredLocale(java.util.Locale preferredLocale) {
		this.preferredLocale = preferredLocale;
	}

}
