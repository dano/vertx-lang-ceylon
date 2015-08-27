package io.vertx.ceylon.core.parsetools;

import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import io.vertx.ceylon.core.buffer.Buffer;
import io.vertx.core.Handler;

@DocAnnotation$annotation$(description = "todo")
public class RecordParser {

  private final io.vertx.core.parsetools.RecordParser delegate;

  public RecordParser(io.vertx.core.parsetools.RecordParser delegate) {
    this.delegate = delegate;
  }

  public Object getDelegate() {
    return delegate;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::Anything")
  public void setOutput(
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.core.buffer::Buffer)") @DocAnnotation$annotation$(description = "todo") Callable<?> output) {
    io.vertx.core.Handler<io.vertx.core.buffer.Buffer> arg_0 = new io.vertx.core.Handler<io.vertx.core.buffer.Buffer>() { public void handle(io.vertx.core.buffer.Buffer event) { output.$call$((Object)new io.vertx.ceylon.core.buffer.Buffer(event)); } };
    delegate.setOutput(arg_0);
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.parsetools::RecordParser")
  public static RecordParser newDelimited(
    final @TypeInfo("ceylon.language::String") @DocAnnotation$annotation$(description = "todo") ceylon.language.String delim, 
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.core.buffer::Buffer)") @DocAnnotation$annotation$(description = "todo") Callable<?> output) {
    java.lang.String arg_0 = delim.toString();
    io.vertx.core.Handler<io.vertx.core.buffer.Buffer> arg_1 = new io.vertx.core.Handler<io.vertx.core.buffer.Buffer>() { public void handle(io.vertx.core.buffer.Buffer event) { output.$call$((Object)new io.vertx.ceylon.core.buffer.Buffer(event)); } };
    RecordParser ret = new io.vertx.ceylon.core.parsetools.RecordParser(io.vertx.core.parsetools.RecordParser.newDelimited(arg_0, arg_1));
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.parsetools::RecordParser")
  public static RecordParser newDelimited(
    final @TypeInfo("io.vertx.ceylon.core.buffer::Buffer") @DocAnnotation$annotation$(description = "todo") Buffer delim, 
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.core.buffer::Buffer)") @DocAnnotation$annotation$(description = "todo") Callable<?> output) {
    io.vertx.core.buffer.Buffer arg_0 = (io.vertx.core.buffer.Buffer)delim.getDelegate();
    io.vertx.core.Handler<io.vertx.core.buffer.Buffer> arg_1 = new io.vertx.core.Handler<io.vertx.core.buffer.Buffer>() { public void handle(io.vertx.core.buffer.Buffer event) { output.$call$((Object)new io.vertx.ceylon.core.buffer.Buffer(event)); } };
    RecordParser ret = new io.vertx.ceylon.core.parsetools.RecordParser(io.vertx.core.parsetools.RecordParser.newDelimited(arg_0, arg_1));
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.parsetools::RecordParser")
  public static RecordParser newFixed(
    final @TypeInfo("ceylon.language::Integer") @DocAnnotation$annotation$(description = "todo") long size, 
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.core.buffer::Buffer)") @DocAnnotation$annotation$(description = "todo") Callable<?> output) {
    int arg_0 = (int)size;
    io.vertx.core.Handler<io.vertx.core.buffer.Buffer> arg_1 = new io.vertx.core.Handler<io.vertx.core.buffer.Buffer>() { public void handle(io.vertx.core.buffer.Buffer event) { output.$call$((Object)new io.vertx.ceylon.core.buffer.Buffer(event)); } };
    RecordParser ret = new io.vertx.ceylon.core.parsetools.RecordParser(io.vertx.core.parsetools.RecordParser.newFixed(arg_0, arg_1));
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::Anything")
  public void delimitedMode(
    final @TypeInfo("ceylon.language::String") @DocAnnotation$annotation$(description = "todo") ceylon.language.String delim) {
    java.lang.String arg_0 = delim.toString();
    delegate.delimitedMode(arg_0);
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::Anything")
  public void delimitedMode(
    final @TypeInfo("io.vertx.ceylon.core.buffer::Buffer") @DocAnnotation$annotation$(description = "todo") Buffer delim) {
    io.vertx.core.buffer.Buffer arg_0 = (io.vertx.core.buffer.Buffer)delim.getDelegate();
    delegate.delimitedMode(arg_0);
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::Anything")
  public void fixedSizeMode(
    final @TypeInfo("ceylon.language::Integer") @DocAnnotation$annotation$(description = "todo") long size) {
    int arg_0 = (int)size;
    delegate.fixedSizeMode(arg_0);
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::Anything")
  public void handle(
    final @TypeInfo("io.vertx.ceylon.core.buffer::Buffer") @DocAnnotation$annotation$(description = "todo") Buffer buffer) {
    io.vertx.core.buffer.Buffer arg_0 = (io.vertx.core.buffer.Buffer)buffer.getDelegate();
    delegate.handle(arg_0);
  }

}
