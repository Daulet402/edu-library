package kz.edu.sdu.diploma;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class App {

	public static void main(String[] args) throws Exception {
		String fileName = "C:\\orders\\meru\\Books.dbx";
		/*List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.ISO_8859_1);
		for (String line : lines) {
			System.out.println(line);
		}*/

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "ASCII"));

		FileInputStream inputStream = new FileInputStream(fileName);
		byte[] bytes = new byte[16];

		int l = 0;
		while ((l = inputStream.read(bytes)) != -1) {
			for (byte aByte : bytes) {
				System.out.println((char)aByte);
			}
		}


		List<String> encodings = Arrays.asList(
				/*"IBM00858",
				"IBM437",
				"IBM775",
				"IBM850",
				"IBM852",
				"IBM855",
				"IBM857",
				"IBM862",
				"IBM866",*/

				/*"ISO-8859-1",
				"ISO-8859-2",
				"ISO-8859-4",
				"ISO-8859-5",
				"ISO-8859-7",
				"ISO-8859-9",
				"ISO-8859-13",
				"ISO-8859-15",
				"KOI8-R",
				"KOI8-U" */

				/*"US-ASCII",
				"UTF-8",
				"UTF-16",
				"UTF-16BE",
				"UTF-16LE" */

				//	"UTF-32",
				//		"UTF-32BE",
				//			"UTF-32LE"

				/*"x-UTF-32BE-BOM",
				"x-UTF-32LE-BOM"*/
				/*"Cp1250",
				"Cp1251",
				"Cp1252",
				"Cp1253",
				"Cp1254",
				"Cp1257"*/
		);


		for (String encoding : encodings) {
			try (BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), encoding))) {
				System.out.println(encoding + ":");
				System.out.println(bufferedReader1.readLine());
				System.out.println("-----------------------------------------------------");
			}
		}
	}
}
