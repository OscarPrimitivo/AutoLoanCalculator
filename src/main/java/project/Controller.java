package project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
/**
 * Name: Oscar Primitivo
 * Username: primod01
 */

public class Controller {

	@FXML
	private TextField basePriceT;

	@FXML
	private Button calculate;

	@FXML
	private TextField downPaymentT;

	@FXML
	private RadioButton fortyEightRb;

	@FXML
	private TextField monthlyPaymentT;

	@FXML
	private CheckBox rearCameraCheck;

	@FXML
	private Button reset;

	@FXML
	private TextField salesTaxT;

	@FXML
	private RadioButton sixtyRb;

	@FXML
	private CheckBox sunRoofCheck;

	@FXML
	private RadioButton thirtySixRb;

	@FXML
	private TextField totalLoanAmountT;

	@FXML
	private TextField totalPaymentT;

	@FXML
	private CheckBox touchScreenCheck;

	@FXML
	private RadioButton twentyFourRb;
	
	 
	

	 public void reset(ActionEvent event) {
		
		 // payment Information back to default values (all values of 0.0 numerically)
		double totalLoanAmount = 0;
		double monthlyPayment = 0;
		double totalPayment = 0;
		
		//Text boxes back to default values of 0.0 visually
		 totalLoanAmountT.setText(String.format("%.1f", totalLoanAmount));
		 monthlyPaymentT.setText(String.format("%.1f", monthlyPayment));
		 totalPaymentT.setText(String.format("%.1f", totalPayment));
		 
		 //Financing Information section set to default values
		double basePrice = 0;
		double downPayment = 0;
		double salesTax = 7; 
		
		//Text boxes set back to default values 0.0 for base Price and down payment text box and 7 for sales tax textBox
		 basePriceT.setText(String.format("%.1f", basePrice));
		 downPaymentT.setText(String.format("%.1f", downPayment));
		 salesTaxT.setText(String.format("%.1f", salesTax));
		 
		 // Loan Term section set to default values (24 Month button set to true)
		twentyFourRb.setSelected(true);
		
		// Extra Options section set to default values sunRoof and touchScreen check boxes are not selected ( rear camera option auto checked)
		rearCameraCheck.setSelected(true);
		sunRoofCheck.setSelected(false);
		touchScreenCheck.setSelected(false);
		
	 }
	 
	 //calls methods that are used to give the final calculations
	 public void calculate(ActionEvent event) {
		
		totalLoan();
		monthlyPayments();
		 
	 }
	 
	//Gets called when calculate button is clicked, contains code that calculates the total Loan Amount text Box
	 public void totalLoan() {

		 double optionCost = 0;
		 
		 double downPayment = Double.parseDouble(downPaymentT.getText());
		 
		 double taxRate = Double.parseDouble(salesTaxT.getText());
		
		 double basePrice = Double.parseDouble(basePriceT.getText());
		 
		 double subTotal = basePrice + optionCosts(optionCost);
		 
		 double tax = subTotal * taxRate/100;
		 System.out.println(tax);
	
		double totalLoan = subTotal + tax - downPayment; 
		System.out.println(totalLoan);
		 
		totalLoanAmountT.setText(String.format("%.2f", totalLoan));
		
		
	 }
	 //Gets called when calculate button is clicked, contains code that calculates the monthly payment text box
	 public void monthlyPayments() {
		 double annualIntRate = 0;
		 double months = 0;
		 double basePrice = Double.parseDouble(basePriceT.getText());
		 double optionCost = 0;
		 double downPayment = Double.parseDouble(downPaymentT.getText());
		 double totalPayment = 0;
		 double taxRate = Double.parseDouble(salesTaxT.getText());
		 double totalLoan = 0;
		 double subTotal = basePrice + optionCosts(optionCost);
		 double tax = subTotal * taxRate/100;
		 
		 totalLoan = subTotal + tax - downPayment;
		 
		 double monthlyInterest = monthlyInterest(annualIntRate)/12;
		 
		 double monthScale = Math.pow((1 + monthlyInterest), months(months));
		 
		 double monthlyPayment = totalLoan * ((monthlyInterest * monthScale)/(monthScale - 1));
		 
		 monthlyPaymentT.setText(String.format("%.2f", monthlyPayment));
		 
		 totalPayment = monthlyPayment * months(months);
		 
		 totalPaymentT.setText(String.format("%.2f", totalPayment));
		 
	
			 
	 }
	 
	
	 // This method contains all the possible combinations for the extra options sections and gives a finalized price for each one 
	 public double optionCosts(double optionCost) { 
		 
		if(sunRoofCheck.isSelected() && !touchScreenCheck.isSelected() && !rearCameraCheck.isSelected()) {
			 optionCost = 1510;
		}
		else if(sunRoofCheck.isSelected() && touchScreenCheck.isSelected() && !rearCameraCheck.isSelected()){
			optionCost = 1510 + 470;
		}
		else if(sunRoofCheck.isSelected() && touchScreenCheck.isSelected() && rearCameraCheck.isSelected()) {
			optionCost = 1510 + 470 + 340;
		}
		else if(!sunRoofCheck.isSelected() && touchScreenCheck.isSelected() && !rearCameraCheck.isSelected()) {
			optionCost = 470;
		}
		else if(!sunRoofCheck.isSelected() && touchScreenCheck.isSelected() && rearCameraCheck.isSelected()) {
			optionCost = 470 + 340;
		}
		else if(sunRoofCheck.isSelected() && !touchScreenCheck.isSelected() && rearCameraCheck.isSelected()) {
			optionCost = 1510 + 340;
		}
		else if(!sunRoofCheck.isSelected() && !touchScreenCheck.isSelected() && rearCameraCheck.isSelected()) {
			optionCost = 340;
		}
		else if(!sunRoofCheck.isSelected() && !touchScreenCheck.isSelected() && !rearCameraCheck.isSelected()) {
			optionCost = 0;
		}
	
		 return  optionCost;
		
	    }
	 
	 // This method checks to see what radio button is selected and gives the corresponding annual interest Rate with it 
	 public double monthlyInterest(double annualIntRate) {
		 
		 if(twentyFourRb.isSelected()) {
			 annualIntRate = 0.07;
			 
		 }
		 else if(thirtySixRb.isSelected()) {
			 annualIntRate = 0.06;
			 
		 }
		 else if(fortyEightRb.isSelected()) {
			 annualIntRate = 0.055;
			 
		 }
		 else if(sixtyRb.isSelected()) {
			 annualIntRate = 0.05;
			 
		 }
		 
		 return annualIntRate;
		 
		 		
		 
	 }
	
	 // This method checks to see what radio button is selected and gives the corresponding month as as double value 
	 public double months(double months) {
		 
		 if(twentyFourRb.isSelected()) {
			 months = 24;
			 
		 }
		 else if(thirtySixRb.isSelected()) {
			 months = 36;
			 
		 }
		 else if(fortyEightRb.isSelected()) {
			 months = 48;
			 
		 }
		 else if(sixtyRb.isSelected()) {
			 months = 60;
			 
		 }
		 
		 return months;
		 
		 		
		 
	 }
 	
 	
	 	   
	 
}

	




 




