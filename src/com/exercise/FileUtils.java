package com.exercise;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtils {

	public void reverseFileContent(File inputFile, File outputFile) throws IOException {

		try (RandomAccessFile raf = new RandomAccessFile(inputFile.getAbsolutePath(), "r")) {
			long position = raf.length();
			while (position > 0) {
				position -= 1;
				raf.seek(position);
				byte b = raf.readByte();

				Files.write(Paths.get(outputFile.getAbsolutePath()), String.valueOf((char) b).getBytes(),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);

			}
		}
	}

}
