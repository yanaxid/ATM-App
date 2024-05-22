package com.tujuhsembilan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tujuhsembilan.logic.ATMLogic;

import data.constant.BankCompany;
import data.model.ATM;
import data.model.Bank;
import data.model.Customer;
import data.model.Transaction;
import data.repository.ATMRepo;
import data.repository.BankRepo;

import static com.tujuhsembilan.logic.ConsoleUtil.*;

public class App {

	public static void main(String[] args) {
		
		List<Transaction> transactions = new ArrayList<Transaction>();

		boolean loop = true;

		while (loop) {
			printClear();


			int num = 1;
			List<BankCompany> bankCompanies = BankCompany.values();
			System.out.println();
			for (String menu : bankCompanies.stream().map(item -> "ATM " + item.getName()).collect(Collectors.toList())) {
				System.out.println(" " + num + ". " + menu);
				num++;
			}
			System.out.println(" 0. EXIT");
			System.out.print(" > ");

			
			int selection = validateInputNumber("> ", "");
			
			if(selection == 0) {
				selection = -1;
			}else {
				selection = selection-1;
			}

			

			if (selection >= 0 && selection < BankCompany.values().size()) {
				new App(BankCompany.getByOrder(selection).getName()).start(transactions);
			} else if (selection == -1) {
				loop = false;
			} else {
				System.out.println(" Invalid input");
			}
		}
		
		System.out.println(" Terimakaish");
	}

	final Bank bank;
	final ATM atm;

	public App(String bankName) {
		Bank lBank = null;
		ATM lAtm = null;

		Optional<Bank> qBank = BankRepo.findBankByName(bankName);

		if (qBank.isPresent()) {
			Optional<ATM> qAtm = ATMRepo.findATMByBank(qBank.get());
			if (qAtm.isPresent()) {
				lBank = qBank.get();
				lAtm = qAtm.get();
			}
		}

		this.bank = lBank;
		this.atm = lAtm;
	}

	public void start(List<Transaction> transactions) {
		if (bank != null && atm != null) {

			Customer customer =  ATMLogic.login(bank, atm);

			while (customer != null ) {
				
				boolean status = ATMLogic.mainMenu(customer, atm, transactions);
				
				if(!status) {
					break;
				}
			}

		} else {
			System.out.println("Cannot find Bank or ATM");
		}
	}

}
