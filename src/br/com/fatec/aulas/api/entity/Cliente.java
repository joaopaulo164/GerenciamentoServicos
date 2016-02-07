package br.com.fatec.aulas.api.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente extends IdentifiedEntity {
	
	public static final String TABLE = "TBL_CLIENTE";
	public static final String COL_NOME = "NOME";
	public static final String COL_SOBRENOME = "SOBRENOME";
	public static final String COL_TELEFONE = "TELEFONE";
	public static final String COL_DATA_CADASTRO = "DATA_CADASTRO";
	public static final String COL_EMAIL = "EMAIL";

	private String nome;
	private String sobrenome;
	private String telefone;
	private Date dataCadastro;
	private String email;
}
