package com.example.erestaurant;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import static com.example.erestaurant.booking.SetDateText;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //setting 7days date picker range for the booking
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),this,year,month,day);
                //add(int field, int value)
                //Adds the given amount to a Calendar field.
        // Add 6 days to Calendar
        calendar.add(Calendar.DATE, 6);

            /*
                getTimeInMillis()
                    Returns the time represented by this Calendar,
                    recomputing the time from its fields if necessary.

                getDatePicker()
                Gets the DatePicker contained in this dialog.

                setMinDate(long minDate)
                    Sets the minimal date supported by this NumberPicker
                    in milliseconds since January 1, 1970 00:00:00 in getDefault() time zone.

                setMaxDate(long maxDate)
                    Sets the maximal date supported by this DatePicker in milliseconds
                    since January 1, 1970 00:00:00 in getDefault() time zone.
             */

        // Set the Calendar new date as maximum date of date picker
        dpd.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        // Subtract 6 days from Calendar updated date
        calendar.add(Calendar.DATE, -6);

        // Set the Calendar new date as minimum date of date picker
        dpd.getDatePicker().setMinDate(calendar.getTimeInMillis());

        // So, now date picker selectable date range is 7 days only

        // Return the DatePickerDialog
        return  dpd;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        SetDateText(year,month + 1, day);
    }

}