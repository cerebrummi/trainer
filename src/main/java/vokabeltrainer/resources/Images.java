package vokabeltrainer.resources;

import java.io.File;

import javax.imageio.ImageIO;

import vokabeltrainer.common.ApplicationImages;

public class Images
{

   public static void read() throws Exception
   {
      ApplicationImages
            .setAnswerNotOkay(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "answer-not-okay.png")));

      ApplicationImages
            .setAnswerOkay(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "answer-okay.png")));

      ApplicationImages
            .setAnswerUndecided(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "answer-undecided.png")));

      ApplicationImages.setArrow(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "arrow.png")));

      ApplicationImages.setBack(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "back.png")));

      ApplicationImages.setReward(ImageIO.read(Images.class.getResourceAsStream(
            "_2_images" + File.separator + "baerlohnung.png")));

      ApplicationImages.setImage(ImageIO.read(Images.class.getResourceAsStream(
            "_2_images" + File.separator + "book-2878724_1280.jpg")));

      ApplicationImages.setCancel(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "cancel.png")));

      ApplicationImages.setLogo(ImageIO.read(Images.class.getResourceAsStream(
            "_2_images" + File.separator + "Cerebrummi.png")));

      ApplicationImages
            .setLogo150(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "Cerebrummi_150px.png")));

      ApplicationImages.setLogo24(ImageIO.read(Images.class.getResourceAsStream(
            "_2_images" + File.separator + "Cerebrummi_24px.png")));

      ApplicationImages.setClear(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "clear.png")));

      ApplicationImages.setCopy(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "copy.png")));

      ApplicationImages.setCopy2(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "copy2.png")));

      ApplicationImages.setCut(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "cut.png")));

      ApplicationImages.setPaste(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "paste.png")));

      ApplicationImages.setDeleteWord(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "delete.png")));

      ApplicationImages.setDone(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "done.png")));

      ApplicationImages.setEmpty(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "empty.png")));

      ApplicationImages
            .setEmptyList(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "empty-list.png")));

      ApplicationImages.setErrorImage(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "error.jpg")));

      ApplicationImages
            .setSoundOn(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "headphones-2104207.png")));

      ApplicationImages.setSoundOff(
            ImageIO.read(Images.class.getResourceAsStream("_2_images"
                  + File.separator + "headphones-2104207-gray.png")));

      ApplicationImages.setHebrewLetters(
            ImageIO.read(Images.class.getResourceAsStream("_2_images"
                  + File.separator + "hebrew-letters-2730159_1280.jpg")));

      // "_2_images/icon.png":

      ApplicationImages
            .setInfoButtonIcon(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "info-button-icon.png")));

      ApplicationImages
            .setInfoCursor(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "info-cursor.png")));

      ApplicationImages
            .setToggleButtonIcon(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "toggle-button-icon.png")));

      ApplicationImages
            .setToggleCursor(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "toggle-cursor.png")));

      ApplicationImages
            .setInfoIcon(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "info-icon.png")));

      // "_2_images/jewish-1159704_1280.jpg":

      ApplicationImages.setL18n(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "L18n.png")));

      ApplicationImages
            .setLanguages(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "languages.png")));

      ApplicationImages
            .setLetterEmpty(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "letter_empty.png")));

      ApplicationImages.setLock(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "lock.png")));

      ApplicationImages
            .setStartImage(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "neutral.jpg")));

      ApplicationImages.setNewWord(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "new.png")));

      ApplicationImages
            .setNewWordSmall(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "new_small.png")));

      ApplicationImages
            .setOkaySave(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "okay-save.png")));

      ApplicationImages
            .setRestore(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "restore.png")));

      ApplicationImages.setDreidel(
            ImageIO.read(Images.class.getResourceAsStream("_2_images"
                  + File.separator + "robert-zunikoff-483401-unsplash.jpg")));

      ApplicationImages.setSaveWord(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "save.png")));

      // "_2_images/scroll-1410168_1280.jpg":

      ApplicationImages.setSearch(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "search.png")));

      ApplicationImages.setSelect(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "select.png")));

      ApplicationImages
            .setSelectDone(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "select-done.png")));

      ApplicationImages.setSend(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "send.png")));

      ApplicationImages.setStart(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "start.png")));

      ApplicationImages.setStop(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "stop.png")));

      ApplicationImages.setTexturedBackground(
            ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "textured-background.jpg")));

      ApplicationImages
            .setShredder(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "trash-97586.png")));

      ApplicationImages
            .setTrashcan(ImageIO.read(Images.class.getResourceAsStream(
                  "_2_images" + File.separator + "trashcan-98470.png")));

      ApplicationImages.setTurn(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "turn.png")));

      ApplicationImages.setTrashcanBackground(
            ImageIO.read(Images.class.getResourceAsStream("_2_images"
                  + File.separator + "white-male-2064827_640.jpg")));

      ApplicationImages.setWork(ImageIO.read(Images.class
            .getResourceAsStream("_2_images" + File.separator + "work.png")));
   }

}
