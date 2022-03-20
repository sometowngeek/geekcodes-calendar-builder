package org.geekcodes.calendar;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The class FMonth.
 */
public class FMonth {
    /** The Local date. */
    private int day;

    /** The Month. */
    private Month month;

    /** The Year. */
    private int year;

    /**
     * Instantiates a new FMonth.
     */
    private FMonth(){
        // Empty constructor
    }

    public FMonth(FMonthBuilder fMonthBuilder) {
        this.month = Month.of(fMonthBuilder.month);
        this.year = fMonthBuilder.year;
        this.day = fMonthBuilder.day;
    }

    public static FMonthBuilder builder(){
        return new FMonthBuilder();
    }

    /**
     * The class F month builder.
     */
    private static class FMonthBuilder {
        private Integer year = null;
        private Integer month = null;
        private Integer day = null;

        public FMonthBuilder() {}

        public FMonthBuilder withYear(int year){
            this.year = year;
            return this;
        }

        public FMonthBuilder withMonth(int month){
            this.month = month;
            return this;
        }

        public FMonthBuilder withDay(int day){
            this.day = day;
            return this;
        }

        public FMonth build(){
            this.validate();
            return new FMonth(this);
        }

        /**
         * Validate.
         */
        private void validate() {
            List<String> messages = new ArrayList<>();

            validateYear(messages);
            validateMonth(messages);
            validateDay(messages);

            if (!messages.isEmpty()){
                throw new IllegalStateException(messages.toString());
            }
        }

        /**
         * Validate year.
         *
         * @param messages the messages
         */
        private void validateYear(List<String> messages) {
            if (this.year == null) {
                messages.add("Year must not be null.");
            }

            if (this.year != null && this.year < 1970 ) {
                messages.add("Year may not be before 1970.");
            }
        }

        /**
         * Validate month.
         *
         * @param messages the messages
         */
        private void validateMonth(List<String> messages) {
            if (this.month == null) {
                messages.add("Month must not be null.");
            }

            if (this.month != null && (this.month < 1 || this.month > 12)) {
                messages.add("Month must be between 1 ad 12.");
            }
        }

        /**
         * Validate day.
         *
         * @param messages the messages
         */
        private void validateDay(List<String> messages){
            int maxDays = 31;
            if (this.day == null) {
                messages.add("Day must not be null.");
            }

            if (this.month == null || this.year == null){
                return;
            }

            if (this.month == 2) {
                if (Math.floorMod(this.year, 4) == 0) {
                    maxDays = 29;
                }
                maxDays = 28;
            }

            var days = Arrays.asList(31, maxDays, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

            int calculatedDays = days.get(this.month - 1).intValue();
            int intDay = this.day.intValue();
            if (intDay < 0 || intDay > calculatedDays) {
                messages.add(String.format("Day must be between 1 and %d", calculatedDays));
            }
        }
    }

}
