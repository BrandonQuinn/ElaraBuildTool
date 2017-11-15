package elara.builder.appbuild

import org.apache.commons.io.*
import java.io.*
import elara.builder.core.*

class BuildToolBuilder
{
	companion object BuildToolBuilder
	{
		val LIB_DIR: String = "lib"
		val CONF_DIR: String = "conf"
		
		fun buildFromTo(from: String, to: String)
		{
			//copy library directory
			try {
				Debug.info("Copying libraries...")
				FileUtils.copyDirectory(File(from + "/" + LIB_DIR), File(to + "/"))
				Debug.info("Libraries copied.")
			} catch (e: IOException) {
				Debug.error("IOException: failed to copy directory: " + from + "/" + LIB_DIR)
			}
			
			// copy conf directory
			try {
				Debug.info("Copying config directories...")
				FileUtils.copyDirectory(File(from + "/" + CONF_DIR), File(to + "/" + CONF_DIR))
				Debug.info("Config directories copied.")
			} catch (e: IOException) {
				Debug.error("IOException: failed to copy directory: " + from + "/" + CONF_DIR)
			}
		}
	}
}