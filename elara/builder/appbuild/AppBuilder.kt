package elara.builder.appbuild

import java.io.*;
import elara.builder.core.*

class AppBuilder (name: String, buildDest: File): Runnable
{
	var buildName: String = "Untitled"
	var dest: File = File("")
	
	init {
		buildName = name
		dest = buildDest
	}
	
	companion object AppBuilder
	{
		/*
 			Start the build process in a separate thread.
		 */
		fun build(name: String, buildDest: File)
		{
			var builder: Runnable = AppBuilder(name, buildDest)
			var buildThread: Thread = Thread(builder)
			buildThread.setName("Application Build Thread")
			buildThread.start()
		}
	}
	
	override fun run()
	{
		Debug.init(true)
		val buildDest: File = File(dest.absolutePath + "/" + buildName)
		
		Debug.info("Building to: " + buildDest.absolutePath)
		Debug.info("Editor Location: " + Config.editorLoc())
		Debug.info("Build Tool Location: " + Config.buildToolLoc())
		Debug.info("Engine Location: " + Config.engineLoc())
		
		Debug.info("Building editor...")
		EditorBuilder.buildFromTo(Config.editorLoc(), buildDest.absolutePath)
		Debug.info("Editor build done!")
		
		Debug.info("Building build tools...")
		BuildToolBuilder.buildFromTo(Config.buildToolLoc(), buildDest.absolutePath)
		Debug.info("Build tools build done!")
		
		Debug.info("Building engine...")
		EngineBuilder.buildFromTo(Config.engineLoc(), buildDest.absolutePath)
		Debug.info("Engine build done!")
		
		Debug.info("DONE!")
	}
}