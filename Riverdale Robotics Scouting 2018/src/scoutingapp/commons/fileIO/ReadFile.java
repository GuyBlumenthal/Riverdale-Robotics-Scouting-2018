package scoutingapp.commons.fileIO;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadFile {

	public ReadFile() {

	}

	public Object readFile(String filePath) {

		try {

			FileInputStream saveFile = new FileInputStream(filePath);

			ObjectInputStream save = new ObjectInputStream(saveFile);
			Object finalValue = save.readObject();

			save.close();
			return finalValue;

		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}
}