package strikd.communication.outgoing;

import strikd.communication.Opcodes;
import strikd.game.player.Player;
import strikd.net.codec.OutgoingMessage;

public class ChallengeDeclinedMessage extends OutgoingMessage
{
	public ChallengeDeclinedMessage(Player player)
	{
		super(Opcodes.Outgoing.CHALLENGE_DECLINED);
		super.writeInt(player.getId());
	}
}
