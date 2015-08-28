package io.vertx.ceylon.codegen.testmodel;

import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;

@TypeParameters({
  @TypeParameter(value="T",variance=Variance.NONE)
})
@DocAnnotation$annotation$(description = "todo")
public class GenericRefedInterface<T> {

  public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(GenericRefedInterface.class);

  public static final io.vertx.lang.ceylon.Converter<io.vertx.codegen.testmodel.GenericRefedInterface, GenericRefedInterface> TO_CEYLON = new io.vertx.lang.ceylon.Converter<io.vertx.codegen.testmodel.GenericRefedInterface, GenericRefedInterface>() {
    public GenericRefedInterface convert(io.vertx.codegen.testmodel.GenericRefedInterface src) {
      return new GenericRefedInterface(src);
    }
  };

  @Ignore
  private final io.vertx.codegen.testmodel.GenericRefedInterface delegate;

  public GenericRefedInterface(io.vertx.codegen.testmodel.GenericRefedInterface delegate) {
    this.delegate = delegate;
  }

  @Ignore
  public Object getDelegate() {
    return delegate;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::Anything")
  public void setValue(
    final @TypeInfo("T") @DocAnnotation$annotation$(description = "todo") T value) {
    java.lang.Object arg_0 = null;
    delegate.setValue(arg_0);
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("T")
  public T getValue() {
    T ret = null;
    return ret;
  }

}