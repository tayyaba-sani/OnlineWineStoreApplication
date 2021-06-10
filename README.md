# OnlineWineApplicationStore

**Project:**

This is a Wines Online application, which provides customers with a variety of search criteria with which they can browse, 
select wines, add them to a shopping cart, and process their order. Customers submit their order. Once an order is submitted, the e-mail notification is send to the 
customer on the status of the order.

**Technologies used:**  
SpringBoot 2.4.5  
Project Type = Maven  
Java Version = 8  
Packaging = jar  

**Maven Project:**  
After cloning the project you can import it to your IDE, then run the "mvn clean install" command.

**REST URLs:**

1) http://localhost:8080/wines/addWine: This is a post URL to add wine into database and below are the request and response.  
RequestBody:{
    "country":"Germany",
    "description":"This is a Germany wine",
    "name":"Germany abc",
    "rating":8,
    "region":"abc",
    "retailPrice":5.5,
    "varietal":"BVarietal",
    "wineVersion":9,
    "year":1995
    }  
Response Body:{
    "data": {
        "id": 27,
        "country": "Germany",
        "description": "This is a Germany wine",
        "name": "Germany abc",
        "rating": 8,
        "region": "abc",
        "retailPrice": 5.5,
        "varietal": "BVarietal",
        "wineVersion": 9,
        "year": 1995
    },
    "message": {
        "status": "200",
        "description": "SUCCESS"
    }
}

2) http://localhost:8080/wines/findById/26: This is a Get URL to get the wine by Id and below are the response.  
ResponseBody:{
    "data": {
        "id": 26,
        "country": "Canada",
        "description": "This is Canadian Wine",
        "name": "Canadian Wine",
        "rating": 8,
        "region": "ABC",
        "retailPrice": 7.0,
        "varietal": "ABC",
        "wineVersion": 9,
        "year": 2006
    },
    "message": {
        "status": "200",
        "description": "SUCCESS"
    }
}

3) http://localhost:8080/wines/findByYear/1999: This is a Get URL to get the wine by year and below are the response.  
Response Body:{
    "data": [
        {
            "id": 23,
            "country": "Dubai",
            "description": "This is a white wine",
            "name": "white abc",
            "rating": 8,
            "region": "abc",
            "retailPrice": 5.5,
            "varietal": "BVarietal",
            "wineVersion": 9,
            "year": 1999
        }
    ],
    "message": {
        "status": "200",
        "description": "SUCCESS"
    }
}

4) http://localhost:8080/wines/findByCountry/bangladesh: This is a Get URL to get the wine by country and below are the response.  
Response Body:{
    "data": [
        {
            "id": 25,
            "country": "Bangladesh",
            "description": "This is a Bangladesh wine",
            "name": "Bangladesh abc",
            "rating": 8,
            "region": "abc",
            "retailPrice": 5.5,
            "varietal": "BVarietal",
            "wineVersion": 9,
            "year": 2021
        }
    ],
    "message": {
        "status": "200",
        "description": "SUCCESS"
    }
}
