import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AppFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JTextPane textPane;
	private JButton btnNewButton;
	public String word ="";
	private JButton btnNewButton_1;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmAbout;
	private JMenuItem mntmExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppFrame frame = new AppFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, "All rights belongs to Gordan Gigovic.\n Email: gigovic.gordan@gmail.com");

			}
		});
		mnNewMenu.add(mntmAbout);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][][][][][grow]", "[][][][26.00][grow]"));
		
		lblNewLabel = new JLabel("Enter word");
		contentPane.add(lblNewLabel, "cell 0 0 7 1,alignx center");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					word = textField.getText().trim();
					String result = convert(word);
					textPane.setText(result);
		            
		        }
			}
		});
		textField.setToolTipText("Enter word that you want to be converted");
		contentPane.add(textField, "cell 0 1 7 1,growx");
		textField.setColumns(10);
		
		btnNewButton = new JButton("CONVERT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				word = textField.getText().trim();
				String result = convert(word);
				textPane.setText(result);
				
			}
		});
		contentPane.add(btnNewButton, "cell 1 2 3 2,grow");
		
		btnNewButton_1 = new JButton("ERASE ALL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(""); 
				textPane.setText("");
				
			}
		});
		contentPane.add(btnNewButton_1, "cell 4 2 2 2,growy");
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setToolTipText("There will be displayed converted text.");
		contentPane.add(textPane, "cell 0 4 7 1,grow");
		
		
	}
	public String convert(String str){
		String returning = "";
		
		String[] array = str.split("");
		
		
		
		for (int i = 0; i < array.length; i++) {
			if(i != array.length-1){
				int next = i+1;
				returning += array[i]+" ";
				returning += array[i]+array[next]+" ";
			}else{
				returning += array[i]+" ";
			}
			
		}
		
		
		return returning;
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
