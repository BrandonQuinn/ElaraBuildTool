package elara.builder.core

import java.util.*;  
import java.io.*;

class Config
{
	companion object Config
	{
		val EDITOR_LOC: String = "editorLocation"
		val BUILDTOOL_LOC: String = "buildToolLocation"
		val ENGINE_LOC: String = "engineLocation"
		
		val DIR: File = File("conf")
		val FILENAME = "conf/config.props"
		var file: File = File(FILENAME)
		var properties: Properties = Properties()
		
		init
		{
			if (!DIR.exists()) {
				DIR.mkdirs()
			}
			
			if (file.exists()) {
				readProps()
			} else {
				file.createNewFile()
			}
		}
		
		fun setEditorLoc(loc: String)
		{
			properties.setProperty(EDITOR_LOC, loc)
			writeProps()
		}
		
		fun setBuildToolLoc(loc: String)
		{
			properties.setProperty(BUILDTOOL_LOC, loc)
			writeProps()
		}
		
		fun setEngineLoc(loc: String)
		{
			properties.setProperty(ENGINE_LOC, loc)
			writeProps()
		}
		
		fun editorLoc(): String
		{
			return properties.getProperty(EDITOR_LOC, "not set")
		}
		
		fun buildToolLoc(): String
		{
			return properties.getProperty(BUILDTOOL_LOC, "not set")
		}
		
		fun engineLoc(): String
		{
			return properties.getProperty(ENGINE_LOC, "not set")
		}
		
		fun readProps()
		{
			var reader = FileReader(file)
			properties.load(reader)
			reader.close()
		}
		
		fun writeProps()
		{
			var writer = FileWriter(file)
			properties.store(writer, "Application Build Configuration")
			writer.close()
		}
	}
}