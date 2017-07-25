package sg.edu.rp.c346.c302_p10_testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitle, tvDate, tvNumDays, tvCompleted;
    private EditText etTitle, etDate, etNumDays;
    private Button btnAdd;
    private CheckBox cb;


    private static final String TAG = "MainActivity";

    // TODO: Task 1 - Declare Firebase variables
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference messagePOJOReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView)findViewById(R.id.textViewTitle);
        tvDate = (TextView)findViewById(R.id.textViewDate);
        tvNumDays = (TextView)findViewById(R.id.textViewNumDays);
        tvCompleted = (TextView)findViewById(R.id.textViewCompleted);
        etTitle = (EditText)findViewById(R.id.editTextTitle);
        etDate = (EditText)findViewById(R.id.editTextDate);
        etNumDays = (EditText)findViewById(R.id.editTextNumDays);
        cb = (CheckBox)findViewById(R.id.checkBoxComplete);
        btnAdd = (Button)findViewById(R.id.buttonAdd);

        firebaseDatabase = FirebaseDatabase.getInstance();
        messagePOJOReference = firebaseDatabase.getReference("toDoItem");

        messagePOJOReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // This method will get fired everytime the "message" value updates in the realtime database. We're getting our data back as a DataSnapshot

                // TODO: Task 4 - Get the latest value from the dataSnapshot and display on the UI using the EditText message
                Message message = dataSnapshot.getValue(Message.class);
                tvTitle.setText("Title: " + message.getTitle());
                tvDate.setText("Date: " + message.getDate());
                tvNumDays.setText("NumOfDays: " + message.getNumDays());
                tvCompleted.setText("Completed: " + message.isComplete());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // This method will be invoked if there is any error
                Log.e(TAG, "Database error occurred", databaseError.toException());
            }
        });


        // TODO: Task 5 - Update UI elements, and then include OnClickListener for Send Message button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean complete;
                if(cb.isChecked() == true){
                    complete = true;
                } else{
                    complete = false;
                }
                // Note: We're not directly updating the text view, but calling setValue() to update the data in the database instead
                Message message = new Message(etTitle.getText().toString(), etDate.getText().toString(),Integer.parseInt(etNumDays.getText().toString()),complete );
                messagePOJOReference.setValue(message);
            }
        });
    }
}
