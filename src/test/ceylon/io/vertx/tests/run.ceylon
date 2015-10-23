import io.vertx.ceylon.codegen.testmodel {
  TestInterface, NullableTCK, RefedInterface1, RefedInterface2, TestDataObject,
  Factory, AbstractHandlerUserType, ConcreteHandlerUserType, ConcreteHandlerUserTypeExtension,
  GenericRefedInterface, TestGenEnum, \iMIKE, \iBOB
}
import io.vertx.codegen.testmodel {
  TestInterfaceImpl, RefedInterface1Impl, NullableTCKImpl
}
import ceylon.test {
  test, assertEquals, assertTrue, assertNull, assertNotNull
}
import ceylon.json {
  JsonObject=Object, JsonArray=Array
}
import ceylon.collection {
  HashSet, ArrayList, HashMap
}

TestInterface obj = TestInterface(TestInterfaceImpl());
NullableTCK nullableTCK = NullableTCK(NullableTCKImpl());

Comparison comparingRefedInterface1(RefedInterface1 x, RefedInterface1 y) {
  return x.getString().compare(y.getString());
}

Comparison comparingRefedInterface2(RefedInterface2 x, RefedInterface2 y) {
  return x.getString().compare(y.getString());
}

Comparison comparingTestDataObject(TestDataObject x, TestDataObject y) {
  assert(exists a=x.foo);
  assert(exists b=y.foo);
  return a.compare(b);
}

shared test void testMethodWithBasicParams() {
  obj.methodWithBasicParams(123.byte, 12345, 1234567, 1265615234, 12.345, 12.34566, true, 'X', "foobar");
}

shared test void testMethodWithBasicBoxedParams() {
  value obj = TestInterface(TestInterfaceImpl());
  obj.methodWithBasicBoxedParams(123.byte, 12345, 1234567, 1265615234, 12.345, 12.34566, true, 'X');
}

shared test void testMethodWithHandlerBasicTypes() {
  variable Integer count = 0;
  void byte_function(Byte val) {
    assertEquals(val, 123.byte);
    count++;
  }
  void short_function(Integer val) {
    assertEquals(val, 12345);
    count++;
  }
  void integer_function(Integer val) {
    assertEquals(val, 1234567);
    count++;
  }
  void long_function(Integer val) {
    assertEquals(val, 1265615234);
    count++;
  }
  void float_function(Float val) {
    assertFloatEquals(val, 12.345);
    count++;
  }
  void double_function(Float val) {
    assertFloatEquals(val, 12.34566);
    count++;
  }
  void boolean_function(Boolean val) {
    assertEquals(val, true);
    count++;
  }
  void character_function(Character val) {
    assertEquals(val, 'X');
    count++;
  }
  void string_function(String val) {
    assertEquals(val, "quux!");
    count++;
  }
  obj.methodWithHandlerBasicTypes(
    byte_function,
    short_function,
    integer_function,
    long_function,
    float_function,
    double_function,
    boolean_function,
    character_function,
    string_function);
  assertEquals(count, 9);
}

shared test void testMethodWithHandlerAsyncResultBasicTypes() {
  variable Integer count = 0;
  void function_succeeded<T>(T expected)(T|Throwable result) {
    if (is T result) {
      assertEquals(result, expected);
      count++;
    }
  }
  void float_function_succeeded(Float expected)(Float|Throwable result) {
    if (is Float result) {
      assertFloatEquals(result, expected);
      count++;
    }
  }
  obj.methodWithHandlerAsyncResultByte(false, function_succeeded<Byte>(123.byte));
  obj.methodWithHandlerAsyncResultShort(false, function_succeeded<Integer>(12345));
  obj.methodWithHandlerAsyncResultInteger(false, function_succeeded<Integer>(1234567));
  obj.methodWithHandlerAsyncResultLong(false, function_succeeded<Integer>(1265615234));
  obj.methodWithHandlerAsyncResultFloat(false, float_function_succeeded(12.345));
  obj.methodWithHandlerAsyncResultDouble(false, float_function_succeeded(12.34566));
  obj.methodWithHandlerAsyncResultBoolean(false, function_succeeded<Boolean>(true));
  obj.methodWithHandlerAsyncResultCharacter(false, function_succeeded<Character>('X'));
  obj.methodWithHandlerAsyncResultString(false, function_succeeded<String>("quux!"));
  assertEquals(count, 9);
  void function_fail<T>(T|Throwable result) {
    if (is Throwable result) {
      assertEquals("foobar!", result.message);
      count++;
    }
  }
  obj.methodWithHandlerAsyncResultByte(true, function_fail<Byte>);
  obj.methodWithHandlerAsyncResultShort(true, function_fail<Integer>);
  obj.methodWithHandlerAsyncResultInteger(true, function_fail<Integer>);
  obj.methodWithHandlerAsyncResultLong(true, function_fail<Integer>);
  obj.methodWithHandlerAsyncResultFloat(true, function_fail<Float>);
  obj.methodWithHandlerAsyncResultDouble(true, function_fail<Float>);
  obj.methodWithHandlerAsyncResultBoolean(true, function_fail<Boolean>);
  obj.methodWithHandlerAsyncResultCharacter(true, function_fail<Character>);
  obj.methodWithHandlerAsyncResultString(true, function_fail<String>);
  assertEquals(count, 18);
}

shared test void testMethodWithUserTypes() {
  value refed = RefedInterface1(RefedInterface1Impl());
  refed.setString("aardvarks");
  obj.methodWithUserTypes(refed);
}

shared test void testObjectParam() {
  obj.methodWithObjectParam("null", null);
  obj.methodWithObjectParam("string", "wibble");
  obj.methodWithObjectParam("true", true);
  obj.methodWithObjectParam("false", false);
  obj.methodWithObjectParam("long", 123);
  obj.methodWithObjectParam("double", 123.456);
  obj.methodWithObjectParam("JsonObject", JsonObject { "foo"->"hello", "bar"->123 });
  obj.methodWithObjectParam("JsonArray", JsonArray { "foo", "bar", "wib" });
}

shared test void testDataObjectParam() {
  value dataObject = TestDataObject {
    foo="hello";
    bar=123;
    wibble=1.23;
  };
  obj.methodWithDataObjectParam(dataObject);
}

shared test void testNullDataObjectParam() {
  obj.methodWithNullDataObjectParam(null);
}

shared test void testMethodWithHandlerDataObject() {
  variable TestDataObject? tmp = null;
  void callback(TestDataObject obj) {
    tmp = obj;
  }
  obj.methodWithHandlerDataObject(callback);
  assert(exists o = tmp);
  assertEquals(o.foo, "foo");
  assertEquals(o.bar, 123);
}

shared test void testMethodWithHandlerAsyncResultDataObject() {
  variable TestDataObject|Throwable|Null tmp = null;
  void callback(TestDataObject|Throwable result) {
    tmp = result;
  }
  obj.methodWithHandlerAsyncResultDataObject(false, callback);
  assert(is TestDataObject o = tmp);
  assertEquals(o.foo, "foo");
  assertEquals(o.bar, 123);
  obj.methodWithHandlerAsyncResultDataObject(true, callback);
  assert(is Throwable t = tmp);
  assertEquals(t.message, "foobar!");
}

