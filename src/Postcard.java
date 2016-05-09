import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Postcard extends Card
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/
	public Postcard(String imagePath)
		{
		super(imagePath);
		this.thresold = 235;
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
	protected void treatment(int thres)
		{
		Imgproc.cvtColor(matInfo, mTreatment, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(mTreatment, mTreatment, thres,255, Imgproc.THRESH_BINARY_INV);//180
		Imgproc.resize(mTreatment, mTreatment, new Size(mTreatment.width()*2, mTreatment.height()*2));
		super.treatment(thres);
		}

	@Override
	protected void hideInfo()
		{
		//Imgproc.rectangle(mTreatment, new Point(infoRect.width - (infoRect.width * 0.1), 0), new Point(infoRect.width, infoRect.height*0.1), new Scalar(0), (int)(infoRect.width*0.1));
		}

	@Override
	protected void printInfos(String[] result)
		{
		infos = "<html><p>POSTCARD</p>";
		for(int i = 0; i < result.length; i++)
			{
			infos += "<p>"+result[i]+"</p>";
			}
		infos += "</html>";
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/
	}

