package vokabeltrainer;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Random;

import vokabeltrainer.editing.HebrewLetter;

public class ApplicationImages
{
   private static BufferedImage image;
   private static List<BufferedImage> greenImages;
   private static List<BufferedImage> blueImages;
   private static BufferedImage trashcan;
   private static BufferedImage shredder;
   private static BufferedImage copy;
   private static BufferedImage clear;
   private static BufferedImage select;
   private static BufferedImage newWord;
   private static BufferedImage saveWord;
   private static BufferedImage deleteWord;
   private static BufferedImage restore;
   private static BufferedImage send;
   private static BufferedImage start;
   private static BufferedImage stop;
   private static BufferedImage search;
   private static BufferedImage okaySave;
   private static BufferedImage empty;
   private static BufferedImage infoCursor;
   private static BufferedImage infoButtonIcon;
   private static BufferedImage infoIcon;
   private static BufferedImage trashcanBackground;
   private static BufferedImage startImage;
   private static BufferedImage errorImage;
   private static BufferedImage texturedBackground;
   private static BufferedImage arrow;
   private static BufferedImage cancel;
   private static BufferedImage done;
   private static BufferedImage emptyList;
   private static BufferedImage logo24;
   private static BufferedImage logo150;
   private static BufferedImage turn;
   private static BufferedImage answerOkay;
   private static BufferedImage answerNotOkay;
   private static BufferedImage answerUndecided;
   private static BufferedImage reward;

   private static Map<HebrewLetter, BufferedImage> letterPicturesMap;
   private static BufferedImage letterEmpty;
   private static BufferedImage letterNone;
   private static BufferedImage work;
   private static BufferedImage soundOn;
   private static BufferedImage soundOff;

   public static void setImage(BufferedImage image)
   {
      ApplicationImages.image = image;
   }

   public static BufferedImage getImage()
   {
      return image;
   }

   public static List<BufferedImage> getGreenImages()
   {
      return greenImages;
   }

   public static void setGreenImages(List<BufferedImage> greenImages)
   {
      ApplicationImages.greenImages = greenImages;
   }

   public static BufferedImage getTrashcan()
   {
      return trashcan;
   }

   public static void setTrashcan(BufferedImage trashcan)
   {
      ApplicationImages.trashcan = trashcan;
   }

   public static BufferedImage getShredder()
   {
      return shredder;
   }

   public static void setShredder(BufferedImage shredder)
   {
      ApplicationImages.shredder = shredder;
   }

   public static BufferedImage getRandomGreenImage()
   {
      Random random = new Random();
      int pictureNumber = random.nextInt(greenImages.size());
      return greenImages.get(pictureNumber);
   }

   public static BufferedImage getRandomBlueImage()
   {
      Random random = new Random();
      int pictureNumber = random.nextInt(blueImages.size());
      return blueImages.get(pictureNumber);
   }

   public static void setCopy(BufferedImage copy)
   {
      ApplicationImages.copy = copy;
   }

   public static BufferedImage getCopy()
   {
      return copy;
   }

   public static BufferedImage getClear()
   {
      return clear;
   }

   public static void setClear(BufferedImage clear)
   {
      ApplicationImages.clear = clear;
   }

   public static BufferedImage getSelect()
   {
      return select;
   }

   public static void setSelect(BufferedImage selected)
   {
      ApplicationImages.select = selected;
   }

   public static BufferedImage getNewWord()
   {
      return newWord;
   }

   public static void setNewWord(BufferedImage newWord)
   {
      ApplicationImages.newWord = newWord;
   }

   public static BufferedImage getSaveWord()
   {
      return saveWord;
   }

   public static void setSaveWord(BufferedImage saveWord)
   {
      ApplicationImages.saveWord = saveWord;
   }

   public static BufferedImage getDeleteWord()
   {
      return deleteWord;
   }

   public static void setDeleteWord(BufferedImage deleteWord)
   {
      ApplicationImages.deleteWord = deleteWord;
   }

   public static BufferedImage getRestore()
   {
      return restore;
   }

   public static void setRestore(BufferedImage restore)
   {
      ApplicationImages.restore = restore;
   }

   public static BufferedImage getSend()
   {
      return send;
   }

   public static void setSend(BufferedImage send)
   {
      ApplicationImages.send = send;
   }

   public static BufferedImage getStart()
   {
      return start;
   }

   public static void setStart(BufferedImage start)
   {
      ApplicationImages.start = start;
   }

   public static BufferedImage getStop()
   {
      return stop;
   }

   public static void setStop(BufferedImage stop)
   {
      ApplicationImages.stop = stop;
   }

   public static BufferedImage getSearch()
   {
      return search;
   }

   public static void setSearch(BufferedImage search)
   {
      ApplicationImages.search = search;
   }

   public static BufferedImage getOkaySave()
   {
      return okaySave;
   }

   public static void setOkaySave(BufferedImage okaySave)
   {
      ApplicationImages.okaySave = okaySave;
   }

   public static BufferedImage getEmpty()
   {
      return empty;
   }

   public static void setEmpty(BufferedImage empty)
   {
      ApplicationImages.empty = empty;
   }

   public static BufferedImage getInfoCursor()
   {
      return infoCursor;
   }

