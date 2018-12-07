package typ21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Main {

	Main(String pathToItemList) {

		/**
		 * from file to ArrayList
		 */
		File file = new File(pathToItemList);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.err.println("Datei nicht gefunden! Beende ...");
			System.exit(1);
		}
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(fis, "UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(isr);
		String line;
		ArrayList<String> list = new ArrayList<String>();
		try {
			while ((line = br.readLine()) != null) {
				if (!line.equals("")) {
					list.add(line);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * calculate necessary combinations
		 */
		ArrayList<String> combinations = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				boolean doIt = true;

				if (i == j) {
					doIt = false;
				}

				String theWillBe;
				if (i > j) {
					theWillBe = j + "_" + i;
				} else {
					theWillBe = i + "_" + j;
				}

				for (int k = 0; k < combinations.size(); k++) {
					if (combinations.get(k).equals(theWillBe)) {
						doIt = false;
					}
				}

				if (doIt) {
					combinations.add(theWillBe);
				}
			}
		}
		System.out.println(combinations.size());
	}

}
