/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop 
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/

package chat.client;

//#MIDP_EXCLUDE_FILE

import java.awt.*;
import java.awt.event.*;

import chat.client.agent.ChatClientAgent;
import chat.client.agent.ModeratorAgent;

/**
   @author Giovanni Caire - TILAB
 */
public class AWTModeratorGui extends Frame implements ChatGui {
	private ModeratorAgent myAgent;
	private TextField writeTf;
	private TextArea allTa;
	private ParticipantsFrame participantsFrame;
	
	public AWTModeratorGui(ModeratorAgent a) {
		myAgent = a;
		
		setTitle(myAgent.getLocalName());
		setSize(getProperSize(256, 320));
		Panel p = new Panel();
		p.setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		
		allTa = new TextArea();
		allTa.setEditable(false);
		allTa.setBackground(Color.white);
		add(allTa, BorderLayout.CENTER);
		
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		show();
	}
	
	public void notifyParticipantsChanged(String[] names) {
		if (participantsFrame != null) {
			participantsFrame.refresh(names);
		}
	}
	
	public void notifySpoken(String speaker, String sentence) {
		allTa.append(speaker+": "+sentence+"\n");
	}
	
	Dimension getProperSize(int maxX, int maxY) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width < maxX ? screenSize.width : maxX);
		int y = (screenSize.height < maxY ? screenSize.height : maxY);
		return new Dimension(x, y);
	}
	
	public void dispose() {
		participantsFrame.dispose();
		super.dispose();
	}
}



