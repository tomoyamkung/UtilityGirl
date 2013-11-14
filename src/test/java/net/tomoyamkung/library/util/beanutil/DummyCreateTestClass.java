package net.tomoyamkung.library.util.beanutil;

import java.util.Date;

public class DummyCreateTestClass {

	private String s;
	@DummyExcludeAnnotation
	private Integer i;
	private Date d;
	private Boolean b;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	public Boolean getB() {
		return b;
	}

	public void setB(Boolean b) {
		this.b = b;
	}

}
