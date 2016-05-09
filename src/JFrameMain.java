import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class JFrameMain extends JFrame
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public JFrameMain()
		{
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private void geometry()
		{
		// JComponent : Instanciation
		panelMain = new JPanelMain(this);

		// Layout : Specification
			{
			BorderLayout borderLayout = new BorderLayout();
			setLayout(borderLayout);
			}

		// JComponent : add
		add(panelMain, BorderLayout.CENTER);
		addMenu();
		}

	private void control()
		{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		}

	private void appearance()
		{
		setSize(340, 740);
		setLocationRelativeTo(null); // frame centrer
		setVisible(true); // last!
		}

	private void addMenu()
		{
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu("Fichier");
		menu.setMnemonic(KeyEvent.VK_E);
		menu.getAccessibleContext().setAccessibleDescription("Maestro, Postcard, Permis");
		menuBar.add(menu);
		menuBar.setOpaque(false);
		// a group of JMenuItems
		menuItem = new JMenuItem("Maestro");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));

		menuItem.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				String path = selectCard();
				if(path != null)
					{
					Maestro carte = new Maestro(path);
					panelMain.setCard(carte);
					panelMain.setSlider(115, 180);
					}
				}
			});

		menu.add(menuItem);

		menuItem = new JMenuItem("Permis");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_D);
		menuItem.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				String path = selectCard();
				if(path != null)
					{
					Permis carte = new Permis(path);
					panelMain.setCard(carte);
					panelMain.setSlider(115, 50);
					}
				}
			});
		menu.add(menuItem);

		menuItem = new JMenuItem("Postcard");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		menuItem.setMnemonic(KeyEvent.VK_P);
		menuItem.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				String path = selectCard();
				if(path != null)
					{
					Postcard carte = new Postcard(path);
					panelMain.setCard(carte);
					panelMain.setSlider(235, 180);
					}
				}
			});

		menu.add(menuItem);

		this.setJMenuBar(menuBar);
		}

	private String selectCard()
		{
		Preferences prefs = Preferences.userRoot().node(getClass().getName());
		JFileChooser fileChooser = new JFileChooser(prefs.get(LAST_USED_FOLDER, new File(".").getAbsolutePath()));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);
		int result = fileChooser.showOpenDialog(JFrameMain.this);
		if (result == JFileChooser.APPROVE_OPTION)
			{
			File selectedFile = fileChooser.getSelectedFile();
			prefs.put(LAST_USED_FOLDER, selectedFile.getParent());
			return selectedFile.getAbsolutePath();
			}
		return null;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Tools
	private JPanelMain panelMain;
	private final String LAST_USED_FOLDER = "lastFolder";
	}
