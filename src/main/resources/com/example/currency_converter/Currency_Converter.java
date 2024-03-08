package com.example.currency_converter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Currency_Converter extends Application {

    @Override
    public void start(Stage primaryStage) {
        ObservableList<String> currencies = FXCollections.observableArrayList("USD", "EURO", "YEN");

        // Source currency
        Label fromLabel = new Label("From: ");
        ComboBox<String> fromCurrency = new ComboBox<>(currencies);
        fromCurrency.getSelectionModel().selectFirst(); // Select first currency by default

        // Target currency
        Label toLabel = new Label("To: ");
        ComboBox<String> toCurrency = new ComboBox<>(currencies);
        toCurrency.getSelectionModel().selectFirst(); // Select first currency by default

        // Amount to convert
        Label amountLabel = new Label("Amount:");
        TextField amountTextField = new TextField();

        // Button to trigger conversion
        Button convertButton = new Button("Convert");

        // Result display
        Label resultLabel = new Label("Result:");
        TextField resultTextField = new TextField();
        resultTextField.setEditable(false); // Make result display read-only

        // Set up the action for the convert button
        convertButton.setOnAction(e -> {
            String sourceCurrency = fromCurrency.getValue();
            String targetCurrency = toCurrency.getValue();
            double amount = Double.parseDouble(amountTextField.getText()); // Parse the user input
            double result = output(amount, sourceCurrency, targetCurrency); // Perform conversion
            String currencySymbol = getCurrencySymbol(targetCurrency); // new line
            resultTextField.setText(currencySymbol + String.format("%.2f", result)); // Display the result
        });

        VBox vbox = new VBox(10, fromLabel, fromCurrency, toLabel, toCurrency, amountLabel, amountTextField, convertButton, resultLabel, resultTextField);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 300, 400);
        primaryStage.setTitle("Currency Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double output(Double amount, String currencyInput, String currencyOutput) {
        // Conversion logic
        double newValue;
        if (currencyInput.equals(currencyOutput)) {
            return amount;
        } else if (currencyInput.equals("USD") && currencyOutput.equals("EURO")) {
            newValue = (amount * 0.93);
        } else if (currencyInput.equals("USD") && currencyOutput.equals("YEN")) {
            newValue = (amount * 149.34);
        } else if (currencyInput.equals("EURO") && currencyOutput.equals("USD")) {
            newValue = (amount * 1.08);
        } else if (currencyInput.equals("EURO") && currencyOutput.equals("YEN")) {
            newValue = (amount * 161.08);
        } else if (currencyInput.equals("YEN") && currencyOutput.equals("USD")) {
            newValue = (amount * 0.0067);
        } else if (currencyInput.equals("YEN") && currencyOutput.equals("EURO")) {
            newValue = (amount * 0.0062);
        } else {
            // If the conversion case is not handled, return the original amount
            newValue = amount;
        }
        return newValue;
    }

    private String getCurrencySymbol(String currencyCode) {
        switch (currencyCode)  {
            case "USD":
                return "$";
            case "EURO":
                return "€";
            case "YEN":
                return"¥";
            default:
                return"";


        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}







