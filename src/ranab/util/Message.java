package ranab.util;

/**
 * Message interface - used in message queue.
 *
 * @author <a href="mailto:rana_b@yahoo.com">Rana Bhattacharyya</a>
 */

public
interface Message {
    void execute() throws Exception;
}    
