package strikd.game.player;

import com.google.common.base.Strings;

import strikd.game.items.AvatarPart;
import strikd.game.items.AvatarPart.PartSlot;
import strikd.game.items.ItemType;
import strikd.game.items.ItemTypeRegistry;

public final class Avatar
{
	private static final char PART_DELIMITER = ':';
	
	private final AvatarPart[] parts = new AvatarPart[PartSlot.values().length];
	
	public AvatarPart get(PartSlot slot)
	{
		return this.parts[slot.ordinal()];
	}
	
	public boolean has(PartSlot slot)
	{
		return (this.get(slot) != null);
	}
	
	public boolean hasPart(AvatarPart part)
	{
		return (this.get(part.getSlot()) == part);
	}
	
	private void set(PartSlot slot, AvatarPart part)
	{
		this.parts[slot.ordinal()] = part;
	}

	public void set(AvatarPart part)
	{
		if(part != null)
		{
			this.set(part.getSlot(), part);
		}
	}
	
	public void remove(PartSlot slot)
	{
		this.set(slot, null);
	}
	
	public void remove(AvatarPart part)
	{
		if(this.hasPart(part))
		{
			this.remove(part.getSlot());
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		
		boolean sep = false;
		for(int i = 0; i < this.parts.length; i++)
		{
			if(this.parts[i] != null)
			{
				if(sep) sb.append(PART_DELIMITER); else sep = true;
				sb.append(this.parts[i].getId());
			}
		}
		
		return sb.toString();
	}
	
	public static Avatar parseAvatar(String str)
	{
		Avatar ava = new Avatar();
		
		if(!Strings.isNullOrEmpty(str))
		{
			for(int pos = 0, end = 0; end != -1; pos = end + 1)
			{
				end = str.indexOf(PART_DELIMITER, pos);
				
				ItemType item = ItemTypeRegistry.getType(Integer.parseInt(end != -1 ? str.substring(pos, end) : str.substring(pos)));
				if(item instanceof AvatarPart)
				{
					ava.set((AvatarPart)item);
				}
			}
		}
		
		return ava;
	}
}