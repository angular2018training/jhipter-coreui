/**
 * @(#) BusinessException.java
 */

package vn.nextlogix.exception;

/**
 * Defines the timeout exception.
 */
public class BusinessException extends BaseException
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 3433924303350393527L;
    private String messageCode;
    private Object[] args;


    public BusinessException()
    {
        super();
    }


    public Object[] getArgs()
    {
        return args;
    }


    public void setArgs(Object[] args)
    {
        this.args = args;
    }


    public BusinessException(String arg0, Throwable arg1)
    {
        super(arg0, arg1);
        messageCode = arg0;
    }


    public BusinessException(String arg0)
    {
        super(arg0);
        messageCode = arg0;
    }


    public BusinessException(String arg0, Object[] args)
    {
        super(arg0);
        messageCode = arg0;
        this.args = args;
    }


    public BusinessException(Throwable arg0)
    {
        super(arg0);
    }


    public String getMessageCode()
    {
        return messageCode;
    }


    public void setMessageCode(String messageCode)
    {
        this.messageCode = messageCode;
    }

}
