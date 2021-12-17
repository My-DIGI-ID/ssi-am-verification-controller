# SSI AM Verification Controller

## Table of contents

* [Set Up](#set-up)
    * [Required Dependencies](#required-dependencies)
    * [Required Environment Variables](#required-environment-variables)
    * [Running the application locally](#running-the-application-locally)
    * [Building the application](#building-the-application)
    * [Running the application locally](#running-the-application-locally)
    * [Access Swagger UI and API specified via OpenAPI 3 for local environment](#access-swagger-ui-and-api-specified-via-openapi-3-for-local-environment)
* [Testing](#testing)
    * [Running the Unit Tests](#running-the-unit-tests)
* [Code Quality](#code-quality)
    * [Checkstyle](#checkstyle)
* [Documentation](#documentation)
    * [JavaDoc](#javadoc)

## Set Up

### Required Dependencies

* Hyperledger Aries Cloud Agent Python
* Keycloak
* MongoDB

### Required Environment Variables

| Variable | Description |
|----------|-------------|
| VERI_CONTROLLER_PORT | Server port |
| VERI_CONTROLLER_SSL_ENABLED | Whether to enable SSL support |
| VERI_CONTROLLER_SSL_KEYSTORE | Path to the key store that holds the certificates |
| VERI_CONTROLLER_SSL_KEYSTORE_PASSWORD | Password used to access the key store |
| VERI_CONTROLLER_SSL_KEYSTORE_TYPE | Type of the keystore |
| VERI_CONTROLLER_SSL_KEY_ALIAS | Alias that identifies the key in the key store |
| VERI_MONGODB_HOST | Mongo server host |
| VERI_MONGODB_PORT | Mongo server port |
| VERI_MONGODB_USERNAME | Login user of the mongo server |
| VERI_MONGODB_PASSWORD | Login password of the mongo server |
| VERI_MONGODB_AUTH_DB_NAME | Authentication database name |
| VERI_MONGODB_DB_NAME | Database name |
| VERI_MONGODB_SSL_ENABLED | Whether to enable SSL support for mongo server connection |
| VERI_MONGODB_SSL_KEYSTORE | Path to the key store that holds the CA-signed client certificate |
| VERI_MONGODB_SSL_KEYSTORE_PASSWORD | Password used to access the key store |
| VERI_MONGODB_SSL_TRUSTSTORE | Path to the trust store that holds the CA key |
| VERI_MONGODB_SSL_TRUSTSTORE_PASSWORD | Password used to access the trust store |
| VERI_MONGODB_SSL_INVALID_HOSTNAME_ALLOWED | Whether to skip hostname verification for mongo server certificate |
| VERI_ALLOWED_ORIGIN_HOST_A | Frontend URL |
| VERI_ALLOWED_ORIGIN_HOST_B | Additional allowed URL |
| VERI_ALLOWED_ORIGIN_HOST_C | Additional allowed URL |
| VERI_AGENT_NAME | Acapy agent name |
| VERI_AGENT_API_URL | Acapy agent URL |
| VERI_AGENT_PORT_ADMIN | Acapy agent admin API port |
| VERI_AGENT_ENDPOINT | Acapy agent service endpoint |
| VERI_AGENT_API_KEY | Acapy agent admin API key |
| VERI_AGENT_WEBHOOK_API_KEY | Acapy webhook API key |
| AGENT_API_KEY_HEADER_NAME | Name of the API key header parameter |
| AGENT_DIDCOMM_URL | DIDComm URL |
| AGENT_ARIES_MESSAGE_TYPE | Aries message type |
| AGENT_ARIES_ATTACH_ID | Aries attachment ID |
| VERI_AGENT_VERKEY | Acapy agent verification key |
| EMPLOYEE_CREDENTIAL_DEFINITION | Employee credential definition ID |
| EMPLOYEE_CREDENTIAL_SCHEMA | Employee credential schema ID |
| GUEST_CREDENTIAL_DEFINITION | Guest credential definition ID |
| GUEST_CREDENTIAL_SCHEMA | Guest credential schema ID |
| BDR_BASIS_ID_CREDENTIAL_DEFINITION | BasisID credential definition ID |
| BDR_BASIS_ID_SCHEMA | BasisID credential schema ID |
| VERI_INFO_TITLE | Swagger application title |
| VERI_INFO_DESCRIPTION | Swagger application description |
| VERI_INFO_VERSION | Swagger application version |
| VERI_INFO_CONTACT_NAME | Swagger application contact name |
| VERI_INFO_CONTACT_URL | Swagger application contact URL |
| VERI_INFO_CONTACT_EMAIL | Swagger application contact email |
| VERI_ID_PROVIDER_HOST | ID Provider host URL |
| VERI_ID_PROVIDER_PERMISSIONS_PATH | Path to check permissions |
| VERI_ID_PROVIDER_TOKEN_PATH | Path to verify token |
| VERI_ID_PROVIDER_PORT | ID Provider port |
| VERI_ID_PROVIDER_REALM | Realm in which the permissions are stored |
| VERI_ID_PROVIDER_VERI_CLIENT_ID | Client ID for the controller |
| VERI_ID_PROVIDER_VERI_CLIENT_SECRET | Client secret for the controller |
| VERI_ID_PROVIDER_SSL_TRUST_ALL | Whether to always trust the ID Provider certificate. Should be false for prod |
| VERI_ID_PROVIDER_VERIFY_HOSTNAME | Whether to verify hostname for the ID Provider certificate.Should be true for prod |
| ACCR_API_URL | Accreditation controller URL |
| ACCR_API_KEY | Accreditation controller API key |
| ACCR_API_KEY_HEADER_NAME | Accreditation controller API key header parameter |
| VERI_SCHEDULER_POOLSIZE | Scheduler thread pool size |
| VERI_VERIFICATION_TIMEOUT_IN_SECONDS | Guest pending verification timeout value in seconds |
| VERI_MGMT_DISCOVERY_ENABLED | Whether to enable the actuators discovery page |
| VERI_MGMT_EXPOSURE_INCLUDE | List of endpoints to expose |
| VERI_MGMT_ENDPOINT_HEALTH_ENABLED | Whether to enable the health endpoint |
| VERI_MGMT_ENDPOINT_HEALTH_SHOW_DETAILS | Whether to expose details on the health endpoint |
| VERI_MGMT_ENDPOINT_INFO_ENABLED | Whether to enable the info endpoint |
| VERI_MGMT_HEALTH_LIVENESS_STATE_ENABLED | Exposes the “Liveness” application availability state |
| VERI_MGMT_HEALTH_READINESS_STATE_ENABLED | Exposes the “Readiness” application availability state |

### Building the application

To compile and package the application use the following command:

```sh
mvn package
```

### Running the application locally

Run the `main` method in the `com.bka.ssi.controller.verification.company.Application` from your
IDE.

Alternatively you can use
the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html)
like so:

```sh
mvn spring-boot:run
```

### Access Swagger UI and API specified via OpenAPI 3 for local environment

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml)

Run `mvn verify -DskipTests` in order to generate OpenAPI 3 json
in `./target/verification.company-0.0.1-SNAPSHOT-openapi.v3.json`.

## Testing

### Running the Unit Tests

The unit test can be run through your preferred IDE.

IntelliJ: https://www.jetbrains.com/help/idea/performing-tests.html

Alternatively the unit test can also be run using the following command:

```sh
mvn test
```

### Coverage

The coverage report can be generated through your preferred IDE.

IntelliJ: https://www.jetbrains.com/help/idea/running-test-with-coverage.html

## Code Quality

### Checkstyle

The code analysis can be run with the following command:

```sh
mvn validate
```

## Documentation

### JavaDoc

To generate the JavaDoc run the following command:

```sh
mvn javadoc:javadoc
```

The JavaDoc will be generated in `target/site/apidocs`, open `index.html` for an overview of the
documentation.