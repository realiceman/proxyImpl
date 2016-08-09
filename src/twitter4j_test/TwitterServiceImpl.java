package twitter4j_test;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterServiceImpl implements TwitterService {

	private static final String TWITTER_CONSUMER_KEY = "ltEZ7UQbk5JxvjY3WyFpZ7rMH";
	private static final String TWITTER_SECRET_KEY = "vM4UAKEpY3ELr3sFJ4To65R43oHx686CIq7xqZqZqSDDi6kyDb";
	private static final String TWITTER_ACCESS_TOKEN = "4159143437-RVhoDSust1hK8kwkZxpK73JrLKllsnu9DeGN631";
	private static final String TWITTER_ACCESS_TOKEN_SECRET = "60tsyoGo5M1AmWrhFc5jmHqXc423TjJdLH23kRayhoKxc";
	
	@Override
	public String getTimeline(String screenName) {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		    .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
		    .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
		    .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
		    .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		StringBuilder builder = new StringBuilder();
		try {
		    Query query = new Query(screenName);
		    QueryResult result;
		    do {
		        result = twitter.search(query);
		        List<Status> tweets = result.getTweets();
		        for (Status tweet : tweets) {
		            builder.append("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
		            builder.append("\n");
		        }
		    } while ((query = result.nextQuery()) != null);
		    
		} catch (TwitterException te) {
		    te.printStackTrace();
		    System.out.println("Failed to search tweets: " + te.getMessage());
		}
		return builder.toString();	
	}

	@Override
	public void postToTimeline(String screenName, String message) {
		//we aren't going to allow this
	}
}
