native("jvm")
module com.acme.ceylon.pkg "1.0.0-SNAPSHOT" {
  native("jvm") import java.base "7";
  shared import ceylon.json "1.2.0";
  shared import io.vertx.lang.ceylon "1.0.0-SNAPSHOT";
  shared import "io.vertx.vertx-codegen-tck" "3.2.0-SNAPSHOT"; // com.acme.ceylon.pkg
  shared import io.vertx.ceylon.codegen.testmodel "1.0.0-SNAPSHOT";
}