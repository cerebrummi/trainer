package vokabeltrainer.common.main;

public final class Model
{   
   // package on purpose
   Model()
   {
      // nothing
   }
   
   public View initImageData(Common common, View view)
   {
      ImageData.initImageDataBase(common, view);
      return view;
   }
   
   public View initSoundData(Common common, View view)
   {
      SoundData.initSoundDataBase(common, view);
      return view;
   }
   
   public View initDatabase(Common common, View view)
   {
      Data.initDatabase(common, view);
      return view;
   }
}
