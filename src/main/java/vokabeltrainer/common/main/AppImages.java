package vokabeltrainer.common.main;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Random;

import vokabeltrainer.editing.NikudLetter;

public class AppImages
{
   private BufferedImage image;
   private BufferedImage dreidel;
   private BufferedImage hebrewLetters;
   private List<BufferedImage> greenImages;
   private List<BufferedImage> blueImages;
   private BufferedImage trashcan;
   private BufferedImage shredder;
   private BufferedImage copy;
   private BufferedImage copy2;
   private BufferedImage clear;
   private BufferedImage cut;
   private BufferedImage paste;
   private BufferedImage select;
   private BufferedImage selectDone;
   private BufferedImage newWord;
   private BufferedImage saveWord;
   private BufferedImage deleteWord;
   private BufferedImage restore;
   private BufferedImage send;
   private BufferedImage start;
   private BufferedImage stop;
   private BufferedImage search;
   private BufferedImage okaySave; // small
   private BufferedImage okaySaveIcon; // big
   private BufferedImage empty;
   private BufferedImage infoCursor;
   private BufferedImage infoButtonIcon;
   private BufferedImage infoIcon;
   private BufferedImage toggleCursor;
   private BufferedImage toggleButtonIcon;
   private BufferedImage trashcanBackground;
   private BufferedImage startImage;
   private BufferedImage errorImage;
   private BufferedImage texturedBackground;
   private BufferedImage arrow;
   private BufferedImage cancel;
   private BufferedImage done;
   private BufferedImage emptyList;

   private BufferedImage logo24;
   private BufferedImage logo150;
   private BufferedImage logo;
   private BufferedImage logoFolder;
   private BufferedImage logoFolderEmpty;

   private BufferedImage turn;
   private BufferedImage answerOkay;
   private BufferedImage answerNotOkay;
   private BufferedImage answerUndecided;
   private BufferedImage reward;

   private Map<NikudLetter, BufferedImage> letterPicturesMap;
   private BufferedImage letterEmpty;
   private BufferedImage letterNone;
   private BufferedImage work;
   private BufferedImage soundOn;
   private BufferedImage soundOff;

   private BufferedImage back;
   private BufferedImage lock;
   private BufferedImage eyeOnly;
   private BufferedImage eye;
   private BufferedImage languages;
   private BufferedImage newWordSmall;
   private BufferedImage l18n;

   private BufferedImage questionsAndAnswers;
   private BufferedImage questionsAndAnswers2;

   private BufferedImage darkmode;

   private BufferedImage icon_notes;
   private BufferedImage icon_eye;
   private BufferedImage icon_bulb;
   private BufferedImage icon_bulb_on;

   private BufferedImage scroll;

   private Map<NikudLetter, BufferedImage> letterIconsNikudMap;
   private Map<NikudLetter, BufferedImage> letterIconsNikudHandwrittenMap;



   public BufferedImage getDarkmode()
   {
      return darkmode;
   }

   public void setDarkmode(BufferedImage darkmode)
   {
      this.darkmode = darkmode;
   }

   public void setImage(BufferedImage image)
   {
      this.image = image;
   }

   public BufferedImage getImage()
   {
      return image;
   }

   public List<BufferedImage> getGreenImages()
   {
      return greenImages;
   }

   public void setGreenImages(List<BufferedImage> greenImages)
   {
      if (greenImages.size() < 72)
      {
         System.exit(1001);
      }
      this.greenImages = greenImages;
   }

   public BufferedImage getTrashcan()
   {
      return trashcan;
   }

   public void setTrashcan(BufferedImage trashcan)
   {
      if (trashcan == null)
      {
         System.exit(1002);
      }
      this.trashcan = trashcan;
   }

   public BufferedImage getShredder()
   {
      return shredder;
   }

   public void setShredder(BufferedImage shredder)
   {
      if (shredder == null)
      {
         System.exit(1003);
      }
      this.shredder = shredder;
   }

   public BufferedImage getRandomGreenImage()
   {
      Random random = new Random();
      return greenImages.get(random.nextInt(greenImages.size()));
   }

   public BufferedImage getRandomBlueImage()
   {
      Random random = new Random();
      return blueImages.get(random.nextInt(blueImages.size()));
   }

   public void setCopy(BufferedImage copy)
   {
      if (copy == null)
      {
         System.exit(1004);
      }
      this.copy = copy;
   }

