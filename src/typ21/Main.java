package typ21;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
		System.out.println("Es gibt " + combinations.size() + " Kombinationen.");

		/**
		 * ask user and setup score
		 */
		int[] score = new int[list.size()];
		for (int i = 0; i < score.length; i++) {
			score[i] = 0;
		}

		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < combinations.size(); i++) {
			String thisCombi = combinations.get(i);
			String[] arrStrCombis = thisCombi.split("_");
			int[] arrIntCombis = new int[arrStrCombis.length];
			for (int j = 0; j < arrStrCombis.length; j++) {
				arrIntCombis[j] = Integer.parseInt(arrStrCombis[j]);
			}
			String[] posi = new String[2];
			posi[0] = list.get(arrIntCombis[0]);
			posi[1] = list.get(arrIntCombis[1]);
			System.out.println(posi[0] + " vs. " + posi[1]);

			System.out.println("Was ist wichtiger/besser?:");
			System.out.println("1: " + posi[0]);
			System.out.println("2: " + posi[1]);
			int choice = sc.nextInt();
			System.out.println(posi[choice - 1] + " ist wichtiger/besser.");

			score[arrIntCombis[choice - 1]]++;
		}
		sc.close();

		/**
		 * convert and output
		 */
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < score.length; i++) {
			map.put(score[i], list.get(i));
		}
		Map<Integer, String> mapSorted = new TreeMap<Integer, String>(map);
		ArrayList<String> listFinal = new ArrayList<String>(Arrays.asList(mapSorted.values().toArray(new String[0])));
		Collections.reverse(listFinal);
		String[] arrInOrder = listFinal.toArray(new String[0]);
		System.out.println("Fertige Reihenfolge:");
		for (int i = 0; i < arrInOrder.length; i++) {
			System.out.println(arrInOrder[i]);
		}
	}

}
