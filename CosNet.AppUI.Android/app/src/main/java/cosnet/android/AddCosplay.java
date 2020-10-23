package cosnet.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cosnet.android.Entities.Cosplay;
import me.abhinay.input.CurrencyEditText;

public class AddCosplay extends Activity {

  private static final String TAG = "AddCosplay";

  private CosnetDb db;

  private EditText characterEditText;
  private EditText seriesEditText;
  private EditText startDateEditText;
  private EditText dueDateEditText;
  private CurrencyEditText budgetEditText;
  private Spinner statusSpinner;
  private ImageButton cancelButton;
  private ImageButton addCosplayButton;
  private List<String> statusses;
  private int year;
  private int month;
  private int day;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_cosplay_main);

    db = CosnetDb.getInstance(this);

    characterEditText = (EditText) findViewById(R.id.characterEditText);
    seriesEditText = (EditText) findViewById(R.id.seriesEditText);
    startDateEditText = (EditText) findViewById(R.id.startDateEditText);
    dueDateEditText = (EditText) findViewById(R.id.dueDateEditText);
    budgetEditText = (CurrencyEditText) findViewById(R.id.budgetEditText);
    statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
    cancelButton = (ImageButton) findViewById(R.id.cancelNewCosplayBTN);
    addCosplayButton = (ImageButton) findViewById(R.id.addCosplayBTN);
    Calendar calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);

    budgetEditText.setCurrency("€");
    budgetEditText.setSpacing(true);

    statusses = new ArrayList<>();
    statusses.add("In porgress");
    statusses.add("Planned");

    ArrayAdapter<String> adapterSpinnerStatus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusses);
    statusSpinner.setAdapter(adapterSpinnerStatus);

    cancelButton.setOnClickListener(v -> onClickCancelButton());
    startDateEditText.setOnClickListener(v -> onClickStartDate());
    dueDateEditText.setOnClickListener(v -> onClickdueDate());
    addCosplayButton.setOnClickListener(v -> onClickAddButton());
  }

  private void onClickAddButton() {

    if (characterEditText.getText().toString().isEmpty()) {
      AlertDialog alertDialog = new AlertDialog.Builder(AddCosplay.this).create();
      alertDialog.setTitle("Oh No");
      alertDialog.setMessage("Character name is required to be filled in");
      alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (dialog, which) -> { });
      alertDialog.show();
      return;
    }

    Cosplay newCosplay = new Cosplay();
    newCosplay.cosplayName = characterEditText.getText().toString();
    newCosplay.cosplaySeries = seriesEditText.getText().toString();
    newCosplay.startDate = startDateEditText.getText().toString();
    newCosplay.dueDate = dueDateEditText.getText().toString();
    newCosplay.budget = budgetEditText.getCleanDoubleValue();
    newCosplay.status = statusSpinner.getSelectedItem().toString();

    db.getCosplayDAO().insertCosplay(newCosplay);
    Intent intent = new Intent(AddCosplay.this, MainActivity.class);
    startActivity(intent);
  }

  private void onClickdueDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      dueDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

  private void onClickStartDate() {
    DatePickerDialog datePickerDialog = new DatePickerDialog(AddCosplay.this, (view, year, month, dayOfMonth) -> {
      month = month + 1;
      String date = dayOfMonth + "/" + month + "/" + year;
      startDateEditText.setText(date);
    }, year, month, day);
    datePickerDialog.show();
  }

  private void onClickCancelButton() {
    Intent intent = new Intent(AddCosplay.this, MainActivity.class);
    startActivity(intent);
  }
}