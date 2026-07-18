package vokabeltrainer.common.main;

public final class Model
{   
   public Data data;

   public Model(Settings settings, Common common, View view)
   {
      initUserExpressionDatabase(settings, common, view);
      initUserImageData(settings, common, view);
      // TODO initUserSoundData
   }
   
   private void initUserExpressionDatabase(Settings settings, Common common, View view)
   {
      data = new Data(settings);
      data.initDatabase(common, view);
   }
   
   private void initUserImageData(Settings settings, Common common, View view)
   {
      ImageData imageData = new ImageData(settings, data);
      imageData.initImageDataBase(common, view);
   }
}
