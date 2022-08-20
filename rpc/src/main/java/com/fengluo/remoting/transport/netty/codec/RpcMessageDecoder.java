package com.fengluo.remoting.transport.netty.codec;

import com.fengluo.common.constant.CompressTypeEnum;
import com.fengluo.common.constant.RpcConstants;
import com.fengluo.common.constant.SerializationTypeEnum;
import com.fengluo.common.extension.ExtensionLoader;
import com.fengluo.compress.Compress;
import com.fengluo.remoting.dto.RpcMessage;
import com.fengluo.remoting.dto.RpcRequest;
import com.fengluo.remoting.dto.RpcResponse;
import com.fengluo.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 自定义解码器
 *
 * @Author: fengluo
 * @Date: 2022/8/2 19:43
 */
@Slf4j
public class RpcMessageDecoder extends LengthFieldBasedFrameDecoder {

    public RpcMessageDecoder() {
        // 魔数：4字节
        // 版本：1字节
        // 消息长度：4字节
        // 消息类型：1字节
        // 压缩类型：1字节
        // 序列化类型：1字节
        // 请求的ID：4字节
        // 消息数据：body
        // lengthAdjustment计算：包长 - 长度域值 - 长度域偏移 - 长度域大小
        this(RpcConstants.MAX_FRAME_LENGTH, 5, 4, -9, 0);
    }

    /**
     * 自定义解码器，基于长度的解码器，LengthFieldBasedFrameDecoder 解决粘包、拆包的问题
     *
     * @param maxFrameLength
     * @param lengthFieldOffset
     * @param lengthFieldLength
     * @param lengthAdjustment  计算：包长 - 长度域的值 - 长度域偏移 - 长度域长，16 - 5 - 4
     * @param initialBytesToStrip
     */
    public RpcMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                             int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        Object decoded = super.decode(ctx, in);
        if (decoded instanceof ByteBuf) {
            ByteBuf frame = (ByteBuf) decoded;
            // 如果可读字节 大于 总长度
            if (frame.readableBytes() >= RpcConstants.TOTAL_LENGTH) {
                Object o = null;
                try {
                    return decodeFrame(frame);
                } catch (Exception e) {
                    log.error("Decode frame error!", e);
                    throw e;
                } finally {
                    frame.release();
                }
            }
        }
        return decoded;
    }

    private Object decodeFrame(ByteBuf in) {
        checkMagicNumber(in);
        checkVersion(in);
        int fullLenth = in.readInt(); // 读 四个字节
        byte messageType = in.readByte();
        byte codecType = in.readByte();
        byte compressType = in.readByte();
        int requestId = in.readInt();
        RpcMessage rpcMessage = RpcMessage.builder()
                .codec(codecType)
                .requestId(requestId)
                .messageType(messageType).build();
        if (messageType == RpcConstants.HEARTBEAT_REQUEST_TYPE) {
            rpcMessage.setData(RpcConstants.PING);
            return rpcMessage;
        }
        if (messageType == RpcConstants.HEARTBEAT_RESPONSE_TYPE) {
            rpcMessage.setData(RpcConstants.PANG);
            return rpcMessage;
        }
        int bodyLength = fullLenth - RpcConstants.HEAD_LENGTH;
        if (bodyLength > 0) {
            byte[] bs = new byte[bodyLength];
            in.readBytes(bs);
            // 解压缩
            String compressName = CompressTypeEnum.getName(compressType);
            Compress compress = ExtensionLoader.getExtensionLoader(Compress.class).getExtension(compressName);
            bs = compress.decompress(bs);
            // 反序列化
            String codecName = SerializationTypeEnum.getName(rpcMessage.getCodec());
            log.info("codec name: [{}]", codecName);
            Serializer serializer = ExtensionLoader.getExtensionLoader(Serializer.class).getExtension(codecName);
            if (messageType == RpcConstants.REQUEST_TYPE) {
                rpcMessage.setData(serializer.deserialize(bs, RpcRequest.class));
            } else {
                rpcMessage.setData(serializer.deserialize(bs, RpcResponse.class));
            }
        }
        return rpcMessage;
    }

    private void checkVersion(ByteBuf in) {
        byte version = in.readByte();
        if (version != RpcConstants.VERSION) {
            throw new RuntimeException("版本错误：" + version);
        }
    }

    private void checkMagicNumber(ByteBuf in) {
        int len = RpcConstants.MAGIC_NUMBER.length;
        byte[] tmp = new byte[len];
        in.readBytes(tmp);
        for (int i = 0; i < len; i++) {
            if (tmp[i] != RpcConstants.MAGIC_NUMBER[i]) {
                throw new IllegalArgumentException("魔数未知：" + Arrays.toString(tmp));
            }
        }
    }

}
