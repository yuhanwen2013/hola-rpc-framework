package cn.yubutong.remoting.transport;


import cn.yubutong.remoting.dto.RpcRequest;
import com.alibaba.nacos.api.exception.NacosException;


public interface ClientTransport {
    /**
     * 发送消息到服务端
     *
     * @param rpcRequest 消息体
     * @return 服务端返回的数据
     */
    Object sendRpcRequest(RpcRequest rpcRequest) throws NacosException;
}
