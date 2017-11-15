package elara.builder.projectbuild

import elara.builder.gui.Console

class ProjectBuilder {
	var projectDirectory: String ?= null
	var projectOutputDirectory: String ?= null
	
	fun build(): Boolean
	{
		if (projectDirectory == null || projectOutputDirectory == null)
			return false
		
		var console: Console = Console()
		console.setVisible(true)
		
		
		
		return true
	}
	
	fun setProjectDir(projectDirectory: String?)
	{
		this.projectDirectory = projectDirectory
	}
	
	fun setOutputDirectory(outputDirectory: String?)
	{
		this.projectOutputDirectory = outputDirectory
	}
}