package Logic.mp3agic;

public final class Version {
	private static final String VERSION;
	private static final String URL = "http://github.com/mpatric/mp3agic";

	static { // get version from JAR manifest
		String implementationVersion = Version.class.getPackage().getImplementationVersion();
		VERSION = implementationVersion != null ? implementationVersion : "UNKNOWN-SNAPSHOT";
	}

	private Version() {}

	public static String asString() {
		return getVersion() + " - " + getUrl();
	}

	public static String getVersion() {
		return VERSION;
	}

	public static String getUrl() {
		return URL;
	}
}
