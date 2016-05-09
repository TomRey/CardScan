import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public abstract class Card
	{

	public Card(String imagePath)
		{
		mBase = Imgcodecs.imread(imagePath);
		mGray = new Mat(mBase.size(), CvType.CV_8U);
		mTransfo = new Mat(mBase.size(), CvType.CV_8U);
		try
			{
			imgCard = matToImg(mBase);
			}
		catch (IOException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		Imgproc.cvtColor(mBase, mGray, Imgproc.COLOR_BGR2GRAY);
		this.contours = new ArrayList<MatOfPoint>();
		}

	public void crop(int thres)
		{
		contours.clear();
		Imgproc.threshold(mGray, mTransfo, thres, 255, Imgproc.THRESH_BINARY);
		Imgproc.findContours(mTransfo, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
		findCard();
		infoRect = getRectInfo();
		matCard = new Mat(mBase, cardRect);
		matInfo = new Mat(mBase, infoRect);
		try
			{
			imgCard = matToImg(matCard);
			}
		catch (IOException e)
			{
			e.printStackTrace();
			}
		}

	public BufferedImage getCardImage()
		{
		return imgCard;
		}

	public void process()
		{
		treatment();
		hideInfo();
		Imgcodecs.imwrite(PATH_IMG_WITH_TREATMENT, mTreatment);
		ocrAnalyse();
		}

	protected abstract void treatment();

	protected abstract void hideInfo();

	protected abstract void printInfos(String[] result);

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

	public String getInfo()
		{
		return infos;
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
		for(MatOfPoint contour:contours)
			{
			double area = Imgproc.contourArea(contour);
			if (area > maxArea && area < imgArea)
				{
				maxArea = area;
				cardContour = contour;
				}
			}
		cardRect = Imgproc.boundingRect(cardContour);
		}

	private BufferedImage matToImg(Mat mat) throws IOException
		{
		MatOfByte bytemat = new MatOfByte();

		Imgcodecs.imencode(".jpg", mat, bytemat);

		byte[] bytes = bytemat.toArray();

		InputStream input = new ByteArrayInputStream(bytes);
		return ImageIO.read(input);
		}

	protected Mat mBase, mGray;
	protected Mat mTransfo, mTreatment;
	protected Mat matInfo, matCard;

	protected List<MatOfPoint> contours;
	protected MatOfPoint cardContour;
	protected Rect cardRect, infoRect;

	protected double zoneInfo;
	protected int thresold;
	protected String infos;
	protected BufferedImage imgCard;

	public static final String PATH_IMG_WITH_TREATMENT = "images/imgToAnalyse.jpg";
	}
