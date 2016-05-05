import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JPanelMain extends JPanel
	{

	/*------------------------------------------------------------------*\
		|*							Constructeurs							*|
		\*------------------------------------------------------------------*/

	public JPanelMain(JFrameMain parent)
		{
		//carte = new Card("images/carte.jpg");
		//carte = new Permis("images/carte2.jpg");
		//carte = new Postcard("images/carte3.jpg");
		this.parent = parent;
		geometry();
		control();
		appearance();
		}

	public void setCard(Card carte)
		{
		this.carte = carte;
		enableCard(true);
		repaint();
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
		slider = new JSlider();
		button = new JButton("Process");
		labelResult = new JLabel("");
		// Layout : Specification
			{
			setLayout(null);
			}

		// JComponent : add
		add(slider);
		add(button);
		add(labelResult);
		}

	private void enableCard(boolean bool)
		{
		button.setEnabled(bool);
		slider.setEnabled(bool);
		}

	private void control()
		{
		enableCard(false);
		Dimension dim = new Dimension(200, 20);
		slider.setSize(dim);
		slider.setPreferredSize(dim);
		slider.setLocation(10, 10);
		slider.setMaximum(255);
		slider.setMinimum(0);
		slider.setValue(112);
		slider.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent e)
				{
				// TODO Auto-generated method stub
				carte.crop(((JSlider)e.getSource()).getValue());
				repaint();
				}
			});

		dim = new Dimension(100, 20);
		button.setSize(dim);
		button.setPreferredSize(dim);
		button.setLocation(220, 10);
		button.addActionListener(new ActionListener()
			{

			@Override
			public void actionPerformed(ActionEvent e)
				{
				// TODO Auto-generated method stub
				carte.process();
				labelResult.setText(carte.getInfo());
				}
			});

		dim = new Dimension(310, 200);
		labelResult.setSize(dim);
		labelResult.setPreferredSize(dim);
		labelResult.setLocation(10, 210);
		labelResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

	private void appearance()
		{
		// rien
		}

	@Override
	public void paintComponent(Graphics g)
		{
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (carte != null)
			{
			g.drawImage(carte.getCardImage(), 10, 40, 310, 155, null);
			}
		}

	/*------------------------------------------------------------------*\
		|*							Attributs Private						*|
		\*------------------------------------------------------------------*/
	JFrameMain parent;
	JSlider slider;
	JButton button;
	JLabel labelResult;
	Card carte;

	}