shared test void testMethodWithHandlerListAndSet() {
  variable List<String>? arg0 = null;
  variable List<Integer>? arg1 = null;
  variable Set<String>? arg2 = null;
  variable Set<Integer>? arg3 = null;
  obj.methodWithHandlerListAndSet(
    (List<String> arg) => arg0 = arg,
    (List<Integer> arg) => arg1 = arg,
    (Set<String> arg) => arg2 = arg,
    (Set<Integer> arg) => arg3 = arg);
  assertEquals(arg0, ArrayList { "foo", "bar", "wibble" });
  assertEquals(arg1, ArrayList { 5, 12, 100 });
  assertEquals(arg2, HashSet { "foo", "bar", "wibble" });
  assertEquals(arg3, HashSet { 5, 12, 100 });
}

shared test void testMethodWithHandlerAsyncResultListAndSet() {
  variable List<String>|Throwable|Null arg0 = null;
  variable List<Integer>|Throwable|Null arg1 = null;
  variable Set<String>|Throwable|Null arg2 = null;
  variable Set<Integer>|Throwable|Null arg3 = null;
  obj.methodWithHandlerAsyncResultListString((List<String>|Throwable arg) => arg0 = arg);
  obj.methodWithHandlerAsyncResultListInteger((List<Integer>|Throwable arg) => arg1 = arg);
  obj.methodWithHandlerAsyncResultSetString((Set<String>|Throwable arg) => arg2 = arg);
  obj.methodWithHandlerAsyncResultSetInteger((Set<Integer>|Throwable arg) => arg3 = arg);
  assertEquals(arg0, ArrayList { "foo", "bar", "wibble" });
  assertEquals(arg1, ArrayList { 5, 12, 100 });
  assertEquals(arg2, HashSet { "foo", "bar", "wibble" });
  assertEquals(arg3, HashSet { 5, 12, 100 });
}

shared test void testMethodWithHandlerListVertxGen() {
  variable List<RefedInterface1>|Null arg = null;
  obj.methodWithHandlerListVertxGen((List<RefedInterface1> arg_) => arg=arg_);
  assert(is List<RefedInterface1> val=arg);
  assertEquals(val.size, 2);
  assert(exists v0=val[0]);
  assertEquals(v0.getString(), "foo");
  assert(exists v1=val[1]);
  assertEquals(v1.getString(), "bar");
}

shared test void testMethodWithHandlerListAbstractVertxGen() {
  variable List<RefedInterface2>|Null arg = null;
  obj.methodWithHandlerListAbstractVertxGen((List<RefedInterface2> arg_) => arg=arg_);
  assert(is List<RefedInterface2> val=arg);
  assertEquals(val.size, 2);
  assert(exists v0=val[0]);
  assertEquals(v0.getString(), "abstractfoo");
  assert(exists v1=val[1]);
  assertEquals(v1.getString(), "abstractbar");
}

shared test void testMethodWithHandlerAsyncResultListVertxGen() {
  variable List<RefedInterface1>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultListVertxGen((List<RefedInterface1>|Throwable arg_) => arg=arg_);
  assert(is List<RefedInterface1> val=arg);
  assertEquals(val.size, 2);
  assert(exists v0=val[0]);
  assertEquals(v0.getString(), "foo");
  assert(exists v1=val[1]);
  assertEquals(v1.getString(), "bar");
}

shared test void testMethodWithHandlerAsyncResultListAbstractVertxGen() {
  variable List<RefedInterface2>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultListAbstractVertxGen((List<RefedInterface2>|Throwable arg_) => arg=arg_);
  assert(is List<RefedInterface2> val=arg);
  assertEquals(val.size, 2);
  assert(exists v0=val[0]);
  assertEquals(v0.getString(), "abstractfoo");
  assert(exists v1=val[1]);
  assertEquals(v1.getString(), "abstractbar");
}

shared test void testMethodWithHandlerSetVertxGen() {
  variable RefedInterface1[]|Null arg = null;
  obj.methodWithHandlerSetVertxGen((Set<RefedInterface1> arg_) => arg=arg_.sort(comparingRefedInterface1));
  assert(is RefedInterface1[] val=arg);
  assertEquals(val.size, 2);
  assert(exists v0=val[0]);
  assertEquals(v0.getString(), "bar");
  assert(exists v1=val[1]);
  assertEquals(v1.getString(), "foo");
}

shared test void testMethodWithHandlerSetAbstractVertxGen() {
  variable RefedInterface2[]|Null arg = null;
  obj.methodWithHandlerSetAbstractVertxGen((Set<RefedInterface2> arg_) => arg=arg_.sort(comparingRefedInterface2));
  assert(is RefedInterface2[] val=arg);
  assertEquals(val.size, 2);
  assert(exists v0=val[0]);
  assertEquals(v0.getString(), "abstractbar");
  assert(exists v1=val[1]);
  assertEquals(v1.getString(), "abstractfoo");
}

shared test void testMethodWithHandlerAsyncResultSetVertxGen() {
  variable Set<RefedInterface1>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultSetVertxGen((Set<RefedInterface1>|Throwable arg_) => arg=arg_);
  assert(is Set<RefedInterface1> tmp = arg);
  value val = tmp.sort(comparingRefedInterface1);
  assertEquals(val.size, 2);
  assert(exists v0=val[0]);
  assertEquals(v0.getString(), "bar");
  assert(exists v1=val[1]);
  assertEquals(v1.getString(), "foo");
}

shared test void testMethodWithHandlerAsyncResultSetAbstractVertxGen() {
  variable Set<RefedInterface2>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultSetAbstractVertxGen((Set<RefedInterface2>|Throwable arg_) => arg=arg_);
  assert(is Set<RefedInterface2> tmp = arg);
  value val = tmp.sort(comparingRefedInterface2);
  assertEquals(val.size, 2);
  assert(exists v0=val[0]);
  assertEquals(v0.getString(), "abstractbar");
  assert(exists v1=val[1]);
  assertEquals(v1.getString(), "abstractfoo");
}

shared test void testMethodWithHandlerListJsonObject() {
  variable List<JsonObject>|Null arg = null;
  obj.methodWithHandlerListJsonObject((List<JsonObject> arg_) => arg=arg_);
  assertEquals(arg, ArrayList { JsonObject { "cheese"->"stilton" }, JsonObject { "socks"->"tartan" } });
}

shared test void testMethodWithHandlerListNullJsonObject() {
  // Todo
}

shared test void testMethodWithHandlerListComplexJsonObject() {
  variable List<JsonObject>|Null arg = null;
  obj.methodWithHandlerListComplexJsonObject((List<JsonObject> arg_) => arg=arg_);
  assertEquals(arg, ArrayList { JsonObject { "outer" -> JsonObject { "socks"->"tartan" }, "list" -> JsonArray { "yellow", "blue" } } });
}

shared test void testMethodWithHandlerAsyncResultListJsonObject() {
  variable List<JsonObject>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultListJsonObject((List<JsonObject>|Throwable arg_) => arg=arg_);
  assertEquals(arg, ArrayList { JsonObject { "cheese"->"stilton" }, JsonObject { "socks"->"tartan" } });
}

shared test void testMethodWithHandlerAsyncResultListNullJsonObject() {
  // Todo
}

shared test void testMethodWithHandlerAsyncResultListComplexJsonObject() {
  variable List<JsonObject>|Throwable|Null arg = null;
  obj.methodWithHandlerListComplexJsonObject((List<JsonObject>|Throwable arg_) => arg=arg_);
  assertEquals(arg, ArrayList { JsonObject { "outer" -> JsonObject { "socks"->"tartan" }, "list" -> JsonArray { "yellow", "blue" } } });
}

