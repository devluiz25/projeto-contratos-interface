package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PayPalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("*** DADOS DO CONTRATO ***");
		System.out.print("INFORME O NUMERO: ");
		int number = sc.nextInt();
		System.out.print("INFORME A DATA(dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.print("INFORME O VALOR DO CONTRATO: U$ ");
		double totalValue = sc.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("INFORME O NUMERO DE PARCELAS: ");
		int installment = sc.nextInt();
		
		ContractService contractService = new ContractService(new PayPalService());
		
		contractService.processContract(contract, installment);
		
		System.out.println("PARCELAS: ");
		for(Installment installments : contract.getInstallments()) {
			System.out.println(installments);
		}
		
		sc.close();
	}
}
