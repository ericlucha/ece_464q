package com.example.testapp;

import android.provider.BaseColumns;

public class FeedReaderContract {
	
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class StudyEntry implements BaseColumns {
        public static final String TABLE_NAME = "StudyGroups";
        public static final String COLUMN_NAME_FIELD = "Field";
        public static final String COLUMN_NAME_COURSE = "CourseNumber";
        public static final String COLUMN_NAME_LOCATION = "Location";
        public static final String COLUMN_NAME_NOTES = "Notes";
        public static final String COLUMN_NAME_START_HRS = "StartHours";
        public static final String COLUMN_NAME_START_MIN = "StartMinutes";
        public static final String COLUMN_NAME_END_HRS = "EndHours";
        public static final String COLUMN_NAME_END_MIN = "EndMinutes";
    }
    
    public static abstract class ProfessorEntry implements BaseColumns {
        public static final String TABLE_NAME = "ProfessorCheckIns";
        public static final String COLUMN_NAME_NAME = "ProfessorName";
        public static final String COLUMN_NAME_AVAILABILITY = "Availability";
        public static final String COLUMN_NAME_LOCATION = "Location";
        public static final String COLUMN_NAME_NOTES = "Notes";
        public static final String COLUMN_NAME_START_HRS = "StartHours";
        public static final String COLUMN_NAME_START_MIN = "StartMinutes";
        public static final String COLUMN_NAME_END_HRS = "EndHours";
        public static final String COLUMN_NAME_END_MIN = "EndMinutes";
    }
}