shared test void testMethodWithHandlerSetJsonObject() {
  variable Set<JsonObject>|Null arg = null;
  obj.methodWithHandlerSetJsonObject((Set<JsonObject> arg_) => arg=arg_);
  assertEquals(arg, HashSet { JsonObject { "cheese"->"stilton" }, JsonObject { "socks"->"tartan" } });
}

shared test void testMethodWithHandlerSetNullJsonObject() {
  // Todo
}

shared test void testMethodWithHandlerSetComplexJsonObject() {
  variable Set<JsonObject>|Null arg = null;
  obj.methodWithHandlerSetComplexJsonObject((Set<JsonObject> arg_) => arg=arg_);
  assertEquals(arg, HashSet { JsonObject { "outer" -> JsonObject { "socks"->"tartan" }, "list" -> JsonArray { "yellow", "blue" } } });
}

shared test void testMethodWithHandlerAsyncResultSetJsonObject() {
  variable Set<JsonObject>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultSetJsonObject((Set<JsonObject>|Throwable arg_) => arg=arg_);
  assertEquals(arg, HashSet { JsonObject { "cheese"->"stilton"}, JsonObject { "socks"->"tartan" } });
}

shared test void testMethodWithHandlerAsyncResultSetNullJsonObject() {
  // Todo
}

shared test void testMethodWithHandlerAsyncResultSetComplexJsonObject() {
  variable Set<JsonObject>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultSetComplexJsonObject((Set<JsonObject>|Throwable arg_) => arg=arg_);
  assertEquals(arg, HashSet { JsonObject { "outer" -> JsonObject { "socks"->"tartan" }, "list" -> JsonArray { "yellow", "blue" } } });
}

shared test void testMethodWithHandlerListJsonArray() {
  variable List<JsonArray>|Null arg = null;
  obj.methodWithHandlerListJsonArray((List<JsonArray> arg_) => arg=arg_);
  assertEquals(arg, ArrayList { JsonArray { "green", "blue" }, JsonArray { "yellow", "purple" } });
}

shared test void testMethodWithHandlerListNullJsonArray() {
  // Todo
}

shared test void testMethodWithHandlerListComplexJsonArray() {
  variable List<JsonArray>|Null arg = null;
  obj.methodWithHandlerListComplexJsonArray((List<JsonArray> arg_) => arg=arg_);
  assertEquals(arg, ArrayList { JsonArray { JsonObject { "foo"->"hello" } }, JsonArray {  JsonObject { "bar"->"bye" } } });
}

shared test void testMethodWithHandlerListDataObject() {
  variable List<TestDataObject>|Null arg = null;
  obj.methodWithHandlerListDataObject((List<TestDataObject> arg_) => arg=arg_);
  assert(is List<TestDataObject> list=arg);
  assertEquals(list.size, 2);
  value obj1 = list[0];
  assert(is TestDataObject obj1);
  assertEquals(obj1.foo, "String 1");
  assertEquals(obj1.bar, 1);
  assertFloatEquals(obj1.wibble, 1.1);
  value obj2 = list[1];
  assert(is TestDataObject obj2);
  assertEquals(obj2.foo, "String 2");
  assertEquals(obj2.bar, 2);
  assertFloatEquals(obj2.wibble, 2.2);
}

shared test void testMethodWithHandlerNullListDataObject() {
  // Todo
}

shared test void testMethodWithHandlerSetDataObject() {
  variable TestDataObject[]|Null arg = null;
  obj.methodWithHandlerSetDataObject((Set<TestDataObject> arg_) => arg=arg_.sort(comparingTestDataObject));
  assert(is TestDataObject[] set=arg);
  assertEquals(set.size, 2);
  value obj1 = set[0];
  assert(is TestDataObject obj1);
  assertEquals(obj1.foo, "String 1");
  assertEquals(obj1.bar, 1);
  assertFloatEquals(obj1.wibble, 1.1);
  value obj2 = set[1];
  assert(is TestDataObject obj2);
  assertEquals(obj2.foo, "String 2");
  assertEquals(obj2.bar, 2);
  assertFloatEquals(obj2.wibble, 2.2);
}

shared test void testMethodWithHandlerNullSetDataObject() {
  // Todo
}

shared test void testMethodWithHandlerAsyncResultListJsonArray() {
  variable List<JsonArray>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultListJsonArray((List<JsonArray>|Throwable arg_) => arg=arg_);
  assertEquals(arg, ArrayList { JsonArray { "green", "blue" }, JsonArray { "yellow", "purple" } });
}

shared test void testMethodWithHandlerAsyncResultListNullJsonArray() {
  // Todo
}

shared test void testMethodWithHandlerAsyncResultListComplexJsonArray() {
  variable List<JsonArray>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultListComplexJsonArray((List<JsonArray>|Throwable arg_) => arg=arg_);
  assertEquals(arg, ArrayList { JsonArray { JsonObject { "foo"->"hello" } }, JsonArray {  JsonObject { "bar"->"bye" } } });
}

shared test void testMethodWithHandlerSetJsonArray() {
  variable Set<JsonArray>|Null arg = null;
  obj.methodWithHandlerSetJsonArray((Set<JsonArray> arg_) => arg=arg_);
  assertEquals(arg, HashSet { JsonArray { "green", "blue" }, JsonArray { "yellow", "purple" } });
}

shared test void testMethodWithHandlerSetNullJsonArray() {
  // Todo
}

shared test void testMethodWithHandlerSetComplexJsonArray() {
  variable Set<JsonArray>|Null arg = null;
  obj.methodWithHandlerSetComplexJsonArray((Set<JsonArray> arg_) => arg=arg_);
  assertEquals(arg, HashSet { JsonArray { JsonObject { "foo"->"hello" } }, JsonArray {  JsonObject { "bar"->"bye" } } });
}

shared test void testMethodWithHandlerAsyncResultSetJsonArray() {
  variable Set<JsonArray>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultSetJsonArray((Set<JsonArray>|Throwable arg_) => arg=arg_);
  assertEquals(arg, HashSet { JsonArray { "green", "blue" }, JsonArray { "yellow", "purple" } });
}

shared test void testMethodWithHandlerAsyncResultNullSetJsonArray() {
  // Todo
}

shared test void testMethodWithHandlerAsyncResultSetComplexJsonArray() {
  variable Set<JsonArray>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultSetComplexJsonArray((Set<JsonArray>|Throwable arg_) => arg=arg_);
  assertEquals(arg, HashSet { JsonArray { JsonObject { "foo"->"hello" } }, JsonArray {  JsonObject { "bar"->"bye" } } });
}

shared test void testMethodWithHandlerAsyncResultListDataObject() {
  variable List<TestDataObject>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultListDataObject((List<TestDataObject>|Throwable arg_) => arg=arg_);
  assert(is List<TestDataObject> list=arg);
  assertEquals(list.size, 2);
  value obj1 = list[0];
  assert(is TestDataObject obj1);
  assertEquals(obj1.foo, "String 1");
  assertEquals(obj1.bar, 1);
  assertFloatEquals(obj1.wibble, 1.1);
  value obj2 = list[1];
  assert(is TestDataObject obj2);
  assertEquals(obj2.foo, "String 2");
  assertEquals(obj2.bar, 2);
  assertFloatEquals(obj2.wibble, 2.2);
}

