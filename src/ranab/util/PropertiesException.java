package ranab.util;

/**
 * Properties exception class.
 * 
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */

public
class PropertiesException extends ranab.BaseException {

    public PropertiesException()  {
        super();
    }

    public PropertiesException(String msg)  {
        super(msg);
    }

    public PropertiesException(Exception ex)  {
        super(ex);
    }
}