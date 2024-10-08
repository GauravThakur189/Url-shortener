# URL Shortener API

## Overview
This URL Shortener API allows users to shorten long URLs, retrieve original URLs using short codes, and manage their shortened URLs effectively. The API is built using Spring Boot and provides a robust backend service.

## Table of Contents
1. [API Endpoints](#api-endpoints)
2. [Request and Response Formats](#request-and-response-formats)
3. [Database Schema](#database-schema)
4. [Security Measures](#security-measures)
5. [Background Jobs](#background-jobs)
6. [Postman Collection](#postman-collection)
7. [Sample Data Generation Script](#sample-data-generation-script)

## API Endpoints

| Method | Endpoint                                            | Description                           |
|--------|-----------------------------------------------------|---------------------------------------|
| POST   | http://localhost:8080/url/shortner                   | Create a new shortened URL            |
| GET    | http://localhost:8080/url/shortner/{shortUrl}         | Retrieve the original URL by short code |
| GET    | http://localhost:8080/url/shortner/analytics/{shortUrl}  | Get all shortened URLs  with analytics value as you have mentioned |


### Example

**POST /api/urls**

*Request Body:*
```json
{
  "originalurl": "https://www.google.com",
  "expirationDate": "2024-12-31T23:59:59"
}


#Response
{
  "id": 1,
  "shorturl": "abc123",
  "originalurl": "https://www.example.com",
  "expirationDate": "2024-12-31T23:59:59"
}

##Request and Response Formats
POST /api/urls
Request Format:

Content-Type: application/json
Request Body:
{
  "originalurl": "string",
  "expirationDate": "2024-12-31T23:59:59"
}

#Response Format:

Status Code: 201 Created
Response Body:
{
    "id": 52,
    "shorturl": "cac87a2c",
    "originalurl": "https://www.google.com/",
    "analytics": {
        "id": 3,
        "totalVisits": 0,
        "uniqueVisitors": [],
        "desktopVisits": 0,
        "mobileVisits": 0,
        "visitTimestamps": [],
        "topReferrer": null
    },
    "createdAt": "2024-10-04T19:45:10.2242383",
    "expirationDate": "2024-10-11T19:45:10.2252413"
}

##Analytics Response
Analytics for Short URL: 50328aa4


Original URL: https://www.google.com
Total number of visits: 2
Unique Visitors: 1
Desktop Visits: 2
Mobile Visits: 0
Time series data of visits : [2024-10-04T19:20:06.915545, 2024-10-04T19:20:01.731630]


Database Url Schema
Column Name           	Data Type     	Description
id	                    Integer	        Primary Key, Auto-increment
originalurl	            String          Original URL provided by the user
shorturl	              String	        Generated short URL
expirationDate	        LocalDateTime	  Expiration date of the short URL
createdAt               LocalDateTime   when the short url created

Database Analytics Schema
Column Name           	Data Type     	Description
id	                    Integer	        Primary Key, Auto-increment
originalurl	            String          Original URL provided by the user
shorturl	              String	        Generated short URL
expirationDate	        LocalDateTime	  Expiration date of the short URL
createdAt               LocalDateTime   when the short url created
totalVisits             Integer	        total no. of visits
uniqueVisitors          Integer         total visitors
desktopVisits           Integer         total desktop visitors



### Steps to Finalize Your Project

1. **Add the README.md File**: Create a `README.md` file in the root of your project and copy the structured content above, making sure to adjust any specifics to accurately reflect your implementation.

2. **Export Postman Collection**: 
   - Open Postman.
   - Go to your collection.
   - Click on the three dots next to your collection name and choose "Export".
   - Save it in a `postman` directory in your project.

3. **Generate Sample Data Script**: If you haven't already created a script to generate sample data, create one in a `scripts` directory. This could be a simple Java class or a Python script that inserts test data into your database.

4. **Update the GitHub Repository**: 
   - Commit your changes with a message like "Finalized README and added Postman collection."
   - Push the changes to your GitHub repository.

5. **Test the API**: Ensure that all endpoints work correctly, and all examples in the README file are valid.

### Additional Tips

- If you have not yet included unit tests, consider adding some basic tests to ensure the reliability of your API.
- Keep your GitHub repository organized; structure folders clearly for easier navigation.
- Use versioning for your API if applicable, especially if you plan on making significant changes in the future.

Once you have completed these steps, your project will be well-documented and ready for others to use! If you have any more questions or need further assistance, feel free to ask.