shared test void testMethodWithHandlerAsyncResultNullListDataObject() {
  // Null
}

shared test void testMethodWithHandlerAsyncResultSetDataObject() {
  variable Set<TestDataObject>|Throwable|Null arg = null;
  obj.methodWithHandlerAsyncResultSetDataObject((Set<TestDataObject>|Throwable arg_) => arg=arg_);
  assert(is Set<TestDataObject> val=arg);
  value set = val.sort(comparingTestDataObject);
  assertEquals(set.size, 2);
  value obj1 = set[0];
  assert(is TestDataObject obj1);
  assertEquals(obj1.foo, "String 1");
  assertEquals(obj1.bar, 1);
  assertFloatEquals(obj1.wibble, 1.1);
  value obj2 = set[1];
  assert(is TestDataObject obj2);
  assertEquals(obj2.foo, "String 2");
  assertEquals(obj2.bar, 2);
  assertFloatEquals(obj2.wibble, 2.2);
}

shared test void testMethodWithHandlerAsyncResultNullSetDataObject() {
  // Null
}

shared test void testMethodWithHandlerUserTypes() {
  variable RefedInterface1? val = null;
  obj.methodWithHandlerUserTypes((RefedInterface1 arg) => val=arg);
  assert(exists u=val);
  assertEquals(u.getString(), "echidnas");
}

shared test void testMethodWithHandlerAsyncResultUserTypes() {
  variable RefedInterface1|Throwable|Null val = null;
  obj.methodWithHandlerAsyncResultUserTypes((RefedInterface1|Throwable arg) => val=arg);
  assert(is RefedInterface1 u=val);
  assertEquals(u.getString(), "cheetahs");
}

shared test void testMethodWithConcreteHandlerUserTypesSubtype() {
  variable RefedInterface1? val = null;
  ConcreteHandlerUserType sub = Factory.createConcreteHandlerUserType((RefedInterface1 arg) => val=arg);
  obj.methodWithConcreteHandlerUserTypeSubtype(sub);
  assert(exists u=val);
  assertEquals(u.getString(), "echidnas");
}

shared test void testMethodWithAbstractHandlerUserTypesSubtype() {
  variable RefedInterface1? val = null;
  AbstractHandlerUserType sub = Factory.createAbstractHandlerUserType((RefedInterface1 arg) => val=arg);
  obj.methodWithAbstractHandlerUserTypeSubtype(sub);
  assert(exists u=val);
  assertEquals(u.getString(), "echidnas");
}

shared test void testMethodWithConcreteHandlerUserTypesSubtypeExtension() {
  variable RefedInterface1? val = null;
  ConcreteHandlerUserTypeExtension sub = Factory.createConcreteHandlerUserTypeExtension((RefedInterface1 arg) => val=arg);
  obj.methodWithConcreteHandlerUserTypeSubtypeExtension(sub);
  assert(exists u=val);
  assertEquals(u.getString(), "echidnas");
}

shared test void testMethodWithHandlerVoid() {
  variable Integer count = 0;
  obj.methodWithHandlerVoid(() => count++);
  assertEquals(count, 1);
}

shared test void testMethodWithHandlerAsyncResultVoid() {
  value arg = ArrayList<Throwable?>();
  obj.methodWithHandlerAsyncResultVoid(false, arg.add);
  assertEquals(arg.size, 1);
  assertEquals(arg[0], null);
}

shared test void testMethodWithHandlerAsyncResultVoidFails() {
  value arg = ArrayList<Throwable?>();
  obj.methodWithHandlerAsyncResultVoid(true, arg.add);
  assertEquals(arg.size, 1);
  assert(exists err=arg[0]);
  assertEquals(err.message, "foo!");
}

shared test void testMethodWithHandlerThrowable() {
  variable Throwable? err = null;
  obj.methodWithHandlerThrowable((Throwable arg) => err=arg);
  assert(exists val=err);
  assertEquals(val.message, "cheese!");
}

shared test void testMethodWithHandlerGenericUserType() {
  variable GenericRefedInterface<String>? gen = null;
  obj.methodWithHandlerGenericUserType<String>("string_value", (GenericRefedInterface<String> arg) => gen=arg);
  assert(is GenericRefedInterface<String> val=gen);
  assertEquals(val.\ivalue, "string_value");
}

shared test void testMethodWithHandlerAsyncResultGenericUserType() {
  variable GenericRefedInterface<String>|Throwable|Null gen = null;
  obj.methodWithHandlerGenericUserType<String>("string_value_2", (GenericRefedInterface<String>?|Throwable arg) => gen=arg);
  assert(is GenericRefedInterface<String> val=gen);
  assertEquals(val.\ivalue, "string_value_2");
}

shared test void testMethodWithGenericParam() {
  obj.methodWithGenericParam("String", "foo");
  obj.methodWithGenericParam("Boolean", true);
  obj.methodWithGenericParam("Byte", 123.byte);
  obj.methodWithGenericParam("Character", 'x');
  obj.methodWithGenericParam("Long", 1265615234);
  obj.methodWithGenericParam("Double", 12.34566);
  obj.methodWithGenericParam("Ref", RefedInterface1Impl().setString("bar"));
  obj.methodWithGenericParam("JsonObject", JsonObject { "foo"->"hello", "bar"->123 });
  obj.methodWithGenericParam("JsonObjectComplex", JsonObject { "outer" -> JsonObject { "foo"->"hello" }, "bar" -> JsonArray { "this", "that" } });
  obj.methodWithGenericParam("JsonArray", JsonArray { "foo", "bar", "wib" });
}

shared test void testMethodWithGenericHandler() {
  void test<T>(String type, T expected, void compare(Object actual, T expected) => assertEquals(actual, expected)) {
    variable Object? val = null;
    obj.methodWithGenericHandler<Object>(type, (Object? result) => val = result);
    assert(exists actual = val);
    compare(actual, expected);
  }
  test("Ref", RefedInterface1Impl().setString("bar"));
  test("String", "foo");
  test("JsonObject", JsonObject { "foo"->"hello", "bar"->123 });
  test("JsonObjectComplex", JsonObject { "outer" -> JsonObject { "foo"->"hello" }, "bar" -> JsonArray { "this", "that" } });
  test("JsonArray", JsonArray { "foo","bar","wib" });
  test("Boolean", true);
  test("Byte", 123.byte);
  test("Short", 12345);
  test("Integer", 1234567);
  test("Long", 1265615234);
  test("Character", 'x');
  test<Float>("Double", 12.34566, (expected, actual) => { assertFloatEquals(expected, actual) });
  test<Float>("Float", 12.345, (expected, actual) => { assertFloatEquals(expected, actual) });
}

shared test void testMethodWithGenericHandlerAsyncResult() {
  void test<T>(String type, T expected, void compare(Object actual, T expected) => assertEquals(actual, expected)) {
    variable Object|Throwable|Null val = null;
    obj.methodWithGenericHandlerAsyncResult<Object>(type, (Object?|Throwable result) => val = result);
    assert(exists actual = val);
    compare(actual, expected);
  }
  test("Ref", RefedInterface1Impl().setString("bar"));
  test("String", "foo");
  test("JsonObject", JsonObject { "foo"->"hello", "bar"->123 });
  test("JsonObjectComplex", JsonObject { "outer" -> JsonObject { "foo"->"hello" }, "bar" -> JsonArray { "this", "that" } });
  test("JsonArray", JsonArray { "foo","bar","wib" });
  test("Boolean", true);
  test("Byte", 123.byte);
  test("Short", 12345);
  test("Integer", 1234567);
  test("Long", 1265615234);
  test("Character", 'x');
  test<Float>("Double", 12.34566, (expected, actual) => { assertFloatEquals(expected, actual) });
  test<Float>("Float", 12.345, (expected, actual) => { assertFloatEquals(expected, actual) });
}

