package cn.yubutong.remoting.transport.socket;

import cn.yubutong.entity.RpcServiceProperties;
import cn.yubutong.exception.RpcException;
import cn.yubutong.registry.ServiceDiscovery;
import cn.yubutong.registry.nacos.NacosServiceDiscovery;
import cn.yubutong.registry.zk.ZkServiceDiscovery;
import cn.yubutong.remoting.dto.RpcRequest;
import cn.yubutong.remoting.transport.ClientTransport;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


@AllArgsConstructor
@Slf4j
public class SocketRpcClient implements ClientTransport {
    private final ServiceDiscovery serviceDiscovery;

    public SocketRpcClient() {
        this.serviceDiscovery = new NacosServiceDiscovery();
    }

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) throws NacosException {
        // build rpc service name by ppcRequest
        String rpcServiceName = RpcServiceProperties.builder().serviceName(rpcRequest.getInterfaceName())
                .group(rpcRequest.getGroup()).version(rpcRequest.getVersion()).build().toRpcServiceName();
        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcServiceName);
        try (Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // Send data to the server through the output stream
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // Read RpcResponse from the input stream
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RpcException("调用服务失败:", e);
        }
    }
}
