package io.vertx.ceylon.core.http;

import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import io.vertx.ceylon.core.buffer.Buffer;
import io.vertx.ceylon.core.streams.WriteStream;
import io.vertx.ceylon.core.streams.ReadStream;
import io.vertx.core.Handler;
import io.vertx.ceylon.core.net.SocketAddress;

@DocAnnotation$annotation$(description = "todo")
public interface WebSocketBase extends ReadStream<Buffer>, WriteStream<Buffer> {

  TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(WebSocketBase.class);

  io.vertx.lang.ceylon.Converter<io.vertx.core.http.WebSocketBase, WebSocketBase> TO_CEYLON = new io.vertx.lang.ceylon.Converter<io.vertx.core.http.WebSocketBase, WebSocketBase>() {
    public WebSocketBase convert(io.vertx.core.http.WebSocketBase src) {
      return new WebSocketBase.Impl(src);
    }
  };

  io.vertx.lang.ceylon.Converter<WebSocketBase, io.vertx.core.http.WebSocketBase> TO_JAVA = new io.vertx.lang.ceylon.Converter<WebSocketBase, io.vertx.core.http.WebSocketBase>() {
    public io.vertx.core.http.WebSocketBase convert(WebSocketBase src) {
      return (io.vertx.core.http.WebSocketBase)src.getDelegate();
    }
  };

