# <span style="color:Green">News-Api</span>

This is an api built using the spark framework.It is able to post,retrieve,update, and delete records from a Postgres
database.It is able to retrieve new articles on the basis of GENERAL news or DEPARTMENTAL news.The api uses exceptions
to enforce the one-to-many relationship between the articles and department.It also does the same for Staff and
Department.This prevents entry of non-corresponding values into tables throwing the corresponding relevant exception
messages as retrieve data to help resolve errors.
description
### <span style="color:red">Note:</span>

#### For easy testing of endpoints it is much easier to use the h2 persistent database because to delete and reset database completely delete the 'newss-Api.db' file in the home directory.

#### Uncomment the following the h2 line and comment the postgres one.

To use the postgres database run the create.sql script in the `src/main/resources/sql` folder in the project directory
or run the following:

````
CREATE DATABASE washington_post;
\c washington_post;
CREATE TABLE staff(
  ID  SERIAL PRIMARY KEY,
  Name VARCHAR,
  email VARCHAR,
  phone VARCHAR,
  Rank VARCHAR,
  staffRoles VARCHAR,
  dept_id int,
department VARCHAR
);

CREATE TABLE departments(
 id serial PRIMARY KEY,
 name VARCHAR,
 description VARCHAR
);

CREATE TABLE articles(
 id serial PRIMARY KEY,
 title VARCHAR,
 message VARCHAR,
 dept_id int,
 department VARCHAR
);
````
#### <span style="color:red"> Make sure you enter the correct username ane password </span>

## <span style="color:Green"> Prerequisites</span>

- Knowledge of On Git CLI
- Java 11
- Gradle as a built tool
- IntelliJ or any Java based IDE
- postgres SQL sever installed
- Postman desktop client installed

## <span style="color:Green">Technologies</span>

- Java 11
- Gradle
- Spark
- JUnit
- Postgres Sql

## <span style="color:Green">Installation</span>

1. Clone or download repository as an archive
2. If archive unzip the archive to get project folder
3. Open the application's root folder in an IDE that can execute builds in java using gradle e.g. IntelliJ or Eclipse.
4. Navigate to the App.java file and run the `main` class
5. Use the link below to access the labeled request collection in Postman web,fork the collection into your personal workspace.
- "https://www.postman.com/dark-rocket-354200/workspace/team-workspace/collection/20876568-ce40f82f-3c9a-492d-a71e-119eed23c031?action=share&creator=20876568" 
6. For testing use Postman desktop client to access the forked collection because their cloud client does not access
   local ports like `localhost:4567` or `0.0.0.0:4567`.
7. In postman replace where indicated `:id` the number with the id you want to use in the path variables
8. Make sure you recreate the database in your local psql by running the psql commands above, or you have switched to
   the h2 database
9. If you do not fork the already created postman collection make sure to enter and label yours correctly to avoid errors because of extra spaces or missing characters 
10. You can use the samples in the `sample jsons` folder to make testing faster

## Some sample endpoint responses using the Articles and Staff Objects

#### Add General article

- Entry

```
{
  "title": "First Article",
  "message": "First Article's message"
}
```

Response

```
{
    "id": 1,
    "title": "First Article",
    "message": "First Article's message",
    "dept_id": 0,
    "department": "THIS IS A GENERAL COMPANY ARTICLE"
}
```

#### Add Departmental article

- Entry

```
{
    "title": "Second Article",
    "message": "Second Article's message",
    "dept_id": 2
  }
```

- Response if department with id does not exist

```
{
    "errorMessage": "The department ID you have allocated this article does not exist please make sure there is a department with an ID of 2 for this to work",
    "status": 404
}
```

- Response if department exists

```
{
    "id": 2,
    "title": "Second Article",
    "message": "Second Article's message",
    "dept_id": 2,
    "department": "Finance Department"
}
```

#### Get all Article

- Response

