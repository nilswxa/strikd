package strikd.communication;

public interface Opcodes
{
	public interface Opcode { }
	
	public enum Incoming implements Opcode
	{
	    CREATE_USER,
	    LOGIN,
	    FACEBOOK_LINK,
	    FACEBOOK_UNLINK,
	    CHANGE_NAME,
	    REQUEST_MATCH,
	    EXIT_MATCH_QUEUE,
	    PLAYER_READY,
	    SELECT_TILES,
	    PURCHASE_ITEM,
	    ACTIVATE_ITEM;
		
		private static final Opcodes.Incoming[] values = values();
		
		public static final Opcodes.Incoming valueOf(byte opcode)
		{
			return values[opcode];
		}
	}
	
	public enum Outgoing implements Opcode
	{
	    VERSIONCHECK,
	    SESSION_INFO,
	    CREDENTIALS,
	    USER_INFO,
	    CURRENCY_BALANCE,
	    ITEMS,
	    ITEM_ADDED,
	    FACEBOOK_STATUS,
	    NAME_CHANGED,
	    NAME_REJECTED,
		QUEUE_ENTERED,
		QUEUE_EXITED,
	    ANNOUNCE_MATCH,
	    SQUARE_UPDATES,
	    START_MATCH,
	    END_MATCH,
	    WORD_FOUND,
		ALERT,
	    SERVER_SHUTTING_DOWN,
	    SERVER_REDIRECT;
		
		private static final Opcodes.Outgoing[] values = values();
		
		public static final Opcodes.Outgoing valueOf(byte opcode)
		{
			return values[opcode];
		}
	}
}
