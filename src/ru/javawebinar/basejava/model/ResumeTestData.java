package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class ResumeTestData {

    private static final String[] achievements = {
            """
                Организация команды и успешная реализация Java проектов для сторонних заказчиков: \
                приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга \
                показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, \
                многомодульный Spring Boot + Vaadin проект для комплексных DIY смет""",
            """
                С 2013 года: разработка проектов "Разработка Web приложения","Java \
                Enterprise", "Многомодульный maven. Многопоточность. XML (JAXB/StAX). \
                Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)". \
                Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.""",
            """
                Реализация двухфакторной аутентификации для онлайн платформы управления проектами \
                Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk."""
    };

    private static final String[] qualifications = {
            """
                JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2""",
            """
                Version control: Subversion, Git, Mercury, ClearCase, Perforce""",
            """
                DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, \
                MySQL, SQLite, MS SQL, HSQLDB"""
    };

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");
        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOME_PAGE, "https://gkislin.ru/");

        String objective = "Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям";
        StringSection stringSection = new StringSection(objective);
        resume.addSection(SectionType.OBJECTIVE, stringSection);

        String personal = "Аналитический склад ума, сильная логика, креативность, " +
                "инициативность. Пурист кода и архитектуры.";
        stringSection = new StringSection(personal);
        resume.addSection(SectionType.PERSONAL, stringSection);

        ListSection listSection = new ListSection();
        Arrays.stream(achievements).forEach(listSection::addString);
        resume.addSection(SectionType.ACHIEVEMENT, listSection);

        listSection = new ListSection();
        Arrays.stream(qualifications).forEach(listSection::addString);
        resume.addSection(SectionType.QUALIFICATIONS, listSection);

        OrganizationSection organizationSection = new OrganizationSection();
        Organization organization = new Organization("Java Online Projects",
                "https://javaops.ru/");
        String title = "Автор проекта";
        String description = "Создание, организация и проведение Java онлайн проектов и стажировок.";
        organization.addPeriod(title, description, LocalDate.of(2013, 10, 1), null);
        organizationSection.addOrganization(organization);

        organization = new Organization("Wrike", "https://www.wrike.com/");
        title = "Старший разработчик (backend)";
        description = """
                Проектирование и разработка онлайн платформы управления проектами Wrike \
                (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). \
                Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.""";
        organization.addPeriod(title, description, LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1));
        organizationSection.addOrganization(organization);
        resume.addSection(SectionType.EXPERIENCE, organizationSection);

        organizationSection = new OrganizationSection();
        organization = new Organization("Coursera",
                "https://www.coursera.org/course/progfun");
        title = "'Functional Programming Principles in Scala' by Martin Odersky";
        organization.addPeriod(title, null, LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1));
        organizationSection.addOrganization(organization);

        organization = new Organization("Luxoft",
                "https://www.luxoft-training.ru/training/catalog/course.html?ID=22366");
        title = "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'";
        organization.addPeriod(title, null, LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1));
        organizationSection.addOrganization(organization);
        resume.addSection(SectionType.EDUCATION, organizationSection);

        showResume(resume);
    }

    private static void showResume(Resume resume) {
        System.out.println(resume.getFullName());
        resume.getContacts().forEach(
                (key, value) -> System.out.println(key.getTitle() + ": " + value)
        );
        System.out.println();
        for (var entry : resume.getSections().entrySet()) {
            SectionType sectionType = entry.getKey();
            System.out.println(sectionType.getTitle());
            switch (sectionType) {
                case OBJECTIVE, PERSONAL -> {
                    StringSection stringSection = (StringSection) entry.getValue();
                    System.out.println(stringSection.getString() + "\n");
                }
                case ACHIEVEMENT, QUALIFICATIONS -> {
                    ListSection listSection = (ListSection) entry.getValue();
                    listSection.getList().forEach(System.out::println);
                    System.out.println();
                }
                case EXPERIENCE, EDUCATION -> {
                    OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                    for (var organization : organizationSection.getOrganizations()) {
                        System.out.print(organization.getName());
                        String webSite = organization.getWebsite();
                        System.out.println(Objects.nonNull(webSite) ? " " + webSite : "");

                        for (var period : organization.getPeriods()) {
                            printRangeDate(period);
                            System.out.println(period.getTitle());
                            if (Objects.nonNull(period.getDescription())) {
                                System.out.println(period.getDescription());
                            }
                        }
                        System.out.println();
                    }
                }
            }
        }
    }

    private static void printRangeDate(Period period) {
        printDate(period.getStart());
        System.out.print(" - ");
        printDate(period.getEnd());
        System.out.println();
    }

    private static void printDate(LocalDate date) {
        if (Objects.nonNull(date)) {
            System.out.printf("%02d%s%d", date.getMonth().getValue(), "/", date.getYear());
        } else {
            System.out.print("Сейчас");
        }
    }
}
