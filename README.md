# Fitness-Journal

## ABOUT
Fitness Journal is a full stack application that allows you to track your workouts from a sleek and easy to use website.
It enables users to input their workout information manually or from your favorite fitness service.
Fitness Journal is well suited for people who want track their fitness in a private and local instance vs other alternatives online.

## Motivations
- The motivation for the application came from my interest in fitness and how I could create a private app for my workout data without commiting to one of the many online app for this.
- These online application that exist are great and offer wonderful features, but it locks users to their vendors.
- These vendor locking can cause issue like:
  - **Data migration**: You cannot easily transfer your years of fitness data to another app.
  - **Feature fragmentation**: Different apps offer different features and limits user insight from their workout data.
  - **Streaks Resets** : While this sounds trivial many user actually have deep connection to the daily steaks and name it a top reason for sticking with the application.
- The goal of this application is to be an agnostic of platform that allows you to have your information in a database and display however you want. If you want to edit you can do that and add features specific for your needs.

## Quick Start
 ### Cloning
  This assumes you have openjdk 17 or above

```git clone https://github.com/Jruz9/Fitness-Journal.git```

## Usage
Features included or are coming to application in the future include:
- Workout Journal
- Workout library
- workout plans/routines
- Food journal
- Food plans
- Body Measurement Recording
- Body weight recording
- Platform exports
- 3rd party fitness integration

## Contributing
 ### java openjdk
This uses java 17. you can install it from here for you respective os you run.

```https://adoptium.net/marketplace/?version=17&os=any```
 ### Cloning
you can clone the project with this command.

```git clone https://github.com/Jruz9/Fitness-Journal.git```

### Project set up and dependencies:
In IntelliJ open the project using the pom.xml and click on maven button to import all dependencies.
it will probably take 1-3 minutes to complete

### Run project
To run the application without compiling to a jar you can press the green button on top of the idea for the file.

```FitnessJournalBackendApplication.java```

## Technologies
- Backend
  - Java 17
  - Spring boot 3.1.3
  - Mysql
- Frontend
  - React
  - Node.js

