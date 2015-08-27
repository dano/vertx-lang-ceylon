import ceylon.json {
  JsonObject=Object
}
import io.vertx.lang.ceylon {
  BaseDataObject
}
import io.vertx.core.net {
  JksOptions_=JksOptions
}
/* Generated from io.vertx.core.net.JksOptions */
shared class JksOptions(
  shared String? password = null,
  shared String? path = null) satisfies
  KeyCertOptions &
  TrustOptions & BaseDataObject {
  shared actual default JsonObject toJson() {
    value json = JsonObject();
    if (exists password) {
      json.put("password", password);
    }
    if (exists path) {
      json.put("path", path);
    }
    return json;
  }
}
shared JksOptions toJksOptions(JsonObject json) {
  String? password = json.getStringOrNull("password");
  String? path = json.getStringOrNull("path");
  return JksOptions {
    password = password;
    path = path;
  };
}
