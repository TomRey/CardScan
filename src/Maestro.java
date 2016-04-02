import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Maestro extends Card
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Maestro(String imagePath)
		{
		super(imagePath);
		this.thresold = 115;
		this.zoneInfo = 0.3;
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

	@Override
	protected void treatment()
		{
		mTreatment = new Mat(matInfo.size(), CvType.CV_8U);
		Imgproc.cvtColor(matInfo, mTreatment, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(mTreatment, mTreatment, thresold, 255, Imgproc.THRESH_BINARY);
		}

	@Override
	protected void hideInfo()
		{
		Imgproc.rectangle(mTreatment, new Point(infoRect.width - (infoRect.width * 0.3), 0), new Point(infoRect.width, 0), new Scalar(0), infoRect.height);
		super.hideInfo();
		}

	@Override
	protected void printInfos(String[] result)
		{
		System.out.println("MAESTRO");
		System.out.println(result[0] + "\n" + result[2].subSequence(0, result[2].length() - 2) + "-" + result[2].charAt(result[2].length() - 1));
		System.out.println("\n");
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
