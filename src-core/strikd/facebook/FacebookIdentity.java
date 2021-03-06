package strikd.facebook;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

@Embeddable
public class FacebookIdentity
{
	@Column(name="fb_uid",unique=true)
	private Long userId;
	
	@Column(name="fb_token")
	private String token;
	
	@Transient
	private FacebookClient api;
	
	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	
	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public FacebookClient getAPI()
	{
		if(this.api == null)
		{
			this.api = new DefaultFacebookClient(this.token);
		}
		return this.api;
	}
	
	@Override
	public String toString()
	{
		return "FB UID #" + this.userId;
	}
}