   public BufferedImage getCopy()
   {
      return copy;
   }

   public BufferedImage getClear()
   {
      return clear;
   }

   public void setClear(BufferedImage clear)
   {
      if (clear == null)
      {
         System.exit(1005);
      }
      this.clear = clear;
   }

   public BufferedImage getSelect()
   {
      return select;
   }

   public void setSelect(BufferedImage selected)
   {
      if (selected == null)
      {
         System.exit(1006);
      }
      this.select = selected;
   }

   public BufferedImage getNewWord()
   {
      return newWord;
   }

   public void setNewWord(BufferedImage newWord)
   {
      if (newWord == null)
      {
         System.exit(1007);
      }
      this.newWord = newWord;
   }

   public BufferedImage getLanguages()
   {
      return languages;
   }

   public void setLanguages(BufferedImage languages)
   {
      if (languages == null)
      {
         System.exit(1008);
      }
      this.languages = languages;
   }

   public BufferedImage getNewWordSmall()
   {
      return newWordSmall;
   }

   public void setNewWordSmall(BufferedImage newWordSmall)
   {
      if (newWordSmall == null)
      {
         System.exit(1009);
      }
      this.newWordSmall = newWordSmall;
   }

   public BufferedImage getL18n()
   {
      return l18n;
   }

   public void setL18n(BufferedImage l18n)
   {
      if (l18n == null)
      {
         System.exit(1010);
      }
      this.l18n = l18n;
   }

   public BufferedImage getSaveWord()
   {
      return saveWord;
   }

   public void setSaveWord(BufferedImage saveWord)
   {
      if (saveWord == null)
      {
         System.exit(1011);
      }
      this.saveWord = saveWord;
   }

   public BufferedImage getDeleteWord()
   {
      return deleteWord;
   }

   public void setDeleteWord(BufferedImage deleteWord)
   {
      if (deleteWord == null)
      {
         System.exit(1012);
      }
      this.deleteWord = deleteWord;
   }

   public BufferedImage getRestore()
   {
      return restore;
   }

   public void setRestore(BufferedImage restore)
   {
      if (restore == null)
      {
         System.exit(1013);
      }
      this.restore = restore;
   }

   public BufferedImage getSend()
   {
      return send;
   }

   public void setSend(BufferedImage send)
   {
      if (send == null)
      {
         System.exit(1014);
      }
      this.send = send;
   }

   public BufferedImage getStart()
   {
      return start;
   }

   public void setStart(BufferedImage start)
   {
      if (start == null)
      {
         System.exit(1015);
      }
      this.start = start;
   }

   public BufferedImage getStop()
   {
      return stop;
   }

   public void setStop(BufferedImage stop)
   {
      if (stop == null)
      {
         System.exit(1016);
      }
      this.stop = stop;
   }

   public BufferedImage getSearch()
   {
      return search;
   }

   public void setSearch(BufferedImage search)
   {
      if (search == null)
      {
         System.exit(1017);
      }
      this.search = search;
   }

   public BufferedImage getOkaySaveIcon()
   {
      return okaySaveIcon;
   }

   public void setOkaySaveIcon(BufferedImage okaySaveIcon)
   {
      if (okaySaveIcon == null)
      {
         System.exit(1018);
      }
      this.okaySaveIcon = okaySaveIcon;
   }

   public void setOkaySave(BufferedImage okaySave)
   {
      if (okaySave == null)
      {
         System.exit(1019);
      }
      this.okaySave = okaySave;
   }

   public BufferedImage getOkaySave()
   {
      return okaySave;
   }

   public BufferedImage getEmpty()
   {
      return empty;
   }

   public void setEmpty(BufferedImage empty)
   {
      if (empty == null)
      {
         System.exit(1020);
      }
      this.empty = empty;
   }

   public BufferedImage getInfoCursor()
   {
      return infoCursor;
   }

   public void setInfoCursor(BufferedImage infoCursor)
   {
      if (infoCursor == null)
      {
         System.exit(1021);
      }
      this.infoCursor = infoCursor;
   }

   public BufferedImage getToggleCursor()
   {
      return toggleCursor;
   }

   public void setToggleCursor(BufferedImage toggleCursor)
   {
      if (toggleCursor == null)
      {
         System.exit(1022);
      }
      this.toggleCursor = toggleCursor;
   }

