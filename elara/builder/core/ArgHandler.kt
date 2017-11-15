package elara.builder.core

import quinn.cmdln.*
import elara.builder.appbuild.*
import javax.swing.*
import elara.builder.gui.*
import java.io.*
import elara.builder.projectbuild.*

class ArgHandler (arguments: Array<String>)
{
	val args: Array<String>
	val options: Options = Options()
	
	var projectBuilder: ProjectBuilder = ProjectBuilder()
	var doBuild: Boolean = false
	
	init
	{
		this.args = arguments
		
		// application build options
		options.addOption(Option("appbuildgui", "abg",
				"Build an application using the GUI.", 0))
		options.addOption(Option("appbuild", "ab",
				"Build an application using current config.", 1))
		options.addOption(Option("appbuildout", "ao",
				"Set output directory for build.", 1))
		
		// project build options
		options.addOption(Option("projectbuild", "pb",
				"Build a project.", 0))
		options.addOption(Option("projectdir", "s",
				"Project directory.", 1))
		options.addOption(Option("outputdir", "o",
				"Where to output the built project.", 1))
	}
	
	fun execute()
	{
		var commandLine = CmdLnParser(args, options).parse()
		for (opstr in commandLine.options()) {
			when (opstr) {
				
				// application build
				
				"appbuildgui" -> {
					ShowGUI()
				}
				
				"appbuild" -> {
					
				}
				
				"appbuildout" -> {
					
				}
				
				// project build
				
				"projectbuild" -> {
					doBuild = true
				}
				
				"projectdir" -> {
					val arg: String ?= commandLine.args(opstr)!!.get(0)
					projectBuilder.setProjectDir(arg)
				}
				
				"outputdir" -> {
					val arg: String ?= commandLine.args(opstr)!!.get(0)
					projectBuilder.setOutputDirectory(arg)
				}
				
				else -> {
					options.printHelp()
				}
			}
		}
		
		if (doBuild) projectBuilder.build()
	}
	
	/**
	 * Show a settings gui that will allow the user to build the editor,
 	 * engine and build tools together in to one package.
	 */
	fun ShowGUI()
	{
		var abw = AppBuildWin()
		abw.setVisible(true)
	}
}