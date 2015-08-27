package com.acme.ceylon.pkg;

import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import com.acme.ceylon.pkg.sub.SubInterface;
import io.vertx.ceylon.codegen.testmodel.TestInterface;

@DocAnnotation$annotation$(description = "todo")
public class MyInterface {

  private final com.acme.pkg.MyInterface delegate;

  public MyInterface(com.acme.pkg.MyInterface delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("com.acme.ceylon.pkg::MyInterface")
  public static MyInterface create() {
    MyInterface ret = new com.acme.ceylon.pkg.MyInterface(com.acme.pkg.MyInterface.create());
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("com.acme.ceylon.pkg.sub::SubInterface")
  public SubInterface sub() {
    SubInterface ret = new com.acme.ceylon.pkg.sub.SubInterface(delegate.sub());
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.codegen.testmodel::TestInterface")
  public TestInterface method() {
    TestInterface ret = new io.vertx.ceylon.codegen.testmodel.TestInterface(delegate.method());
    return ret;
  }

}
