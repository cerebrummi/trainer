package vokabeltrainer.resources;

import java.io.IOException;

import javax.imageio.ImageIO;

import vokabeltrainer.common.main.AppImages;

public class Images
{
   private AppImages appImages;
   
   public void read() throws IOException
   {
      appImages = new AppImages();
      
      appImages.setDarkmode(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/darkmode.png")));
      appImages.setAnswerNotOkay(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/answer-not-okay.png")));

      appImages.setAnswerOkay(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/answer-okay.png")));

      appImages.setAnswerUndecided(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/answer-undecided.png")));

      appImages.setArrow(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/arrow.png")));

      appImages.setBack(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/back.png")));

      appImages.setReward(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/baerlohnung.png")));

      appImages.setImage(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/book-2878724_1280.png")));
      
      appImages.setFabric(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/jewish-1159704_1280.png")));

      appImages.setCancel(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/cancel.png")));

      appImages.setLogo(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/Cerebrummi.png")));

      appImages.setLogo150(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/Cerebrummi_150px.png")));

      appImages.setLogo24(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/Cerebrummi_24px.png")));

      appImages.setClear(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/clear.png")));

      appImages.setCopy(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/copy.png")));

      appImages.setCopy2(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/copy2.png")));

      appImages.setCut(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/cut.png")));

      appImages.setPaste(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/paste.png")));

      appImages.setDeleteWord(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/delete.png")));

      appImages.setDone(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/done.png")));

      appImages.setEmpty(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/empty.png")));

      appImages.setEmptyList(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/empty-list.png")));

      appImages.setErrorImage(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/error.jpg")));

      appImages.setSoundOn(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/headphones-2104207.png")));

      appImages.setSoundOff(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/headphones-2104207-gray.png")));

      appImages.setHebrewLetters(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/hebrew-letters-2730159_1280.jpg")));

      appImages.setInfoButtonIcon(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/info-button-icon.png")));

      appImages.setInfoCursor(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/info-cursor.png")));

      appImages.setToggleButtonIcon(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/toggle-button-icon.png")));

      appImages.setToggleCursor(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/toggle-cursor.png")));

      appImages.setInfoIcon(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/info-icon.png")));

      appImages.setL18n(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/L18n.png")));

      appImages.setLanguages(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/languages.png")));

      appImages.setLetterEmpty(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/letter_empty.png")));

      appImages.setLock(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/lock.png")));

      appImages.setEye(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/eye.png")));

      appImages.setEyeOnly(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/eye_only.png")));

      appImages.setStartImage(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/neutral.jpg")));

      appImages.setNewWord(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/new.png")));

      appImages.setNewWordSmall(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/new_small.png")));

      appImages.setOkaySave(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/okay-save.png")));

      appImages.setOkaySaveIcon(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/okay-save_icon.png")));

      appImages.setRestore(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/restore.png")));

      appImages
            .setDreidel(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images/robert-zunikoff-483401-unsplash.jpg")));

      appImages.setSaveWord(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/save.png")));

      appImages.setSearch(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/search.png")));

      appImages.setSelect(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/select.png")));

      appImages.setSelectDone(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/select-done.png")));

      appImages.setSend(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/send.png")));

      appImages.setStart(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/start.png")));

      appImages.setStop(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/stop.png")));

      appImages.setTexturedBackground(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/textured-background.jpg")));

      appImages.setShredder(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/trash-97586.png")));

      appImages.setTrashcan(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/trashcan-98470.png")));

      appImages.setTurn(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/turn.png")));

      appImages.setTrashcanBackground(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/white-male-2064827_640.jpg")));

      appImages.setWork(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/work.png")));

      appImages.setQuestionsAndAnswers(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/questions-and-answers.png")));

      appImages.setQuestionsAndAnswers2(ImageIO.read(Images.class
            .getResourceAsStream("_2_images/questions-and-answers2.png")));

      appImages.setLogoFolder(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/iconFolder.png")));

      appImages.setLogoFolderEmpty(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/iconFolderEmpty.png")));

      appImages.setIcon_notes(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/icon_notes.png")));

      appImages.setIcon_eye(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/icon_eye.png")));

      appImages.setIcon_bulb(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/icon_bulb.png")));

      appImages.setIcon_bulb_on(ImageIO.read(
            Images.class.getResourceAsStream("_2_images/icon_bulb_on.png")));

      appImages.setScroll(ImageIO
            .read(Images.class.getResourceAsStream("_2_images/scroll.png")));
   }

   public AppImages getAppImages()
   {
      return appImages;
   }

}