```
[
    {
        "id": 1,
        "title": "First Article",
        "message": "First Article's message",
        "dept_id": 0,
        "department": "THIS IS A GENERAL COMPANY ARTICLE"
    },
    {
        "id": 2,
        "title": "Second Article",
        "message": "Second Article's message",
        "dept_id": 2,
        "department": "Finance Department"
    },
    {
        "id": 3,
        "title": "Third Article",
        "message": "Third Article's message",
        "dept_id": 2,
        "department": "Finance Department"
    }
]
```

### Delete article by id

- If article does not Exist

````
{
    "errorMessage": "The Article with an ID of 78 does not exist you cant delete it",
    "status": 404
}

````

- If it Exists.
- Example at id=2,deletes entry and gets a list off all articles without deleted one to confirm deletion.

```
[
    {
        "id": 1,
        "title": "First Article",
        "message": "First Article's message",
        "dept_id": 0,
        "department": "THIS IS A GENERAL COMPANY ARTICLE"
    },
    {
        "id": 3,
        "title": "Third Article",
        "message": "Third Article's message",
        "dept_id": 2,
        "department": "Finance Department"
    }
]
```
### Get by staff by id 
- If staff member with id:4 doesn't exist
```
{
    "errorMessage": "The staff member with id:4 does not exist thus cant be retrieved",
    "status": 404
}
```
- If staff member with id:4 exists
``` 
{
    "id": 4,
    "name": "Fourth Staff Member",
    "email": "Fourth@email.com",
    "phone": "Fourth Staff Member",
    "rank": "Personal Assistant",
    "staffRole": "Assists Manager",
    "dept_id": 1
    "department": "Public Relations Department",

}
```
### Delete all Staff
- If staff table has entries, it deletes and return an empty array
```
[]
```
- If staff table is already empty
```   
{
    "errorMessage": "Staff is already empty, cant delete all",
    "status": 404
}

```

## <span style="color:green">Sample response to show one-to-many relationship to the Department table</span>

### If the department does not exist

- Staff retrieval error

```
{
    "errorMessage": "The department with id:23 does not exist thus cannot have staff allocated",
    "status": 404
}
```

- Article retrieval error

```
{
    "errorMessage": "The department with id:23 does not exist thus cannot have Articles allocated",
    "status": 404
}
```

### If it exists

- But with no staff assigned

```
{
    "errorMessage": "This department doesn't have any staff members allocated. Add staff to it by allocating a staff member the dept_id:7",
    "status": 404
}
```

- With staff assigned

```
[
    {
        "id": 2,
        "name": "First Staff Member",
        "email": "First@email.com",
        "phone": "First Staff Member",
        "rank": "Video Editor",
        "staffRole": "Edits videos",
        "dept_id": 2
        "department": "Finance Department"

    },
    {
        "id": 3,
        "name": "Third Staff Member",
        "email": "Third@email.com",
        "phone": "Third Staff Member",
        "rank": "Secretary",
        "staffRole": "Files office papers",
        "dept_id": 2 
        "department": "Finance Department"

    }
]
```

#### NOTE: Not all individual response behaviors are covered by the samples but all response types are covered by the samples to provide a correct idea of what responses will look like endpoints are hit

## <span style="color:Green">To Contribute or Fix bug

To fix a bug or enhance an existing module, follow these steps:

- Fork the repo
- Create a new branch (`git checkout -b improve-feature`)
- Make the appropriate changes in the files
- Add changes to reflect the changes made
- Commit your changes (`git commit -m 'Improve feature'`)
- Push to the branch (`git push origin improve-feature`)
- Create a Pull Request

## <span style="color:Green">BUGS FOUND</span>

If you come across any bug in the project kindly report using the link below

#### [Link 🔗 ](https://github.com/Levi-Opunga/newss-api/issues/new)

## Licence

### MIT License

> Copyright (c) 2022 Levi Opunga

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NON INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

## <span style="color:blue">Support and contact details</span>

> > EMAIL: leviopunga@gmail.com





