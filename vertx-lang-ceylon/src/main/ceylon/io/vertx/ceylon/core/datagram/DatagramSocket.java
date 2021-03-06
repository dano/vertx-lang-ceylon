package io.vertx.ceylon.core.datagram;

import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Name;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;
import com.redhat.ceylon.compiler.java.runtime.model.ReifiedType;
import ceylon.language.Callable;
import ceylon.language.DocAnnotation$annotation$;
import io.vertx.ceylon.core.buffer.Buffer;
import io.vertx.ceylon.core.metrics.Measured;
import io.vertx.ceylon.core.streams.ReadStream;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ceylon.core.net.SocketAddress;

@Ceylon(major = 8)
@DocAnnotation$annotation$(description = " A datagram socket can be used to send [DatagramPacket](../datagram/DatagramPacket.type.html)'s to remote datagram servers\n and receive [DatagramPacket](../datagram/DatagramPacket.type.html)s .\n <p>\n Usually you use a datagram socket to send UDP over the wire. UDP is connection-less which means you are not connected\n to the remote peer in a persistent way. Because of this you have to supply the address and port of the remote peer\n when sending data.\n <p>\n You can send data to ipv4 or ipv6 addresses, which also include multicast addresses.\n <p>\n Please consult the documentation for more information on datagram sockets.\n")
public class DatagramSocket implements ReifiedType,  ReadStream<DatagramPacket>,  Measured {

  @Ignore
  public static final io.vertx.lang.ceylon.ConverterFactory<io.vertx.core.datagram.DatagramSocket, DatagramSocket> TO_CEYLON = new io.vertx.lang.ceylon.ConverterFactory<io.vertx.core.datagram.DatagramSocket, DatagramSocket>() {
    public io.vertx.lang.ceylon.Converter<io.vertx.core.datagram.DatagramSocket, DatagramSocket> converter(final TypeDescriptor... descriptors) {
      return new io.vertx.lang.ceylon.Converter<io.vertx.core.datagram.DatagramSocket, DatagramSocket>() {
        public DatagramSocket convert(io.vertx.core.datagram.DatagramSocket src) {
          return new DatagramSocket(src);
        }
      };
    }
  };

  @Ignore
  public static final io.vertx.lang.ceylon.Converter<DatagramSocket, io.vertx.core.datagram.DatagramSocket> TO_JAVA = new io.vertx.lang.ceylon.Converter<DatagramSocket, io.vertx.core.datagram.DatagramSocket>() {
    public io.vertx.core.datagram.DatagramSocket convert(DatagramSocket src) {
      return src.delegate;
    }
  };

  @Ignore private SocketAddress cached_localAddress;
  @Ignore public static final TypeDescriptor $TypeDescriptor$ = TypeDescriptor.klass(DatagramSocket.class);
  @Ignore private final io.vertx.core.datagram.DatagramSocket delegate;

  public DatagramSocket(io.vertx.core.datagram.DatagramSocket delegate) {
    this.delegate = delegate;
  }

  @Ignore 
  public TypeDescriptor $getType$() {
    return $TypeDescriptor$;
  }

  @Ignore
  public Object getDelegate() {
    return delegate;
  }

  @DocAnnotation$annotation$(description = " Whether the metrics are enabled for this measured object\n")
  @TypeInfo("ceylon.language::Boolean")
  public boolean $isMetricsEnabled() {
    boolean ret = delegate.isMetricsEnabled();
    return ret;
  }

