package strikd.communication;

public interface Opcodes
{
	public interface Opcode { }
	
	public enum Incoming implements Opcode
	{
		CLIENT_CRYPTO,
	    CREATE_PLAYER,
	    LOGIN,
	    CHANGE_NAME,
	    REQUEST_MATCH,
	    EXIT_MATCH,
	    PLAYER_READY,
	    UPDATE_TILE_SELECTION,
	    PURCHASE_ITEM,
	    ACTIVATE_ITEM,
	    FACEBOOK_LINK,
	    FACEBOOK_UNLINK,
	    FACEBOOK_CLAIM_LIKE,
	    FACEBOOK_REGISTER_INVITES;
		
		private static final Opcodes.Incoming[] values = values();
		
		public static final Opcodes.Incoming valueOf(byte opcode)
		{
			return values[opcode];
		}
	}
	
	public enum Outgoing implements Opcode
	{
		VERSIONCHECK,
		SERVER_CRYPTO,
		SESSION_INFO,
		CREDENTIALS,
		PLAYER_INFO,
		CURRENCY_BALANCE,
		ITEMS,
		ITEM_ADDED,
		FACEBOOK_STATUS,
		NAME_CHANGED,
		NAME_REJECTED,
		QUEUE_ENTERED,
		QUEUE_EXITED,
		ANNOUNCE_MATCH,
		MATCH_STARTED,
		MATCH_ENDED,
		BOARD_INIT,
		BOARD_UPDATE,
		TILE_SELECTION_EXTENDED,
		TILE_SELECTION_CLEARED,
		WORD_FOUND,
		ALERT,
		SERVER_SHUTTING_DOWN,
		SERVER_REDIRECT
	}
}
