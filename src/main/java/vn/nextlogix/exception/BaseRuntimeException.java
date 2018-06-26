package vn.nextlogix.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseRuntimeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4660866840197519646L;
	private String exceptionID;

	protected BaseRuntimeException() {
		super();
		genExceptionId();
	}

	protected BaseRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		genExceptionId();
	}

	protected BaseRuntimeException(String arg0) {
		super(arg0);
		genExceptionId();
	}

	protected BaseRuntimeException(Throwable arg0) {
		super(arg0);
		genExceptionId();
	}

	private void genExceptionId() {
		if (exceptionID != null) {
			return;
		}

		DateFormat format = new SimpleDateFormat("yyMMddkkmmssSSS");
		StringBuffer buffer = new StringBuffer();

		buffer.append(format.format(new Date()));

		NumberFormat numberFormat = new DecimalFormat("0000");
		numberFormat.setMaximumIntegerDigits(4);
		buffer.append(numberFormat.format(this.hashCode()));

		exceptionID = buffer.toString();
	}

	public String getExceptionID() {
		return exceptionID;
	}

	public void setExceptionID(String exceptionID) {
		this.exceptionID = exceptionID;
	}

	@Override
	public void printStackTrace() {
		System.out.println("exception ID:" + getExceptionID() + " ");
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		s.println("exception ID:" + getExceptionID() + " ");
		super.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		s.println("exception ID:" + getExceptionID() + " ");
		super.printStackTrace(s);
	}

	@Override
	public String toString() {
		return "exception ID:" + getExceptionID() + " " + super.toString();
	}

}
