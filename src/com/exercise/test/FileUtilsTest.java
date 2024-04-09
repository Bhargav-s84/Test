package com.exercise.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.exercise.FileUtils;

public class FileUtilsTest {

	@Rule
	public TemporaryFolder rootFolder = new TemporaryFolder();

	private File myFolder;

	private FileUtils fileUtils = new FileUtils();

	@Before
	public void beforeEach() throws IOException {
		myFolder = rootFolder.newFolder("test-folder");

	}

	@Test
	public void testFileWithSingleWord() throws IOException {

		File inputFile = new File(myFolder, "input.txt");

		File outputFile = new File(myFolder, "output.txt");

		String filContent = "ABCD";
		Files.write(Paths.get(inputFile.getAbsolutePath()), filContent.getBytes(), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);

		fileUtils.reverseFileContent(inputFile, outputFile);

		String content = new String(Files.readAllBytes(Paths.get(outputFile.getAbsolutePath())));

		assertEquals(content, "DCBA");

	}

	@Test
	public void testFileWithSingleLine() throws IOException {

		File inputFile = new File(myFolder, "input.txt");

		File outputFile = new File(myFolder, "output.txt");

		String filContent = "Single line test";
		Files.write(Paths.get(inputFile.getAbsolutePath()), filContent.getBytes(), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);

		fileUtils.reverseFileContent(inputFile, outputFile);

		String content = new String(Files.readAllBytes(Paths.get(outputFile.getAbsolutePath())));

		assertEquals(content, "tset enil elgniS");

	}

	@Test
	public void testFileWithMultipleLine() throws IOException {

		File inputFile = new File(myFolder, "input.txt");

		File outputFile = new File(myFolder, "output.txt");

		String filContent = "This" + "\nis multiline" + "\ntest";
		Files.write(Paths.get(inputFile.getAbsolutePath()), filContent.getBytes(), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);

		fileUtils.reverseFileContent(inputFile, outputFile);

		String content = new String(Files.readAllBytes(Paths.get(outputFile.getAbsolutePath())));

		assertEquals(content, "tset\n" + "enilitlum si\n" + "sihT");

	}

	@Test
	public void testFileWithOnlyBlankSpaces() throws IOException {

		File inputFile = new File(myFolder, "input.txt");

		File outputFile = new File(myFolder, "output.txt");
		String empty = "  ";
		Files.write(Paths.get(inputFile.getAbsolutePath()), empty.getBytes(), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);

		fileUtils.reverseFileContent(inputFile, outputFile);

		String content = new String(Files.readAllBytes(Paths.get(outputFile.getAbsolutePath())));

		assertEquals(content, empty);

	}

	@Test
	public void testEmptyFile() throws IOException {

		File inputFile = new File(myFolder, "input.txt");

		File outputFile = new File(myFolder, "output.txt");
		String empty = "";
		Files.write(Paths.get(inputFile.getAbsolutePath()), empty.getBytes(), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);

		fileUtils.reverseFileContent(inputFile, outputFile);

		boolean outPutFileexists = Files.notExists(Paths.get(outputFile.getAbsolutePath()));

		assertEquals(outPutFileexists, Boolean.TRUE);

	}
}
