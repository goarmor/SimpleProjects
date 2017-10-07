package com.example.android.notepad;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.notepad.NotePad.NoteColumns;


public class NoteEditor extends Activity {
    private static final String TAG = "NoteEditor";


    private static final String[] PROJECTION = new String[] {
        NoteColumns._ID,
        NoteColumns.NOTE,
        NoteColumns.TITLE,
    };

    private static final int COLUMN_INDEX_NOTE = 1;

    private static final int COLUMN_INDEX_TITLE = 2;
    

    private static final String ORIGINAL_CONTENT = "origContent";

    private static final int STATE_EDIT = 0;
    private static final int STATE_INSERT = 1;

    private int mState;
    private Uri mUri;
    private Cursor mCursor;
    private EditText mText;
    private String mOriginalContent;


    public static class LinedEditText extends EditText {
        private Rect mRect;
        private Paint mPaint;


        public LinedEditText(Context context, AttributeSet attrs) {
            super(context, attrs);
            
            mRect = new Rect();
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(0x800000FF);
        }
        
        @Override
        protected void onDraw(Canvas canvas) {
            int count = getLineCount();
            Rect r = mRect;
            Paint paint = mPaint;

            for (int i = 0; i < count; i++) {
                int baseline = getLineBounds(i, r);

                canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
            }

            super.onDraw(canvas);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = getIntent();


        final String action = intent.getAction();
        if (Intent.ACTION_EDIT.equals(action)) {
            mState = STATE_EDIT;
            mUri = intent.getData();
        } else if (Intent.ACTION_INSERT.equals(action)) {
            mState = STATE_INSERT;
            mUri = getContentResolver().insert(intent.getData(), null);

            if (mUri == null) {
                Log.e(TAG, "Failed to insert new note into " + getIntent().getData());
                finish();
                return;
            }

            setResult(RESULT_OK, (new Intent()).setAction(mUri.toString()));

        } else {
            Log.e(TAG, "Unknown action, exiting");
            finish();
            return;
        }

        setContentView(R.layout.note_editor);

        mText = (EditText) findViewById(R.id.note);

        mCursor = managedQuery(mUri, PROJECTION, null, null, null);

        if (savedInstanceState != null) {
            mOriginalContent = savedInstanceState.getString(ORIGINAL_CONTENT);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCursor != null) {
            mCursor.requery();
            mCursor.moveToFirst();

            if (mState == STATE_EDIT) {
                String title = mCursor.getString(COLUMN_INDEX_TITLE);
                Resources res = getResources();
                String text = String.format(res.getString(R.string.title_edit), title);
                setTitle(text);
            } else if (mState == STATE_INSERT) {
                setTitle(getText(R.string.title_create));
            }


            String note = mCursor.getString(COLUMN_INDEX_NOTE);
            mText.setTextKeepState(note);
            

            if (mOriginalContent == null) {
                mOriginalContent = note;
            }

        } else {
            setTitle(getText(R.string.error_title));
            mText.setText(getText(R.string.error_message));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(ORIGINAL_CONTENT, mOriginalContent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        String text = mText.getText().toString();
        int length = text.length();

        if (isFinishing() && (length == 0) && mCursor != null) {
            setResult(RESULT_CANCELED);
            deleteNote();
        } else {
            saveNote();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editor_options_menu, menu);

        Intent intent = new Intent(null, getIntent().getData());
        intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
        menu.addIntentOptions(Menu.CATEGORY_ALTERNATIVE, 0, 0,
                new ComponentName(this, NoteEditor.class), null, intent, 0, null);

        return super.onCreateOptionsMenu(menu);
    }
    
    

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (mState == STATE_EDIT) {
            menu.setGroupVisible(R.id.menu_group_edit, true);
            menu.setGroupVisible(R.id.menu_group_insert, false);

            String savedNote = mCursor.getString(COLUMN_INDEX_NOTE);
            String currentNote = mText.getText().toString();
            if (savedNote.equals(currentNote)) {
                menu.findItem(R.id.menu_revert).setEnabled(false);
            } else {
                menu.findItem(R.id.menu_revert).setEnabled(true);
            }
        } else {
            menu.setGroupVisible(R.id.menu_group_edit, false);
            menu.setGroupVisible(R.id.menu_group_insert, true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_save:
            saveNote();
            finish();
            break;
        case R.id.menu_delete:
            deleteNote();
            finish();
            break;
        case R.id.menu_revert:
        case R.id.menu_discard:
            cancelNote();
            break;
        }
        return super.onOptionsItemSelected(item);
        
    }
    
    private final void saveNote() {
        if (mCursor != null) {
            ContentValues values = new ContentValues();

            values.put(NoteColumns.MODIFIED_DATE, System.currentTimeMillis());

            String text = mText.getText().toString();
            int length = text.length();
            if (mState == STATE_INSERT) {
                if (length == 0) {
                    Toast.makeText(this, R.string.nothing_to_save, Toast.LENGTH_SHORT).show();
                    return;
                }
                String title = text.substring(0, Math.min(30, length));
                if (length > 30) {
                    int lastSpace = title.lastIndexOf(' ');
                    if (lastSpace > 0) {
                        title = title.substring(0, lastSpace);
                    }
                }
                values.put(NoteColumns.TITLE, title);
            }


            values.put(NoteColumns.NOTE, text);

            try {
                getContentResolver().update(mUri, values, null, null);
            } catch (NullPointerException e) {
                Log.e(TAG, e.getMessage());
            }
            
        }
    }


    private final void cancelNote() {
        if (mCursor != null) {
            if (mState == STATE_EDIT) {
                mCursor.close();
                mCursor = null;
                ContentValues values = new ContentValues();
                values.put(NoteColumns.NOTE, mOriginalContent);
                getContentResolver().update(mUri, values, null, null);
            } else if (mState == STATE_INSERT) {
                deleteNote();
            }
        }
        setResult(RESULT_CANCELED);
        finish();
    }


    private final void deleteNote() {
        if (mCursor != null) {
            mCursor.close();
            mCursor = null;
            getContentResolver().delete(mUri, null, null);
            mText.setText("");
        }
    }
}
