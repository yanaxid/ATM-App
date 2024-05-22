package com.tujuhsembilan.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import data.constant.TransactionType;
import data.model.ATM;
import data.model.Bank;
import data.model.Customer;
import data.model.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ATMLogic {

	public static Customer login(Bank bank, ATM atm) {
		Customer customer = null;

		int counter = 1;

		do {
			System.out.print(" ACCOUNT : ");
			String acc = String.valueOf(ConsoleUtil.validateInputNumber(" ACCOUNT : ", " Acount tidak ditemukan"));
			System.out.print(" PIN     : ");
			String pin = String.valueOf(ConsoleUtil.validateInputNumber(" PIN     : ", " PIN anda salah, ulangi"));

			String message = "";
			Set<Customer> customers = bank.getCustomers();

			for (Customer c : customers) {

				if (acc.equals(c.getAccount())) {

					if (pin.equals(c.getPin())) {
						message = " login ... as " + c.getFullName();
						customer = c;
						break;
					} else {
						if (counter == 3) {
							message = " PIN salah, kesempatan habis";

						} else {
							message = " PIN salah, kesempatan " + (3 - counter) + "x lagi";
							c.setInvalidTries(c.getInvalidTries() + 1);
						}
						break;
					}
				} else {
					if (counter == 3) {
						message = " ACCOUNT salah, kesempatan habis";
					} else {
						message = " ACCOUNT salah, coba lagi";
					}
				}

			}

			System.out.println(message);
			if (customer != null) {
				break;
			}
			counter++;
		} while (counter <= 3);

		return customer;

	}

	public static void accountBalanceInformation(Customer customer) {

		System.out.print(" Informasi Saldo\n");

		System.out.println(" Balance .. : Rp. " + String.format("%,.0f", customer.getBalance()));
		System.out.print(" Terbilang :");
		System.out.print(ConsoleUtil.terbilang(customer.getBalance()) + " Rupiah");
		System.out.println();

	}

	public static void moneyWithdrawal(Customer customer, ATM atm, List<Transaction> transactions) {
		System.out.print(" Tarik Tunai\n");

		System.out.print(" Masukan jumlah penarikan : ");
		double x = Double.valueOf(ConsoleUtil.validateInputNumber(" Masukan jumlah penarikan : ", ""));

		boolean status = false;

		if (x % 10_000 == 0) {
			status = true;
		} else {
			System.out.println(" Harus kelipatan Rp. 10.000 ");
			System.out.print(" Terbilang :");
			System.out.print(ConsoleUtil.terbilang(Double.valueOf(10_000)) + " Rupiah");
			System.out.println();

		}

		if (status) {

			if (atm.getBalance() >= x) {

				if (x <= atm.getBank().getMaxExpensePerWithdrawal()) {

					double count = customer.getBalance() - x;

					if (count >= 10_000) {
						atm.setBalance(atm.getBalance() - x);
						customer.setBalance(customer.getBalance() - x);
						System.out.println(" Berhasil tarik sebesar Rp. " + String.format("%,.0f", (double) x));

						System.out.print(" Terbilang :");
						System.out.print(ConsoleUtil.terbilang(Double.valueOf(x)) + " Rupiah");
						System.out.println();

						System.out.println(" Sisa saldo anda Rp. " + String.format("%,.0f", (double) customer.getBalance()));

						System.out.print(" Terbilang :");
						System.out.print(ConsoleUtil.terbilang(Double.valueOf(customer.getBalance())) + " Rupiah");
						System.out.println();

						transactions.add(new Transaction(UUID.randomUUID().toString(), String.valueOf(new java.util.Date()), customer,
								TransactionType.WITHDRAWAL, x));
					} else {
						System.out.println(" saldo tidak cukup");
					}

				} else {
					System.out.println(" maximum penarikan 2.500.000");
				}

			} else {
				System.out.println(" ATM balance tidak cukup");
			}
		}

	}

	public static void phoneCreditsTopUp(Customer customer, ATM atm, List<Transaction> transactions) {
		System.out.print(" Isi lang pulsa\n");

		boolean isNoTelp = false;

		do {
			System.out.print(" Masukan Nomor Telpon : ");
			String noTelp = ConsoleUtil.validateInputNoTelp(" Masukan Nomor Telpon : ", "");

			if (noTelp.length() >= 3 && noTelp.length() <= 15) {
				isNoTelp = true;
			} else {
				System.out.println(" No telp tidak valid");
			}

		} while (!isNoTelp);

		if (isNoTelp) {

			int nominalPulsa = 0;
			boolean isNumber = false;
			do {
				String[] listMenu = { "10.000", "20.000", "50.000", "100.000" };
				ConsoleUtil.printMenuSimple(listMenu, "Pilihan pulsa");
				System.out.print(" > ");
				int x = ConsoleUtil.validateInputNumber("> ", "");

				switch (x) {
					case 1:
						nominalPulsa = 10_000;
						isNumber = true;
						break;
					case 2:
						nominalPulsa = 20_000;
						isNumber = true;
						break;
					case 3:
						nominalPulsa = 50_000;
						isNumber = true;
						break;
					case 4:
						nominalPulsa = 100_000;
						isNumber = true;
						break;
					default:
						System.out.println(" pilihan anda tidak ada dalam daftar");
						break;
				}

			} while (!isNumber);
			


			if (atm.getBalance() >= nominalPulsa) {

				double count = customer.getBalance() - nominalPulsa;
				if (count >= 10_000) {
					atm.setBalance(atm.getBalance() - nominalPulsa);
					customer.setBalance(customer.getBalance() - nominalPulsa);
					System.out.println(" Berhasil membeli pulsa sebesar Rp. " + String.format("%,.0f", (double) nominalPulsa));
					System.out.print(" Terbilang :");
					System.out.print(ConsoleUtil.terbilang(Double.valueOf(nominalPulsa)) + " Rupiah");
					System.out.println();

					System.out.println(" Sisa saldo anda Rp. " + String.format("%,.0f", (double) customer.getBalance()));

					System.out.print(" Terbilang :");
					System.out.print(ConsoleUtil.terbilang(Double.valueOf(customer.getBalance())) + " Rupiah");
					System.out.println();

					transactions.add(
							new Transaction(UUID.randomUUID().toString(), String.valueOf(new java.util.Date()), customer, TransactionType.TOP_UP, nominalPulsa));

				} else {
					System.out.println(" saldo tidak cukup");
				}

			} else {
				System.out.println(" ATM balance tidak cukup");
			}

		}

	}

	public static void electricityBillsToken(Customer customer, ATM atm, List<Transaction> transactions) {

		System.out.print(" Isi token listrik\n");
		
		int nominalToken = 0;
		boolean isNumber = false;
		do {
			String[] listMenu = { "50.000", "100.000", "200.000", "500.000" };
			ConsoleUtil.printMenuSimple(listMenu, "Pilihan Listrik");
			System.out.print(" > ");
			int x = ConsoleUtil.validateInputNumber("> ", "");

			switch (x) {
				case 1:
					nominalToken = 50_000;
					isNumber = true;
					break;
				case 2:
					nominalToken = 100_000;
					isNumber = true;
					break;
				case 3:
					nominalToken = 200_000;
					isNumber = true;
					break;
				case 4:
					nominalToken = 500_000;
					isNumber = true;
					break;
				default:
					System.out.println(" pilihan anda tidak ada dalam daftar");
					break;
			}

		} while (!isNumber);
		
		

	

		if (atm.getBalance() >= nominalToken) {

			double count = customer.getBalance() - nominalToken;
			if (count >= 10_000) {
				atm.setBalance(atm.getBalance() - nominalToken);
				customer.setBalance(customer.getBalance() - nominalToken);
				System.out.println(" Berhasil membeli token sebesar Rp. " + String.format("%,.0f", (double) nominalToken));
				System.out.print(" Terbilang :");
				System.out.print(ConsoleUtil.terbilang(Double.valueOf(nominalToken)) + " Rupiah");
				System.out.println();
				System.out.println(" Sisa saldo anda Rp. " + String.format("%,.0f", (double) customer.getBalance()));
				System.out.print(" Terbilang :");
				System.out.print(ConsoleUtil.terbilang(Double.valueOf(customer.getBalance())) + " Rupiah");
				System.out.println();

				transactions.add(
						new Transaction(UUID.randomUUID().toString(), String.valueOf(new java.util.Date()), customer, TransactionType.TOP_UP, nominalToken));

			} else {
				System.out.println(" saldo tidak cukup");
			}

		} else {
			System.out.println(" ATM balance tidak cukup");
		}
	}

	public static void accountMutation(Customer customer, List<Transaction> transactions) {

		if (transactions.size() == 0) {
			System.out.print("   +-------------------+\n");
			System.out.print("   | Data masih kosong |\n");
			System.out.print("   +-------------------+\n");
		} else {

			String message = " Data masih kosong";

			for (Transaction t : transactions) {
				if (customer.getId().equals(t.getCustomer().getId())) {

					List<Transaction> newTransactions = transactions.stream().filter(x -> x.getCustomer().getId().equals(customer.getId()))
							.collect(Collectors.toList());

					ConsoleUtil.createTableTransaction(newTransactions);

					message = "";
					break;
				}

			}

			System.out.println(message);
		}

	}

	public static void moneyDeposit(Customer customer, List<Transaction> transactions) {
		System.out.print(" Deposit\n");

		System.out.print(" Masukan jumlah deposit : ");
		double x = Double.valueOf(ConsoleUtil.validateInputNumber(" Masukan jumlah deposit : ", ""));

		boolean status = false;

		if (x % 10_000 == 0) {
			status = true;
		} else {
			System.out.println(" Harus kelipatan Rp. 10.000");
			System.out.print(" Terbilang :");
			System.out.print(ConsoleUtil.terbilang(Double.valueOf(10_000)) + " Rupiah");
			System.out.println();

		}

		if (status) {

			customer.setBalance(customer.getBalance() + x);
			System.out.println(" Berhasil menambah deposit sebesar Rp. " + String.format("%,.0f", (double) x));
			System.out.print(" Terbilang :");
			System.out.print(ConsoleUtil.terbilang(Double.valueOf(x)) + " Rupiah");
			System.out.println();

			System.out.println(" Saldo anda sekarang Rp. " + String.format("%,.0f", (double) customer.getBalance()));
			System.out.print(" Terbilang :");
			System.out.print(ConsoleUtil.terbilang(Double.valueOf(customer.getBalance())) + " Rupiah");
			System.out.println();

			transactions
					.add(new Transaction(UUID.randomUUID().toString(), String.valueOf(new java.util.Date()), customer, TransactionType.TOP_UP, x));
		}

	}

	// Logout

	public static List<Boolean> logOut(Customer customer) {
		List<Boolean> isBool = new ArrayList<Boolean>();

		boolean isLoop = true;
		do {

			System.out.print(" Anda yakin mau logout? (Y/T) : ");
			String x = ConsoleUtil.sc.next();
			if (x.equalsIgnoreCase("y")) {
				isLoop = false;
				System.out.println(" " + customer.getFullName() + " ... Logout");
				isBool.addAll(Arrays.asList(false, false));
			} else if (x.equalsIgnoreCase("t")) {
				isBool.addAll(Arrays.asList(true, false));
				isLoop = false;
			} else {
				System.out.println(" Inputkan hurup y/t");
			}

		} while (isLoop);

		return isBool;

	}

	public static boolean mainMenu(Customer customer, ATM atm, List<Transaction> transactions) {

		boolean status = true;

		String[] listMenu = { "Account Information", "Money Withdrawal", "Phone Credits TopUp", "Electricity Bills Token", "accountMutation",
				"moneyDeposit", "Logout" };
		boolean isLooping = true;

		do {

			ConsoleUtil.printMenu(listMenu, "MENU ATM");
			int x = ConsoleUtil.validateInputNumber("> ", "");
			switch (x) {
				case 1:
					accountBalanceInformation(customer);
					break;
				case 2:
					moneyWithdrawal(customer, atm, transactions);
					break;
				case 3:
					phoneCreditsTopUp(customer, atm, transactions);
					break;
				case 4:
					electricityBillsToken(customer, atm, transactions);
					break;
				case 5:
					accountMutation(customer, transactions);
					break;
				case 6:
					moneyDeposit(customer, transactions);
					break;
				case 0:
					List<Boolean> isBool = logOut(customer);
					isLooping = isBool.get(0);
					status = isBool.get(1);
					break;
				default:
					System.out.println("Pilihan tidak ada dalam daftar menu, ulangi lagi");
					break;
			}
		} while (isLooping);

		return status;

	}

}