   public BufferedImage getInfoButtonIcon()
   {
      return infoButtonIcon;
   }

   public void setInfoButtonIcon(BufferedImage infoButtonIcon)
   {
      if (infoButtonIcon == null)
      {
         System.exit(1023);
      }
      this.infoButtonIcon = infoButtonIcon;
   }

   public BufferedImage getToggleButtonIcon()
   {
      return toggleButtonIcon;
   }

   public void setToggleButtonIcon(BufferedImage toggleButtonIcon)
   {
      if (toggleButtonIcon == null)
      {
         System.exit(1024);
      }
      this.toggleButtonIcon = toggleButtonIcon;
   }

   public BufferedImage getInfoIcon()
   {
      return infoIcon;
   }

   public void setInfoIcon(BufferedImage infoIcon)
   {
      if (infoIcon == null)
      {
         System.exit(1025);
      }
      this.infoIcon = infoIcon;
   }

   public BufferedImage getTrashcanBackground()
   {
      return trashcanBackground;
   }

   public void setTrashcanBackground(BufferedImage trashcanBackground)
   {
      if (trashcanBackground == null)
      {
         System.exit(1026);
      }
      this.trashcanBackground = trashcanBackground;
   }

   public BufferedImage getStartImage()
   {
      return startImage;
   }

   public void setStartImage(BufferedImage startImage)
   {
      if (startImage == null)
      {
         System.exit(1027);
      }
      this.startImage = startImage;
   }

   public BufferedImage getErrorImage()
   {
      return errorImage;
   }

   public void setErrorImage(BufferedImage errorImage)
   {
      if (errorImage == null)
      {
         System.exit(1028);
      }
      this.errorImage = errorImage;
   }

   public BufferedImage getTexturedBackground()
   {
      return texturedBackground;
   }

   public void setTexturedBackground(BufferedImage texturedBackground)
   {
      if (texturedBackground == null)
      {
         System.exit(1029);
      }
      this.texturedBackground = texturedBackground;
   }

   public BufferedImage getArrow()
   {
      return arrow;
   }

   public void setArrow(BufferedImage arrow)
   {
      if (arrow == null)
      {
         System.exit(1030);
      }
      this.arrow = arrow;
   }

   public BufferedImage getDone()
   {
      return done;
   }

   public void setDone(BufferedImage done)
   {
      if (done == null)
      {
         System.exit(1031);
      }
      this.done = done;
   }

   public BufferedImage getEmptyList()
   {
      return emptyList;
   }

   public void setEmptyList(BufferedImage emptyList)
   {
      if (emptyList == null)
      {
         System.exit(1032);
      }
      this.emptyList = emptyList;
   }

   public BufferedImage getLogo()
   {
      return logo;
   }

   public void setLogo(BufferedImage logo)
   {
      if (logo == null)
      {
         System.exit(1033);
      }
      this.logo = logo;
   }

   public BufferedImage getLogo24()
   {
      return logo24;
   }

   public void setLogo24(BufferedImage logo24)
   {
      if (logo24 == null)
      {
         System.exit(1034);
      }
      this.logo24 = logo24;
   }

   public BufferedImage getLogo150()
   {
      return logo150;
   }

   public void setLogo150(BufferedImage logo150)
   {
      if (logo150 == null)
      {
         System.exit(1035);
      }
      this.logo150 = logo150;
   }

   public Map<NikudLetter, BufferedImage> getLetterPicturesMap()
   {
      return letterPicturesMap;
   }

   public void setLetterPicturesMap(
         Map<NikudLetter, BufferedImage> letterPicturesMap)
   {
      if (letterPicturesMap.size() < 30)
      {
         System.exit(1036);
      }
      else if (letterPicturesMap.size() > 30)
      {
         System.exit(1037);
      }
      this.letterPicturesMap = letterPicturesMap;
   }

   public Map<NikudLetter, BufferedImage> getLetterIconsNikudMap()
   {
      return letterIconsNikudMap;
   }

   public void setLetterIconsNikudMap(
         Map<NikudLetter, BufferedImage> letterIconsNikudMap)
   {
      if (letterIconsNikudMap.size() < 56)
      {
         System.exit(1038);
      }
      else if (letterIconsNikudMap.size() > 56)
      {
         System.exit(1039);
      }
      this.letterIconsNikudMap = letterIconsNikudMap;
   }

