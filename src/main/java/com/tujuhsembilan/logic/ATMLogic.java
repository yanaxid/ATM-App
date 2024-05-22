package com.tujuhsembilan.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import data.model.ATM;
import data.model.Bank;
import data.model.Customer;
import data.repository.BankRepo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ATMLogic {

	public static Customer login(Bank bank, ATM atm) {
		Customer customer = null;

		// pesan input
		System.out.print(" ACCOUNT : ");
		String acc = String.valueOf(ConsoleUtil.validateInputNumber(" ACCOUNT : ", " Acount tidak ditemukan"));

		// get all customers
		Set<Customer> customers = getAllCustomer();

		// looping
		String pesan = "";
		int counter = 0;

		for (Customer c : customers) {
			if (acc.equals(c.getAccount())) {
				if (c.getInvalidTries() == 0) {
					boolean isPin = true;

					do {
						System.out.print(" PIN     : ");
						String pin = String.valueOf(ConsoleUtil.validateInputNumber(" PIN     : ", " PIN anda salah, ulangi"));

						if (pin.equals(c.getPin())) {
							System.out.println(" login ... as " + c.getFullName());
							c.setInvalidTries(0);
							customer = c;
							break;
						} else {
							System.out.println(" pin salah");
							counter++;
						}

						if (counter == 3) {
							pesan = " akun terblokir";
							c.setInvalidTries(3);
							isPin = false;
						}
					} while (isPin);

					break;
				} else {
					System.out.println(" akun anda terblokir");
					break;
				}
			} else {
				pesan = " akun tidak ada";
			}
		}

		if (counter == 3) {
			System.out.println(pesan);
		}
		return customer;
	}

	public static void accountBalanceInformation(Customer customer) {

		System.out.print(" Informasi Saldo\n");

		System.out.println(" Balance .. : Rp. " + String.format("%,.0f", customer.getBalance()));
		System.out.print(" Terbilang :");
		System.out.print(ConsoleUtil.terbilang(customer.getBalance()) + " Rupiah");
		System.out.println();

	}

	public static void moneyWithdrawal(Customer customer, ATM atm) {
		System.out.print(" Tarik Tunai\n");

		double nominalPenarikan = 0;

		boolean isNumber = false;
		do {
			String[] listMenu = { "10.000", "20.000", "50.000", "100.000", "Penarikan jumlah lain" };
			ConsoleUtil.printMenuSimple(listMenu, "Pilihan Nominal Penarikan");
			System.out.print(" > ");
			int x = ConsoleUtil.validateInputNumber("> ", "");

			switch (x) {
				case 1:
					nominalPenarikan = 10_000;
					isNumber = true;
					break;
				case 2:
					nominalPenarikan = 20_000;
					isNumber = true;
					break;
				case 3:
					nominalPenarikan = 50_000;
					isNumber = true;
					break;
				case 4:
					nominalPenarikan = 100_000;
					isNumber = true;
					break;
				case 5:
					nominalPenarikan = tarikLainnya();
					isNumber = true;
					break;
				default:
					System.out.println(" pilihan anda tidak ada dalam daftar");
					break;
			}

		} while (!isNumber);

		if (atm.getBalance() >= nominalPenarikan) {

			if (nominalPenarikan <= atm.getBank().getMaxExpensePerWithdrawal()) {

				double count = customer.getBalance() - nominalPenarikan;

				if (count >= 10_000) {
					atm.setBalance(atm.getBalance() - nominalPenarikan);
					customer.setBalance(customer.getBalance() - nominalPenarikan);
					System.out.println(" Berhasil tarik sebesar Rp. " + String.format("%,.0f", (double) nominalPenarikan));

					System.out.print(" Terbilang :");
					System.out.print(ConsoleUtil.terbilang(Double.valueOf(nominalPenarikan)) + " Rupiah");
					System.out.println();

					System.out.println(" Sisa saldo anda Rp. " + String.format("%,.0f", (double) customer.getBalance()));

					System.out.print(" Terbilang :");
					System.out.print(ConsoleUtil.terbilang(Double.valueOf(customer.getBalance())) + " Rupiah");
					System.out.println();

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

	public static double tarikLainnya() {
		boolean status = true;
		double nominalPenarikan;

		do {
			System.out.print(" Masukan jumlah penarikan : ");
			nominalPenarikan = Double.valueOf(ConsoleUtil.validateInputNumber(" Masukan jumlah penarikan : ", ""));

			if (nominalPenarikan % 10_000 == 0) {
				status = false;
			} else {
				System.out.println(" Harus kelipatan Rp. 10.000 ");
				System.out.print(" Terbilang :");
				System.out.print(ConsoleUtil.terbilang(Double.valueOf(10_000)) + " Rupiah");
				System.out.println();

			}
		} while (status);

		return nominalPenarikan;
	}

	public static void phoneCreditsTopUp(Customer customer, ATM atm) {
		System.out.print(" Isi lang pulsa\n");

		boolean isNoTelp = false;
		String noTelp = "";

		do {
			System.out.print(" Masukan Nomor Telpon : ");
			noTelp = ConsoleUtil.validateInputNoTelp(" Masukan Nomor Telpon : ", "");

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
					System.out.println(
							" Berhasil membeli pulsa untuk nomor " + noTelp + " sebesar Rp. " + String.format("%,.0f", (double) nominalPulsa));
					System.out.print(" Terbilang :");
					System.out.print(ConsoleUtil.terbilang(Double.valueOf(nominalPulsa)) + " Rupiah");
					System.out.println();

					System.out.println(" Sisa saldo anda Rp. " + String.format("%,.0f", (double) customer.getBalance()));

					System.out.print(" Terbilang :");
					System.out.print(ConsoleUtil.terbilang(Double.valueOf(customer.getBalance())) + " Rupiah");
					System.out.println();

				} else {
					System.out.println(" saldo tidak cukup");
				}

			} else {
				System.out.println(" ATM balance tidak cukup");
			}

		}

	}

	public static void electricityBillsToken(Customer customer, ATM atm) {

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

			} else {
				System.out.println(" saldo tidak cukup");
			}

		} else {
			System.out.println(" ATM balance tidak cukup");
		}
	}

	public static void accountMutation(Customer customer) {

		Set<Bank> banks = getAllBank();

		Bank bank = null;
		boolean isNumber = false;

		do {

			String[] listMenu = { "BCA", "BRI", "BNI", "MANDIRI" };
			ConsoleUtil.printMenuSimple(listMenu, "Pilihan Bank tujuan");
			System.out.print(" > ");
			int x = ConsoleUtil.validateInputNumber("> ", "");

			switch (x) {
				case 1:
					bank = getBank(banks, "bca");
					isNumber = true;
					break;
				case 2:
					bank = getBank(banks, "bri");
					isNumber = true;
					break;
				case 3:
					bank = getBank(banks, "bni");
					isNumber = true;
					break;
				case 4:
					bank = getBank(banks, "mandiri");
					isNumber = true;
					break;
				default:
					System.out.println(" pilihan anda tidak ada dalam daftar");
					break;
			}

		} while (!isNumber);

		System.out.println();
		Customer cuss = validateInputCustomer(bank);

		if (cuss.getId().equals(customer.getId())) {
			System.out.println(" Anda tidak bia melakukan transfer ke diri sendiri");
		} else {
			System.out.print(" Masukan nominal : ");

			int x = ConsoleUtil.validateInputNumber(" > ", "");

			System.out.println(" Berhasil tranfer ke");
			System.out.println(" Bank Tujuan ...... :" + bank.getName());
			System.out.println(" Rekening Tujuan .. :" + cuss.getAccount());
			System.out.println(" Nominal  ......... :" + x);
			cuss.setBalance(cuss.getBalance() + x);
			customer.setBalance(customer.getBalance() - x);
			;

			System.out.println();
			System.out.println(" Sisa saldo anda Rp. " + String.format("%,.0f", (double) customer.getBalance()));

			System.out.print(" Terbilang :");
			System.out.print(ConsoleUtil.terbilang(Double.valueOf(customer.getBalance())) + " Rupiah");
			System.out.println();
		}

	}

	public static Customer validateInputCustomer(Bank bank) {
		String acc = "";
		Customer customer = null;

		Set<Customer> customers = bank.getCustomers();

		//
		boolean status = true;
		System.out.print(" Masukan No rekening Tujuan : ");
		acc = ConsoleUtil.sc.next();

		for (Customer c : customers) {
			if (acc.equals(c.getAccount())) {
				status = false;
				customer = c;
			}
		}

		while (status) {
			System.out.println(" Rekening Tujuan tidak terdaftar");
			System.out.print(" Masukan No rekening Tujuan : ");
			acc = ConsoleUtil.sc.next();

			for (Customer c : customers) {
				if (acc.equals(c.getAccount())) {
					status = false;
					customer = c;
				}
			}
		}

		return customer;
	}

	public static Bank getBank(Set<Bank> banks, String name) {
		Bank bank = null;
		for (Bank b : banks) {
			if (name.equalsIgnoreCase(b.getName())) {

				bank = b;
			}
		}
		return bank;
	}

	public static void moneyDeposit(Customer customer) {
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

		}

	}

	// Logout

	public static List<Boolean> logOut(Customer customer) {
		List<Boolean> isBool = new ArrayList<Boolean>();

		boolean isLoop = true;
		do {

			System.out.print(" Anda yakin mau logout? (Y/T) : ");
			String x = ConsoleUtil.sc.next();
			if (x.equals("y")) {
				isLoop = false;
				System.out.println(" " + customer.getFullName() + " ... Logout");
				isBool.addAll(Arrays.asList(false, false));
			} else if (x.equals("t")) {
				isBool.addAll(Arrays.asList(true, false));
				isLoop = false;
			} else {
				System.out.println(" Inputkan hurup y/t");
			}

		} while (isLoop);

		return isBool;

	}

	public static boolean mainMenu(Customer customer, ATM atm) {

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
					moneyWithdrawal(customer, atm);
					break;
				case 3:
					phoneCreditsTopUp(customer, atm);
					break;
				case 4:
					electricityBillsToken(customer, atm);
					break;
				case 5:
					accountMutation(customer);
					break;
				case 6:
					moneyDeposit(customer);
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

	public static Set<Customer> getAllCustomer() {
		Set<Customer> customers = new HashSet<Customer>();
		List<Set<Customer>> banks = BankRepo.store.stream().map(x -> x.getCustomers()).collect(Collectors.toList());

		for (Set<Customer> o : banks) {
			customers.addAll(o);
		}
		return customers;
	}

	public static Set<Bank> getAllBank() {
		Set<Bank> banks = new HashSet<Bank>();
		for (Bank o : BankRepo.store) {
			banks.add(o);
		}
		return banks;
	}

}
