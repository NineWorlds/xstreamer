package us.nineworlds.xstreamer.model.template;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "templates")
public class Templates {

	
	@JacksonXmlProperty(localName = "template", isAttribute = false)
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<SquadTemplateModel> templates;
	
	public Templates() {
		// TODO Auto-generated constructor stub
	}

	public List<SquadTemplateModel> getTemplates() {
		return templates;
	}

	public void setTemplates(List<SquadTemplateModel> templates) {
		this.templates = templates;
	}

}
