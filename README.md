# maven-Sptify-API-OAuth2.0
A framework with **`TestNG`** + **`Rest Assured`** + **`Maven`** in Java.
This framework for API automation. They are currently used for REST API Testing:

**Spotify Playlist using the OAuth 2.0 flow (Authorization COde Grant Flow]**
 - [Spotify for Developer](https://developer.spotify.com/)
 - [Spotify](https://open.spotify.com/)
 
## Languages and Frameworks
This project uses the following languages and frameworks:

- **`Java 11`** as the programming language
- **`TestNG`** to support the test creation
- **`Rest Assured`** `(version 5.3.0)` is a web browser automation framework using Java binding.
- **`Hamcrest`**  is a Java library for writing matchers, which allows flexible checking of values in tests.
- **`Allure Reports`** as the testing report strategy
- **`Jackson API`** is a Java library for handling JSON data, supports features like serialization, deserialization.
- **`Lombok`** is a Java library that reduces boilerplate code, uses annotations to minimize repetitive code


### Maven Surefire plugin on pom.xml

The Maven Surefire plugin was used to execute the test 
- We must add the BASE_URI and ACCOUNT_BASE_URI for this testing with the command:

 ``` bash
mvn clean test -DBASE_URI=https://api.spotify.com -DACCOUNT_BASE_URI=https://accounts.spotify.com

```

### Allure Report
- Following report was generated after running:

``` bash
 target/allure-sesults/
```
- You need to run allure command under root directory to see the report

``` bash
 allure serve allure-results
```

## Frameworks Scalability 

