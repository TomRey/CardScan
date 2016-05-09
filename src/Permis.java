import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Permis extends Card
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Permis(String imagePath)
		{
		super(imagePath);
		this.thresold = 115;
		this.zoneInfo = 0.4;
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
		Imgproc.threshold(mTreatment, mTreatment, 50, 255, Imgproc.THRESH_BINARY_INV);
		Imgproc.resize(mTreatment, mTreatment, new Size(mTreatment.width() * 5, mTreatment.height() * 5));
		Imgproc.erode(mTreatment, mTreatment, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2, 2)));
		}

	@Override
	protected void hideInfo()
		{
		Imgproc.rectangle(mTreatment, new Point(mTreatment.width() - (mTreatment.width() * 0.1), 0), new Point(mTreatment.width(), mTreatment.height() * 0.1), new Scalar(0), (int)(mTreatment.width() * 0.1));
		}

	@Override
	protected void printInfos(String[] result)
		{
		infos = "<html><p>PERMIS DE CONDUIRE</p>";
		for(int i = 0; i < result.length; i++)
			{
			infos += "<p>" + result[i].replaceAll(" ", "") + "</p>";
			}
		infos += "</html>";
		}
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
