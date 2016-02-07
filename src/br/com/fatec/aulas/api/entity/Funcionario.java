package br.com.fatec.aulas.api.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Funcionario extends IdentifiedEntity {

	public static final String TABLE = "TBL_FUNCIONARIO";	
	public static final String COL_NOME = "NOME";
	public static final String COL_SOBRENOME = "SOBRENOME";
	public static final String COL_TELEFONE = "TELEFONE";
	public static final String COL_DATA_ADMISSAO = "DATA_ADMISSAO";
	public static final String COL_DATA_DEMISSAO = "DATA_DEMISSAO";
	public static final String COL_EMAIL = "EMAIL";

	private String nome;
	private String sobrenome;
	private String telefone;
	private Date dataAdmissao;
	private Date dataDemissao;
	private String Email;
}
