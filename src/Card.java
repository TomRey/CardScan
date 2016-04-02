import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Card
	{

	public Card(String imagePath)
		{
		mBase = Imgcodecs.imread(imagePath);
		mTransfo = new Mat(mBase.size(), CvType.CV_8U);
		this.contours = new ArrayList<MatOfPoint>();
		}

	public void process()
		{
		crop();
		treatment();
		hideInfo();
		ocrAnalyse();
		}

	public void crop()
		{
		Imgproc.cvtColor(mBase, mTransfo, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(mTransfo, mTransfo, this.thresold, 255, Imgproc.THRESH_BINARY);
		Imgcodecs.imwrite("images/thresBase.jpg", mTransfo);
		Imgproc.findContours(mTransfo, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		findCard();
		infoRect = getRectInfo();
		matCard =  new Mat(mBase, cardRect);
		Imgcodecs.imwrite("images/cropCard.jpg", matCard);
		matInfo = new Mat(mBase, infoRect);
		}

	protected void treatment()
		{
		}

	protected void hideInfo()
		{
		Imgcodecs.imwrite(PATH_IMG_WITH_TREATMENT, mTreatment);
		}

	public void ocrAnalyse()
		{
		File imageFile = new File(PATH_IMG_WITH_TREATMENT);
		ITesseract instance = new Tesseract(); // JNA Interface Mapping

		try
			{
			String[] result = instance.doOCR(imageFile).split("\n");
			printInfos(result);
			}
		catch (TesseractException e)
			{
			System.out.println("ERREUR");
			}
		}

	protected void printInfos(String[] result)
		{

		}

	private Rect getRectInfo()
		{
		int hCard = cardRect.height;
		int hInfo = (int)(hCard * zoneInfo);

		return new Rect(cardRect.x, cardRect.y + (hCard - hInfo), cardRect.width, hInfo);
		}

	private void findCard()
		{
		double maxArea = 0;
		double imgArea = mBase.width() * mBase.height() * 0.9;
		int indice = 0, cpt = 0;
		for(MatOfPoint contour:contours)
			{
			double area = Imgproc.contourArea(contour);
			if (area > maxArea && area < imgArea)
				{
				maxArea = area;
				cardContour = contour;
				indice = cpt;
				}
			cpt++;
			}
		cardRect = Imgproc.boundingRect(cardContour);
		}

	protected Mat mBase;
	protected Mat mTransfo, mTreatment;
	protected Mat matInfo, matCard;

	protected List<MatOfPoint> contours;
	protected MatOfPoint cardContour;
	protected Rect cardRect, infoRect;

	protected double zoneInfo;
	protected int thresold;

	public static final String PATH_IMG_WITH_TREATMENT = "images/imgToAnalyse.jpg";
	}
