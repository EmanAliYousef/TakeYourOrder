package com.example.android.takeyourorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class
MainActivity extends AppCompatActivity {


    int quantity = 0;
    int creamPrice = 1;
    int chocolatePrice = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText editText = (EditText) findViewById(R.id.edt);
        String name = editText.getText().toString();

        CheckBox WhippedCream = (CheckBox) findViewById(R.id.checkBox);
        boolean hasWhippedCream = WhippedCream.isChecked();


        CheckBox WhippedChocolate = (CheckBox) findViewById(R.id.checkBox2);
        boolean hasWhippedChocolate = WhippedChocolate.isChecked();

        int price = calculatePrice();

        String message = createOrderMethod(price, hasWhippedCream, hasWhippedChocolate, name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "This order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }


    private String createOrderMethod(int price, boolean addWhippedCream, boolean addWhippedChocolate, String eName) {

        String message = eName + "\n" + "hasWhippedCream? " + addWhippedCream + "\n" + "hasWhippedChocolate? " + addWhippedChocolate + "\n" + "quantity : " + quantity + "\n" + "Total : $" + price + "\n" + getString(R.string.thank_you);

        return message;

    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {

        int BasePrice = 5;

        CheckBox WhippedCream = (CheckBox) findViewById(R.id.checkBox);
        boolean hasWhippedCream = WhippedCream.isChecked();


        CheckBox WhippedChocolate = (CheckBox) findViewById(R.id.checkBox2);
        boolean hasWhippedChocolate = WhippedChocolate.isChecked();

        if (hasWhippedCream) {

            BasePrice = BasePrice + 1;

        }

        if (hasWhippedChocolate) {
            BasePrice = BasePrice + 2;
        }


        int totalPrice = BasePrice * quantity;

        return totalPrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    public void increment(View view) {


        if (quantity == 100) {


            Toast toast = Toast.makeText(getApplicationContext(), "Un Expected Number of cups!!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        quantity = quantity + 1;
        display(quantity);

    }


    public void deccrement(View view) {


        quantity = quantity - 1;
        if (quantity < 1) {

            quantity = 1;
        }
        display(quantity);


    }


}