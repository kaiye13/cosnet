package cosnet.android.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cosnet.android.CosnetDb;
import cosnet.android.Entities.Cosplay;
import cosnet.android.Entities.Note;
import cosnet.android.R;

public class AddNoteToCosplay extends AppCompatActivity {

  private static final String TAG = "Add Note";

  private CosnetDb db;
  private Cosplay cosplay;
  private Button addNoteButton;
  private TextInputLayout noteNameLayout;
  private TextInputLayout noteDescriptionLayout;
  private String noteType;
  private String date;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_note);
    getWindow().setSoftInputMode(
      WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    addToolbar();
    addDatabase();
    getItemsBound();
    initializeItems();
    setListeners();

    Intent incomingIntent = getIntent();
    cosplay = (Cosplay) incomingIntent.getSerializableExtra("cosplay");
  }

  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void addToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void addDatabase() {
    db = CosnetDb.getInstance(this);
  }

  private void getItemsBound() {
    addNoteButton = (Button) findViewById(R.id.NoteAddButton);
    noteNameLayout = (TextInputLayout) findViewById(R.id.noteNametextInput);
    noteDescriptionLayout = (TextInputLayout) findViewById(R.id.noteDescriptionTextInput);
  }

  private void initializeItems() {
    noteType="cosplay";
    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy  HH:mm", Locale.getDefault());
    date = df.format(c);
  }

  private void setListeners() {
    addNoteButton.setOnClickListener(v -> onClickAddButton());
  }

  private boolean validateCosplayNoteDescrition() {
    String description = noteDescriptionLayout.getEditText().getText().toString();
    if (description.length() > 650) {
      noteDescriptionLayout.setError(getApplicationContext().getString(R.string.max650Characters));
      return false;
    } else {
      noteDescriptionLayout.setError(null);
      return true;
    }
  }

  private boolean validateCosplayNoteName() {
    String cosplayNoteName = noteNameLayout.getEditText().getText().toString();
    if (cosplayNoteName.length() > 150) {
      noteNameLayout.setError(getApplicationContext().getString(R.string.max150Characters));
      return false;
    } else if(cosplayNoteName.isEmpty()){
      noteNameLayout.setError(getApplicationContext().getString(R.string.requiredFieldErrorEmpty));
      return false;
    }
    else {
      noteNameLayout.setError(null);
      return true;
    }
  }

  private void onClickAddButton() {
    if (!validateCosplayNoteName() | !validateCosplayNoteDescrition()) {
      return;
    }
    Note newNote = new Note();
    newNote.cosplayId=cosplay.cosplayId;
    newNote.title = noteNameLayout.getEditText().getText().toString();
    newNote.description= noteDescriptionLayout.getEditText().getText().toString();
    newNote.type=noteType;
    newNote.createdDate=date;

    db.getNoteDAO().insertItem(newNote);

    Intent intent = new Intent();
    intent.putExtra("addedNote",newNote.title);
    setResult(RESULT_OK,intent);
    finish();
  }
}
