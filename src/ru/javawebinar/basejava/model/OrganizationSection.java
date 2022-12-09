package ru.javawebinar.basejava.model;

import java.util.ArrayList;
import java.util.List;

public class OrganizationSection extends AbstractSection {
    List<Organization> organizations = new ArrayList<>();

    public OrganizationSection() {
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizations=" + organizations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }
}