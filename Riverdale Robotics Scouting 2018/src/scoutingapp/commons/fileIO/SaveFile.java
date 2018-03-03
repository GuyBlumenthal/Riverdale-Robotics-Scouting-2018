package scoutingapp.commons.fileIO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import scoutingapp.commons.RegionalCollection;

public class SaveFile {

	public SaveFile() {

	}

	public void saveFile(RegionalCollection contents, String fileName, String fileExtension) throws IOException {
		
		FileOutputStream saveFile = new FileOutputStream(fileName + "." + fileExtension);

		ObjectOutputStream save = new ObjectOutputStream(saveFile);
		save.writeObject(contents);

		save.close();
		
	}

}