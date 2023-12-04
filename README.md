Project Overview:

    This project tries to emulate two of the main functionalities of IRCTC, with certain constraints and assumptions:

        1. Being able to inquire about the available trains from a desired source station to a desired destination station on a particular serviceable date for a particular available class of ticket type.
        2. Being able to pre book a ticket of a specified available class, for any available train, from a desired source station to a desired destination station, on a particular serviceable date for desired number of passengers.


Additional Requirements (considered as future scope):

    1. We should consider intermediate stations within a train's route and should be able to select any of the subsequent stations from within it's route as source station and a destination station.


Constraints:

    1. The serviceable dates for any given date starts from the next day to 6 days from the present day(inclusive of the 6th day).


Assumptions:

    1. Every train must have atleast two stations in it's route.
    2. Every train has a fixed initial station, a fixed terminal station and a fixed route, specific for the train, which are the same on all the serviceable dates.
    3. Any particular train is available for journey between any two subsequent stations within it's determined route, on all the servicable dates.
    4. Any particular train completes it's journey from it's initial station to it's terminal station within a day.
    5. Any particular train on any servicable date travles from it's intial station to it's source station, following the determined route, only once and only in that direction.
    6. Every train must provide positive number of seats for atleast one class type of ticket.
    7. There are no waiting lists concept for ticket booking system.
    8. (related to additional requirements) All the stations enlisted within a train's route must be available for being selected as a source or a destination station.

Implementation:

    This application is built in five versions based on the set of methods, technologies or frameworks used. The list of the respective methods, technologies or frameworks used in each version are specified as follows:

    irctccli [console-app]: Log4j JDBC, MySQL
    irctcweb [web-app]: JSP, Servlets, Log4j, JDBC, MySQL
    irctcjpa [web-app]: JSP, Servlets, Log4j, JPA using Hibernate, MySQL
    irctcspringcore [web-app]: JSP, Servlets, Spring Core, Log4j, JPA using Hibernate, MySQL
    irctcspringmvc [web-app]: JSP, Spring MVC, Spring Core, Log4j, JPA using Hibernate, MySQL
    irctcspringrest [web-app]: JSON, Spring MVC, Spring Core, Log4j, JPA using Hibernate, MySQL
    irctcspringjpa [web-app]: JSON, Spring MVC, Spring Core, Log4j, JPA using Spring Data, MySQL


Ensure the Required Dependencies to run each version of this project locally.

Instructions before running the application locally:

    1. Check all the dependencies mentioned above, in the local machine, within which the application is to be executed.
    2. Ensure the existence of database schema (run irctcDB.sql to create the necessary schema).
    3. Ensure the presence of data within the database schema (for testing purposes use test data from test-data.sql provided within the db folders).
    4. Verify the database connection properties within the relevant configuration files of the application.
