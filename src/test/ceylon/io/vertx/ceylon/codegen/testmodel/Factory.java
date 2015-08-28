package io.vertx.ceylon.codegen.testmodel;

import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import io.vertx.core.Handler;

@DocAnnotation$annotation$(description = "todo")
public class Factory {

  public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(Factory.class);

  public static final io.vertx.lang.ceylon.Converter<io.vertx.codegen.testmodel.Factory, Factory> TO_CEYLON = new io.vertx.lang.ceylon.Converter<io.vertx.codegen.testmodel.Factory, Factory>() {
    public Factory convert(io.vertx.codegen.testmodel.Factory src) {
      return new Factory(src);
    }
  };

  @Ignore
  private final io.vertx.codegen.testmodel.Factory delegate;

  public Factory(io.vertx.codegen.testmodel.Factory delegate) {
    this.delegate = delegate;
  }

  @Ignore
  public Object getDelegate() {
    return delegate;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.codegen.testmodel::ConcreteHandlerUserType")
  public static ConcreteHandlerUserType createConcreteHandlerUserType(
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.codegen.testmodel::RefedInterface1)") @DocAnnotation$annotation$(description = "todo") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.codegen.testmodel.RefedInterface1> arg_0 = new io.vertx.core.Handler<io.vertx.codegen.testmodel.RefedInterface1>() { public void handle(io.vertx.codegen.testmodel.RefedInterface1 event) { handler.$call$((Object)new io.vertx.ceylon.codegen.testmodel.RefedInterface1(event)); } };
    ConcreteHandlerUserType ret = new io.vertx.ceylon.codegen.testmodel.ConcreteHandlerUserType(io.vertx.codegen.testmodel.Factory.createConcreteHandlerUserType(arg_0));
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.codegen.testmodel::AbstractHandlerUserType")
  public static AbstractHandlerUserType createAbstractHandlerUserType(
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.codegen.testmodel::RefedInterface1)") @DocAnnotation$annotation$(description = "todo") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.codegen.testmodel.RefedInterface1> arg_0 = new io.vertx.core.Handler<io.vertx.codegen.testmodel.RefedInterface1>() { public void handle(io.vertx.codegen.testmodel.RefedInterface1 event) { handler.$call$((Object)new io.vertx.ceylon.codegen.testmodel.RefedInterface1(event)); } };
    AbstractHandlerUserType ret = new io.vertx.ceylon.codegen.testmodel.AbstractHandlerUserType.Impl(io.vertx.codegen.testmodel.Factory.createAbstractHandlerUserType(arg_0));
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.codegen.testmodel::ConcreteHandlerUserTypeExtension")
  public static ConcreteHandlerUserTypeExtension createConcreteHandlerUserTypeExtension(
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.codegen.testmodel::RefedInterface1)") @DocAnnotation$annotation$(description = "todo") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.codegen.testmodel.RefedInterface1> arg_0 = new io.vertx.core.Handler<io.vertx.codegen.testmodel.RefedInterface1>() { public void handle(io.vertx.codegen.testmodel.RefedInterface1 event) { handler.$call$((Object)new io.vertx.ceylon.codegen.testmodel.RefedInterface1(event)); } };
    ConcreteHandlerUserTypeExtension ret = new io.vertx.ceylon.codegen.testmodel.ConcreteHandlerUserTypeExtension(io.vertx.codegen.testmodel.Factory.createConcreteHandlerUserTypeExtension(arg_0));
    return ret;
  }

}