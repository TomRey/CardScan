import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JPanel;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class JPanelMain extends JPanel
	{

	/*------------------------------------------------------------------*\
		|*							Constructeurs							*|
		\*------------------------------------------------------------------*/

	public JPanelMain()
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

		// Layout : Specification
			{
			FlowLayout flowlayout = new FlowLayout(FlowLayout.CENTER);
			setLayout(flowlayout);

			// flowlayout.setHgap(20);
			// flowlayout.setVgap(20);
			}

		// JComponent : add

		}

	private void control()
		{
		File imageFile = new File("images/nomnum.png");
		//File imageFile = new File("eurotext.bmp");
        ITesseract instance = new Tesseract();  // JNA Interface Mapping
        //ITesseract instance = new Tesseract1(); // JNA Direct Mapping

        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.out.println("ERREUR");
        }

		}

	private void appearance()
		{
		// rien
		}

	/*------------------------------------------------------------------*\
		|*							Attributs Private						*|
		\*------------------------------------------------------------------*/

	// Tools
	}
