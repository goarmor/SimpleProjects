package com.example.android.notepad;

import android.net.Uri;
import android.provider.BaseColumns;


public final class NotePad {
    public static final String AUTHORITY = "com.example.notepad.provider.NotePad";


    private NotePad() {}
    

    public static final class NoteColumns implements BaseColumns {
        private NoteColumns() {}


        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/notes");


        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.google.note";


        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.google.note";


        public static final String DEFAULT_SORT_ORDER = "modified DESC";


        public static final String TITLE = "title";


        public static final String NOTE = "note";


        public static final String CREATED_DATE = "created";


        public static final String MODIFIED_DATE = "modified";
    }
}
