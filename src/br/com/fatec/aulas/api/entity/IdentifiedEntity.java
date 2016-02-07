package br.com.fatec.aulas.api.entity;

public abstract class IdentifiedEntity {
	
	public static final String COL_ID = "ID";
	
	protected Long id;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