  @DocAnnotation$annotation$(description = " Write the given [Buffer](../buffer/Buffer.type.html) to the [SocketAddress](../net/SocketAddress.type.html).\n The _handler_ will be notified once the write completes.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket send(
    final @TypeInfo("io.vertx.ceylon.core.buffer::Buffer") @Name("packet") @DocAnnotation$annotation$(description = "the [Buffer](../buffer/Buffer.type.html) to write\n") Buffer packet, 
    final @TypeInfo("ceylon.language::Integer") @Name("port") @DocAnnotation$annotation$(description = "the host port of the remote peer\n") long port, 
    final @TypeInfo("ceylon.language::String") @Name("host") @DocAnnotation$annotation$(description = "the host address of the remote peer\n") ceylon.language.String host, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "the _handler_ to notify once the write completes.\n") Callable<?> handler) {
    io.vertx.core.buffer.Buffer arg_0 = io.vertx.ceylon.core.buffer.Buffer.TO_JAVA.safeConvert(packet);
    int arg_1 = (int)port;
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(host);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.send(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @DocAnnotation$annotation$(description = " Returns a [PacketWritestream](../datagram/PacketWritestream.type.html) able to send  to the\n [SocketAddress](../net/SocketAddress.type.html).\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::PacketWritestream")
  public PacketWritestream sender(
    final @TypeInfo("ceylon.language::Integer") @Name("port") @DocAnnotation$annotation$(description = "the port of the remote peer\n") long port, 
    final @TypeInfo("ceylon.language::String") @Name("host") @DocAnnotation$annotation$(description = "the host address of the remote peer\n") ceylon.language.String host) {
    int arg_0 = (int)port;
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(host);
    PacketWritestream ret = io.vertx.ceylon.core.datagram.PacketWritestream.TO_CEYLON.converter().safeConvert(delegate.sender(arg_0, arg_1));
    return ret;
  }

  @DocAnnotation$annotation$(description = " Write the given `String` to the [SocketAddress](../net/SocketAddress.type.html) using UTF8 encoding.\n The  will be notified once the write completes.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket send(
    final @TypeInfo("ceylon.language::String") @Name("str") @DocAnnotation$annotation$(description = "the `String` to write\n") ceylon.language.String str, 
    final @TypeInfo("ceylon.language::Integer") @Name("port") @DocAnnotation$annotation$(description = "the host port of the remote peer\n") long port, 
    final @TypeInfo("ceylon.language::String") @Name("host") @DocAnnotation$annotation$(description = "the host address of the remote peer\n") ceylon.language.String host, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "the _handler_ to notify once the write completes.\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(str);
    int arg_1 = (int)port;
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(host);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.send(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @DocAnnotation$annotation$(description = " Write the given `String` to the [SocketAddress](../net/SocketAddress.type.html) using the given encoding.\n The  will be notified once the write completes.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket send(
    final @TypeInfo("ceylon.language::String") @Name("str") @DocAnnotation$annotation$(description = "the `String` to write\n") ceylon.language.String str, 
    final @TypeInfo("ceylon.language::String") @Name("enc") @DocAnnotation$annotation$(description = "the charset used for encoding\n") ceylon.language.String enc, 
    final @TypeInfo("ceylon.language::Integer") @Name("port") @DocAnnotation$annotation$(description = "the host port of the remote peer\n") long port, 
    final @TypeInfo("ceylon.language::String") @Name("host") @DocAnnotation$annotation$(description = "the host address of the remote peer\n") ceylon.language.String host, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "the _handler_ to notify once the write completes.\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(str);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(enc);
    int arg_2 = (int)port;
    java.lang.String arg_3 = io.vertx.lang.ceylon.ToJava.String.safeConvert(host);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_4 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.send(arg_0, arg_1, arg_2, arg_3, arg_4));
    return this;
  }

  @DocAnnotation$annotation$(description = " Closes the [DatagramSocket](../datagram/DatagramSocket.type.html) implementation asynchronous\n and notifies the handler once done.\n")
  @TypeInfo("ceylon.language::Anything")
  public void close(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable?)") @Name("handler") @DocAnnotation$annotation$(description = "the handler to notify once complete\n") Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.AsyncResult<java.lang.Void>> arg_0 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<java.lang.Void>(handler) {
      public Object toCeylon(java.lang.Void event) {
        return null;
      }
    };
    delegate.close(arg_0);
  }

  @DocAnnotation$annotation$(description = " Closes the [DatagramSocket](../datagram/DatagramSocket.type.html). The close itself is asynchronous.\n")
  @TypeInfo("ceylon.language::Anything")
  public void close() {
    delegate.close();
  }

  @DocAnnotation$annotation$(description = " Return the [SocketAddress](../net/SocketAddress.type.html) to which\n this [DatagramSocket](../datagram/DatagramSocket.type.html) is bound.\n")
  @TypeInfo("io.vertx.ceylon.core.net::SocketAddress")
  public SocketAddress localAddress() {
    if (cached_localAddress != null) {
      return cached_localAddress;
    }
    SocketAddress ret = io.vertx.ceylon.core.net.SocketAddress.TO_CEYLON.converter().safeConvert(delegate.localAddress());
    cached_localAddress = ret;
    return ret;
  }

  @DocAnnotation$annotation$(description = " Joins a multicast group and listens for packets send to it.\n The  is notified once the operation completes.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket listenMulticastGroup(
    final @TypeInfo("ceylon.language::String") @Name("multicastAddress") @DocAnnotation$annotation$(description = "the address of the multicast group to join\n") ceylon.language.String multicastAddress, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "then handler to notify once the operation completes\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(multicastAddress);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.listenMulticastGroup(arg_0, arg_1));
    return this;
  }

  @DocAnnotation$annotation$(description = " Joins a multicast group and listens for packets send to it on the given network interface.\n The  is notified once the operation completes.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket listenMulticastGroup(
    final @TypeInfo("ceylon.language::String") @Name("multicastAddress") @DocAnnotation$annotation$(description = "the address of the multicast group to join\n") ceylon.language.String multicastAddress, 
    final @TypeInfo("ceylon.language::String") @Name("networkInterface") @DocAnnotation$annotation$(description = "the network interface on which to listen for packets.\n") ceylon.language.String networkInterface, 
    final @TypeInfo("ceylon.language::String?") @Name("source") @DocAnnotation$annotation$(description = "the address of the source for which we will listen for multicast packets\n") ceylon.language.String source, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "then handler to notify once the operation completes\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(multicastAddress);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(networkInterface);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(source);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.listenMulticastGroup(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @DocAnnotation$annotation$(description = " Leaves a multicast group and stops listening for packets send to it.\n The  is notified once the operation completes.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket unlistenMulticastGroup(
    final @TypeInfo("ceylon.language::String") @Name("multicastAddress") @DocAnnotation$annotation$(description = "the address of the multicast group to leave\n") ceylon.language.String multicastAddress, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "then handler to notify once the operation completes\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(multicastAddress);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_1 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.unlistenMulticastGroup(arg_0, arg_1));
    return this;
  }

  @DocAnnotation$annotation$(description = " Leaves a multicast group and stops listening for packets send to it on the given network interface.\n The  is notified once the operation completes.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket unlistenMulticastGroup(
    final @TypeInfo("ceylon.language::String") @Name("multicastAddress") @DocAnnotation$annotation$(description = "the address of the multicast group to join\n") ceylon.language.String multicastAddress, 
    final @TypeInfo("ceylon.language::String") @Name("networkInterface") @DocAnnotation$annotation$(description = "the network interface on which to listen for packets.\n") ceylon.language.String networkInterface, 
    final @TypeInfo("ceylon.language::String?") @Name("source") @DocAnnotation$annotation$(description = "the address of the source for which we will listen for multicast packets\n") ceylon.language.String source, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "the handler to notify once the operation completes\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(multicastAddress);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(networkInterface);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(source);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.unlistenMulticastGroup(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @DocAnnotation$annotation$(description = " Block the given address for the given multicast address and notifies the  once\n the operation completes.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket blockMulticastGroup(
    final @TypeInfo("ceylon.language::String") @Name("multicastAddress") @DocAnnotation$annotation$(description = "the address for which you want to block the source address\n") ceylon.language.String multicastAddress, 
    final @TypeInfo("ceylon.language::String") @Name("sourceToBlock") @DocAnnotation$annotation$(description = "the source address which should be blocked. You will not receive an multicast packets for it anymore.\n") ceylon.language.String sourceToBlock, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "the handler to notify once the operation completes\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(multicastAddress);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(sourceToBlock);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.blockMulticastGroup(arg_0, arg_1, arg_2));
    return this;
  }

  @DocAnnotation$annotation$(description = " Block the given address for the given multicast address on the given network interface and notifies\n the  once the operation completes.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket blockMulticastGroup(
    final @TypeInfo("ceylon.language::String") @Name("multicastAddress") @DocAnnotation$annotation$(description = "the address for which you want to block the source address\n") ceylon.language.String multicastAddress, 
    final @TypeInfo("ceylon.language::String") @Name("networkInterface") @DocAnnotation$annotation$(description = "the network interface on which the blocking should occur.\n") ceylon.language.String networkInterface, 
    final @TypeInfo("ceylon.language::String") @Name("sourceToBlock") @DocAnnotation$annotation$(description = "the source address which should be blocked. You will not receive an multicast packets for it anymore.\n") ceylon.language.String sourceToBlock, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "the handler to notify once the operation completes\n") Callable<?> handler) {
    java.lang.String arg_0 = io.vertx.lang.ceylon.ToJava.String.safeConvert(multicastAddress);
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(networkInterface);
    java.lang.String arg_2 = io.vertx.lang.ceylon.ToJava.String.safeConvert(sourceToBlock);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_3 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.blockMulticastGroup(arg_0, arg_1, arg_2, arg_3));
    return this;
  }

  @DocAnnotation$annotation$(description = " Start listening on the given port and host. The handler will be called when the socket is listening.\n")
  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket listen(
    final @TypeInfo("ceylon.language::Integer") @Name("port") @DocAnnotation$annotation$(description = "the port to listen on\n") long port, 
    final @TypeInfo("ceylon.language::String") @Name("host") @DocAnnotation$annotation$(description = "the host to listen on\n") ceylon.language.String host, 
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable|io.vertx.ceylon.core.datagram::DatagramSocket)") @Name("handler") @DocAnnotation$annotation$(description = "the handler will be called when listening\n") Callable<?> handler) {
    int arg_0 = (int)port;
    java.lang.String arg_1 = io.vertx.lang.ceylon.ToJava.String.safeConvert(host);
    io.vertx.core.Handler<io.vertx.core.AsyncResult<io.vertx.core.datagram.DatagramSocket>> arg_2 = handler == null ? null : new io.vertx.lang.ceylon.CallableAsyncResultHandler<io.vertx.core.datagram.DatagramSocket>(handler) {
      public Object toCeylon(io.vertx.core.datagram.DatagramSocket event) {
        return io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.listen(arg_0, arg_1, arg_2));
    return this;
  }

  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket pause() {
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.pause());
    return this;
  }

  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket resume() {
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.resume());
    return this;
  }

  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket endHandler(
    final @TypeInfo("ceylon.language::Anything()?") @Name("endHandler")  Callable<?> endHandler) {
    io.vertx.core.Handler<java.lang.Void> arg_0 = endHandler == null ? null : new io.vertx.core.Handler<java.lang.Void>() {
      public void handle(java.lang.Void event) {
        endHandler.$call$();
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.endHandler(arg_0));
    return this;
  }

  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket handler(
    final @TypeInfo("ceylon.language::Anything(io.vertx.ceylon.core.datagram::DatagramPacket)?") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<io.vertx.core.datagram.DatagramPacket> arg_0 = handler == null ? null : new io.vertx.core.Handler<io.vertx.core.datagram.DatagramPacket>() {
      public void handle(io.vertx.core.datagram.DatagramPacket event) {
        handler.$call$((Object)io.vertx.ceylon.core.datagram.DatagramPacket.TO_CEYLON.converter().safeConvert(event));
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.handler(arg_0));
    return this;
  }

  @TypeInfo("io.vertx.ceylon.core.datagram::DatagramSocket")
  public DatagramSocket exceptionHandler(
    final @TypeInfo("ceylon.language::Anything(ceylon.language::Throwable)?") @Name("handler")  Callable<?> handler) {
    io.vertx.core.Handler<java.lang.Throwable> arg_0 = handler == null ? null : new io.vertx.core.Handler<java.lang.Throwable>() {
      public void handle(java.lang.Throwable event) {
        handler.$call$((Object)event);
      }
    };
    DatagramSocket ret = io.vertx.ceylon.core.datagram.DatagramSocket.TO_CEYLON.converter().safeConvert(delegate.exceptionHandler(arg_0));
    return this;
  }

}
