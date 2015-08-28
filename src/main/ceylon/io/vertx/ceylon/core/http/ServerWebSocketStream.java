package io.vertx.ceylon.core.http;

import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import io.vertx.ceylon.core.streams.ReadStream;
import io.vertx.core.Handler;

@DocAnnotation$annotation$(description = "todo")
public class ServerWebSocketStream implements ReadStream<ServerWebSocket> {

  public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(ServerWebSocketStream.class);

  public static final io.vertx.lang.ceylon.Converter<io.vertx.core.http.ServerWebSocketStream, ServerWebSocketStream> TO_CEYLON = new io.vertx.lang.ceylon.Converter<io.vertx.core.http.ServerWebSocketStream, ServerWebSocketStream>() {
    public ServerWebSocketStream convert(io.vertx.core.http.ServerWebSocketStream src) {
      return new ServerWebSocketStream(src);
    }
  };

  @Ignore
  private final io.vertx.core.http.ServerWebSocketStream delegate;

  public ServerWebSocketStream(io.vertx.core.http.ServerWebSocketStream delegate) {
    this.delegate = delegate;
  }

  @Ignore
  public Object getDelegate() {
    return delegate;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::ServerWebSocketStream")
  public ServerWebSocketStream exceptionHandler(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable)") @DocAnnotation$annotation$(description = "todo") Callable<?> handler) {
    io.vertx.core.Handler<java.lang.Throwable> arg_0 = new io.vertx.core.Handler<java.lang.Throwable>() { public void handle(java.lang.Throwable event) { handler.$call$((Object)event); } };
    ServerWebSocketStream ret = new io.vertx.ceylon.core.http.ServerWebSocketStream(delegate.exceptionHandler(arg_0));
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::ServerWebSocketStream")
  public ServerWebSocketStream handler(
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.core.http::ServerWebSocket)") @DocAnnotation$annotation$(description = "todo") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.http.ServerWebSocket> arg_0 = new io.vertx.core.Handler<io.vertx.core.http.ServerWebSocket>() { public void handle(io.vertx.core.http.ServerWebSocket event) { handler.$call$((Object)new io.vertx.ceylon.core.http.ServerWebSocket(event)); } };
    ServerWebSocketStream ret = new io.vertx.ceylon.core.http.ServerWebSocketStream(delegate.handler(arg_0));
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::ServerWebSocketStream")
  public ServerWebSocketStream pause() {
    ServerWebSocketStream ret = new io.vertx.ceylon.core.http.ServerWebSocketStream(delegate.pause());
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::ServerWebSocketStream")
  public ServerWebSocketStream resume() {
    ServerWebSocketStream ret = new io.vertx.ceylon.core.http.ServerWebSocketStream(delegate.resume());
    return ret;
  }

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::ServerWebSocketStream")
  public ServerWebSocketStream endHandler(
    final @TypeInfo("ceylon.language::Anything()") @DocAnnotation$annotation$(description = "todo") Callable<?> endHandler) {
    io.vertx.core.Handler<java.lang.Void> arg_0 = new io.vertx.core.Handler<java.lang.Void>() { public void handle(java.lang.Void event) { endHandler.$call$((Object)null); } };
    ServerWebSocketStream ret = new io.vertx.ceylon.core.http.ServerWebSocketStream(delegate.endHandler(arg_0));
    return ret;
  }

}