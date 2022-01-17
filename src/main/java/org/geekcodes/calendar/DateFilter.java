package org.geekcodes.calendar;

import java.time.LocalDate;
import java.util.function.Predicate;

@FunctionalInterface
public interface DateFilter extends Predicate<LocalDate> {

}
