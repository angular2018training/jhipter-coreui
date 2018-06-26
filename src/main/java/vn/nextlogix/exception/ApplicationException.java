package vn.nextlogix.exception;

import java.io.PrintStream;
import java.io.PrintWriter;


public class ApplicationException extends BaseException
{
    /**
	 * 
	 */
    private static final long serialVersionUID = -5496100867391321688L;

    private String messageCode = null;

    private Object[] messageParams = null;


    public String getMessageCode()
    {
        return messageCode;
    }


    public Object[] getMessageParams()
    {
        return messageParams;
    }


    public ApplicationException()
    {
        super();
    }


    public ApplicationException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
        messageCode = arg0;
    }


    public ApplicationException(String arg0)
    {
        super(arg0);
        messageCode = arg0;
    }


    public ApplicationException(String errorCode, String errorMessage)
    {
        messageCode = errorMessage;
        setExceptionID(errorCode);
    }


    public ApplicationException(String arg0, Throwable arg1, Object[] params)
    {
        super(arg0, arg1);
        messageParams = params;
        messageCode = arg0;
    }


    public ApplicationException(String arg0, Object[] params)
    {
        super(arg0);
        messageParams = params;
        messageCode = arg0;
    }


    private String printParams()
    {
        if (messageParams == null)
        {
            return null;
        }
        String s = "";
        for (Object b : messageParams)
        {
            s += "params :" + (b == null ? "null" : b.toString()) + "\n";
        }
        return s;
    }


    @Override
    public void printStackTrace()
    {
        System.out.println("message code:" + messageCode + " ");
        System.out.println("message params:" + printParams() + " ");
        super.printStackTrace();
    }


    @Override
    public void printStackTrace(PrintStream s)
    {
        s.println("message code:" + messageCode + " ");
        s.println("message params:" + printParams() + " ");
        super.printStackTrace(s);
    }


    @Override
    public void printStackTrace(PrintWriter s)
    {
        s.println("message code:" + messageCode + " ");
        s.println("message params:" + printParams() + " ");
        super.printStackTrace(s);
    }


    @Override
    public String toString()
    {
        return "message code:" + messageCode + " " + "message params:" + printParams() + " " + super.toString();
    }

}