shared test void testMethodListParams() {
  value refed1 = RefedInterface1(RefedInterface1Impl().setString("foo"));
  value refed2 = RefedInterface1(RefedInterface1Impl().setString("bar"));
  obj.methodWithListParams(
    ArrayList { "foo", "bar" },
    ArrayList { 2.byte, 3.byte },
    ArrayList { 12, 13 },
    ArrayList { 1234, 1345 },
    ArrayList { 123, 456 },
    ArrayList { JsonObject { "foo"->"bar" }, JsonObject { "eek"->"wibble" } },
    ArrayList { JsonArray { "foo" }, JsonArray { "blah" } },
    ArrayList { refed1, refed2 },
    ArrayList { TestDataObject { foo="String 1"; bar=1; wibble=1.1; }, TestDataObject { foo="String 2"; bar=2; wibble=2.2; } },
    ArrayList { "JULIEN", "TIM" }
  );
}

shared test void testMethodSetParams() {
  value refed1 = RefedInterface1(RefedInterface1Impl().setString("foo"));
  value refed2 = RefedInterface1(RefedInterface1Impl().setString("bar"));
  obj.methodWithSetParams(
    HashSet { "foo", "bar" },
    HashSet { 2.byte, 3.byte },
    HashSet { 12, 13 },
    HashSet { 1234, 1345 },
    HashSet { 123, 456 },
    HashSet { JsonObject { "foo"->"bar" }, JsonObject { "eek"->"wibble" } },
    HashSet { JsonArray { "foo" }, JsonArray { "blah" } },
    HashSet { refed1, refed2 },
    HashSet { TestDataObject { foo="String 1"; bar=1; wibble=1.1; }, TestDataObject { foo="String 2"; bar=2; wibble=2.2; } },
    HashSet { "JULIEN", "TIM" }
  );
}

shared test void testMethodMapParams() {
  value refed1 = RefedInterface1(RefedInterface1Impl().setString("foo"));
  value refed2 = RefedInterface1(RefedInterface1Impl().setString("bar"));
  obj.methodWithMapParams(
    HashMap { "foo"->"bar", "eek"->"wibble" },
    HashMap { "foo"->2.byte, "eek"->3.byte },
    HashMap { "foo"->12, "eek"->13 },
    HashMap { "foo"->1234, "eek"->1345 },
    HashMap { "foo"->123, "eek"->456 },
    HashMap { "foo"->JsonObject { "foo"->"bar" }, "eek"->JsonObject { "eek"->"wibble" } },
    HashMap { "foo"->JsonArray { "foo" }, "eek"->JsonArray { "blah" } },
    HashMap { "foo"->refed1, "eek"->refed2 }
  );
}

shared test void testBasicReturns() {
  assertEquals(obj.methodWithByteReturn(), 123.byte);
  assertEquals(obj.methodWithShortReturn(), 12345);
  assertEquals(obj.methodWithIntReturn(), 12345464);
  assertEquals(obj.methodWithLongReturn(), 65675123);
  assertFloatEquals(obj.methodWithFloatReturn(), 1.23);
  assertFloatEquals(obj.methodWithDoubleReturn(), 3.34535);
  assertEquals(obj.methodWithBooleanReturn(), true);
  assertEquals(obj.methodWithCharReturn(), 'Y');
  assertEquals(obj.methodWithStringReturn(), "orangutan");
}

shared test void testVertxGenReturn() {
  value r = obj.methodWithVertxGenReturn();
  assertEquals(r.getString(), "chaffinch");
}

shared test void testVertxGenNullReturn() {
  // Todo
}

shared test void testVertxAbstractGenReturn() {
  value r = obj.methodWithAbstractVertxGenReturn();
  assertEquals(r.getString(), "abstractchaffinch");
}

shared test void testDataObjectReturn() {
  value r = obj.methodWithDataObjectReturn();
  assertEquals(r.foo, "foo");
  assertEquals(r.bar, 123);
}

shared test void testDataObjectNullReturn() {
  // Todo
}

shared test void testListStringReturn() {
  value list = obj.methodWithListStringReturn();
  assertEquals(list.size, 3);
  assertEquals(list[0], "foo");
  assertEquals(list[1], "bar");
  assertEquals(list[2], "wibble");
}

shared test void testListLongReturn() {
  value list = obj.methodWithListLongReturn();
  assertEquals(list.size, 2);
  assertEquals(list[0], 123);
  assertEquals(list[1], 456);
}

shared test void testListJsonObjectReturn() {
  value list = obj.methodWithListJsonObjectReturn();
  assertEquals(list.size, 2);
  assertEquals(list[0], JsonObject { "foo"->"bar" });
  assertEquals(list[1], JsonObject { "blah"->"eek" });
}

shared test void testListComplexJsonObjectReturn() {
  value list = obj.methodWithListComplexJsonObjectReturn();
  assertEquals(list.size, 1);
  assertEquals(list[0], JsonObject {
    "outer"->JsonObject {
      "socks"->"tartan"
    },
    "list"->JsonArray {
      "yellow",
      "blue"
    }
  });
}

shared test void testListJsonArrayReturn() {
  value list = obj.methodWithListJsonArrayReturn();
  assertEquals(list.size, 2);
  assertEquals(list[0], JsonArray { "foo" });
  assertEquals(list[1], JsonArray { "blah" });
}

shared test void testListComplexJsonArrayReturn() {
  value list = obj.methodWithListComplexJsonArrayReturn();
  assertEquals(list.size, 2);
  assertEquals(list[0], JsonArray { JsonObject { "foo"->"hello" } });
  assertEquals(list[1], JsonArray { JsonObject { "bar"->"bye" } });
}

shared test void testListVertxGenReturn() {
  value list = obj.methodWithListVertxGenReturn();
  assertEquals(list.size, 2);
  value refed1 = list[0];
  assert(is RefedInterface1 refed1);
  assertEquals(refed1.getString(), "foo");
  value refed2 = list[1];
  assert(is RefedInterface1 refed2);
  assertEquals(refed2.getString(), "bar");
}

shared test void testListDataObjectReturn() {
  value list = obj.methodWithListDataObjectReturn();
  assertEquals(list.size, 2);
  value obj1 = list[0];
  assert(is TestDataObject obj1);
  assertEquals(obj1.foo, "String 1");
  assertEquals(obj1.bar, 1);
  assertFloatEquals(obj1.wibble, 1.1);
  value obj2 = list[1];
  assert(is TestDataObject obj2);
  assertEquals(obj2.foo, "String 2");
  assertEquals(obj2.bar, 2);
  assertFloatEquals(obj2.wibble, 2.2);
}

shared test void testSetStringReturn() {
  value set = obj.methodWithSetStringReturn();
  assertEquals(set.size, 3);
  assertTrue(set.contains("foo"));
  assertTrue(set.contains("bar"));
  assertTrue(set.contains("wibble"));
}

