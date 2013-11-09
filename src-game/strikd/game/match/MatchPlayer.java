package strikd.game.match;

import java.util.ArrayList;
import java.util.List;

import strikd.game.board.Square;
import strikd.game.player.Player;
import strikd.net.codec.OutgoingMessage;
import strikd.sessions.Session;

public class MatchPlayer
{
	private final Session session;
	
	private Match match;
	private int playerId;
	private boolean ready;
	private int score;
	
	private List<Square> selected = new ArrayList<Square>();
	
	public MatchPlayer(Session session)
	{
		this.session = session;
	}
	
	public void send(OutgoingMessage msg)
	{
		this.session.send(msg);
	}
		
	public MatchPlayer setMatch(Match match, int playerId)
	{
		this.match = match;
		this.playerId = playerId;
		if(this.session != null)
		{
			this.session.setMatchPlayer(this);
		}
		
		return this;
	}
	
	public void leave()
	{
		this.match.removePlayer(this);
	}
	
	public Session getSession()
	{
		return this.session;
	}
	
	public Player getInfo()
	{
		return this.session.getPlayer();
	}
	
	public Match getMatch()
	{
		return this.match;
	}
	
	public int getPlayerId()
	{
		return this.playerId;
	}
	
	public void setReady()
	{
		this.ready = true;
		if(this.match != null)
		{
			this.match.checkReady();
		}
	}
	
	public boolean isReady()
	{
		return this.ready;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public void setScore(int score)
	{
		this.score = score;
	}
	
	public void modScore(int points)
	{
		this.score += points;
	}
	
	public void selectTile(Square square)
	{
		this.selected.add(square);
	}
	
	public List<Square> getSelection()
	{
		return this.selected;
	}
	
	public void clearSelection()
	{
		this.selected.clear();
	}
	
	@Override
	public String toString()
	{
		return this.getInfo().name;
	}
}
