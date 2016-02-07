package br.com.fatec.aulas.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class TestStrutsAction extends ActionSupport {
	private int a;
	private int b;
	private int sum;
	
	public String calcular() {
		this.sum = this.a + this.b;
		return SUCCESS;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
	

}
