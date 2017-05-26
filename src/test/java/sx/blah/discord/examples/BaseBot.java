package sx.blah.discord.examples;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * This represents a SUPER basic bot (literally all it does is login).
 * This is used as a base for all example bots.
 */
public class BaseBot {

	public static BaseBot INSTANCE; // Singleton instance of the bot.
	public IDiscordClient client; // The instance of the discord client.

	public static void main(String[] args) { // Main method
		INSTANCE = login("Mjc1MzcwMDE2Mjk4MzY5MDI1.C3s07A.qQRhQAp7R61A-NYTvejx1JGWJhw"); // Creates the bot instance and logs it in.
	}

	public BaseBot(IDiscordClient client) {
		this.client = client; // Sets the client instance to the one provided
	}

	/**
	 * A custom login() method to handle all of the possible exceptions and set the bot instance.
	 */
	public static BaseBot login(String token) {
		BaseBot bot = null; // Initializing the bot variable

		ClientBuilder builder = new ClientBuilder(); // Creates a new client builder instance
		builder.withToken(token); // Sets the bot token for the client
		try {
			IDiscordClient client = builder.login(); // Builds the IDiscordClient instance and logs it in
			bot = new BoppBot(client); // Creating the bot instance
		} catch (DiscordException e) { // Error occurred logging in
			System.err.println("Error occurred while logging in!");
			e.printStackTrace();
		}

		return bot;
	}
}
