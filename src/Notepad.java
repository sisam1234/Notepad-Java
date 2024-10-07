import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.datatransfer.*;
public class Notepad extends JFrame implements ActionListener{
        JFrame f;
        JTextArea a;
        File file;
        Notepad(){
         f = new JFrame();
        setTitle("Notepad");
        JMenuBar jb = new JMenuBar();
        JMenu file =  new JMenu("File");
        jb.add(file);
        JMenuItem newdoc,open,save;
        newdoc = new JMenuItem("New");
        newdoc.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        save=new JMenuItem("Save");
        save.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        open = new JMenuItem("Open");
        open.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        file.add(newdoc);
        file.add(save);
        file.add(open);
        newdoc.addActionListener(this);
        save.addActionListener(this);
        open.addActionListener(this);
        JMenu edit =  new JMenu("Edit");
        jb.add(edit);
        JMenuItem copy,paste,cut,selectall;
        copy = new JMenuItem("Copy");
        copy.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        paste = new JMenuItem("Paste");
        paste.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        cut =  new JMenuItem("Cut");
        cut.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        selectall =new JMenuItem("Select All");
        selectall.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        edit.add(copy);edit.add(paste);edit.add(cut);edit.add(selectall);
        JMenu view =  new JMenu("View");
        jb.add(view);
        JMenuItem wordwrap,zoomin,zoomout;
        JMenu zoom = new JMenu("Zoom");
        zoomin =new JMenuItem("Zoom in");
        zoomout = new JMenuItem("Zoom out");
        wordwrap = new JMenuItem("Word wrap");
        view.add(zoom);
        zoom.add(zoomin);
        zoom.add(zoomout);
        view.add(wordwrap);
        setJMenuBar(jb);
         a = new JTextArea();
        a.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
        add(a);
        JScrollPane pane = new JScrollPane(a);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setVisible(true);
        add(pane);
        setLocation(200, 150);
        setSize(800, 500);
        setTitle("Untitled.txt - Notepad");
        setVisible(true);

    }
    public void SaveFile(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(a.getText()); // Write the content of JTextArea to the file
        writer.close(); // Close the writer
    }
    public void actionPerformed(ActionEvent e){
        JFileChooser jc = new JFileChooser();
        if(e.getActionCommand().equals("New")){ 
			//new
			this.setTitle("Untitled.txt - Notepad");
			a.setText("");
			f=null;
		}
        else if(e.getActionCommand().equals("Open")){
            int returnValue = jc.showOpenDialog(this); // Open dialog

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jc.getSelectedFile();
                try {
                    // Read the file and display it in the JTextArea
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    a.read(reader, null); // Load the file's content into the JTextArea
                    reader.close();
    
                    // Set the title of the window to the name of the opened file
                    this.setTitle(selectedFile.getName() + " - Notepad");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        else if(e.getActionCommand().equals("Save")){
            if (file != null) {
                // Set current directory if a file is already opened
                jc.setCurrentDirectory(file); // Use the parent directory of the current file
                jc.setSelectedFile(file); // Set the current file as selected
            } else {
                // If no file is opened, use default file name
                jc.setSelectedFile(new File("Untitled.txt"));
            }

            int ret=jc.showSaveDialog(null);
				
			if(ret == JFileChooser.APPROVE_OPTION){
				try{
					
					File fyl=jc.getSelectedFile();
					SaveFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName()+ " - Notepad");
					file=fyl;
					
				}catch(Exception ers2){}
			}
        }
    }
        public static void main(String[] args) {
            new Notepad();
    }
}
