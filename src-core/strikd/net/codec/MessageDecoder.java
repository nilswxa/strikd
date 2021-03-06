package strikd.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import strikd.communication.Opcodes;

public class MessageDecoder extends ByteToMessageDecoder
{
	private static final Logger logger = LoggerFactory.getLogger(MessageDecoder.class);
	
	private static final int MAX_MESSAGE_SIZE = 5120;
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception
	{
		// Length header available?
		if(buffer.isReadable(2))
		{
			// Mark reader index
			buffer.markReaderIndex();
	
			// Read message length (2 bytes)
			short length = buffer.readShort();
	
			// Valid length and all data arrived?
			if(length > 0 && length <= MAX_MESSAGE_SIZE)
			{
				// Has all data arrived?
				if(buffer.isReadable(length))
				{
					// Parse opcode
					Opcodes.Incoming op = Opcodes.Incoming.valueOf(buffer.readByte());
					
					// Retain buffer (NEVER FORGET TO RELEASE AFTER HANDLING THE MESSAGE!) and use it
					//buffer.retain();
					//out.add(new IncomingMessage(op, buffer.readSlice(length)));
					
					// FIXME: the above is more efficient, but seems to cause race conditions in busy traffic
					out.add(new IncomingMessage(op, buffer.readBytes(length - 1)));
				}
				else
				{
					// Probably latency or something, come back later
					logger.debug("length={} available={}, waiting for remaining {} bytes", length, buffer.readableBytes(), length - buffer.readableBytes());
					buffer.resetReaderIndex();
				}
			}
			else
			{
				// Something is messed up, forget about it!
				int available = buffer.readableBytes();
				buffer.skipBytes(available);
				logger.warn("length={} is invalid (max={} available={}), killing connection", length, MAX_MESSAGE_SIZE, available);
				
				// Bye!
				ctx.close();
			}
		}
	}
}
