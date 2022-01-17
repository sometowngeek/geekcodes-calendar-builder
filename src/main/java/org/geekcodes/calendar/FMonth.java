package org.geekcodes.calendar;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * The class FMonth.
 */
public class FMonth {
    /**
     * The Local date.
     */
    private LocalDate localDate = null;
    /**
     * The Month.
     */
    private Month month = null;
    /**
     * The Year.
     */
    private int year = 0;

    /**
     * Instantiates a new F month.
     */
    private FMonth(){
        // Empty constructor
    }

    /**
     * Get instance f month.
     *
     * @return the f month
     */
    public static FMonth getInstance(){
        return new FMonth();
    }

    /**
     * With month f month.
     *
     * @param month the month
     * @return the f month
     */
    public FMonth withMonth(Month month){
        this.month = month;
        return this;
    }

    /**
     * With year f month.
     *
     * @param year the year
     * @return the f month
     */
    public FMonth withYear(int year){
        this.year = year;
        return this;
    }

    /**
     * Get local date local date.
     *
     * @return the local date
     */
    public LocalDate getLocalDate(){
        List<String> errors = new ArrayList<>();

        if (this.year == 0){
            errors.add("Must call withYear() with a valid year.");
        }

        if (this.month == null){
            errors.add("Must call withMonth() with a valid month.");
        }

        if (errors.size() > 0){
            throw new NullPointerException(String.join(" ", errors));
        }

        return LocalDate.of(this.year, this.month, 1);
    }

}
