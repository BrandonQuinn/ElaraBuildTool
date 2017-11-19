package elara.builder.projectbuild;

import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import elara.builder.gui.Console;
import elara.builder.gui.JSON;

public class ProjectBuilder {
	public static final String PROJECT_CONF_FILE = "project.config";
	public static Console console;
	public static String projectDirectory = null;
	public static String projectOutputDirectory = null;
	private static JSONObject projectConfigJSON = new JSONObject();
	
	public static boolean build()
	{
		if (projectDirectory == null || projectOutputDirectory == null)
			return false;
		System.out.println("test");
		console = new Console();
		console.setVisible(true);
		
		// start building the project
		try {
			projectConfigJSON = JSON.read(projectDirectory + "/" + PROJECT_CONF_FILE);
		} catch (ParseException e) {
			console.writeln("[ERROR]: failed to parse project configuration file,"
					+ "please check its formatting");
			return false;
		} catch (IOException e) {
			console.writeln("[ERROR]: failed to open project configuration file");
			return false;
		}
		
		
		
		return true;
	}
	
	public static void setProjectDir(String projectDirectory)
	{
		ProjectBuilder.projectDirectory = projectDirectory;
	}
	
	public static void setOutputDirectory(String outputDirectory)
	{
		ProjectBuilder.projectOutputDirectory = outputDirectory;
	}
}
