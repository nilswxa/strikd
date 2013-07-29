package strikd.game.match.queues;

import strikd.game.match.MatchManager;
import strikd.sessions.Session;

public class TimedPlayerQueue extends PlayerQueue
{
	public TimedPlayerQueue(MatchManager matchMgr)
	{
		super(matchMgr);
	}

	@Override
	public PlayerQueue.Entry enqueue(Session session)
	{
		return new TimedPlayerQueue.Entry(session, this);
	}

	@Override
	public void dequeue(PlayerQueue.Entry entry)
	{
		
	}
	
	public int getAvgWaitingTime()
	{
		return 0;
	}
	
	private static class Entry extends PlayerQueue.Entry
	{
		private final long entryTime = System.currentTimeMillis();
		
		public Entry(Session session, PlayerQueue queue)
		{
			super(session, queue);
		}
		
		public long getWaitingTime()
		{
			return ((System.currentTimeMillis() - this.entryTime) / 1000);
		}
	}
}