  @Ignore
  Object getDelegate();

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::Boolean")
  boolean writeQueueFull();

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase exceptionHandler(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable)") @DocAnnotation$annotation$(description = "todo") Callable<?> handler);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase handler(
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.core.buffer::Buffer)") @DocAnnotation$annotation$(description = "todo") Callable<?> handler);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase pause();

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase resume();

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase endHandler(
    final @TypeInfo("ceylon.language::Anything()") @DocAnnotation$annotation$(description = "todo") Callable<?> endHandler);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase write(
    final @TypeInfo("io.vertx.ceylon.core.buffer::Buffer") @DocAnnotation$annotation$(description = "todo") Buffer data);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase setWriteQueueMaxSize(
    final @TypeInfo("ceylon.language::Integer") @DocAnnotation$annotation$(description = "todo") long maxSize);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase drainHandler(
    final @TypeInfo("ceylon.language::Anything()") @DocAnnotation$annotation$(description = "todo") Callable<?> handler);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::String")
  ceylon.language.String binaryHandlerID();

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::String")
  ceylon.language.String textHandlerID();

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase writeFrame(
    final @TypeInfo("io.vertx.ceylon.core.http::WebSocketFrame") @DocAnnotation$annotation$(description = "todo") WebSocketFrame frame);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase writeFinalTextFrame(
    final @TypeInfo("ceylon.language::String") @DocAnnotation$annotation$(description = "todo") ceylon.language.String text);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase writeFinalBinaryFrame(
    final @TypeInfo("io.vertx.ceylon.core.buffer::Buffer") @DocAnnotation$annotation$(description = "todo") Buffer data);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase writeBinaryMessage(
    final @TypeInfo("io.vertx.ceylon.core.buffer::Buffer") @DocAnnotation$annotation$(description = "todo") Buffer data);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase closeHandler(
    final @TypeInfo("ceylon.language::Anything()") @DocAnnotation$annotation$(description = "todo") Callable<?> handler);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.http::WebSocketBase")
  WebSocketBase frameHandler(
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.core.http::WebSocketFrame)") @DocAnnotation$annotation$(description = "todo") Callable<?> handler);

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("ceylon.language::Anything")
  void close();

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.net::SocketAddress")
  SocketAddress remoteAddress();

  @DocAnnotation$annotation$(description = "todo")
  @TypeInfo("io.vertx.ceylon.core.net::SocketAddress")
  SocketAddress localAddress();

  @Ignore
  public class Impl implements WebSocketBase {
    @Ignore
    private final io.vertx.core.http.WebSocketBase delegate;

    public Impl(io.vertx.core.http.WebSocketBase delegate) {
      this.delegate = delegate;
    }

    @Ignore
    public Object getDelegate() {
      return delegate;
    }

    @Override
    public boolean writeQueueFull() {
      boolean ret = delegate.writeQueueFull();
      return ret;
    }

    @Override
    public WebSocketBase exceptionHandler(final Callable<?> handler) {
      io.vertx.core.Handler<java.lang.Throwable> arg_0 = new io.vertx.core.Handler<java.lang.Throwable>() { public void handle(java.lang.Throwable event) { handler.$call$((Object)event); } };
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.exceptionHandler(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase handler(final Callable<?> handler) {
      io.vertx.core.Handler<io.vertx.core.buffer.Buffer> arg_0 = new io.vertx.core.Handler<io.vertx.core.buffer.Buffer>() { public void handle(io.vertx.core.buffer.Buffer event) { handler.$call$((Object)new io.vertx.ceylon.core.buffer.Buffer(event)); } };
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.handler(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase pause() {
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.pause());
      return ret;
    }

    @Override
    public WebSocketBase resume() {
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.resume());
      return ret;
    }

    @Override
    public WebSocketBase endHandler(final Callable<?> endHandler) {
      io.vertx.core.Handler<java.lang.Void> arg_0 = new io.vertx.core.Handler<java.lang.Void>() { public void handle(java.lang.Void event) { endHandler.$call$(); } };
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.endHandler(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase write(final Buffer data) {
      io.vertx.core.buffer.Buffer arg_0 = (io.vertx.core.buffer.Buffer)data.getDelegate();
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.write(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase setWriteQueueMaxSize(final long maxSize) {
      int arg_0 = (int)maxSize;
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.setWriteQueueMaxSize(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase drainHandler(final Callable<?> handler) {
      io.vertx.core.Handler<java.lang.Void> arg_0 = new io.vertx.core.Handler<java.lang.Void>() { public void handle(java.lang.Void event) { handler.$call$(); } };
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.drainHandler(arg_0));
      return ret;
    }

    @Override
    public ceylon.language.String binaryHandlerID() {
      ceylon.language.String ret = io.vertx.lang.ceylon.ToCeylon.String.convert(delegate.binaryHandlerID());
      return ret;
    }

    @Override
    public ceylon.language.String textHandlerID() {
      ceylon.language.String ret = io.vertx.lang.ceylon.ToCeylon.String.convert(delegate.textHandlerID());
      return ret;
    }

    @Override
    public WebSocketBase writeFrame(final WebSocketFrame frame) {
      io.vertx.core.http.WebSocketFrame arg_0 = (io.vertx.core.http.WebSocketFrame)frame.getDelegate();
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.writeFrame(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase writeFinalTextFrame(final ceylon.language.String text) {
      java.lang.String arg_0 = text.toString();
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.writeFinalTextFrame(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase writeFinalBinaryFrame(final Buffer data) {
      io.vertx.core.buffer.Buffer arg_0 = (io.vertx.core.buffer.Buffer)data.getDelegate();
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.writeFinalBinaryFrame(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase writeBinaryMessage(final Buffer data) {
      io.vertx.core.buffer.Buffer arg_0 = (io.vertx.core.buffer.Buffer)data.getDelegate();
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.writeBinaryMessage(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase closeHandler(final Callable<?> handler) {
      io.vertx.core.Handler<java.lang.Void> arg_0 = new io.vertx.core.Handler<java.lang.Void>() { public void handle(java.lang.Void event) { handler.$call$(); } };
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.closeHandler(arg_0));
      return ret;
    }

    @Override
    public WebSocketBase frameHandler(final Callable<?> handler) {
      io.vertx.core.Handler<io.vertx.core.http.WebSocketFrame> arg_0 = new io.vertx.core.Handler<io.vertx.core.http.WebSocketFrame>() { public void handle(io.vertx.core.http.WebSocketFrame event) { handler.$call$((Object)new io.vertx.ceylon.core.http.WebSocketFrame(event)); } };
      WebSocketBase ret = new io.vertx.ceylon.core.http.WebSocketBase.Impl(delegate.frameHandler(arg_0));
      return ret;
    }

    @Override
    public void close() {
      delegate.close();
    }

    @Override
    public SocketAddress remoteAddress() {
      SocketAddress ret = new io.vertx.ceylon.core.net.SocketAddress(delegate.remoteAddress());
      return ret;
    }

    @Override
    public SocketAddress localAddress() {
      SocketAddress ret = new io.vertx.ceylon.core.net.SocketAddress(delegate.localAddress());
      return ret;
    }
  }

}
