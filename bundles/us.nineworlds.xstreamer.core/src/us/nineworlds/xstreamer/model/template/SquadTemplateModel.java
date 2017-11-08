package us.nineworlds.xstreamer.model.template;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SquadTemplateModel {
	

	@JacksonXmlProperty(isAttribute = true, localName = "name")
	private String name;
	
	@JacksonXmlProperty(isAttribute = true, localName = "description")
	private String description;
	
	@JacksonXmlProperty(isAttribute = true, localName = "id")
	private String id;
	
	@JacksonXmlProperty(isAttribute = true, localName = "filename")
	private String filename;
	
	@JacksonXmlProperty(isAttribute = true, localName = "path")
	private String path;
	
	@JacksonXmlProperty(isAttribute = true, localName = "enabled")
	private boolean enabled;
	
	@JacksonXmlProperty(isAttribute = true, localName = "type")
	private String type;
	
	@JacksonXmlProperty(isAttribute = true, localName = "format")
	private String format;
	

	public SquadTemplateModel() {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}


}