   public Map<NikudLetter, BufferedImage> getLetterIconsNikudHandwrittenMap()
   {
      return letterIconsNikudHandwrittenMap;
   }

   public void setLetterIconsNikudHandwrittenMap(
         Map<NikudLetter, BufferedImage> letterIconsNikudHandwrittenMap)
   {
      if (letterIconsNikudHandwrittenMap.size() < 27)
      {
         System.exit(1040);
      }
      else if (letterIconsNikudHandwrittenMap.size() > 27)
      {
         System.exit(1041);
      }
      this.letterIconsNikudHandwrittenMap = letterIconsNikudHandwrittenMap;
   }

   public BufferedImage getTurn()
   {
      return turn;
   }

   public void setTurn(BufferedImage turn)
   {
      if (turn == null)
      {
         System.exit(1042);
      }
      this.turn = turn;
   }

   public BufferedImage getAnswerOkay()
   {
      return answerOkay;
   }

   public void setAnswerOkay(BufferedImage answerOkay)
   {
      if (answerOkay == null)
      {
         System.exit(1043);
      }
      this.answerOkay = answerOkay;
   }

   public BufferedImage getAnswerNotOkay()
   {
      return answerNotOkay;
   }

   public void setAnswerNotOkay(BufferedImage answerNotOkay)
   {
      if (answerNotOkay == null)
      {
         System.exit(1044);
      }
      this.answerNotOkay = answerNotOkay;
   }

   public BufferedImage getAnswerUndecided()
   {
      return answerUndecided;
   }

   public void setAnswerUndecided(BufferedImage answerUndecided)
   {
      if (answerUndecided == null)
      {
         System.exit(1045);
      }
      this.answerUndecided = answerUndecided;
   }

   public List<BufferedImage> getBlueImages()
   {
      return blueImages;
   }

   public void setBlueImages(List<BufferedImage> blueImages)
   {
      if (blueImages.size() < 32)
      {
         System.exit(1046);
      }
      this.blueImages = blueImages;
   }

   public BufferedImage getCancel()
   {
      return cancel;
   }

   public void setCancel(BufferedImage cancel)
   {
      if (cancel == null)
      {
         System.exit(1047);
      }
      this.cancel = cancel;
   }

   public BufferedImage getReward()
   {
      return reward;
   }

   public void setReward(BufferedImage reward)
   {
      if (reward == null)
      {
         System.exit(1048);
      }
      this.reward = reward;
   }

   public void setLetterEmpty(BufferedImage letterEmpty)
   {
      if (letterEmpty == null)
      {
         System.exit(1049);
      }
      this.letterEmpty = letterEmpty;
   }

   public void setLetterNone(BufferedImage letterNone)
   {
      if (letterNone == null)
      {
         System.exit(1050);
      }
      this.letterNone = letterNone;
   }

   public BufferedImage getLetterEmpty()
   {
      return letterEmpty;
   }

   public BufferedImage getLetterNone()
   {
      return letterNone;
   }

   public void setWork(BufferedImage work)
   {
      if (work == null)
      {
         System.exit(1051);
      }
      this.work = work;
   }

   public BufferedImage getWork()
   {
      return work;
   }

   public void setSoundOn(BufferedImage soundOn)
   {
      if (soundOn == null)
      {
         System.exit(1052);
      }
      this.soundOn = soundOn;
   }

   public BufferedImage getSoundOn()
   {
      return soundOn;
   }

   public void setSoundOff(BufferedImage soundOff)
   {
      if (soundOff == null)
      {
         System.exit(1053);
      }
      this.soundOff = soundOff;
   }

   public BufferedImage getSoundOff()
   {
      return soundOff;
   }

   public void setDreidel(BufferedImage dreidel)
   {
      if (dreidel == null)
      {
         System.exit(1054);
      }
      this.dreidel = dreidel;
   }

   public BufferedImage getDreidel()
   {
      return dreidel;
   }

   public BufferedImage getHebrewLetters()
   {
      return hebrewLetters;
   }

   public void setHebrewLetters(BufferedImage hebrewLetters)
   {
      if (hebrewLetters == null)
      {
         System.exit(1055);
      }
      this.hebrewLetters = hebrewLetters;
   }

   public BufferedImage getBack()
   {
      return back;
   }

   public void setBack(BufferedImage back)
   {
      if (back == null)
      {
         System.exit(1056);
      }
      this.back = back;
   }

