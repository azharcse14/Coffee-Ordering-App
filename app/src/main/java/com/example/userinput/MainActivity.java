package com.example.userinput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 1; // global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

//    public void increment(View view) {
////        int quantity = 2; //local variable
//        //quantity = 3;
//        quantity = quantity + 1;
//        display(quantity);
//    }
//
//    public void decrement(View view) {
////        int quantity = 2;
//        //quantity = 1;
//        quantity = quantity - 1;
//        display(quantity);
//    }

    public void submitOrderEmail(View view){
        EditText nameField = findViewById(R.id.nameEt);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();
        String addresses = "mdazharcse14@gmail.com";
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCb);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolateCb);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        // Calculate the price
        int price = calculatePrice(hasWhippedCream, hasChocolate);

        // Display the order summary on the screen
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee delivery for:"+ name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void submitOrder(View view) {

        EditText nameField = findViewById(R.id.nameEt);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCb);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolateCb);
        boolean hasChocolate = chocolateCheckBox.isChecked();


////        display(1);
////        int number = 18*3+4/(2+2)-1;
////        int quantity = 2;//........................
//          int price = quantity*10;
////        display(quantity);
////        displayPrice(quantity*price);
//
//        String priceMessage = "Total: $"+price;
////        displayMessage(priceMessage+(quantity*price));
//        priceMessage = priceMessage+"\nThank you!";
//        displayMessage(priceMessage);
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(message);

    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream){
            basePrice = basePrice +1;
        }
        if (addChocolate){
            basePrice = basePrice + 2;
        }
        return quantity*basePrice;
    }

//    private int calculatePrice() {
//        int price = quantity * 5;
//        return price;
//    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: "+name;
        priceMessage = priceMessage + "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }


    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantityDisplayTv);
        quantityTextView.setText("" + number);
    }

//    private void displayPrice(int number) {
//        TextView priceTextView = findViewById(R.id.priceDisplayTv);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummeryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummeryTextView.setText(message);
    }
}