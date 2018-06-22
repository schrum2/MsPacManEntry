/**
 * JMException.java
 *
 * @author Antonio J. Nebro
 * @version 1.0
 */
package entrants.pacman.Spooky.jmetal.util;

import java.io.Serializable;

/**
 * jmetal exception class
 */
public class JMException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 *
	 * @param Error
	 *            message
	 */
	public JMException(String message) {
		super(message);
	} // JmetalException
}