shared test void testSetLongReturn() {
  value set = obj.methodWithSetLongReturn();
  assertEquals(set.size, 2);
  assertTrue(set.contains(123));
  assertTrue(set.contains(456));
}

shared test void testSetJsonObjectReturn() {
  value set = obj.methodWithSetJsonObjectReturn();
  assertEquals(set.size, 2);
  assertTrue(set.contains(JsonObject { "foo"->"bar" }));
  assertTrue(set.contains(JsonObject { "blah"->"eek" }));
}

shared test void testSetComplexJsonObjectReturn() {
  value set = obj.methodWithSetComplexJsonObjectReturn();
  assertEquals(set.size, 1);
  assertTrue(set.contains(JsonObject {
    "outer"->JsonObject { "socks"->"tartan" },
    "list"->JsonArray { "yellow", "blue" }
    }));
}

shared test void testSetJsonArrayReturn() {
  value set = obj.methodWithSetJsonArrayReturn();
  assertEquals(set.size, 2);
  assertTrue(set.contains(JsonArray { "foo" }));
  assertTrue(set.contains(JsonArray { "blah" }));
}

shared test void testSetComplexJsonArrayReturn() {
  value set = obj.methodWithSetComplexJsonArrayReturn();
  assertEquals(set.size, 2);
  assertTrue(set.contains(JsonArray { JsonObject { "foo"->"hello" }}));
  assertTrue(set.contains(JsonArray { JsonObject { "bar"->"bye" } }));
}

shared test void testSetVertxGenReturn() {
  value set = obj.methodWithSetVertxGenReturn().sort(comparingRefedInterface1);
  assertEquals(set.size, 2);
  assert(is RefedInterface1 f=set[0]);
  assert(is RefedInterface1 l=set[1]);
  assertEquals(f.getString(), "bar");
  assertEquals(l.getString(), "foo");
}

shared test void testSetDataObjectReturn() {
  value set = obj.methodWithSetDataObjectReturn().sort(comparingTestDataObject);
  assertEquals(set.size, 2);
  assert(is TestDataObject f=set[0]);
  assert(is TestDataObject l=set[1]);
  JsonObject j1 = f.toJson();
  JsonObject j2 = l.toJson();
  assertEquals(j1["foo"], "String 1");
  assertEquals(j1["bar"], 1);
  assertFloatEquals(j1["wibble"], 1.1);
  assertEquals(j2["foo"], "String 2");
  assertEquals(j2["bar"], 2);
  assertFloatEquals(j2["wibble"], 2.2);
}

shared test void testMapStringReturn() {
  Map<String, String> map = obj.methodWithMapStringReturn((String arg) => {});
  assertEquals(map, HashMap { "foo"->"bar" });
}

shared test void testMapLongReturn() {
  Map<String, Integer> map = obj.methodWithMapLongReturn((String arg) => {});
  assertEquals(map, HashMap { "foo"->123 });
}

shared test void testMapJsonObjectReturn() {
  Map<String, JsonObject> map = obj.methodWithMapJsonObjectReturn((String arg) => {});
  assertEquals(map, HashMap { "foo"->JsonObject { "wibble"->"eek" } });
}

shared test void testMapComplexJsonObjectReturn() {
  Map<String, JsonObject> map = obj.methodWithMapComplexJsonObjectReturn((String arg) => {});
  assertEquals(map, HashMap { "foo"->JsonObject { "outer"->JsonObject { "socks"->"tartan" }, "list"->JsonArray { "yellow", "blue" } } });
}

shared test void testMapJsonArrayReturn() {
  Map<String, JsonArray> map = obj.methodWithMapJsonArrayReturn((String arg) => {});
  assertEquals(map, HashMap { "foo"->JsonArray { "wibble" } });
}

shared test void testMapComplexJsonArrayReturn() {
  Map<String, JsonArray> map = obj.methodWithMapComplexJsonArrayReturn((String arg) => {});
  assertEquals(map, HashMap { "foo"->JsonArray { JsonObject { "foo"->"hello" }, JsonObject { "bar"->"bye" } } });
}

shared test void testOverloadedMethods() {
  value refed = RefedInterface1(RefedInterface1Impl());
  refed.setString("dog");
  assertEquals(obj.overloadedMethod("cat", refed), "meth1");
  variable String? callback = null;
  assertEquals(obj.overloadedMethod("cat", refed, 12345, (String arg) => callback = arg), "meth2");
  assertEquals(callback, "giraffe");
  callback = null;
  assertEquals(obj.overloadedMethod("cat", (String arg) => callback = arg), "meth3");
  assertEquals(callback, "giraffe");
  callback = null;
  assertEquals(obj.overloadedMethod("cat", refed, (String arg) => callback = arg), "meth4");
  assertEquals(callback, "giraffe");
}

shared test void testSuperInterfaces() {
  obj.superMethodWithBasicParams(123.byte, 12345, 1234567, 1265615234, 12.345, 12.34566, true, 'X', "foobar");
  obj.otherSuperMethodWithBasicParams(123.byte, 12345, 1234567, 1265615234, 12.345, 12.34566, true, 'X', "foobar");
}

shared test void testMethodWithGenericReturn() {
  value jsonObject = obj.methodWithGenericReturn<JsonObject>("JsonObject");
  assertEquals(jsonObject, JsonObject { "foo"->"hello", "bar"->123 });
  value jsonArray = obj.methodWithGenericReturn<JsonArray>("JsonArray");
  assertEquals(jsonArray, JsonArray { "foo", "bar", "wib" });
}

shared test void testFluentMethod() {
  value ret = obj.fluentMethod("bar");
  assertEquals(ret, obj);
}

shared test void testStaticFactoryMethod() {
  value ret = TestInterface.staticFactoryMethod("bar");
  assertEquals(ret.getString(), "bar");
}

shared test void testMethodWithCachedReturn() {
  value ret1 = obj.methodWithCachedReturn("bar");
  assertEquals(ret1.getString(), "bar");
  value ret2 = obj.methodWithCachedReturn("bar");
  assertEquals(ret2, ret1);
  value ret3 = obj.methodWithCachedReturn("bar");
  assertEquals(ret3, ret1);
}

shared test void testJsonReturns() {
  assertEquals(obj.methodWithJsonObjectReturn(), JsonObject { "cheese"->"stilton" });
  assertEquals(obj.methodWithJsonArrayReturn(), JsonArray { "socks", "shoes" });
}

shared test void testNullJsonReturns() {
  // Todo
}

shared test void testComplexJsonReturns() {
  assertEquals(obj.methodWithComplexJsonObjectReturn(), JsonObject { "outer"->JsonObject { "socks"->"tartan" }, "list"->JsonArray { "yellow", "blue" } });
  assertEquals(obj.methodWithComplexJsonArrayReturn(), JsonArray { JsonObject { "foo"->"hello" }, JsonObject { "bar"->"bye" } });
}

shared test void testJsonParams() {
  obj.methodWithJsonParams(JsonObject { "cat"->"lion", "cheese"->"cheddar" }, JsonArray { "house", "spider" });
}

shared test void testNullJsonParams() {
  // Todo
}

shared test void testJsonHandlerParams() {
  variable JsonObject? jsonObject = null;
  variable JsonArray? jsonArray = null;
  obj.methodWithHandlerJson((JsonObject arg) => jsonObject = arg, (JsonArray arg) => jsonArray = arg);
  assertEquals(jsonObject, JsonObject { "cheese"->"stilton" });
  assertEquals(jsonArray, JsonArray { "socks", "shoes" });
}

