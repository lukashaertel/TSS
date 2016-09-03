package org.alpha.tss.logic.dto;

@javax.xml.bind.annotation.XmlRootElement
@javax.annotation.Generated(value = "org.alpha.tss.logic.dto.GenDTOs", date = "2016-09-03")
public class Assistant extends Role {
	private static final long serialVersionUID = 1L;

	public Assistant() { }

	public Assistant(long id
	) {
		super(id
		);
	}

	public static java.util.Set<Assistant> wrapAssistant(java.util.Set<org.alpha.tss.entities.AssistantEntity> ins) {
		if(ins == null) return null;
		java.util.Set<Assistant> out = new java.util.HashSet<>();
		for(org.alpha.tss.entities.AssistantEntity in : ins)
			out.add(wrapAssistant(in));
		return out;
	}
	public static java.util.List<Assistant> wrapAssistant(java.util.List<org.alpha.tss.entities.AssistantEntity> ins) {
		if(ins == null) return null;
		java.util.List<Assistant> out = new java.util.ArrayList<>();
		for(org.alpha.tss.entities.AssistantEntity in : ins)
			out.add(wrapAssistant(in));
		return out;
	}
	public static Assistant wrapAssistant(org.alpha.tss.entities.AssistantEntity in) {
		if(in == null) return null;
		return new Assistant(in.getId()
		);
	}
}
