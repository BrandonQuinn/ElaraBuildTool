package elara.builder.appbuild

import org.apache.commons.io.*
import java.io.*
import elara.builder.core.*

class EngineBuilder {
	companion object EngineBuilder
	{
		val LIB_DIR: String = "lib"
		val LOG_DIR: String = "logs"
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
		}
	}
}