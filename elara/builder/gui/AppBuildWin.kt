package elara.builder.gui

import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import elara.builder.core.*;
import elara.builder.appbuild.*;

class AppBuildWin(): JFrame(), ActionListener
{
	var editorLocLbl: JLabel = JLabel("Editor Location:")
	var editorLocField: JTextField = JTextField()
	var editorLocBtn: JButton = JButton("Change Editor Location")
	
	var buildToolLocLbl: JLabel = JLabel("Build Tools Location:")
	var buildToolLocField: JTextField = JTextField()
	var buildToolBtn: JButton = JButton("Change Build Tool Location")
	
	var engineLocLbl: JLabel = JLabel("Game Engine Location:")
	var engineLocField: JTextField = JTextField()
	var engineLocBtn: JButton = JButton("Change Engine Location")
	
	var cancelBtn: JButton = JButton("Cancel")
	var buildBtn: JButton = JButton("Build")
	
	init
	{
		setTitle("Application Build Tool")
		setDefaultCloseOperation(EXIT_ON_CLOSE)
		setSize(380, 290)
		setLocationRelativeTo(null)
		setLayout(null)
		
		editorLocField.setText(Config.editorLoc())
		buildToolLocField.setText(Config.buildToolLoc())
		engineLocField.setText(Config.engineLoc())
		
		editorLocLbl.setBounds(5, 5, 100, 25)
		editorLocField.setBounds(105, 5, 250, 25)
		editorLocField.setEditable(false)
		editorLocBtn.setBounds(5, 35, 250, 25)
		editorLocBtn.addActionListener(this)
		
		buildToolLocLbl.setBounds(5, 75, 125, 25)
		buildToolLocField.setBounds(130, 75, 225, 25)
		buildToolLocField.setEditable(false)
		buildToolBtn.setBounds(5, 105, 250, 25)
		buildToolBtn.addActionListener(this)
		
		engineLocLbl.setBounds(5, 145, 150, 25)
		engineLocField.setBounds(150, 145, 205, 25)
		engineLocField.setEditable(false)
		engineLocBtn.setBounds(5, 175, 250, 25)
		engineLocBtn.addActionListener(this)
		
		buildBtn.setBounds(5, 220, 150, 25)
		buildBtn.addActionListener(this)
		
		cancelBtn.setBounds(205, 220, 150, 25)
		cancelBtn.addActionListener(this)
		
		add(editorLocLbl)
		add(editorLocField)
		add(editorLocBtn)
		add(buildToolLocLbl)
		add(buildToolLocField)
		add(buildToolBtn)
		add(engineLocLbl)
		add(engineLocField)
		add(engineLocBtn)
		add(cancelBtn)
		add(buildBtn)
	}
	
	override
	fun actionPerformed(e: ActionEvent)
	{
		var src = e.getSource()
		
		/*
 			Handle events for building and cancelling.
 		*/
		when (src) {
			buildBtn -> {
				var fileChooser: JFileChooser = JFileChooser()
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
				fileChooser.setDialogTitle("Select save destination")
				val choice: Int = fileChooser.showOpenDialog(null)
				
				if (choice == JFileChooser.APPROVE_OPTION) {
					var name: String = JOptionPane.showInputDialog(null, "Name of build", "Name")
					
					if (!name.equals("")) {
						AppBuilder.build(name, fileChooser.getSelectedFile())
					}
				}
			}
			
			cancelBtn -> {
				setVisible(false)
			}
		}
		
		/*
 			Handle events for location change buttons.
 		 */
		when (src) {
			editorLocBtn -> {
				var fileChooser: JFileChooser = JFileChooser()
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
				val choice: Int = fileChooser.showOpenDialog(null)
				
				if (choice == JFileChooser.APPROVE_OPTION) {
					val file: File = fileChooser.getSelectedFile()
					editorLocField.setText(file.absolutePath)
					Config.setEditorLoc(file.absolutePath)
				}
			}
			
			buildToolBtn -> {
				var fileChooser: JFileChooser = JFileChooser()
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
				val choice: Int = fileChooser.showOpenDialog(null)
				
				if (choice == JFileChooser.APPROVE_OPTION) {
					val file: File = fileChooser.getSelectedFile()
					buildToolLocField.setText(file.absolutePath)
					Config.setBuildToolLoc(file.absolutePath)
				}
			}
			
			engineLocBtn -> {
								var fileChooser: JFileChooser = JFileChooser()
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY)
				val choice: Int = fileChooser.showOpenDialog(null)
				
				if (choice == JFileChooser.APPROVE_OPTION) {
					val file: File = fileChooser.getSelectedFile()
					engineLocField.setText(file.absolutePath)
					Config.setEngineLoc(file.absolutePath)
				}
			}
		}
	}
}