   public BufferedImage getSelectDone()
   {
      return selectDone;
   }

   public void setSelectDone(BufferedImage selectDone)
   {
      if (selectDone == null)
      {
         System.exit(1057);
      }
      this.selectDone = selectDone;
   }

   public void setCopy2(BufferedImage copy2)
   {
      if (copy2 == null)
      {
         System.exit(1058);
      }
      this.copy2 = copy2;
   }

   public void setCut(BufferedImage cut)
   {
      if (cut == null)
      {
         System.exit(1059);
      }
      this.cut = cut;
   }

   public void setPaste(BufferedImage paste)
   {
      if (paste == null)
      {
         System.exit(1060);
      }
      this.paste = paste;
   }

   public BufferedImage getCopy2()
   {
      return copy2;
   }

   public BufferedImage getCut()
   {
      return cut;
   }

   public BufferedImage getPaste()
   {
      return paste;
   }

   public BufferedImage getLock()
   {
      return lock;
   }

   public void setLock(BufferedImage lock)
   {
      if (lock == null)
      {
         System.exit(1061);
      }
      this.lock = lock;
   }

   public BufferedImage getEyeOnly()
   {
      return eyeOnly;
   }

   public void setEyeOnly(BufferedImage eyeOnly)
   {
      if (eyeOnly == null)
      {
         System.exit(1062);
      }
      this.eyeOnly = eyeOnly;
   }

   public BufferedImage getEye()
   {
      return eye;
   }

   public void setEye(BufferedImage eye)
   {
      if (eye == null)
      {
         System.exit(1063);
      }
      this.eye = eye;
   }

   public BufferedImage getQuestionsAndAnswers()
   {
      return questionsAndAnswers;
   }

   public void setQuestionsAndAnswers(BufferedImage questionsAndAnswers)
   {
      if (questionsAndAnswers == null)
      {
         System.exit(1064);
      }
      this.questionsAndAnswers = questionsAndAnswers;
   }

   public BufferedImage getQuestionsAndAnswers2()
   {
      return questionsAndAnswers2;
   }

   public void setQuestionsAndAnswers2(
         BufferedImage questionsAndAnswers2)
   {
      if (questionsAndAnswers2 == null)
      {
         System.exit(1065);
      }
      this.questionsAndAnswers2 = questionsAndAnswers2;
   }

   public BufferedImage getLogoFolder()
   {
      return logoFolder;
   }

   public void setLogoFolder(BufferedImage logoFolder)
   {
      if (logoFolder == null)
      {
         System.exit(1066);
      }
      this.logoFolder = logoFolder;
   }

   public BufferedImage getLogoFolderEmpty()
   {
      return logoFolderEmpty;
   }

   public void setLogoFolderEmpty(BufferedImage logoFolderEmpty)
   {
      if (logoFolderEmpty == null)
      {
         System.exit(1067);
      }
      this.logoFolderEmpty = logoFolderEmpty;
   }

   public BufferedImage getIcon_notes()
   {
      return icon_notes;
   }

   public void setIcon_notes(BufferedImage icon_notes)
   {
      if (icon_notes == null)
      {
         System.exit(1068);
      }
      this.icon_notes = icon_notes;
   }

   public BufferedImage getIcon_eye()
   {
      return icon_eye;
   }

   public void setIcon_eye(BufferedImage icon_eye)
   {
      if (icon_eye == null)
      {
         System.exit(1069);
      }
      this.icon_eye = icon_eye;
   }

   public BufferedImage getIcon_bulb()
   {
      return icon_bulb;
   }

   public void setIcon_bulb(BufferedImage icon_bulb)
   {
      if (icon_bulb == null)
      {
         System.exit(1070);
      }
      this.icon_bulb = icon_bulb;
   }

   public BufferedImage getIcon_bulb_on()
   {
      return icon_bulb_on;
   }

   public void setIcon_bulb_on(BufferedImage icon_bulb_on)
   {
      if (icon_bulb_on == null)
      {
         System.exit(1071);
      }
      this.icon_bulb_on = icon_bulb_on;
   }

   public BufferedImage getScroll()
   {
      return scroll;
   }

   public void setScroll(BufferedImage scroll)
   {
      if (scroll == null)
      {
         System.exit(1072);
      }
      this.scroll = scroll;
   }

}
