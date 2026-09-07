package vokabeltrainer.common.main;

public final class Model
{   
   public Data data;
   public ImageData imageData;

   public Model(App app, Common common, View view)
   {
      initUserExpressionDatabase(app, common, view);
      initUserImageData(app, common, view);
      // TODO initUserSoundData
   }
   
   private void initUserExpressionDatabase(App app, Common common, View view)
   {
      data = new Data();
      data.initDatabase(app, common, view);
   }
   
   private void initUserImageData(App app, Common common, View view)
   {
      imageData = new ImageData(data);
      imageData.initImageDataBase(app, common, view);
   }
}
