/*****************************************************************
 *
 * Author: Brandon
 * Date Created: 27 Oct. 2017
 * File : Console.java
 *
 * This software is open source and released under the MIT 
 * licence.
 *
 * Copyright (c) 2017 Brandon Quinn
 *
 *****************************************************************/

package elara.builder.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/*****************************************************************
 *
 * Console
 *
 * Description:
 *
 *****************************************************************/

public class Console extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	private JTextArea textArea;
	
	public void writeln(String log)
	{
		textArea.setText(textArea.getText() + "\n" + log);
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	/**
	 * Create the dialog.
	 */
	public Console()
	{
		setTitle("Console");
		setBounds(100, 100, (int) (Toolkit.getDefaultToolkit().getScreenSize().width / 1.5f), 
				(int) (Toolkit.getDefaultToolkit().getScreenSize().height / 1.3f));
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				textArea = new JTextArea();
				textArea.setEditable(false);
				textArea.setFont(new Font("Consolas", Font.PLAIN, 11));
				textArea.setWrapStyleWord(true);
				textArea.setLineWrap(true);
				scrollPane.setViewportView(textArea);
			}
		}
	}


}
