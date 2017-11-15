package elara.builder.core

import org.apache.commons.cli.*;

/*
 * This application has two functions. Taking a project and turinging
 * into the game files that the engine will load.
 *
 * The other function is for development. This is where it will take
 * in all the libraries, assets, configs and src and build a
 * fully working application that combined the elara editor, game engine libraries
 * and build tools and anything else in to one package/directory so they can all work
 * together for release.
 */

fun main(args: Array<String>)
{
	var argHandler = ArgHandler(args)
	argHandler.execute()
}