   public static void setInfoCursor(BufferedImage infoCursor)
   {
      ApplicationImages.infoCursor = infoCursor;
   }

   public static BufferedImage getInfoButtonIcon()
   {
      return infoButtonIcon;
   }

   public static void setInfoButtonIcon(BufferedImage infoButtonIcon)
   {
      ApplicationImages.infoButtonIcon = infoButtonIcon;
   }

   public static BufferedImage getInfoIcon()
   {
      return infoIcon;
   }

   public static void setInfoIcon(BufferedImage infoIcon)
   {
      ApplicationImages.infoIcon = infoIcon;
   }

   public static BufferedImage getTrashcanBackground()
   {
      return trashcanBackground;
   }

   public static void setTrashcanBackground(BufferedImage trashcanBackground)
   {
      ApplicationImages.trashcanBackground = trashcanBackground;
   }

   public static BufferedImage getStartImage()
   {
      return startImage;
   }

   public static void setStartImage(BufferedImage startImage)
   {
      ApplicationImages.startImage = startImage;
   }

   public static BufferedImage getErrorImage()
   {
      return errorImage;
   }

   public static void setErrorImage(BufferedImage errorImage)
   {
      ApplicationImages.errorImage = errorImage;
   }

   public static BufferedImage getTexturedBackground()
   {
      return texturedBackground;
   }

   public static void setTexturedBackground(BufferedImage texturedBackground)
   {
      ApplicationImages.texturedBackground = texturedBackground;
   }

   public static BufferedImage getArrow()
   {
      return arrow;
   }

   public static void setArrow(BufferedImage arrow)
   {
      ApplicationImages.arrow = arrow;
   }

   public static BufferedImage getDone()
   {
      return done;
   }

   public static void setDone(BufferedImage done)
   {
      ApplicationImages.done = done;
   }

   public static BufferedImage getEmptyList()
   {
      return emptyList;
   }

   public static void setEmptyList(BufferedImage emptyList)
   {
      ApplicationImages.emptyList = emptyList;
   }

   public static BufferedImage getLogo24()
   {
      return logo24;
   }

   public static void setLogo24(BufferedImage logo24)
   {
      ApplicationImages.logo24 = logo24;
   }

   public static BufferedImage getLogo150()
   {
      return logo150;
   }

   public static void setLogo150(BufferedImage logo150)
   {
      ApplicationImages.logo150 = logo150;
   }

   public static Map<HebrewLetter, BufferedImage> getLetterPicturesMap()
   {
      return letterPicturesMap;
   }

   public static void setLetterPicturesMap(
         Map<HebrewLetter, BufferedImage> letterPicturesMap)
   {
      ApplicationImages.letterPicturesMap = letterPicturesMap;
   }

   public static BufferedImage getTurn()
   {
      return turn;
   }

   public static void setTurn(BufferedImage turn)
   {
      ApplicationImages.turn = turn;
   }

   public static BufferedImage getAnswerOkay()
   {
      return answerOkay;
   }

   public static void setAnswerOkay(BufferedImage answerOkay)
   {
      ApplicationImages.answerOkay = answerOkay;
   }

   public static BufferedImage getAnswerNotOkay()
   {
      return answerNotOkay;
   }

   public static void setAnswerNotOkay(BufferedImage answerNotOkay)
   {
      ApplicationImages.answerNotOkay = answerNotOkay;
   }

   public static BufferedImage getAnswerUndecided()
   {
      return answerUndecided;
   }

   public static void setAnswerUndecided(BufferedImage answerUndecided)
   {
      ApplicationImages.answerUndecided = answerUndecided;
   }

   public static List<BufferedImage> getBlueImages()
   {
      return blueImages;
   }

   public static void setBlueImages(List<BufferedImage> blueImages)
   {
      ApplicationImages.blueImages = blueImages;
   }

   public static BufferedImage getCancel()
   {
      return cancel;
   }

   public static void setCancel(BufferedImage cancel)
   {
      ApplicationImages.cancel = cancel;
   }

   public static BufferedImage getReward()
   {
      return reward;
   }

   public static void setReward(BufferedImage reward)
   {
      ApplicationImages.reward = reward;
   }

   public static void setLetterEmpty(BufferedImage letterEmpty)
   {
      ApplicationImages.letterEmpty = letterEmpty;
   }

   public static void setLetterNone(BufferedImage letterNone)
   {
      ApplicationImages.letterNone = letterNone;
   }

   public static BufferedImage getLetterEmpty()
   {
      return letterEmpty;
   }

   public static BufferedImage getLetterNone()
   {
      return letterNone;
   }

   public static void setWork(BufferedImage work)
   {
      ApplicationImages.work = work;
   }

   public static BufferedImage getWork()
   {
      return work;
   }

   public static void setSoundOn(BufferedImage soundOn)
   {
      ApplicationImages.soundOn = soundOn;
   }

   public static BufferedImage getSoundOn()
   {
      return soundOn;
   }

   public static void setSoundOff(BufferedImage soundOff)
   {
      ApplicationImages.soundOff = soundOff;
   }

   public static BufferedImage getSoundOff()
   {
      return soundOff;
   }
}
