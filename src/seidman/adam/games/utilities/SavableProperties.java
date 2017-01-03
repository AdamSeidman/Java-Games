package seidman.adam.games.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * Writable properties to a resource folder.
 * 
 * @author AdamSeidman
 *
 */
public class SavableProperties extends HashMap<Property, Object> implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String PROPERTIES_FILE_PATH = "SavableProperties.bat";

	/**
	 * Get the current properties from the resource folder.
	 * 
	 * @return The current version of SavableProperties if there is one. Null,
	 *         if there is not.
	 * @throws IOException
	 *             If there is an error with reading an existing file.
	 * @throws ClassNotFoundException
	 *             Will only throw if the version of SavableProperties
	 *             serialized does not match up with this version.
	 */
	public static SavableProperties getPropertiesFromResources() throws IOException, ClassNotFoundException {
		InputStream is;
		try {
			is = SavableProperties.class.getResourceAsStream(PROPERTIES_FILE_PATH);
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			throw new NoResourceDirectoryException();
		}
		ObjectInputStream ois = new ObjectInputStream(is);
		return (SavableProperties) ois.readObject();
	}

}
