# SSI AM Verification Controller

## ACAPY client library

Current generated ACAPY client library is based on `verification-acapy-openapi.v2.json`.

### Troubleshoot: ACAPY client library .jar files are missing in build process

Manually install the .jar file into your local Maven repository.

   ```sh
   mvn install:install-file \
      -Dfile=./lib/com/bka/ssi/controller/verification.acapy-client/0.6.0/verification.acapy-client-0.6.0.jar \
      -DgroupId=com.bka.ssi.controller \
      -DartifactId=verification.acapy-client \
      -Dversion=0.6.0 \
      -Dpackaging=jar \
      -DgeneratePom=true
   ```

In case installing fails, copy ACAPY client library .jar files to local Maven registry manually by
running the command:

   ```sh
   chmod u+x ./scripts/resolve-acapy-client-dependency.sh
   ./scripts/resolve-acapy-client-dependency.sh
   ```

In case installing fails, copy ACAPY client library .jar files to local Maven registry manually.
Make sure the path ~/.m2/repository/com/bka/ssi/controller/verification.acapy-client/ exists.

   ```sh
   cp -r ./lib/com/bka/ssi/controller/verification.acapy-client/ ~/.m2/repository/com/bka/ssi/controller/verification.acapy-client/
   ```

### Generate the ACAPY client library given an OpenAPI specification

1. Make sure Maven `mvn` and NPM `npm` are available on your system. E.g. you can install them via
   Homebrew.
    ```sh
    brew install node
    brew install maven  
    ```

2. Make sure `openapi-generator-cli` is available on your system. E.g. you can install it as a
   global NodeJS dependency.
    ```sh
    npm install @openapitools/openapi-generator-cli -g
    ```

3. OpenAPI specification (v2 or v3) of Aries Cloud Agent Python is the input for the OpenAPI
   generator. E.g. when running an instance of ACAPY v0.6.0 locally on your system with admin API
   available on port `12080`, generate the client library with the following command. Otherwise,
   use `./verification-acapy-openapi.v2.json`.
    ```sh
    openapi-generator-cli generate -i http://127.0.0.1:11080/api/docs/swagger.json -o verification.acapy-client --api-package com.bka.ssi.controller.verification.acapy-client.api --model-package com.bka.ssi.controller.verification.acapy-client.model --invoker-package com.bka.ssi.controller.verification.acapy-client.invoker --group-id com.bka.ssi.controller --artifact-id verification.acapy-client --artifact-version 0.6.0 -g java --skip-validate-spec -p dateLibrary=java8 --library=jersey2
   ```

   Swagger UI and JSON are usually accessible via
    - [http://localhost:12080/api/doc](http://localhost:12080/api/doc)
    - [http://localhost:12080/api/docs/swagger.json](http://localhost:12080/api/docs/swagger.json)

4. Register Maven project of client library to local Maven repository.
    ```sh
    cd verification.acapy-client && mvn clean install
    ```

5. Copy entry from local Maven repository into this project. Make sure the
   path `<PATH-TO-THIS-PROJECT>/lib/com/bka/ssi/controller/verification.acapy-client/` exists.
    ```sh
    cd ~/.m2/repository &&
    cp -r ./com/bka/ssi/controller/verification.acapy-client/ <PATH-TO-THIS-PROJECT>/lib/com/bka/ssi/controller/verification.acapy-client/
    ```

## Access Swagger UI and API specified via OpenAPI 3 for local environment

- [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml)

Run `mvn verify -DskipTests` in order to generate OpenAPI 3 json
in `./target/verification.company-0.0.1-SNAPSHOT-openapi.v3.json`.
