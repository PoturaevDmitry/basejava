package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final String name;
    private final String website;
    private final List<Period> periods = new ArrayList<>();

    public Organization(String name, String website) {
        Objects.requireNonNull(name);
        this.name = name;
        this.website = website;
    }

    public void addPeriod(String title, String description, LocalDate start, LocalDate end) {
        periods.add(new Period(title, description, start, end));
    }

    public void show() {
        System.out.println(name + (Objects.nonNull(website) ? " " + website : ""));
        for (var period : periods) {
            printRangeDate(period);
            System.out.println(period.title());
            if (Objects.nonNull(period.description())) {
                System.out.println(period.description());
            }
        }
    }

    private static void printRangeDate(Period period) {
        printDate(period.start());
        System.out.print(" - ");
        printDate(period.end());
        System.out.println();
    }

    private static void printDate(LocalDate date) {
        if (Objects.nonNull(date)) {
            System.out.printf("%02d%s%d", date.getMonth().getValue(), "/", date.getYear());
        } else {
            System.out.print("Сейчас");
        }
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", website='" + website + '\'' +
                ", periods=" + periods +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!name.equals(that.name)) return false;
        if (!Objects.equals(website, that.website)) return false;
        return periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + periods.hashCode();
        return result;
    }

    private record Period(String title, String description, LocalDate start, LocalDate end) {
        public Period {
            Objects.requireNonNull(title);
        }

        @Override
        public String toString() {
            return "Period{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Period period = (Period) o;

            if (!title.equals(period.title)) return false;
            if (!Objects.equals(description, period.description))
                return false;
            if (!Objects.equals(start, period.start)) return false;
            return Objects.equals(end, period.end);
        }

        @Override
        public int hashCode() {
            int result = title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            result = 31 * result + (start != null ? start.hashCode() : 0);
            result = 31 * result + (end != null ? end.hashCode() : 0);
            return result;
        }
    }
}