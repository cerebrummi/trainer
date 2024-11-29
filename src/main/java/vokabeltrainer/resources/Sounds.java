package vokabeltrainer.resources;

import java.io.File;

import vokabeltrainer.common.ApplicationSound;
import vokabeltrainer.editing.SwedishLetter;

public class Sounds
{
   public static void read() throws Exception
   {
      ApplicationSound.setSplotchSound(Sounds.class.getResourceAsStream(
            "sounds" + File.separator + "splotch-sound.byt"));
      ApplicationSound.setClappingSound(Sounds.class.getResourceAsStream(
            "sounds" + File.separator + "clapping-sound.byt"));
      ApplicationSound.setWaveSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "wave-sound.byt"));
      ApplicationSound.setShredderSound(Sounds.class.getResourceAsStream(
            "sounds" + File.separator + "shredder-sound.byt"));

      SwedishLetter.A.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "A.byt"));
      SwedishLetter.a.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "A.byt"));
      SwedishLetter.B.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "B.byt"));
      SwedishLetter.b.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "B.byt"));
      SwedishLetter.C.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "C.byt"));
      SwedishLetter.c.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "C.byt"));
      SwedishLetter.D.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "D.byt"));
      SwedishLetter.d.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "D.byt"));
      SwedishLetter.E.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "E.byt"));
      SwedishLetter.e.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "E.byt"));
      SwedishLetter.F.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "F.byt"));
      SwedishLetter.f.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "F.byt"));
      SwedishLetter.G.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "G.byt"));
      SwedishLetter.g.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "G.byt"));
      SwedishLetter.H.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "H.byt"));
      SwedishLetter.h.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "H.byt"));
      SwedishLetter.I.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "I.byt"));
      SwedishLetter.i.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "I.byt"));
      SwedishLetter.J.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "J.byt"));
      SwedishLetter.j.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "J.byt"));
      SwedishLetter.K.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "K.byt"));
      SwedishLetter.k.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "K.byt"));
      SwedishLetter.L.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "L.byt"));
      SwedishLetter.l.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "L.byt"));
      SwedishLetter.M.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "M.byt"));
      SwedishLetter.m.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "M.byt"));
      SwedishLetter.N.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "N.byt"));
      SwedishLetter.n.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "N.byt"));
      SwedishLetter.O.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "O.byt"));
      SwedishLetter.o.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "O.byt"));
      SwedishLetter.P.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "P.byt"));
      SwedishLetter.p.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "P.byt"));
      SwedishLetter.Q.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "Q.byt"));
      SwedishLetter.q.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "Q.byt"));
      SwedishLetter.R.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "R.byt"));
      SwedishLetter.r.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "R.byt"));
      SwedishLetter.S.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "S.byt"));
      SwedishLetter.s.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "S.byt"));
      SwedishLetter.T.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "T.byt"));
      SwedishLetter.t.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "T.byt"));
      SwedishLetter.U.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "U.byt"));
      SwedishLetter.u.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "U.byt"));
      SwedishLetter.V.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "V.byt"));
      SwedishLetter.v.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "V.byt"));
      SwedishLetter.W.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "W.byt"));
      SwedishLetter.w.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "W.byt"));
      SwedishLetter.X.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "X.byt"));
      SwedishLetter.x.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "X.byt"));
      SwedishLetter.Y.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "Y.byt"));
      SwedishLetter.y.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "Y.byt"));
      SwedishLetter.Z.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "Z.byt"));
      SwedishLetter.z.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "Z.byt"));
      SwedishLetter.AE.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "AE.byt"));
      SwedishLetter.ae.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "AE.byt"));
      SwedishLetter.ARING.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "ARING.byt"));
      SwedishLetter.aring.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "ARING.byt"));
      SwedishLetter.OE.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "OE.byt"));
      SwedishLetter.oe.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "OE.byt"));
      SwedishLetter.SPACE.setSound(Sounds.class
            .getResourceAsStream("sounds" + File.separator + "SPACE.byt"));
   }
}
