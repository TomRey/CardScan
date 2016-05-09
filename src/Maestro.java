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
	protected void treatment(int thres)
		{
		Imgproc.cvtColor(matInfo, mTreatment, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(mTreatment, mTreatment, thres, 255, Imgproc.THRESH_BINARY);
		super.treatment(thres);
		}

	@Override
	protected void hideInfo()
		{
		Imgproc.rectangle(mTreatment, new Point(infoRect.width - (infoRect.width * 0.3), 0), new Point(infoRect.width, 0), new Scalar(0), infoRect.height);
		}

	@Override
	protected void printInfos(String[] result)
		{
		infos = "<html><p>MAESTRO</p>";
		for(int i = 0; i < result.length; i++)
			{
			if (i != 1)
				{
				infos += "<p>"+result[i]+"</p>";
				}
			}
		infos += "</html>";
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}
