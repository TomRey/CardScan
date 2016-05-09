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
		this.parent = parent;
		geometry();
		control();
		appearance();
		}

	/*------------------------------------------------------------------*\
		|*							Methodes Public							*|
		\*------------------------------------------------------------------*/

	public void setSlider(int crop, int treatment)
		{
		carte.crop(crop);
		carte.treatment(treatment);
		slider.setValue(crop);
		sliderThres.setValue(treatment);
		}

	public void setCard(Card carte)
		{
		this.carte = carte;
		enableCard(true);
		repaint();
		}

	/*------------------------------*\
		|*				Set				*|
		\*------------------------------*/

	/*------------------------------*\
		|*				Get				*|
		\*------------------------------*/

	/*------------------------------------------------------------------*\
		|*							Methodes Private						*|
		\*------------------------------------------------------------------*/

	private void enableCard(boolean bool)
		{
		button.setEnabled(bool);
		slider.setEnabled(bool);
		sliderThres.setEnabled(bool);
		}

	private void geometry()
		{
		// JComponent : Instanciation
		slider = new JSlider();
		sliderThres = new JSlider();
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
		add(sliderThres);
		}

	private void control()
		{
		enableCard(false);
		Dimension dim = new Dimension(300, 20);
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

		sliderThres.setSize(dim);
		sliderThres.setPreferredSize(dim);
		sliderThres.setLocation(10, 210);
		sliderThres.setMaximum(255);
		sliderThres.setMinimum(0);
		sliderThres.setValue(112);
		sliderThres.addChangeListener(new ChangeListener()
			{

			@Override
			public void stateChanged(ChangeEvent e)
				{
				// TODO Auto-generated method stub
				carte.treatment(((JSlider)e.getSource()).getValue());
				repaint();
				}
			});

		dim = new Dimension(200, 20);
		button.setSize(dim);
		button.setPreferredSize(dim);
		button.setLocation(60, 410);
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

		dim = new Dimension(300, 210);
		labelResult.setSize(dim);
		labelResult.setPreferredSize(dim);
		labelResult.setLocation(10, 440);
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
			g.drawImage(carte.getCardImage(), 10, 40, 300, 150, null);
			g.drawImage(carte.getCardThres(), 10, 240, 300, 150, null);
			}
		}

	/*------------------------------------------------------------------*\
		|*							Attributs Private						*|
		\*------------------------------------------------------------------*/
	JFrameMain parent;
	JSlider slider;
	JSlider sliderThres;
	JButton button;
	JLabel labelResult;
	Card carte;

	}
