package twitter4j_test;

public interface TwitterService {

	String getTimeline(String screenName);

	void postToTimeline(String screenName, String message);

}
