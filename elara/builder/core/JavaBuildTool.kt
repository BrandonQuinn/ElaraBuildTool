package elara.builder.core

import org.apache.commons.io.*
import java.io.*
import java.nio.file.*
import elara.builder.core.*
import org.apache.commons.io.filefilter.*
import java.util.*

class JavaBuildTool
{
	companion object JavaBuildTool
	{
		/*
 			Build java/kotlin source.
 		 */
		fun buildJavaSource(src: String, dest: String, libDir: String, mainClass: String)
		{
			// build src
			var tempDir: File = File(dest)
			tempDir.mkdirs()
			
			Debug.info("Project being build at: " + tempDir.absolutePath)
			FileUtils.copyDirectory(File(src), tempDir)
			
			// iterate and add each java file to the command
			val javaFiles = findFiles(tempDir, "java")
			val kotlinFiles = findFiles(tempDir, "kt")
			
			// find libraries and attach them to the command
			Debug.info("Finding libraries in: " + libDir)
			val libs = findLibs(libDir)
			
			// execute the java compiler and wait
			Debug.info("Executing: " + "javac -cp")
			val pr = Runtime.getRuntime().exec("javac -cp " + libs + " " + javaFiles)
			pr.waitFor()
			
			// execute the kotlin compiler and wait
			Debug.info("Executing: " + "cmd /c kotlinc -cp")
			val pr1 = Runtime.getRuntime().exec("cmd /c kotlinc -cp " + libs + " " + kotlinFiles)
			pr1.waitFor()
			
			Debug.info("Source compiled!")
			Debug.info("Finding binary classes...")

			Debug.info("Class files found.")
			Debug.info("Cleaning build directory...")
			deleteSourceIn(tempDir.absolutePath)
			
			// seems to hand around
			FileUtils.deleteDirectory(File(".git"))
			Debug.info("Cleaning done.")
			
			Debug.info("Creating execution scripts...")
			genRunBatFile(tempDir.absolutePath, mainClass)
			genBinExe(tempDir.absolutePath, mainClass)
		}
		
		/*
 			Creates a binary executable which simply executes the java runtime.
 		*/
		fun genBinExe(dest: String, mainClass: String)
		{
			val exe: File = File(dest + "/ee.cpp")
			Debug.info("Creating run C++: " + exe.absolutePath)
			exe.createNewFile()
			
			FileUtils.writeStringToFile(exe, "#include <iostream>\n\n"
				+ "using namespace std;\n\n"
				+ "int main() {\n"
				+ "\tsystem(\"java -cp"
				+ " \\" + "\"./*;../*;" + "\\" + "\" " + mainClass + "\");\n"
				+ "\treturn 0;\n"
				+ "}\n")
			
			Debug.info("Compiling C++ binary executable: " + "g++ " + exe.absolutePath + " -o " + dest + "/ElaraEditor")
			val pr = Runtime.getRuntime().exec("g++ " + exe.absolutePath + " -o " + dest + "/ElaraEditor")
			pr.waitFor()
			
			exe.delete()
		}
		
		/*
 			Creates a bat and sh file with the same functionality as genBinExe().
 		*/
		fun genRunBatFile(dest: String, mainClass: String)
		{
			try {
				val runBat: File = File(dest + "/run_editor.bat")
				val runSh: File = File(dest + "/run_editor.sh")
				runBat.createNewFile()
				runSh.createNewFile()
				FileUtils.writeStringToFile(runBat, "java -cp \"./*;../*;\" " + mainClass)
				FileUtils.writeStringToFile(runSh, "java -cp \"./*;../*;\" " + mainClass)
			} catch(e: IOException) {
				Debug.error("IOException: could not write out either run.bat or run.sh execution script")
			}
		}
		
		/*
 			Fines all sources files with extension .java and deletes them.
		 */
		fun deleteSourceIn(dir: String)
		{
			var iter: Iterator<File> = FileUtils.iterateFiles(File(dir), Array(2, {"java"; "kt"}), true)
			while (iter.hasNext()) {
				iter.next().delete()
			}
		}
		
		/**
		 * Will search a directory for all jar files and create a string out of them for compilation
		 */
		fun findLibs(directory: String): String
		{
			var libStr: String = ""
			
			var iter: Iterator<File> = FileUtils.iterateFiles(File(directory), Array(1, {"jar"}), true)
			while (iter.hasNext()) {
				val file = iter.next()
				Debug.info("\tLibrary found: " + file.absolutePath)
				libStr += "\"" + file.absolutePath + "\";"
			}
			
			return libStr
		}
		
		/*
 			Returns a space separated list of java files in a directory.
		 */
		fun findFiles(dir: File, ext: String): String
		{
			var iter: Iterator<File> = FileUtils.iterateFiles(dir, Array(1, {ext}), true)
			var javaFiles: String = ""
			while (iter.hasNext()) {
				val javaF = (iter.next().path + " ")
				Debug.info("\tSource found: " + javaF)
				javaFiles += javaF
			}
			
			return javaFiles
		}
	}
}