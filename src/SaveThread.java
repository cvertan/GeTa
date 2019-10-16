public class SaveThread extends Thread {

   private boolean exitAfterSave = false;

   public SaveThread() {
     this(false);
   }

   public SaveThread(boolean exitAfterSave) {
     this.exitAfterSave = exitAfterSave;
   }

  // @Overwrite
   public void run() {

      // Save what has to be saved

      if (exitAfterSave) {
        System.exit(0);
      }
   }
}