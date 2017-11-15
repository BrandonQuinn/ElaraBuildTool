package elara.builder.core

import elara.builder.gui.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Debug
{
	companion object Debug
	{
		var useConsole = false
		var console: Console = Console()
				
		fun init(useConsole: Boolean)
		{
			Debug.useConsole = useConsole
			
			// initliase the console
			if (useConsole) {
				console = Console()
				console.setVisible(true)
			}
		}
		
		fun info(msg: String)
		{
			var log: String = "[INFO] ("+ timeStr() +"): " + msg;
			writeLog(log)
		}
		
		fun warning(msg: String)
		{
			var log: String = "[WARNING] ("+ timeStr() +"): " + msg;
			writeLog(log)
		}
		
		fun error(msg: String)
		{
			var log: String = "[ERROR] ("+ timeStr() +"): " + msg;
			writeLog(log)
		}
		
		fun writeLog(log: String) {
			if (useConsole) {
				console.writeln(log)
			} else {
				println(log)
			}
		}
		
		fun timeStr(): String
		{
			var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			return LocalDateTime.now().format(formatter);
		}
	}
}