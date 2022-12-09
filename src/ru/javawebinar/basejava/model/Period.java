package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final String title;
    private final String description;
    private final LocalDate start;
    private final LocalDate end;

    public Period(String title, String description, LocalDate start, LocalDate end) {
        Objects.requireNonNull(title);
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
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