shared test void testNullJsonHandlerParams() {
  // Todo
}

shared test void testComplexJsonHandlerParams() {
  variable JsonObject? jsonObject = null;
  variable JsonArray? jsonArray = null;
  obj.methodWithHandlerComplexJson((JsonObject arg) => jsonObject = arg, (JsonArray arg) => jsonArray = arg);
  assertEquals(jsonObject, JsonObject { "outer" -> JsonObject { "socks"->"tartan" }, "list" -> JsonArray { "yellow", "blue" } });
  assertEquals(jsonArray, JsonArray { JsonArray { JsonObject { "foo"->"hello" } }, JsonArray {  JsonObject { "bar"->"bye" } } });
}

shared test void testJsonHandlerAsyncResultParams() {
  variable JsonObject|Throwable|Null jsonObject = null;
  variable JsonArray|Throwable|Null jsonArray = null;
  obj.methodWithHandlerAsyncResultJsonObject((JsonObject|Throwable arg) => jsonObject = arg);
  obj.methodWithHandlerAsyncResultJsonArray((JsonArray|Throwable arg) => jsonArray = arg);
  assertEquals(jsonObject, JsonObject { "cheese"->"stilton" });
  assertEquals(jsonArray, JsonArray { "socks", "shoes" });
}

shared test void testNullJsonHandlerAsyncResultParams() {
  // Todo
}

shared test void testComplexJsonHandlerAsyncResultParams() {
  variable JsonObject|Throwable|Null jsonObject = null;
  variable JsonArray|Throwable|Null jsonArray = null;
  obj.methodWithHandlerAsyncResultComplexJsonObject((JsonObject|Throwable arg) => jsonObject = arg);
  obj.methodWithHandlerAsyncResultComplexJsonArray((JsonArray|Throwable arg) => jsonArray = arg);
  assertEquals(jsonObject, JsonObject { "outer" -> JsonObject { "socks"->"tartan" }, "list" -> JsonArray { "yellow", "blue" } });
  assertEquals(jsonArray, JsonArray { JsonObject { "foo"->"hello" },  JsonObject { "bar"->"bye" } });
}

shared test void testMethodWithEnumParam() {
  value ret = obj.methodWithEnumParam("cabbages", "JULIEN");
  assertEquals(ret, "cabbagesJULIEN");
}

shared test void testMethodWithGenEnumParam() {
  value ret = obj.methodWithGenEnumParam("cabbages", \iMIKE);
  assertEquals(ret, "cabbagesMIKE");
}

shared test void testMethodWithEnumReturn() {
  value ret = obj.methodWithEnumReturn("JULIEN");
  assertEquals(ret, "JULIEN");
}

shared test void testMethodWithGenEnumReturn() {
  value ret = obj.methodWithGenEnumReturn("BOB");
  assertEquals(ret, \iBOB);
}

shared test void testNullableByte() => testNullable(67.byte, nullableTCK.methodWithNullableByteParam, nullableTCK.methodWithNullableByteHandler, nullableTCK.methodWithNullableByteHandlerAsyncResult, nullableTCK.methodWithNullableByteReturn);
shared test void testNullableShort() => testNullable(1024, nullableTCK.methodWithNullableShortParam, nullableTCK.methodWithNullableShortHandler, nullableTCK.methodWithNullableShortHandlerAsyncResult, nullableTCK.methodWithNullableShortReturn);
shared test void testNullableInteger() => testNullable(1234567, nullableTCK.methodWithNullableIntegerParam, nullableTCK.methodWithNullableIntegerHandler, nullableTCK.methodWithNullableIntegerHandlerAsyncResult, nullableTCK.methodWithNullableIntegerReturn);
shared test void testNullableLong() => testNullable(9876543210, nullableTCK.methodWithNullableLongParam, nullableTCK.methodWithNullableLongHandler, nullableTCK.methodWithNullableLongHandlerAsyncResult, nullableTCK.methodWithNullableLongReturn);
shared test void testNullableFloat() => testNullable(3.14, nullableTCK.methodWithNullableFloatParam, nullableTCK.methodWithNullableFloatHandler, nullableTCK.methodWithNullableFloatHandlerAsyncResult, nullableTCK.methodWithNullableFloatReturn, assertFloatEquals);
shared test void testNullableDouble() => testNullable(3.1415926, nullableTCK.methodWithNullableDoubleParam, nullableTCK.methodWithNullableDoubleHandler, nullableTCK.methodWithNullableDoubleHandlerAsyncResult, nullableTCK.methodWithNullableDoubleReturn);
shared test void testNullableBoolean() => testNullable(true, nullableTCK.methodWithNullableBooleanParam, nullableTCK.methodWithNullableBooleanHandler, nullableTCK.methodWithNullableBooleanHandlerAsyncResult, nullableTCK.methodWithNullableBooleanReturn);
shared test void testNullableString() => testNullable("the_string_value", nullableTCK.methodWithNullableStringParam, nullableTCK.methodWithNullableStringHandler, nullableTCK.methodWithNullableStringHandlerAsyncResult, nullableTCK.methodWithNullableStringReturn);
shared test void testNullableChar() => testNullable('f', nullableTCK.methodWithNullableCharParam, nullableTCK.methodWithNullableCharHandler, nullableTCK.methodWithNullableCharHandlerAsyncResult, nullableTCK.methodWithNullableCharReturn);
shared test void testNullableJsonObject() => testNullable(JsonObject { "foo"->"wibble", "bar"->3 }, nullableTCK.methodWithNullableJsonObjectParam, nullableTCK.methodWithNullableJsonObjectHandler, nullableTCK.methodWithNullableJsonObjectHandlerAsyncResult, nullableTCK.methodWithNullableJsonObjectReturn);
shared test void testNullableJsonArray() => testNullable(JsonArray { "one","two","three" }, nullableTCK.methodWithNullableJsonArrayParam, nullableTCK.methodWithNullableJsonArrayHandler, nullableTCK.methodWithNullableJsonArrayHandlerAsyncResult, nullableTCK.methodWithNullableJsonArrayReturn);
shared test void testNullableApi() => testNullable(RefedInterface1(RefedInterface1Impl().setString("lovely_dae")), nullableTCK.methodWithNullableApiParam, nullableTCK.methodWithNullableApiHandler, nullableTCK.methodWithNullableApiHandlerAsyncResult, nullableTCK.methodWithNullableApiReturn, assertRefedInterface1Equals);
shared test void testNullableDataObject() => testNullable(TestDataObject { foo="foo_value"; bar=12345; wibble=3.5; }, nullableTCK.methodWithNullableDataObjectParam, nullableTCK.methodWithNullableDataObjectHandler, nullableTCK.methodWithNullableDataObjectHandlerAsyncResult, nullableTCK.methodWithNullableDataObjectReturn, assertTestDataObjectEquals);
shared test void testNullableEnum() => testNullable("TIM", nullableTCK.methodWithNullableEnumParam, nullableTCK.methodWithNullableEnumHandler, nullableTCK.methodWithNullableEnumHandlerAsyncResult, nullableTCK.methodWithNullableEnumReturn);

