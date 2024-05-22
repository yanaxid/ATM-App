package com.tujuhsembilan.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import data.model.Bank;
import data.model.Customer;
import data.model.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ConsoleUtil {

	public static final Scanner sc = new Scanner(System.in);

	public static void printClear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void printDivider(Character character) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 35; i++) {
			sb.append(character);
		}
		System.out.println(sb.toString());
	}

	public static void printDivider() {
		printDivider('=');
	}

	public static int validateInputNumber(String sign, String message) {
		boolean isValid = true;

		String numberInput = "";

		while (isValid) {
			numberInput = sc.next();
			if (numberInput.matches("[0-9]+")) {
				isValid = false;
			} else {
				if (message.equalsIgnoreCase("")) {
					message += " Inputan harus angka!";
				}
				System.out.println(message);
				System.out.print(" " + sign);
			}
		}
		return Integer.valueOf(numberInput);
	}

	public static String validateInputNoTelp(String sign, String message) {
		boolean isValid = true;

		String numberInput = "";

		while (isValid) {
			numberInput = sc.next();
			if (numberInput.matches("[0-9]+")) {
				isValid = false;
			} else {
				if (message.equalsIgnoreCase("")) {
					message += " Inputan harus angka!";
				}
				System.out.println(message);
				System.out.print(" " + sign);
			}
		}
		return numberInput;
	}

	public static void printMenu(String[] listMenu, String title) {

		int number = 1;
		System.out.println();
		System.out.println(" " + title);

		for (String data : listMenu) {
			if (number < listMenu.length) {
				System.out.println(" " + number + ". " + data);

			} else {
				System.out.println(" 0. " + data);
				System.out.print(" > ");
			}
			number++;
		}
	}

	public static void printMenuSimple(String[] listMenu, String title) {

		int number = 1;
		System.out.println();
		System.out.println(" " + title);

		for (String data : listMenu) {

			System.out.println(" " + number + ". " + data);

			number++;
		}
	}

	public static void createTableTransaction(List<Transaction> transactions) {
		List<String[]> dataTable = new ArrayList<String[]>();

		//
		String[] heading = { "No", "Timestamp", "Customer Name", "TransaactionType", "Expanse", "Terbilang" };
		int no = 1;
		for (Transaction e : transactions) {
			if (dataTable.size() == 0) {
				dataTable.add(heading);
			}
			String[] body = { String.valueOf(no), e.getTimestamp(), e.getCustomer().getFullName(), String.valueOf(e.getType()),
					"Rp. " + String.format("%,.0f", (double) e.getExpense()), ConsoleUtil.terbilang(e.getExpense()) };
			dataTable.add(body);
			no++;
		}

		//
		int[] listColumnWidht = getColumnWidth(dataTable);
		String lines = createLines(listColumnWidht);

		String columnWidth = "";
		for (int i = 0; i < listColumnWidht.length; i++) {
			columnWidth += "|" + " %-" + listColumnWidht[i] + "s ";
		}
		columnWidth += "|";

		//
		System.out.println("   " + lines);
		for (int i = 0; i < dataTable.size(); i++) {
			if (i == 0) {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4], dataTable.get(i)[5]);
				System.out.println();
				System.out.println("   " + lines);
			} else {
				System.out.printf("   " + columnWidth, dataTable.get(i)[0], dataTable.get(i)[1], dataTable.get(i)[2], dataTable.get(i)[3],
						dataTable.get(i)[4], dataTable.get(i)[5]);
				System.out.println();
			}
		}
		System.out.println("   " + lines);
	}

	public static int[] getColumnWidth(List<String[]> dataTable) {
		int[] listColumnWidht = new int[dataTable.get(0).length];

		for (int x = 0; x < dataTable.get(0).length; x++) {

			int[] columnConten = new int[dataTable.size()];
			for (int i = 0; i < dataTable.size(); i++) {
				columnConten[i] = dataTable.get(i)[x].length();
			}
			int max = 0;
			for (int i = 0; i < columnConten.length; i++) {
				if (max < columnConten[i] || max == 0) {
					max = columnConten[i];
				}
			}
			listColumnWidht[x] = max;
		}
		return listColumnWidht;
	}

	public static String createLines(int[] listColumnWidht) {
		String lines = "+";

		for (int i = 0; i < listColumnWidht.length; i++) {
			String line = "--";
			for (int j = 0; j < listColumnWidht[i]; j++) {
				line += "-";
			}
			lines += line + "+";
		}
		return lines;
	}

	public static String terbilang(Double angka) {

		String[] huruf = { "", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam", "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas" };

		String result = "";
		if (angka < 12)
			return huruf[angka.intValue()];
		if (angka >= 12 && angka <= 19)
			return huruf[angka.intValue() % 10] + " Belas";
		if (angka >= 20 && angka <= 99)
			return terbilang(angka / 10) + " Puluh " + huruf[angka.intValue() % 10];
		if (angka >= 100 && angka <= 199)
			return "Seratus " + terbilang(angka % 100);
		if (angka >= 200 && angka <= 999)
			return terbilang(angka / 100) + " Ratus " + terbilang(angka % 100);
		if (angka >= 1000 && angka <= 1999)
			return "Seribu " + terbilang(angka % 1000);
		if (angka >= 2000 && angka <= 999999)
			return terbilang(angka / 1000) + " Ribu " + terbilang(angka % 1000);
		if (angka >= 1000000 && angka <= 999999999)
			return terbilang(angka / 1000000) + " Juta " + terbilang(angka % 1000000);

		return result;
	}
	
	
	
	
	

}
