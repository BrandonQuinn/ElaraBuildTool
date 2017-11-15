package elara.builder.appbuild

import org.apache.commons.io.*
import java.io.*
import elara.builder.core.*

class EditorBuilder {
	companion object EditorBuilder
	{
		val LIB_DIR: String = "lib"
		val LOG_DIR: String = "logs"
		val RES_DIR: String = "res"
		val CONF_DIR: String = "conf"
		val SRC_DIR: String = "src"
		
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
			
			// copy logs directory
			try {
				Debug.info("Copying logs...")
				FileUtils.copyDirectory(File(from + "/" + LOG_DIR), File(to + "/" + LOG_DIR))
				Debug.info("Logs copied.")
			} catch (e: IOException) {
				Debug.error("IOException: failed to copy directory: " + from + "/" + LOG_DIR)
			}
			
			// copy res directory
			try {
				Debug.info("Copying resources...")
				FileUtils.copyDirectory(File(from + "/" + RES_DIR), File(to + "/" + RES_DIR))
				Debug.info("Resources copied.")
			} catch (e: IOException) {
				Debug.error("IOException: failed to copy directory: " + from + "/" + LOG_DIR)
			}
			
			// copy conf directory
			try {
				Debug.info("Copying config directories...")
				FileUtils.copyDirectory(File(from + "/" + CONF_DIR), File(to + "/" + CONF_DIR))
				Debug.info("Config directories copied.")
			} catch (e: IOException) {
				Debug.error("IOException: failed to copy directory: " + from + "/" + CONF_DIR)
			}
			
			Debug.info("Building editor source...")
			JavaBuildTool.buildJavaSource(from + "/" + SRC_DIR, to, from + "/" + LIB_DIR, "elara.editor.Main")
			Debug.info("Editor build complete.")
		}
	}
}