shared void testNullable<T>(
    T expected,
    Anything(Boolean,T?) nullableParamFunction,
    Anything(Boolean,Anything(T?)) nullableHandlerFunction,
    Anything(Boolean,Anything(T?|Throwable)) nullableHandlerAsyncResultFunction,
    T?(Boolean) nullableReturnFunction,
    Anything(Anything,Anything) check = assertEquals
    ) {
  nullableParamFunction(true, null);
  nullableParamFunction(false, expected);
  variable Integer count = 0;
  void a(T? val) {
    assertNull(val);
    count++;
  }
  nullableHandlerFunction(false, a);
  void b(T? val) {
    check(expected, val);
    count++;
  }
  nullableHandlerFunction(true, b);
  void c(T?|Throwable val) {
    assertNull(val);
    count++;
  }
  nullableHandlerAsyncResultFunction(false, c);
  void d(T?|Throwable val) {
    check(expected, val);
    count++;
  }
  nullableHandlerAsyncResultFunction(true, d);
  assertNull(nullableReturnFunction(false));
  check(expected, nullableReturnFunction(true));
  assertEquals(4, count);
}

shared void testGenericNullableTypeVariable() {
  nullableTCK.methodWithNullableTypeVariableParam(false, "whatever");
  nullableTCK.methodWithNullableTypeVariableParam(true, null);
  variable Integer count = 0;
  void a(String? val) {
    assertEquals("wibble", val);
    count++;
  }
  nullableTCK.methodWithNullableTypeVariableHandler<String>(true, "wibble", a);
  void b(String? val) {
    assertNull(val);
    count++;
  }
  nullableTCK.methodWithNullableTypeVariableHandler<String>(true, null, b);
  void c(String?|Throwable val) {
    assertEquals("sausages", val);
    count++;
  }
  nullableTCK.methodWithNullableTypeVariableHandlerAsyncResult<String>(true, "sausages", c);
  void d(String?|Throwable val) {
    assertNull(val);
    count++;
  }
  nullableTCK.methodWithNullableTypeVariableHandlerAsyncResult<String>(true, null, d);
  assertEquals("fizz1", nullableTCK.methodWithNullableTypeVariableReturn<String>(true, "fizz1"));
  assertNull(nullableTCK.methodWithNullableTypeVariableReturn(false, "fizz2"));
  assertEquals(4, count);
}

/*
shared test void testMethodWithNullableStringParam() {
  testNullable.methodWithNullableStringParam(null, true);
  testNullable.methodWithNullableStringParam("abc", false);
}

shared test void testMethodWithNullableStringHandler() {
  variable String? ret = null;
  void callback(String? val) {
    ret = val;
  }
  testNullable.methodWithNullableStringHandler(true, callback);
  assertEquals("the_string_value", ret);
  testNullable.methodWithNullableStringHandler(false, callback);
  assertNull(ret);
}

shared test void testMethodWithNullableStringHandlerAsyncResult() {
  variable Throwable|String? ret = null;
  void callback(Throwable|String? val) {
    ret = val;
  }
  testNullable.methodWithNullableStringHandlerAsyncResult(true, callback);
  assertEquals("the_string_value", ret);
  testNullable.methodWithNullableStringHandlerAsyncResult(false, callback);
  assertNull(ret);
}

shared test void testMethodWithNullableStringReturn() {
  String? ret1 = testNullable.methodWithNullableStringReturn(true);
  assertEquals("the_string_value", ret1);
  String? ret2 = testNullable.methodWithNullableStringReturn(false);
  assertNull(ret2);
}

shared test void testMethodWithListNullableStringParam() {
  testNullable.methodWithListNullableStringParam(ArrayList { "first", null, "third" });
}

shared test void testMethodWithListNullableStringHandler() {
  variable Integer count = 0;
  void f(List<String?> l) {
    assertEquals(l, ArrayList { "first", null, "third" });
    count++;
  }
  testNullable.methodWithListNullableStringHandler(f);
  assertEquals(count, 1);
}

shared test void testMethodWithListNullableStringHandlerAsyncResult() {
  variable Integer count = 0;
  void f(Throwable|List<String?> l) {
    assert(is List<String?> l);
    assertEquals(l, ArrayList { "first", null, "third" });
    count++;
  }
  testNullable.methodWithListNullableStringHandler(f);
  assertEquals(count, 1);
}

shared test void testMethodWithListNullableStringReturn() {
  assertEquals(testNullable.methodWithListNullableStringReturn(), ArrayList { "first", null, "third" });
}

shared test void testMethodWithNullableHandler() {
  testNullable.methodWithNullableHandler(null, true);
  variable Integer count = 0;
  void f(String s) {
    assertEquals(s, "the_string_value");
    count++;
  }
  testNullable.methodWithNullableHandler(f, false);
  assertEquals(count, 1);
}

shared test void testMethodWithNullableHandlerAsyncResult() {
  testNullable.methodWithNullableHandlerAsyncResult(null, true);
  variable Integer count = 0;
  void f(Throwable|String s) {
    assertEquals(s, "the_string_value");
    count++;
  }
  testNullable.methodWithNullableHandlerAsyncResult(f, false);
  assertEquals(count, 1);
}

shared test void testMethodWithGenericNullableStringParam() {
  GenericRefedInterface<String?> refed = testNullable.methodWithGenericNullableStringReturn<String>();
  testNullable.methodWithGenericNullableStringParam("wibble", refed);
}

shared test void methodWithGenericNullableStringHandler() {
  variable Integer count = 0;
  void f(GenericRefedInterface<String?> refed) {
    refed.\ivalue = "wibble";
    assertEquals(refed.\ivalue, "wibble");
    refed.\ivalue = null;
    assertEquals(refed.\ivalue, null);
    count++;
  }
  testNullable.methodWithGenericNullableStringHandler<String>(f);
  assertEquals(count, 1);
}

shared test void methodWithGenericNullableStringHandlerAsyncResult() {
  variable Integer count = 0;
  void f(Throwable|GenericRefedInterface<String?> refed) {
    assert(is GenericRefedInterface<String?> refed);
    refed.\ivalue = "wibble";
    assertEquals(refed.\ivalue, "wibble");
    refed.\ivalue = null;
    assertEquals(refed.\ivalue, null);
    count++;
  }
  testNullable.methodWithGenericNullableStringHandler<String>(f);
  assertEquals(count, 1);
}

shared test void testMethodWithGenericNullableStringReturn() {
  GenericRefedInterface<String?> refed = testNullable.methodWithGenericNullableStringReturn<String>();
  refed.\ivalue = "wibble";
  assertEquals(refed.\ivalue, "wibble");
  refed.\ivalue = null;
  assertEquals(refed.\ivalue, null);
}
*/

void assertTestDataObjectEquals(Anything actual, Anything expected) {
  assert(is TestDataObject actual);
  assert(is TestDataObject expected);
  assertEquals(actual.foo, expected.foo);
  assertEquals(actual.bar, expected.bar);
  assertEquals(actual.wibble, expected.wibble);
}

void assertRefedInterface1Equals(Anything actual, Anything expected) {
  assert(is RefedInterface1 actual);
  assert(is RefedInterface1 expected);
  assertEquals(actual.getString(), expected.getString());
}

void assertFloatEquals(Anything actual, Anything expected) {
  assert(is Float actual);
  assert(is Float expected);
  variable value diff = expected - actual;
  if (diff < 0.float) {
    diff -= diff;
  }
  assertTrue(diff < 0.0001);
}