/*
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package issues;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.jooby.openapi.OpenAPIResult;
import io.jooby.openapi.OpenAPITest;

public class Issue1585 {

  @OpenAPITest(App1585.class)
  public void shouldAddRegex(OpenAPIResult result) {
    assertEquals(
        "openapi: 3.0.1\n"
            + "info:\n"
            + "  title: 1585 API\n"
            + "  description: 1585 API description\n"
            + "  version: \"1.0\"\n"
            + "paths:\n"
            + "  /user/{id}:\n"
            + "    get:\n"
            + "      operationId: getUserId09\n"
            + "      parameters:\n"
            + "      - name: id\n"
            + "        in: path\n"
            + "        required: true\n"
            + "        schema:\n"
            + "          pattern: \"[0-9]+\"\n"
            + "          type: integer\n"
            + "          format: int32\n"
            + "      responses:\n"
            + "        \"200\":\n"
            + "          description: Success\n"
            + "          content:\n"
            + "            application/json:\n"
            + "              schema:\n"
            + "                type: integer\n"
            + "                format: int32\n"
            + "  /company/{id}:\n"
            + "    get:\n"
            + "      operationId: getCompanyId\n"
            + "      parameters:\n"
            + "      - name: id\n"
            + "        in: path\n"
            + "        required: true\n"
            + "        schema:\n"
            + "          type: integer\n"
            + "          format: int32\n"
            + "      responses:\n"
            + "        \"200\":\n"
            + "          description: Success\n"
            + "          content:\n"
            + "            application/json:\n"
            + "              schema:\n"
            + "                type: integer\n"
            + "                format: int32\n"
            + "  /file/{*}:\n"
            + "    get:\n"
            + "      operationId: getFile\n"
            + "      parameters:\n"
            + "      - name: '*'\n"
            + "        in: path\n"
            + "        required: true\n"
            + "        schema:\n"
            + "          pattern: \\.*\n"
            + "          type: string\n"
            + "      responses:\n"
            + "        \"200\":\n"
            + "          description: Success\n"
            + "          content:\n"
            + "            application/json:\n"
            + "              schema:\n"
            + "                type: string\n"
            + "  /resources/{path}:\n"
            + "    get:\n"
            + "      operationId: getResourcesPath\n"
            + "      parameters:\n"
            + "      - name: path\n"
            + "        in: path\n"
            + "        required: true\n"
            + "        schema:\n"
            + "          pattern: \\.*\n"
            + "          type: string\n"
            + "      responses:\n"
            + "        \"200\":\n"
            + "          description: Success\n"
            + "          content:\n"
            + "            application/json:\n"
            + "              schema:\n"
            + "                type: string\n",
        result.toYaml());
  }
}
