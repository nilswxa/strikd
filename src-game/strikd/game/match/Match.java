package strikd.game.match;

import strikd.game.board.AbstractBoard;
import strikd.game.board.GappieBoard;

public class Match
{
	private final MatchPlayer players[];
	private final MatchTimer timer;
	private final AbstractBoard board;
	
	public Match(MatchPlayer playerOne, MatchPlayer playerTwo)
	{
		this.players = new MatchPlayer[] { playerOne, playerTwo };
		this.timer = new MatchTimer(2 * 60);
		this.board = new GappieBoard(20, 20);
	}
	
	private boolean isExtraTimeActive()
	{
		// Test for players that have their extra timer running
		for(MatchPlayer player : this.players)
		{
			if(!player.getExtraTimer().isDone())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isEnded()
	{
		return this.timer.isDone() && !this.isExtraTimeActive();
	}
	
	public MatchPlayer getPlayerOne()
	{
		return this.players[0];
	}
	
	public MatchPlayer getPlayerTwo()
	{
		return this.players[0];
	}
	
	public MatchTimer getTimer()
	{
		return this.timer;
	}
	
	public AbstractBoard getBoard()
	{
		return this.board;
	}
}
