package ranab;

/**
 * Base exception class of all the exceptions in this package.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */
public class BaseException extends Exception {

    public BaseException() {
        super();
    }

    public BaseException(String msg)  {
        super(msg);
    } 

    public BaseException(Exception ex)  {
        super(ex.getMessage());
    } 

}