package cn.yubutong.remoting.transport.netty.server;

import cn.yubutong.enumeration.RpcMessageType;
import cn.yubutong.enumeration.RpcResponseCode;
import cn.yubutong.factory.SingletonFactory;
import cn.yubutong.remoting.dto.RpcRequest;
import cn.yubutong.remoting.dto.RpcResponse;
import cn.yubutong.remoting.handler.RpcRequestHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private final RpcRequestHandler rpcRequestHandler;

    public NettyServerHandler() {
        this.rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            log.info("server receive msg: [{}] ", msg);
            RpcRequest rpcRequest = (RpcRequest) msg;
            if (rpcRequest.getRpcMessageType() == RpcMessageType.HEART_BEAT) {
                log.info("receive heat beat msg from client");
                return;
            }
            // Execute the target method (the method the client needs to execute) and return the method result
            Object result = rpcRequestHandler.handle(rpcRequest);
            log.info(String.format("server get result: %s", result.toString()));
            if (ctx.channel().isActive() && ctx.channel().isWritable()) {
                RpcResponse<Object> rpcResponse = RpcResponse.success(result, rpcRequest.getRequestId());
                ctx.writeAndFlush(rpcResponse).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            } else {
                RpcResponse<Object> rpcResponse = RpcResponse.fail(RpcResponseCode.FAIL);
                ctx.writeAndFlush(rpcResponse).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                log.error("not writable now, message dropped");
            }
        } finally {
            //Ensure that ByteBuf is released, otherwise there may be memory leaks
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                log.info("idle check happen, so close the connection");
                ctx.close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("server catch exception");
        cause.printStackTrace();
        ctx.close();
    }
}
