package sg.edu.rp.c346.id22036196.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //variables
    EditText amount;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    RadioGroup payment;
    ToggleButton split;
    ToggleButton reset;
    TextView totalBill;
    TextView eachPays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Strings
        amount=findViewById(R.id.editTextAmount);
        pax=findViewById(R.id.editTextPax);
        svs=findViewById(R.id.toggleButtonSVS);
        gst=findViewById(R.id.toggleButtonGST);
        discount=findViewById(R.id.editTextNumberDiscount);
        payment=findViewById(R.id.radioGroupMOP);
        split=findViewById(R.id.toggleButtonSplit);
        reset=findViewById(R.id.toggleButtonReset);
        totalBill=findViewById(R.id.textViewTotalBill);
        eachPays=findViewById(R.id.textViewEachPays);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                if (amount.getText().toString().trim().length()!=0 && pax.getText().toString().trim().length()!=0){
                    double origAmt=Double.parseDouble(amount.getText().toString());
                    double newAmt=0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = origAmt;
                    }else if(svs.isChecked() && !gst.isChecked()) {
                        newAmt = origAmt * 1.1;
                    }else if(!svs.isChecked() && gst.isChecked()) {
                        newAmt = origAmt * 1.07;
                    } else{
                        newAmt=origAmt*1.17;
                    }

                    //discount
                    if (discount.getText().toString().trim().length()!=0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    // enahancement(incomplete - to make error msg)
                    String stringResponse = " ";
                    Toast.makeText(MainActivity.this, "Invalid input, please re-enter."+stringResponse, Toast.LENGTH_SHORT).show();

                    //mode of payment
                    String mode=" in cash";
                    if (payment.getCheckedRadioButtonId()==R.id.radioButtonPaynow){
                        mode=" via Paynow to 912345678";
                    }

                    //total Bill
                    totalBill.setText("Total Bill: $"+String.format("%.2f",newAmt));
                    int numPerson=Integer.parseInt(pax.getText().toString());
                    if(numPerson!=1) {
                        eachPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson) + mode);
                    }else{
                        eachPays.setText("Each Pays: $"+newAmt+mode);
                    }
                }
            }
        });
    }
}