package com.example.currency_converter;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.util.*;
import javafx.util.converter.DoubleStringConverter;


public class Currency_Converter extends Application{

    private final static String imgStrUsd = "src/main/java/com.example.currency.converter/usd.png";
    private final static String imgStrEuro = "src/main/java/com.example.currency.converter/euro.png";
    private final static String imgStrYen = "src/main/java/com.example.currency.converter/yen.png";

    //Object Property

    @Override
    public void start(Stage primaryStage) throws Exception{
/*
        Image imgUsd = new Image(imgStrUsd);

        Image imgEuro = new Image(imgStrEuro);

        Image imgYen = new Image(imgStrYen);

        ImageView fromImg = new ImageView();

        ImageView toImg = new ImageView();
*/

        String[] fromCurrencyValue = new String[1];
        String[] toCurrencyValue = new String [1];
        Button calculate = new Button ("Calculate");

        ObservableList<String> currencies =
                FXCollections.observableArrayList("USD", "EURO", "YEN");

        Label fromLabel = new Label("From: ");
        TextField fCurrency = new TextField();
        ComboBox<String> fromCurrency = new ComboBox<>(currencies);

        Label toLabel = new Label("To: ");
        TextField tCurrency = new TextField();
        tCurrency.setEditable(false);
        ComboBox<String> toCurrency = new ComboBox<>(currencies);

        fromCurrency.setOnAction(e -> {
            fromCurrencyValue[0] = fromCurrency.getValue();
            //fromImg.setImage(new Image(fromCurrencyValue));

        });

        toCurrency.setOnAction(e -> {
            toCurrencyValue[0] = toCurrency.getValue();
            //toImg.setImage(new Image(toCurrencyValue));
        });

        calculate.setOnAction(e -> {
            String strFCurrency = fCurrency.getText();
            Double dblFCurrency = null;
            try {
                if(!strFCurrency.isEmpty()){
                    dblFCurrency = Double.parseDouble(strFCurrency);
                } else {
                    System.err.println("Invalid input: " + strFCurrency);
                }
            } catch (NumberFormatException ex){
                System.err.println("Error parsing input: " + ex.getMessage());
            }
            //Double.parseDouble(strFCurrency);
            Double tOutput = output(dblFCurrency, fromCurrencyValue[0], toCurrencyValue[0]);
            tCurrency.setText(String.format("%.2f", tOutput));
        });



        VBox vbox = new VBox(fromLabel, fromCurrency, fCurrency, toLabel, toCurrency, tCurrency, calculate);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox);


        primaryStage.setTitle("Currency Calculator");
        primaryStage.setScene(scene);
        primaryStage.setWidth(568);
        primaryStage.setHeight(320);
        primaryStage.show();
    }

    private Double output(Double fromCurrencyValue, String currencyInput, String currencyOutput) {
        Double newValue;
        if (currencyInput.equals(currencyOutput)){
            return fromCurrencyValue;
        }
        else if(currencyInput.equals("USD") && currencyOutput.equals("EURO")){
            newValue = (fromCurrencyValue * .93);
            return newValue;
        }
        else if(currencyInput.equals("USD") && currencyOutput.equals("YEN")){
            newValue = (fromCurrencyValue * 149.34);
            return newValue;
        }
        else if(currencyInput.equals("EURO") && currencyOutput.equals("USD")){
            newValue = (fromCurrencyValue * 1.08);
            return newValue;
        }
        else if(currencyInput.equals("EURO") && currencyOutput.equals("YEN")){
            newValue = (fromCurrencyValue * 161.08);
            return newValue;
        }
        else if(currencyInput.equals("YEN") && currencyOutput.equals("USD")){
            newValue = (fromCurrencyValue * .0067);
            return newValue;
        }
        else if(currencyInput.equals("YEN") && currencyOutput.equals("EURO")){
            newValue = (fromCurrencyValue * .0062);
            return newValue;
        }
        return fromCurrencyValue;
    }

    private TextField createFCurrencyField() {
        TextField fTextField = new TextField();

        StringConverter<Double> converter = new DoubleStringConverter();
        DoubleProperty doubleValue = new SimpleDoubleProperty();
        TextFormatter<Double> formatter = new TextFormatter<>(converter, doubleValue.getValue());
        fTextField.setTextFormatter(formatter);

        doubleValue.bind(Bindings.createDoubleBinding(() -> {
            try {
                return Double.parseDouble(fTextField.getText());
            } catch (NumberFormatException e){
                return 0.0;
            }
        }, fTextField.textProperty()));
        return fTextField;
    }

    private void ImageDisplay(ImageView imgView, String currency){
        String imgPath = "";
        switch(currency){
            case "USD":
                imgPath = imgStrUsd;
                break;
            case "EURO":
                imgPath = imgStrEuro;
                break;
            case "YEN":
                imgPath = imgStrYen;
                break;
            default:
                imgPath = imgStrUsd;
                break;

        }
    }





    public static void main(String[] args){launch(args);}

}

/*
USD -> EURO = .93
USD -> YEN = 149.34
EURO -> USD = 1.08
EURO -> YEN = 161.08
YEN -> USD = .0067
YEN -> EURO = .0062
 */